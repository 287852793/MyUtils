package com.xiaoer.tests;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

public class JsonTest {
	public static void main(String[] args) {
//		String s = "{ \"edges\": [ { \"src_node_id\": 102, \"src_output_idx\": 0, \"dst_node_id\": 104, \"dst_input_idx\": 0, \"id\": 101 }, { \"src_node_id\": 103, \"src_output_idx\": 0, \"dst_node_id\": 104, \"dst_input_idx\": 1, \"id\": 102 }, { \"src_node_id\": 104, \"src_output_idx\": 0, \"dst_node_id\": 105, \"dst_input_idx\": 0, \"id\": 103 } ], \"nodes\": [ { \"pos_x\": 195, \"pos_y\": 185, \"dag_x\": 240, \"dag_y\": 0, \"label\": \"读数据表\", \"identifier\": \"igis.etl.table_input\", \"in\": 0, \"out\": 1, \"icon_text\": \"读\", \"tooltips\": \"读入一张表\", \"nodeKey\": 2, \"drag\": true, \"state\": 0, \"name\": \"读数据表\", \"id\": 102, \"in_ports\": [], \"out_ports\": [0], \"params\": { \"url\": \"jdbc:mysql://172.20.20.1:3307/ispatial\", \"user\": \"mysql\", \"password\": \"mysql\", \"in_table\": \"dayu_plan\" } }, { \"pos_x\": 470, \"pos_y\": 191, \"dag_x\": 240, \"dag_y\": 0, \"label\": \"读数据表\", \"identifier\": \"igis.etl.table_input\", \"in\": 0, \"out\": 1, \"icon_text\": \"读\", \"tooltips\": \"读入一张表\", \"nodeKey\": 2, \"drag\": true, \"state\": 0, \"name\": \"读数据表\", \"id\": 103, \"in_ports\": [], \"out_ports\": [0], \"params\": { \"url\": \"jdbc:mysql://172.20.20.1:3307/ispatial\", \"user\": \"mysql\", \"password\": \"mysql\", \"in_table\": \"dayu_sched\" } }, { \"pos_x\": 293, \"pos_y\": 329, \"dag_x\": 240, \"dag_y\": 0, \"label\": \"连接表\", \"identifier\": \"igis.etl.table_join\", \"in\": 2, \"out\": 1, \"icon_text\": \"连\", \"in_ports_tips\": [\"left table\", \"right table\"], \"out_ports_tips\": [\"out data\"], \"nodeKey\": 4, \"drag\": true, \"state\": 0, \"name\": \"连接表\", \"id\": 104, \"in_ports\": [0, 1], \"out_ports\": [0], \"params\": { \"left_columns\": \"id\", \"right_columns\": \"plan_id\" } }, { \"pos_x\": 312, \"pos_y\": 500, \"dag_x\": 240, \"dag_y\": 0, \"label\": \"写数据表\", \"identifier\": \"igis.etl.table_output\", \"in\": 1, \"out\": 0, \"icon_text\": \"写\", \"nodeKey\": 3, \"drag\": true, \"state\": 0, \"name\": \"写数据表\", \"id\": 105, \"in_ports\": [0], \"out_ports\": [], \"params\": { \"url\": \"jdbc:mysql://172.20.20.1:3307/ispatial\", \"user\": \"mysql\", \"password\": \"mysql\", \"out_table\": \"dayu_test\" } } ] }";
//		System.out.println(s);
//		JSONObject json = JSONUtil.parseObj(s);
//		System.out.println(json.get("nodes"));
//		
//		List<String> stringList = new ArrayList<String>();
//        stringList.add("-identifier");
//        stringList.add("igis.pipline.entry");
//        stringList.add("-Djson=" + s);
//        String[] ss = stringList.toArray(new String[]{});
//        
//        for (String str : ss) {
//			System.out.println(str.replace(" ", ""));
//		}

		String s = "{ \"test\": \"123\", \"test\": 1234 }";

//		JSONConfig c = JSONConfig.create();
//		c.setIgnoreError(true);
//		c.setIgnoreNullValue(true);
//		JSONUtil.parseObj(s, c);

//		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setLenient().enableComplexMapKeySerialization().serializeNulls().setPrettyPrinting().create();
//		JsonObject json = gson.fromJson(s, JsonObject.class);
//		System.out.println(json.toString());
		
		net.sf.json.JSONObject json = net.sf.json.JSONObject.fromObject(s);
		System.out.println(json.toString());
	}
}
