package com.example.experience;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.resume.ActivityUtils;
import com.example.resume.MainActivity;
import com.example.resume.R;
import com.example.resume.edu.AddEducationActivity;
import com.example.resume.edu.EducationActivity;
import com.example.resume.edu.ModifyEducationActivity;
import com.example.resume.language.LanguageActivity;
import com.example.resume.model.Education;
import com.example.resume.model.Experience;
import com.example.resume.model.Language;
import com.example.resume.utils.Global;

public class ExperiencesActivity extends ListActivity {
	public final static String EXTRA_EXPRIENCE = "com.example.resume.eucation.EXPRIENCE";
    private SimpleAdapter expListAdapter = null;
    private List<Map<String, Object>> expList= null;
    private Integer positionOfExp = null;
    private String[] items = new String[] { "删除", "修改" };
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_experiences);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setListViewAdapter();
	}
	
	public void setListViewAdapter()
	{
		String[] from = new String[]{"id","fromdate","overdate","company","occupation","describtion",
				"fromdateText","overdateText","companyText","occupationText","describtionText",};
        int[] to = new int[]{R.id.experienceId, R.id.fromdate,R.id.overate, R.id.company,R.id.occupation,R.id.describtion,
        		R.id.fromdateText,R.id.overateText, R.id.companyText,R.id.occupationText,R.id.describtionText};
        expList = this.getData();
        
        if(expList != null && expList.size() != 0)
        {
        	 expListAdapter =  new SimpleAdapter(this, expList, R.layout.activity_experiences,from,to);
             setListAdapter(expListAdapter);
        }
	}
	
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Experience> expList = Experience.getAll(this);

		if(expList == null || expList.size() == 0)
			return null;
		
		for (Experience exp : expList)
		{		
			Map<String, Object> map = expToMap(exp);
			list.add(map);
		}

		return list;
	}
	
	/**
	 * 把education转化为map
	 * @param exp
	 * @return
	 */
	private Map<String, Object> expToMap(Experience exp)
	{
			
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", String.valueOf(exp.getId()));
		map.put("fromdate", exp.getFromDate());
		map.put("overdate", exp.getOverDate());
		map.put("company", exp.getCompany());
		map.put("occupation", exp.getOccupation());
		map.put("describtion", exp.getDescribtion());
		map.put("fromdateText", "开始时间：");
		map.put("overdateText", "结束时间：");
		map.put("companyText", "公司名称：");
		map.put("occupationText", "职　　位：");
		map.put("describtionText", "工作职责：");
		return map;
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
	//	ActivityUtils.showTip(this, msg)
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	       switch (item.getItemId()) {
	       case R.id.action_add:
	    	   ActivityUtils.gotoActivity(this, AddExperienceActivity.class);
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
	
   @Override
   protected void onListItemClick(ListView listView, View view, int position, long id) {

	   positionOfExp = position;
	   showEditExpDialog();

   }


	//用于选择修改或者删除
	private void showEditExpDialog() 
	{
		Builder build=new AlertDialog.Builder(this);
		build.setItems(items, new clickToShowExpDialogAction());
		build.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		build.show();
	}

	
	   private void modifyExp() {
		    Map<String, Object> expData = (Map<String, Object>) expListAdapter.getItem(positionOfExp);
		   	String id = expData.get("id").toString();
			String fromDate = expData.get("fromdate").toString();
			String overDate = expData.get("overdate").toString();
			String company = expData.get("company").toString();
			String occupation = expData.get("occupation").toString();
			String describtion = expData.get("describtion").toString();
	
			Experience exp = new Experience(Long.parseLong(id), fromDate, overDate, company, occupation, describtion);
	    	Bundle bundle=new Bundle();  
	 	   	bundle.putParcelable(EXTRA_EXPRIENCE, exp);  
	 	   	Intent intent=new Intent(this, ModifyExperienceActivity.class);  
	 	   	intent.putExtras(bundle);  
	 	   	startActivity(intent); 
		
	   }

	   
		private void showConfirmDeleteDialog()
		{
			  AlertDialog.Builder builder = new AlertDialog.Builder(this);
	          builder.setMessage("确定要删除该项？");
	          builder.setCancelable(true);
	          builder.setPositiveButton("确定", new deleteExpListener());
	          builder.setNegativeButton("取消",  new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
	          builder.show();
		}
	   
		private class clickToShowExpDialogAction implements DialogInterface.OnClickListener
	    {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				switch (which) {
				case 0:
					//删除
					showConfirmDeleteDialog();
					break;
				case 1:
					//修改
					modifyExp();
					break;
				}
			}
		}
	 
		private class deleteExpListener implements DialogInterface.OnClickListener
	    {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Map<String, Object> langData = (Map<String, Object>) expListAdapter.getItem(positionOfExp);
				String id = (String) langData.get("id");   
				Experience.deleteById(ExperiencesActivity.this, id);
				ActivityUtils.refreshActivity(ExperiencesActivity.this);
//		        Language.deleteById(LanguageActivity.this, id);
//		        ActivityUtils.refreshActivity(LanguageActivity.this);
			}
		}
		
}
