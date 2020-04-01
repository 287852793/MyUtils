package com.xiaoer.utils;

import java.io.File;

/**
 * mp3 文件名，歌手-歌名 改 歌名-歌手
 * 
 * @author: pangyafei
 * @date: 2020年2月21日 下午4:11:17
 */
public class MP3FileNameChange {
	// 参数
	private static String dirPath = "E:\\KwDownload\\tmp";
	
	public static void main(String[] args) {
		try {
			File dir = new File(dirPath);
			File[] mp3List = dir.listFiles();
			for (File mp3 : mp3List) {
				System.out.println(mp3.getName());
				String newName = dirPath + File.separator
						+ mp3.getName().substring(0, mp3.getName().lastIndexOf(".mp3")).split("-")[1] + "-"
						+ mp3.getName().split("-")[0] + ".mp3";
				System.out.println(mp3.renameTo(new File(newName)));
				System.out.println(newName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
