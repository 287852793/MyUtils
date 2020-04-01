package com.xiaoer.utils;

import java.io.File;

/**
 * 文件夹里头的文件批量重命名
 * 
 * @author: pangyafei
 * @date: 2020年2月25日 下午9:04:07
 */
public class FilesRename {
	private static String dirPath = "D:\\movies\\jojo星尘斗士\\JoJo no Kimyou na Bouken BD-1080p-asxzwang"; // 文件夹路径

	private static String delStr = "JoJo no Kimyou na Bouken BD-1080p-asxzwang "; // 要删除的字符
	
	public static void main(String[] args) {
		File dir = new File(dirPath);
		File[] files = dir.listFiles();
		
		for (File file : files) {
			if (!file.isDirectory()) {
				System.out.print(file.getName());
				file.renameTo(new File(dirPath + "\\" + file.getName().replace(delStr, "")));
				System.out.print("\t ==> ");
				System.out.println(file.getName());
			}
		}
	}
}
