package com.xiaoer.utils;

import java.io.File;

/**
 * 文件夹下所有子目录中的所有文件重命名
 * 
 * @author pyf
 * @time 2023-03-23 11:18:21
 */
public class FolderFilesRename {
	private static String dirPath = "F:\\清华附小英语动画1-6年级 287集 13.45g"; // 文件夹路径

	public static void main(String[] args) {
		File folder = new File(dirPath);
		System.out.println(folder.isDirectory());
		searchFolder(folder);
	}

	private static void searchFolder(File folder) {
		File[] files = folder.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				searchFolder(file);
			} else {
				fileRename(file);
			}
		}
	}

	private static void fileRename(File file) {
		String before = file.getName();
		String s = before.substring(0, before.indexOf("."));
		String after = before.replace("【更多资料免费分享请加微信xiatianxn】", "");
		if (s.length() < 2) {
			after = "0" + after;
		}
		System.out.println(before + " => " + after);
		file.renameTo(new File(file.getParent() + File.separator + after));
	}
}
