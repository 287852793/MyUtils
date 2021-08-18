package com.xiaoer.tests;

import java.nio.charset.Charset;

import cn.hutool.core.net.URLDecoder;
import cn.hutool.core.net.URLEncoder;

public class EncodeTest {
	public static void main(String[] args) {
		String a = "-indetifier";
		String b = "igis.pipline.entry";
		String c = "-Djson= { \"test\": 123 }";
		
		URLEncoder encoder = URLEncoder.createDefault();
		
		String[] arr = new String[3];
		arr[0] = encoder.encode(a, Charset.forName("utf-8"));
		arr[1] = encoder.encode(b, Charset.forName("utf-8"));
		arr[2] = encoder.encode(c, Charset.forName("utf-8"));
		
		for (String s : arr) {
			System.out.println(s);
			System.out.println(URLDecoder.decode(s, Charset.forName("utf-8")));
		}
		
	}
}
