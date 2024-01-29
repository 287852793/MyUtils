package com.xiaoer.work;

import org.geotools.referencing.CRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;

public class TransformTest {
	public static void main(String[] args) {

		try {
			CoordinateReferenceSystem source = CRS.decode("EPSG:4506", true);
			CoordinateReferenceSystem target = CRS.decode("EPSG:4326", true);

			MathTransform transform = CRS.findMathTransform(source, target, true);
			
			System.out.println("success");
		} catch (NoSuchAuthorityCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
