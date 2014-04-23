package com.example.resume;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.resume.model.Education;
import com.example.resume.utils.Global;

public class TestActivity extends Activity  {
	private ListView listView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		listView = new ListView(this);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,getData()));
        setContentView(listView);
	}



	 private List<String> getData(){
         
	        List<String> data = new ArrayList<String>();
	        data.add("测试数据1");
	        data.add("测试数据2");
	        data.add("测试数据3");
	        data.add("测试数据4");
	        data.add("测试数据5");
	        data.add("测试数据6");
	        data.add("测试数据7");
	        data.add("测试数据8");
	        data.add("测试数据9");
	        data.add("测试数据10");
	        data.add("测试数据11");
	        data.add("测试数据12");
	        data.add("测试数据13");
	        data.add("测试数据14");
	        data.add("测试数据15");
	        data.add("测试数据16");
	        data.add("测试数据17");
	        data.add("测试数据18");
	        data.add("测试数据19");
	        data.add("测试数据20");
	         
	        return data;
	    }
	

	

}
