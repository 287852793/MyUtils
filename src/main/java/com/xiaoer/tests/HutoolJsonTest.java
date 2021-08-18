package com.xiaoer.tests;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

/**
 * 
 * @author pyf
 * @time 2020-12-29 16:58:39
 */
public class HutoolJsonTest {
	public static void main(String[] args) {
		String jsonStr = "{\"name\":\"igis\",\"password\": null}";

		JSONObject o1 = JSONUtil.parseObj(jsonStr, false);

		System.out.println("print 1 : " + (o1.get("name")));
		System.out.println("print 2 : " + (o1.get("password") == null));
		System.out.println("print 3 : " + (o1.get("password").getClass()));

		JSONObject o2 = JSONUtil.parseObj(jsonStr, true); // 这里的true表示，在解析阶段，忽略空值
		System.out.println("print 4 : " + (o2.get("password") == null));

		// 下面这种写法不推荐
		JSONObject o3 = new JSONObject(jsonStr, false); // 这里的false标识，在构建阶段，不忽略空值，让用户能判断的出 == null
		System.out.println("print 5 : " + (o3.get("password") == null));

	}
}
