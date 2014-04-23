package com.example.resume.ability;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.resume.ActivityUtils;
import com.example.resume.MainActivity;
import com.example.resume.R;
import com.example.resume.model.Ability;

public class AbilityActivity extends Activity {
	List<Map<String, Object>> abilityList = null;
	private ListView listView;
	private ListAdapter adapter = null;
	private int selectItemPosition = 0;
    private String[] items = new String[] { "删除"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		listView = new ListView(this);
		abilityList = getData();
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(listView);
		adapter =  getListAdapter();
		if (adapter != null && !adapter.isEmpty())
		{
			listView.setAdapter(adapter);
			listView.setOnItemClickListener(new onItemClickListener());
	        
		}
        

	}

  private List<Map<String, Object>> getData(){
    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    List<Ability> abilities =  Ability.getAll(this);
    if(abilities == null || abilities.size() == 0)
    	return null;
    for (Ability ability : abilities)
    {
    	Map<String, Object> map = AbilityToMap(ability);
		list.add(map);
    }
    return list;

    }
  
  
	private Map<String, Object> AbilityToMap(Ability ability)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", String.valueOf(ability.getId()));
		map.put("skill",ability.getSkill());
		return map;
	}
	
	
	public ListAdapter getListAdapter()
	{
        String[] from = new String[]{"id","skill"};
        int[] to = new int[]{R.id.abilityId, R.id.skill};
        abilityList = this.getData();
        if(abilityList == null || abilityList.size() == 0)
        {
        	return null;
        }
        ListAdapter langListAdapter =  new SimpleAdapter(this, abilityList, R.layout.activity_ability,from,to);
        return langListAdapter;
    	
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	       case R.id.action_add:
	    	   ActivityUtils.startActivity(this, AddAbilityActivity.class);
	           return true;
	       case android.R.id.home:
	    	   ActivityUtils.gotoActivity(this, MainActivity.class);
	    	   return true;
	       default:
	           return false;
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
	

	/**
	 * 显示删除对话框
	 */
	private void showDeleteDialog() 
	{
		Builder build=new AlertDialog.Builder(this);
		build.setItems(items, new clickToDeleteAbilityDialogAction());
		build.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		build.show();
	}
	
	private class clickToDeleteAbilityDialogAction implements DialogInterface.OnClickListener
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
			Map<String, Object> abilityData = (Map<String, Object>) adapter.getItem(selectItemPosition);
			String id = (String) abilityData.get("id");   
			Ability.deleteById(AbilityActivity.this, id);
	        ActivityUtils.refreshActivity(AbilityActivity.this);
		}
	}
	
	
	private class onItemClickListener implements OnItemClickListener
	{
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItemPosition = position;
			showDeleteDialog() ;
			
		}
		
	}
	

}
