package com.xiaoer.tests;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;

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

//		String s = "a.b.zip";
//		System.out.println(s.substring(s.lastIndexOf(".")));
//		
//		System.out.println(new Date(1585111278773l).toLocaleString());
//		
//		System.out.println(UUID.randomUUID().toString());

//		String s = "hdfs://192.168.20.11:9000";
//		System.out.println(s.substring(s.indexOf("hdfs://") + 7, s.lastIndexOf(":")));
//		System.out.println(s.substring(s.lastIndexOf(":") + 1));
//
//		Double d = 0.123d;
//		System.out.println(new DecimalFormat("##.####").format(100 * 1.0 / 200));
//
//		String zip = "aaa.zip";
//		System.out.println(zip.contains(".") ? zip.substring(0, zip.lastIndexOf(".")) : zip);

//		String s = "/a//b/cd//e/";
//		System.out.println(s.replaceAll("//", "/"));

//		String s = "http://172.18.18.195:8080/eureka/";
//		System.out.println(s.substring(0, s.lastIndexOf(":")));
//		
//		System.out.println(new Date().getTime());

//		String s = "insert into table() values(?,?,?,?,?,?,?,?,?,?)";
//		int i = 5;
//		String geom = "test geom";
//		s = s.substring(0, s.indexOf("values(") + 7 + i * 2) + geom +","+ s.substring(s.indexOf("values(") + 7 + i * 2);
//		System.out.println(s);
//		
//		Integer a = null;
//		if (1 == a) {
//			System.out.println(123);
//		}

//		String p = "/adf/df///asdf//ef//fasd/f";
//		p = p.replaceAll("/+", "/");
//		System.out.println(p);

//		String s = "/aaa/bbb.zip";
//		System.out.println(s.substring(s.lastIndexOf("/") + 1));

//		String s = "abcdeg.zip";
//		System.out.println(s.substring(1, 5));
//		System.out.println(s.substring(0, s.lastIndexOf(".")));
//		System.out.println(String.format("%.2f", 66666 * 100.0d / 88888));

//		String s = "1,1,1,1,1,1,1,1,2,5,10,10,10,5,1,1";
//		Map<Integer, Double> map = new HashMap<Integer, Double>();
//		try {
//            String[] filters = s.split(",");
//            for (int i = 0; i <= filters.length; i++) {
//            	map.put(i, Double.parseDouble(filters[i]));
//            }
//        } catch (Exception e) {
//        	e.printStackTrace();
//        }

//		String s = "jdbc:mysql://${mysql.host}:${mysql.port}/ispatial?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC&connectTimeout=60000&socketTimeout=60000&autoReconnect=true&autoReconnectForPools=true&failOverReadOnly=false&allowMultiQueries=true";
//		s = StrUtil.replace(s, "(\\$)(\\{)[^\\}]*(\\})", item -> {
//			String r = item.group().replaceAll("\\$|\\{|\\}", "");
//			System.out.println("${" + r + "}");
//			System.out.println(("${" + r + "}").replaceAll("\\$", "\\\\\\$"));
//			return ("${" + r + "}").replaceAll("\\$", "\\\\\\$");
//		});
//		
//		String s2 = "123${abc}456";
//		System.out.println(s2.matches(".*(\\$)(\\{)[^\\}]*(\\}).*"));
//		
//		System.out.println(s);
//		
//		
//		System.out.println(s);
		
		
//		String s = "_igis_test";
//		System.out.println(s.substring("_igis_".length()));
		
		String s = "/abc/def";
		System.out.println(s.substring(0, s.lastIndexOf("/")));
		

	}
}
