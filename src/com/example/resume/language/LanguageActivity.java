package com.example.resume.language;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.resume.ActivityUtils;
import com.example.resume.MainActivity;
import com.example.resume.R;
import com.example.resume.model.Language;

public class LanguageActivity extends Activity {
	
    private List<Map<String, Object>> langList= null;
    private ListView listView;
    private final int padding = 10;
    private int selectItemPosition = 0;
    private ListAdapter adapter = null;
    private String[] items = new String[] { "删除"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		
		listView=new ListView(this);
        listView.addHeaderView(getHeadView());
        setContentView(listView);
    	adapter = getListAdapter();
    	if(adapter != null)
    	{
    		listView.setAdapter(adapter);
    		listView.setOnItemClickListener(new onItemClickListener());
    	} 
	}
	

	
	private View getHeadView()
	{
	   TextView t1=new TextView(this);
	   TextView t2=new TextView(this);
	   TextView t3=new TextView(this);
	   TextView t4=new TextView(this);
       
	   t1.setText("语言");
	   t2.setText("掌握程度");
	   t3.setText("读写能力");
	   t4.setText("听说能力");

	   LinearLayout lin=new LinearLayout(this);
	   lin.setOrientation(LinearLayout.HORIZONTAL);
	   lin.setPadding(padding, padding, padding, padding);
	    
	   LinearLayout.LayoutParams lp1 = new LayoutParams(0, LayoutParams.FILL_PARENT, 1);
	   lin.addView(t1,lp1);
	   
	   LinearLayout.LayoutParams lp2 = new LayoutParams(0, LayoutParams.FILL_PARENT, 1);
	   lin.addView(t2,lp2);
	    
	   LinearLayout.LayoutParams lp3 = new LayoutParams(0, LayoutParams.FILL_PARENT, 1);
	   lin.addView(t3,lp3);
	    
	   LinearLayout.LayoutParams lp4 = new LayoutParams(0, LayoutParams.FILL_PARENT, 1);
	   lin.addView(t4,lp4);
	   
	   lin.setEnabled(false);
	   lin.setClickable(false);
	   return lin;
        
	}
	
	
	public ListAdapter getListAdapter()
	{
        String[] from = new String[]{"id","language","skill","readwrite","speaklisten"};
        int[] to = new int[]{R.id.languageId, R.id.type, R.id.skill, R.id.readwrite, R.id.speaklisten};
        langList = this.getData();
        if(langList == null || langList.size() == 0)
        {
        	langList = new ArrayList<Map<String, Object>>();
        	return new SimpleAdapter(this, langList, R.layout.activity_language,from,to);
        }
        ListAdapter langListAdapter =  new SimpleAdapter(this, langList, R.layout.activity_language,from,to);
        return langListAdapter;
    	
	}

	
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Language> langList = Language.getAll(this);
		if(langList == null || langList.size() == 0)
			return null;
		
		for (Language lang : langList)
		{		
			Map<String, Object> map = null;
			
			map = languageToMap(lang);
			list.add(map);
		}

		return list;
	}
	
	
	private Map<String, Object> languageToMap(Language language)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", String.valueOf(language.getId()));
		map.put("language", language.getType());
		map.put("skill",language.getSkill());
		map.put("readwrite", language.getReadwrite());
		map.put("speaklisten", language.getSpeaklisten());
		return map;
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	 switch(item.getItemId() )
        {
        	case android.R.id.home:
        		ActivityUtils.gotoActivity(this, MainActivity.class);
                return true;
        	case R.id.action_add:
        		ActivityUtils.startActivity(this, AddLanguageActivity.class);
                return true;
            default:
            	 return super.onOptionsItemSelected(item);
        }
	}
	@Override 
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	  if(keyCode == KeyEvent.KEYCODE_BACK){ 
		  ActivityUtils.gotoActivity(this, MainActivity.class);
		  return true;
	  }
	  return super.onKeyDown(keyCode, event);
	}

	/**
	 * 显示删除对话框
	 */
	private void showDeleteDialog() 
	{
		Builder build=new AlertDialog.Builder(this);
		build.setItems(items, new clickToDeleteLanguageDialogAction());
		build.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		build.show();
	}
	
	private void showConfirmDeleteDialog()
	{
		  AlertDialog.Builder builder = new AlertDialog.Builder(this);
          builder.setMessage("确定要删除该项？");
          builder.setCancelable(true);
          builder.setPositiveButton("确定", new deleteLanguageListener());
          builder.setNegativeButton("取消",  new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.dismiss();
				}
			});
          builder.show();
	}
	

	private class clickToDeleteLanguageDialogAction implements DialogInterface.OnClickListener
    {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case 0:
				showConfirmDeleteDialog();
				break;
			}
		}
	}
	
	
	private class deleteLanguageListener implements DialogInterface.OnClickListener
    {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			Map<String, Object> langData = (Map<String, Object>) adapter.getItem(selectItemPosition-1);
			String id = (String) langData.get("id");   
	        Language.deleteById(LanguageActivity.this, id);
	        ActivityUtils.refreshActivity(LanguageActivity.this);
		}
	}
	
	
	private class onItemClickListener implements OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItemPosition = position;
			Log.d("resume", "selectItemPosition:"+selectItemPosition);
			//selectItemPosition==0的时候是标题头，去掉
			if(selectItemPosition != 0)
			{
				showDeleteDialog() ;
			}
			
			
		}
		
	}

}
