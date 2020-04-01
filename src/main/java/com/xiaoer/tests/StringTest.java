package com.xiaoer.tests;

import java.util.Date;

public class StringTest {
	public static void main(String[] args) {
//		String s = "jdbc:oracle:thin:@192.168.20.8:1521:orcl";
//		String r = s.substring(s.indexOf("@") + 1);
//		System.out.println(r);
//		String ip = r.substring(0, r.indexOf(":"));
//		System.out.println(ip);
//		String sid = r.substring(r.lastIndexOf(":") + 1, r.length());
//		System.out.println(sid);
//		String port = r.substring(r.indexOf(":") + 1, r.lastIndexOf(":"));
//		System.out.println(port);
		
		String s = "a.b.zip";
		System.out.println(s.substring(s.lastIndexOf(".")));
		
		System.out.println(new Date(1585111278773l).toLocaleString());

	}
}
