package com.xiaoer.tests;

import java.io.File;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class ServiceGetFeature {
	public static void main(String[] args) throws DocumentException {
		// typename参数
		String typename = "jzw_fw";
		// 以下meta是根据typename获得（通过接口）
//		String meta = "<Views type=\"wfs\">\r\n" + "  <View Name=\"entity_ds\" Alias=\"JMD视图\">\r\n"
//				+ "    <SQL RepeatPostfix=\"\" RepeatPrefix=\"\">SELECT * FROM JMD</SQL>\r\n" + "    <Tables>\r\n" + "      <Table>g_jmd_fw_a</Table>\r\n"
//				+ "    </Tables>\r\n" + "  </View>\r\n" + "  <View Name=\"entity_ds\" Alias=\"JMD视图\">\r\n"
//				+ "    <SQL RepeatPostfix=\"\" RepeatPrefix=\"\">SELECT * FROM JMD</SQL>\r\n" + "    <Tables>\r\n" + "      <Table>entity_ds</Table>\r\n"
//				+ "      <Table>entity_ds_relation</Table>\r\n" + "    </Tables>\r\n" + "  </View>\r\n" + "</Views>";
		
//		String meta = "<Views type=\"wfs\">\r\n" + 
//				"                <View Name=\"entity_jmd_view\" Alias=\"JMD视图\">\r\n" + 
//				"                    <Tables>\r\n" + 
//				"                        <Table>entityserver:entity_jmd</Table>\r\n" + 
//				"                        <Table>entityserver:entity_jmd_relation</Table>\r\n" + 
//				"                    </Tables>\r\n" + 
//				"                </View>\r\n" + 
//				"            </Views>";
		
		String meta = "<Views type=\"wfs\">\r\n" + 
				"                <View Name=\"entity_jmd_view\" Alias=\"JMD视图\">\r\n" + 
				"                    <Tables>\r\n" + 
				"                        <Table>entityserver:entity_jmd</Table>\r\n" + 
				"                        <Table>entityserver:entity_jmd_relation</Table>\r\n" + 
				"                    </Tables>\r\n" + 
				"                </View>\r\n" + 
				"            </Views>";

		Document xml = DocumentHelper.parseText(meta);
		Element root = xml.getRootElement();
		List<Element> views = root.elements();

		String baseUrl = "http://192.168.10.206:8081/geoserver/wfs?request=GetFeature&TYPENAME=";
//		String filter = "&filter=%3cogc%3aFilter+xmlns%3aogc%3d%22http%3a%2f%2fwww.opengis.net%2fogc%22+xmlns%3agml%3d%22http%3a%2f%2fwww.opengis.net%2fgml%22%3e+%3cogc%3aPropertyIsEqualTo%3e+%3cogc%3aPropertyName%3eobjectid%3c%2fogc%3aPropertyName%3e+%3cogc%3aLiteral%3e3426%3c%2fogc%3aLiteral%3e+%3c%2fogc%3aPropertyIsEqualTo%3e+%3c%2fogc%3aFilter%3e";
//		String filter = "&filter=%3cogc%3aFilter+xmlns%3aogc%3d%22http%3a%2f%2fwww.opengis.net%2fogc%22+xmlns%3agml%3d%22http%3a%2f%2fwww.opengis.net%2fgml%22%3e+%3cogc%3aPropertyIsLessThan%3e+%3cogc%3aPropertyName%3eobjectid%3c%2fogc%3aPropertyName%3e+%3cogc%3aLiteral%3e10%3c%2fogc%3aLiteral%3e+%3c%2fogc%3aPropertyIsLessThan%3e+%3c%2fogc%3aFilter%3e";
		String filter = "&filter=%3cogc%3aFilter+xmlns%3aogc%3d%22http%3a%2f%2fwww.opengis.net%2fogc%22+xmlns%3agml%3d%22http%3a%2f%2fwww.opengis.net%2fgml%22%3e+%3cogc%3aPropertyIsLessThan%3e+%3cogc%3aPropertyName%3eobjectid%3c%2fogc%3aPropertyName%3e+%3cogc%3aLiteral%3e160%3c%2fogc%3aLiteral%3e+%3c%2fogc%3aPropertyIsLessThan%3e+%3c%2fogc%3aFilter%3e";
//		String filter = "&MAXFEATURES=10";

		Element res = null;

		for (Element view : views) {
			String table = "";
			Boolean isEntity = false;
			List<Element> tables = view.element("Tables").elements();
			if (tables.size() > 1) {
				// 地理实体数据才会有多个表
				table = tables.stream().filter(item -> !item.getText().toLowerCase().contains("relation")).findFirst().orElse(null).getTextTrim();
				table += "_view";
				isEntity = true;
			} else {
				// 图元数据只会有一个表
				table = tables.get(0).getTextTrim();
			}

//			System.out.println("url : " + baseUrl + table + filter);
			String data = HttpUtil.get(baseUrl + table + filter);
//			System.out.println(baseUrl + table + filter);
			if (data.contains("ows:Exception")) {
				// 有错误 不返回数据
			} else {
				if (res == null) {
					// 处理结果的第一个子集
					res = DocumentHelper.parseText(data).getRootElement();
					List<Element> members = res.elements("member");
					for (Element member : members) {
						List<Element> features = member.elements();
						for (Element e : features) {
							if (isEntity) {
								removeGeometry(e);
								makeJsonPrepertyWithType(e, "parententity");
								makeJsonPrepertyWithType(e, "childelement");
								makeJsonPrepertyWithType(e, "childentity");
							} else {
								makeJsonProperty(e, "parententity");
								makeJsonProperty(e, "geoentroot");
							}
//							System.out.println(e.getQualifiedName() + " : " + typename);
							e.setName(e.getNamespacePrefix() + ":" + typename);

						}
					}
					
					
//					System.out.println("first data for table " + table + ": " + data);
				} else {
					// 处理结果的后续子集
//					System.out.println("else data for table " + table + ":" + data);
					Document d1 = DocumentHelper.parseText(data);
					Element d2 = d1.getRootElement();
					List<Element> d3 = d2.elements("member");
					for (Element member : d3) {
						member = (Element) member.clone();
						member.setParent(null);
						member.setDocument(null);
						List<Element> elements = member.elements();
						for (Element e : elements) {
							if (isEntity) {
								
								for (Element ee : e.elements()) {
									System.out.println(ee.getQualifiedName());
								}
								
								removeGeometry(e);
								makeJsonPrepertyWithType(e, "parententity");
								makeJsonPrepertyWithType(e, "childelement");
								makeJsonPrepertyWithType(e, "childentity");
							} else {
								makeJsonProperty(e, "parententity");
								makeJsonProperty(e, "geoentroot");
							}
							e.setName(e.getNamespacePrefix() + ":" + typename);
						}
						res.add(member);
					}
				}
			}

		}

		if (res == null) {
			System.out.println("no result");
		} else {
			System.out.println(res.asXML());
			
//			File file = new File("D:/sss.xml");
//			FileWriter writer = FileWriter.create(file);
//			writer.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + res.asXML());
//			System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + res.asXML());
		}

//		String url = "http://192.168.10.206:8081/geoserver/wfs?request=GetFeature&TYPENAME=entity_ds_view&filter=%3cogc%3aFilter+xmlns%3aogc%3d%22http%3a%2f%2fwww.opengis.net%2fogc%22+xmlns%3agml%3d%22http%3a%2f%2fwww.opengis.net%2fgml%22%3e+%3cogc%3aPropertyIsEqualTo%3e+%3cogc%3aPropertyName%3eentity_id%3c%2fogc%3aPropertyName%3e+%3cogc%3aLiteral%3e1202020022ECC4DC0050EC201803130001%3c%2fogc%3aLiteral%3e+%3c%2fogc%3aPropertyIsEqualTo%3e+%3c%2fogc%3aFilter%3e";
//		String res = HttpUtil.get(url);
//		System.out.println(res);
//		
//		Document xml = DocumentHelper.parseText(res);
//		Element root = xml.getRootElement();
//		List<Element> childs = root.elements();
//		System.out.println(childs.size());
//		System.out.println(childs.get(0).getName());
//		Element member = childs.get(0);
//		List<Element> elements = member.elements();
//		Element feature = elements.get(0);
//		System.out.println(feature.getName());
//		System.out.println(feature.getNamespacePrefix());
//		List<Attribute> attributes = feature.attributes();
//		Attribute attr = attributes.get(0);
//		System.out.println(attr.getName());
//		System.out.println(attr.getNamespacePrefix());
//		System.out.println(attr.getQualifiedName());

	}

	public static void removeGeometry(Element e) {
		if (e != null) {
			Element attr = e.element("geometry");
			e.remove(attr);
		}
	}

	public static void makeJsonPrepertyWithType(Element e, String name) {
		String prefix = e.getNamespacePrefix();
//		String prefix = e.getName().substring(0, e.getName().indexOf(":"));
		Element property = e.element(name);
		if (property == null) {
			return;
		}
		String propertyName = property.getName();
		
		String jsonStr = property.getTextTrim();
		if (StrUtil.isBlank(jsonStr)) {
			return;
		}

		JSONObject json = JSONUtil.parseObj(jsonStr);
		for (String key : json.keySet()) {
//			System.out.println(":" + prefix + key);
//			Element entities = DocumentHelper.createElement(prefix + ":" + key);
			
			JSONObject types = JSONUtil.parseObj(json.get(key));

			for (String type : types.keySet()) {
				Element entities = DocumentHelper.createElement(prefix + ":" + propertyName);
				Attribute attr = DocumentHelper.createAttribute(entities, "eitityname", type);
				entities.add(attr);
				
//				Element te = DocumentHelper.createElement(prefix + ":" + type);

				JSONArray arr = JSONUtil.parseArray(types.get(type));
				for (Object o : arr) {
					Element entity = DocumentHelper.createElement(prefix + ":entityid");
					entity.setText(o.toString());
					entities.add(entity);
				}

//				entities.add(entities);
				property.add(entities);
			}

			property.setText("");
			property.setName(property.getQualifiedName() + "Rel");
			
		}
	}

	public static void makeJsonProperty(Element e, String name) {
//		System.out.println(e.asXML());
//		System.out.println(e.getNamespacePrefix());
//		String prefix = e.getName().substring(0, e.getName().indexOf(":"));
		String prefix = e.getNamespacePrefix();

		Element property = e.element(name);
		if (property == null) {
			return;
		}
		String jsonStr = property.getTextTrim();
		if (StrUtil.isBlank(jsonStr)) {
			return;
		}

		JSONObject json = JSONUtil.parseObj(jsonStr);
		for (String key : json.keySet()) {
			Element entities = DocumentHelper.createElement(prefix + ":" + key);
			JSONArray arr = JSONUtil.parseArray(json.get(key));
			for (Object o : arr) {
				Element entity = DocumentHelper.createElement(prefix + ":entityid");
				entity.setText(o.toString());
				entities.add(entity);
			}

			property.setText("");
			property.add(entities);
		}

	}
}
