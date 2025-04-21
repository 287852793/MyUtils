package com.xiaoer.work;

import java.io.File;
import java.io.IOException;

import org.geotools.coverage.grid.GridCoverage2D;
import org.geotools.data.DataSourceException;
import org.geotools.gce.geotiff.GeoTiffReader;
import org.geotools.metadata.iso.citation.Citations;
import org.geotools.referencing.CRS;
import org.locationtech.proj4j.CRSFactory;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;

/**
 * 
 * 
 * @author pyf
 * @time 2023-05-11 10:20:45
 */
public class EpsgTest {
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try {
//			File tifFile = new File("e:/playground.tif");
//			GeoTiffReader reader = new GeoTiffReader(tifFile);
//			String wkt = reader.getCoordinateReferenceSystem().toWKT();
//			System.out.println(wkt);
			
			
			String wkt = "PROJCS[\"CGCS_2000_3_degree_Transverse_Mercator_zone_40\",GEOGCS[\"GCS_CGCS_2000\",DATUM[\"D_CGCS_2000\",SPHEROID[\"CGCS2000\",6378137,298.257222101004,AUTHORITY[\"EPSG\",\"1024\"]]],PRIMEM[\"Greenwich\",0],UNIT[\"degree\",0.0174532925199433,AUTHORITY[\"EPSG\",\"9122\"]]],PROJECTION[\"Transverse_Mercator\"],PARAMETER[\"latitude_of_origin\",0],PARAMETER[\"central_meridian\",120],PARAMETER[\"scale_factor\",1],PARAMETER[\"false_easting\",40500000],PARAMETER[\"false_northing\",0],UNIT[\"metre\",1,AUTHORITY[\"EPSG\",\"9001\"]],AXIS[\"Easting\",EAST],AXIS[\"Northing\",NORTH]]";
//			wkt = "GEOGCS[\"China Geodetic Coordinate System 2000\",DATUM[\"China_2000\",SPHEROID[\"CGCS2000\",6378137,298.257222101],AUTHORITY[\"EPSG\",\"1043\"]],PRIMEM[\"Greenwich\",0],UNIT[\"degree\",0.0174532925199433],AXIS[\"Longitude\",EAST],AXIS[\"Latitude\",NORTH]]";
			
			CoordinateReferenceSystem c = CRS.parseWKT(wkt);
			String cc = c.getCoordinateSystem().getName().getCode();
			System.out.println("=== " + c.getCoordinateSystem().getName().getCode());
			
			String epsgCode = CRS.lookupIdentifier(c, false);
			System.out.println("=== " + epsgCode);
			
			
			
			try {
	            // 解析WKT字符串获取CoordinateReferenceSystem对象
	            CoordinateReferenceSystem crs = CRS.parseWKT(wkt);
	            // 获取EPSG代码
	            String code = crs.getIdentifiers().stream()
	                    .filter(identifier -> "EPSG".equalsIgnoreCase(identifier.getAuthority().toString()))
	                    .findFirst()
	                    .map(identifier -> identifier.getCode())
	                    .orElse(null);
	            if (epsgCode != null) {
	                System.out.println("EPSG代码: " + code);
	            } else {
	                System.out.println("未找到EPSG代码");
	            }
	        } catch (FactoryException e) {
	            e.printStackTrace();
	        }
			
			
//			EPSGChina.defaultEPSG8.get(code)
			
//			CRSFactory cf = new CRSFactory();
//			org.locationtech.proj4j.CoordinateReferenceSystem cfn = cf.createFromName(wkt);
//			int epsgCode = cfn.getProjection().getEPSGCode();
//			System.out.println(epsgCode);
			
			
//			String identifier = CRS.lookupIdentifier(Citations.EPSG, c, true);
//			System.out.println(identifier);
//			Integer i = CRS.lookupEpsgCode(c, true);
//			System.out.println(i);
			
//			CoordinateReferenceSystem crs = reader.getCoordinateReferenceSystem();
//			CoordinateReferenceSystem crs2 = reader.getCrs();
//
//			GridCoverage2D coverage = reader.read(null);
//			CoordinateReferenceSystem targetCRS = coverage.getCoordinateReferenceSystem();
//			String srs = null;
//			Integer code = CRS.lookupEpsgCode(crs2, true);
//			System.out.println("code : " + code);
//
//			if (code != null) {
//				srs = "EPSG:" + code;
//			} else {
//				String name = CRS.toSRS(targetCRS);
//				if ("GCS Name = GCS_China_Geodetic_Coordinate_System_2000".equals(name)) {
//					srs = "EPSG:" + 4490;
//				} else {
//					throw new Exception("tif 文件异常");
//				}
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
