package com.xiaoer.work;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashSet;
import java.util.Set;

import org.geotools.data.FeatureReader;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import cn.hutool.core.io.FileUtil;

public class ShapeFieldReadTest {
	public static void main(String[] args) {
		String shpFile = "C:\\Users\\庞亚菲\\Desktop\\新建文件夹\\sd3-1.shp";
		String fieldsName = "Type";

		Set<String> result = new HashSet<>();
		File file = new File(shpFile);
		ShapefileDataStore shapeFileDataStore = null;
		try {
			shapeFileDataStore = (ShapefileDataStore) new ShapefileDataStoreFactory().createDataStore(file.toURI().toURL());
//			System.out.println(Charset.forName(getCharset(shpFile)));
			shapeFileDataStore.setCharset(Charset.forName(getCharset(shpFile)));
//			shapeFileDataStore.setCharset(Charset.forName("utf-8"));
//			shapeFileDataStore.setCharset(Charset.forName("UTF-8"));
			try (FeatureReader<SimpleFeatureType, SimpleFeature> featureReader = shapeFileDataStore.getFeatureReader()) {
				while (featureReader.hasNext()) {
					SimpleFeature feature = featureReader.next();
					Object dlmc = feature.getAttribute(fieldsName);
					if (dlmc != null && !"".equals(dlmc)) {
						result.add(dlmc.toString());
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (shapeFileDataStore != null) {
				shapeFileDataStore.dispose();
			}
		}

		System.out.println(result);
		
		
		
//		// Shapefile 文件路径
//        String filePath = "C:\\Users\\庞亚菲\\Desktop\\新建文件夹\\sd3-1.shp";
//        // Shapefile 读取字段
//        String filed = "Type";
//        
//        // 读取 Shapefile
//        File file = new File(filePath);
//        try {
//            DataStore dataStore = FileDataStoreFinder.getDataStore(file);
//            if (dataStore != null) {
//                // 获取要素类型
//                String typeName = dataStore.getTypeNames()[0]; // 获取第一个类型
//                SimpleFeatureCollection collection = dataStore.getFeatureSource(typeName).getFeatures();
//                // 使用Set存储唯一的属性值
//                Set<Object> uniqueValues = new HashSet<>();
//                // 遍历特征集合
//                try (SimpleFeatureIterator iterator = collection.features()) {
//                    while (iterator.hasNext()) {
//                        SimpleFeature feature = iterator.next();
//                        // 假设我们关注的字段名为"attributeName"
//                        Object attributeValue = feature.getAttribute("Type");
//                        uniqueValues.add(attributeValue);
//                    }
//                }
//                // 打印唯一的属性值
//                for (Object value : uniqueValues) {
//                    System.out.println(value);
//                }
//                // 关闭数据存储
//                dataStore.dispose();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        
	}

	public static String getCharset(String shpFile) throws IOException {
		String cpgPath = shpFile.substring(0, shpFile.length() - 4) + ".cpg";
		File cpgFile = new File(cpgPath);
		if (cpgFile.exists()) {
			System.out.println("exist cpg...");
			return FileUtil.readString(cpgFile, "utf-8");
		} else {
			String dbfPath = shpFile.substring(0, shpFile.length() - 4) + ".dbf";
			InputStream dbf = new FileInputStream(new File(dbfPath));
			byte[] bytes = new byte[30];
			dbf.read(bytes);
			byte b = bytes[29];
			dbf.close();
			String bm = Integer.toHexString(Byte.toUnsignedInt(b));
			if ("4d".equals(bm)) {
				return "gbk";
			}
		}
		return "utf-8";
	}

}
