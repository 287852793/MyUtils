package com.xiaoer.tests;

import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.hutool.http.HttpUtil;

public class ServiceDescribeFeatureType {
	public static void main(String[] args) throws Exception {
		// typename参数
		String typename = "jzw_fw";
		// 以下meta是根据typename获得（通过接口）
		String meta = "<Views type=\"wfs\">\r\n" + "  <View Name=\"entity_ds\" Alias=\"JMD视图\">\r\n"
				+ "    <SQL RepeatPostfix=\"\" RepeatPrefix=\"\">SELECT * FROM JMD</SQL>\r\n" + "    <Tables>\r\n" + "      <Table>g_jmd_fw_a</Table>\r\n"
				+ "    </Tables>\r\n" + "  </View>\r\n" + "  <View Name=\"entity_ds\" Alias=\"JMD视图\">\r\n"
				+ "    <SQL RepeatPostfix=\"\" RepeatPrefix=\"\">SELECT * FROM JMD</SQL>\r\n" + "    <Tables>\r\n" + "      <Table>entity_ds</Table>\r\n"
				+ "      <Table>entity_ds_relation</Table>\r\n" + "    </Tables>\r\n" + "  </View>\r\n" + "</Views>";

		Document xml = DocumentHelper.parseText(meta);
		Element root = xml.getRootElement();
		List<Element> views = root.elements();

		String baseUrl = "http://192.168.10.206:8081/geoserver/wfs?request=DescribeFeatureType&TYPENAME=";

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

			String data = HttpUtil.get(baseUrl + table);
			if (data.contains("ows:Exception")) {
				// 有错误 不返回数据
			} else {
				if (res == null) {
					// 处理结果的第一个子集
					res = DocumentHelper.parseText(data).getRootElement();

					if (isEntity) {
						Element complexType = res.element("complexType");
						removeGeometryType(complexType);
						changeEntityViewComplexTypeName(complexType);
						Element element = res.element("element");
						changeEntityViewElementName(element);
					}

				} else {
					// 处理结果的后续子集
					Document d1 = DocumentHelper.parseText(data);
					Element d2 = d1.getRootElement();
					Element d3 = d2.element("complexType");
					Element d4 = d2.element("element");
					
					if (isEntity) {
						removeGeometryType(d3);
						changeEntityViewComplexTypeName(d3);
						changeEntityViewElementName(d4);
					}
					
					d3 = (Element) d3.clone();
					d3.setParent(null);
					d3.setDocument(null);
					
					d4 = (Element) d4.clone();
					d4.setParent(null);
					d4.setDocument(null);
					
					res.add(d3);
					res.add(d4);
					
				}
			}

		}

		if (res == null) {
			System.out.println("no result");
		} else {
			System.out.println("<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + res.asXML());
		}
	}

	public static void removeGeometryType(Element e) {
		Element sequence = e.element("complexContent").element("extension").element("sequence");
		List<Element> fields = sequence.elements();
		for (Element field : fields) {
			String value = field.attributeValue("name");
			if (value.equals("geometry")) {
				sequence.remove(field);
			}
		}
		
	}
	
	public static void changeEntityViewComplexTypeName(Element e) {
		e.attribute("name").setText(e.attribute("name").getText().replace("_view", ""));
	}
	
	public static void changeEntityViewElementName(Element e) {
		e.attribute("name").setText(e.attribute("name").getText().replace("_view", ""));
		e.attribute("type").setText(e.attribute("type").getText().replace("_view", ""));
	}
}
