package com.xiaoer.work;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Base64;

public class AESTest {

	public static void main(String[] args) throws Exception {
		
		// 待加密的原始文本
		String data_to_encrypt = "igis_72bf0342-b55d-bdb7-9273-5689cf04b9a1_2024-04-24";

		byte[] keyBytes = null;
		String keyPath = "E:/igiskey";
		try (DataInputStream dis = new DataInputStream(new FileInputStream(keyPath))){
			keyBytes = new byte[dis.available()];
            dis.readFully(keyBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SecretKeySpec secretKeySpec = new SecretKeySpec(keyBytes, "AES");

		System.out.println("待加密的数据: " + data_to_encrypt);
		// 加密操作
		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
		
		// ECB 模式加密
		byte[] encrypted_bytes = cipher.doFinal(data_to_encrypt.getBytes("UTF-8"));
		String encrypted_data = Base64.getEncoder().encodeToString(encrypted_bytes);

		System.out.println("加密后的数据: " + encrypted_data);

		cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
		byte[] decrypted_bytes = cipher.doFinal(Base64.getDecoder().decode(encrypted_data));
		String decrypted_data = new String(decrypted_bytes, "UTF-8");

		System.out.println("解密后的数据: " + decrypted_data);
	}
}
