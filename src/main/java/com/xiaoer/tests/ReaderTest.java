package com.xiaoer.tests;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReaderTest {
	public static void main(String[] args) {
		try {
			File file = new File("E:/tmp/oper1-20200928-135536-30941012-89c8-4dba-8a66-4fa31641c2c9.txt");
			FileInputStream fileInputStream = new FileInputStream(file);
			byte[] bytes = new byte[fileInputStream.available()];
			fileInputStream.read(bytes);
			fileInputStream.close();

			InputStream inputStream = new ByteArrayInputStream(bytes);

			int start = 0;
			int blockSize = 1024;
			int tempBlockSize = 1024;

			for (int j = 0; j < 10; j++) {
				inputStream.reset();
				inputStream.skip(start);
				
				if (inputStream.available() > blockSize) {
					tempBlockSize = blockSize;
					byte[] bs = new byte[tempBlockSize];
					inputStream.read(bs);
					InputStream is = new ByteArrayInputStream(bs);

					BufferedReader reader = new BufferedReader(new InputStreamReader(is));
					long count = reader.lines().count();
					is.reset();
					String text = "";
					for (int i = 0; i < count - 1; i++) {
						text += reader.readLine() + System.lineSeparator();
					}

					
					System.out.print(text);
//					System.out.println(text.getBytes().length);
//					
//					int a = (int)(count - 1) * System.lineSeparator().getBytes().length;
					start = start + text.getBytes().length;
					
					System.out.println("start : " + start);
				}
			}

			System.out.println(System.lineSeparator().getBytes().length);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
