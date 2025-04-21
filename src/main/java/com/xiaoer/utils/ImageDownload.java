package com.xiaoer.utils;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownload {
	public static void main(String[] args) {
//		String imageUrl1 = "https://janus.piesat.cn/gateway/api?path=s3://pie-engine-ai/devel/193mRLEF1J4R6ub6scRGK/dataset/segmentation/0e14c634-6dd2-44d2-b402-5d0798f67104/train/images/";
//		String imageUrl2 = "_0.tiff&width=310&height=310&labelName=[{\"class_color\":\"rgb(0,0,0)\",\"class_name\":\"背景\"},{\"class_color\":\"rgb(255,255,255)\",\"class_name\":\"光伏\"}]&gw_appid=e7e5731a52a43122c3e8fbf82b80677c&gw_api=engine.ai.imageRead&access_token=b8a2ff7555143772d87a09d7715f361a"; // 替换为图片的URL
//		String savePath1 = "F:/work/光伏数据/images/"; // 替换为保存图片的本地路径
//		String savePath2 = ".jpg";
		
		
		String imageUrl1 = "https://janus.piesat.cn/gateway/api?path=s3://pie-engine-ai/devel/193mRLEF1J4R6ub6scRGK/dataset/segmentation/0e14c634-6dd2-44d2-b402-5d0798f67104/train/labels/";
		String imageUrl2 = "_0.tiff&width=310&height=310&labelName=[{\"class_color\":\"rgb(0,0,0)\",\"class_value\":0},{\"class_color\":\"rgb(255,255,255)\",\"class_value\":1}]&gw_appid=e7e5731a52a43122c3e8fbf82b80677c&gw_api=engine.ai.imageRead&access_token=b8a2ff7555143772d87a09d7715f361a"; // 替换为图片的URL
		String savePath1 = "F:/work/光伏数据/labels/"; // 替换为保存图片的本地路径
		String savePath2 = ".jpg";

		for (int i = 0; i < 955; i++) {
			try {
				downloadImage(imageUrl1 + i + imageUrl2, savePath1 + i + savePath2);
				System.out.println("图片下载并保存成功: " + i +".jpg");
			} catch (IOException e) {
				e.printStackTrace();
				i--;
				System.out.println("图片下载或保存失败：" + e.getMessage());
			}
		}

		
	}

	private static void downloadImage(String imageUrl, String savePath) throws IOException {
		URL url = new URL(imageUrl);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		try {
			connection.setRequestMethod("GET");
			connection.connect();

			int responseCode = connection.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				try (InputStream inputStream = connection.getInputStream();
						BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
						FileOutputStream fileOutputStream = new FileOutputStream(savePath)) {

					byte[] dataBuffer = new byte[1024];
					int bytesRead;
					while ((bytesRead = bufferedInputStream.read(dataBuffer, 0, 1024)) != -1) {
						fileOutputStream.write(dataBuffer, 0, bytesRead);
					}
				}
			} else {
				throw new IOException("服务器返回错误状态码：" + responseCode);
			}
		} finally {
			connection.disconnect();
		}
	}
}
