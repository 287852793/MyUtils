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
			File tifFile = new File("e:/playground.tif");
			GeoTiffReader reader = new GeoTiffReader(tifFile);
			String wkt = reader.getCoordinateReferenceSystem().toWKT();
			System.out.println(wkt);
			
			CoordinateReferenceSystem c = CRS.parseWKT(wkt);
			String cc = c.getCoordinateSystem().getName().getCode();
			System.out.println("=== " + c.getCoordinateSystem().getName().getCode());
			
//			EPSGChina.defaultEPSG8.get(code)
			
			CRSFactory cf = new CRSFactory();
			org.locationtech.proj4j.CoordinateReferenceSystem cfn = cf.createFromName(wkt);
			int epsgCode = cfn.getProjection().getEPSGCode();
			System.out.println(epsgCode);
			
			
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
		} catch (DataSourceException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (FactoryException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
