package com.xiaoer.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.geotools.data.FeatureWriter;
import org.geotools.data.Transaction;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.locationtech.jts.geom.MultiPolygon;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

/**
 * GeoJSONL to Shapefile
 * 
 * @author pyf
 * @time 2023-03-31 10:21:24
 */
public class GeoJSONL2SHP {
	/* GeoJSONL path */
	private static String path1 = "C:\\Users\\庞亚菲\\Desktop\\Armenia.geojsonl";
	/* Shapefile path */
	private static String path2 = "C:\\Users\\庞亚菲\\Desktop\\test\\test.shp";

	/**
	 * process
	 * 
	 * @author pyf
	 * @time 2023-03-31 10:26:11
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// start
			long start = System.currentTimeMillis();
			System.out.println("start...");

			// Geometry json obj
			GeometryJSON gjson = new GeometryJSON();

			// geojsonl reader
			BufferedReader fileReader = new BufferedReader(new FileReader(new File(path1)));

			// create shapefile
			File file = new File(path2);
			Map<String, Serializable> params = new HashMap<String, Serializable>();
			params.put(ShapefileDataStoreFactory.URLP.key, file.toURI().toURL());
			ShapefileDataStore datastore = (ShapefileDataStore) new ShapefileDataStoreFactory().createNewDataStore(params);

			// create simple feature type
			SimpleFeatureTypeBuilder tb = new SimpleFeatureTypeBuilder();
			tb.setCRS(DefaultGeographicCRS.WGS84);
			tb.setName("shapefile");
			tb.add("the_geom", MultiPolygon.class);
			tb.add("pid", Long.class);
			datastore.createSchema(tb.buildFeatureType());
			datastore.setCharset(Charset.forName("GBK"));

			// feature writer
			FeatureWriter<SimpleFeatureType, SimpleFeature> writer = datastore.getFeatureWriter(datastore.getTypeNames()[0], Transaction.AUTO_COMMIT);

			// read file to shp
			int i = 0;
			String geojsonStr = "";
			while ((geojsonStr = fileReader.readLine()) != null) {
				Reader geojsonReader = new StringReader(geojsonStr);
				MultiPolygon polygon = gjson.readMultiPolygon(geojsonReader);

				SimpleFeature feature = writer.next();
				feature.setAttribute("the_geom", polygon);
				feature.setAttribute("pid", i);
				writer.write();
				i++;
				if (i % 100 == 0) {
					System.out.println("count : " + i);
				}
			}
			writer.close();
			datastore.dispose();

			// end
			long end = System.currentTimeMillis();
			System.out.println("finished...");
			System.out.println("total count : " + i);
			System.out.println("time : " + (end - start));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
