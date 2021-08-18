package com.xiaoer.tests;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.file.FileWriter;

public class ArrayTest {
	public static void main(String[] args) {
//		List<String> list = Arrays.asList(new String[10]);
//		Collections.fill(list, "?");
//		System.out.println(String.join(",", list));
		List<String> s = new ArrayList<String>();
		s.add("123123");
		s.add("123123");
		s.add("123123");
		s.add("123123");
		s.add("123123");
		s.add("123123");
		
		System.out.println("{"+
"\"nodes\":["+
        "{"+
        "\"preId\":-1,"+
        "\"out_ports\":["+
        "0"+
        "],"+
        "\"out_ports_tips\":["+
        "\"输出数据表\""+
        "],"+
        "\"label\":\"表输入\","+
        "\"params\":{"+
        "\"url\":\"jdbc:mysql://172.20.20.1:3307/ispatial\","+
        "\"user\":\"mysql\","+
        "\"password\":\"mysql\","+
        "\"in_table\":\"dayu_plan\""+
        "},"+
        "\"type\":\"文件\","+
        "\"dag_x\":240,"+
        "\"parentId\":75,"+
        "\"failto\":0,"+
        "\"pos_y\":106.0,"+
        "\"userdata\":{"+
        "\"node_type\":\"not_collect\","+
        "\"canResetParams\":\"[]\""+
        "},"+
        "\"pos_x\":325.0,"+
        "\"name\":\"igis.etl.table_input\","+
        "\"nodeKey\":36,"+
        "\"id\":102,"+
        "\"in_ports\":[]"+
        "},"+
        "{"+
        "\"preId\":-1,"+
        "\"out_ports\":["+
        "0"+
        "],"+
        "\"out_ports_tips\":["+
        "\"输出数据表\""+
        "],"+
        "\"label\":\"表输入\","+
        "\"params\":{"+
        "\"url\":\"jdbc:mysql://172.20.20.1:3307/ispatial\","+
        "\"user\":\"mysql\","+
        "\"password\":\"mysql\","+
        "\"in_table\":\"dayu_plan\""+
        "},"+
        "\"type\":\"文件\","+
        "\"dag_x\":240,"+
        "\"parentId\":75,"+
        "\"failto\":0,"+
        "\"pos_y\":106.0,"+
        "\"userdata\":{"+
        "\"node_type\":\"not_collect\","+
        "\"canResetParams\":\"[]\""+
        "},"+
        "\"pos_x\":325.0,"+
        "\"name\":\"igis.etl.table_input\","+
        "\"nodeKey\":36,"+
        "\"id\":103,"+
        "\"in_ports\":[]"+
        "},"+
        "{"+
        "\"preId\":-1,"+
        "\"out_ports\":["+
        "0"+
        "],"+
        "\"out_ports_tips\":["+
        "\"表连接\""+
        "],"+
        "\"label\":\"表连接\","+
        "\"params\":{"+
        "\"left_columns\":\"plan_name\","+
        "\"right_columns\":\"plan_name\""+
        "},"+
        "\"type\":\"文件\","+
        "\"dag_x\":240,"+
        "\"parentId\":75,"+
        "\"failto\":0,"+
        "\"pos_y\":106.0,"+
        "\"userdata\":{"+
        "\"node_type\":\"not_collect\","+
        "\"canResetParams\":\"[]\""+
        "},"+
        "\"pos_x\":325.0,"+
        "\"name\":\"igis.etl.table_join\","+
        "\"nodeKey\":36,"+
        "\"id\":104,"+
        "\"in_ports\":[0,1]"+
        "},"+
        "{"+
        "\"preId\":-1,"+
        "\"out_ports\":["+
        "0"+
        "],"+
        "\"out_ports_tips\":["+
        "\"输出数据表\""+
        "],"+
        "\"label\":\"表输入\","+
        "\"params\":{"+
        "\"url\":\"jdbc:mysql://172.20.20.1:3307/ispatial\","+
        "\"user\":\"mysql\","+
        "\"password\":\"mysql\","+
        "\"out_table\":\"dayu_plan_join\""+
        "},"+
        "\"type\":\"文件\","+
        "\"dag_x\":240,"+
        "\"parentId\":75,"+
        "\"failto\":0,"+
        "\"pos_y\":106.0,"+
        "\"userdata\":{"+
        "\"node_type\":\"not_collect\","+
        "\"canResetParams\":\"[]\""+
        "},"+
        "\"pos_x\":325.0,"+
        "\"name\":\"igis.etl.table_output\","+
        "\"nodeKey\":36,"+
        "\"id\":105,"+
        "\"in_ports\":[2]"+
        "}"+
        "],"+
        "\"edges\":["+
        "{"+
        "\"src_node_id\":102,"+
        "\"dst_node_id\":104,"+
        "\"src_output_idx\":0,"+
        "\"dst_input_idx\":0"+
        "},"+
        "{"+
        "\"src_node_id\":103,"+
        "\"dst_node_id\":104,"+
        "\"src_output_idx\":0,"+
        "\"dst_input_idx\":0"+
        "},"+
        "{"+
        "\"src_node_id\":104,"+
        "\"dst_node_id\":105,"+
        "\"src_output_idx\":0,"+
        "\"dst_input_idx\":0"+
        "}"+
        "],"+
        "\"params\":["+
        "{"+
        "\"valueType\":\"string\","+
        "\"label\":\"a\","+
        "\"type\":\"dynamic\","+
        "\"value\":\"f\","+
        "\"key\":\"a\""+
        "}"+
        "],"+
        "\"plan_comment\":\"测试yarn执行器\","+
        "\"plan_id\":59,"+
        "\"plan_name\":\"测试yarn执行器\""+
        "}"
);
		
		try {
			File file = new File("E:/res2.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileWriter writer = new FileWriter(file);
			for (String key : s) {
				writer.append(key + "\n");
			}
		} catch (IORuntimeException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
