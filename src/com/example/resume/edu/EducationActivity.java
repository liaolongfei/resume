package com.example.resume.edu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ListActivity;
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
import com.example.resume.model.Education;
import com.example.resume.objective.ModifyObjectiveActivity;
import com.example.resume.utils.Global;

public class EducationActivity extends ListActivity {
	public final static String EXTRA_EDUCATION = "com.example.resume.eucation.EDUCATION";
    private SimpleAdapter eduListAdapter = null;
    private List<Map<String, Object>> eduList= null;

    private static final int CONFIRM_DELETE_DIALOG = 1;


    private Dialog confirmDeleteEduDialog = null;
    private Integer positionOfEducation = null;
    private Map<String, Object> eduData = null;
    private String[] items = new String[] { "删除", "修改" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_education);
		getActionBar().setDisplayHomeAsUpEnabled(true);

		setListViewAdapter();
	}
	
	
	public void setListViewAdapter()
	{
		String[] from = new String[]{"id","date_separator","fromdate","overdate","school","degree","major"};
        int[] to = new int[]{R.id.eduId, R.id.date_separator,R.id.fromdate, R.id.overdate,R.id.school,R.id.degree,R.id.major};
        eduList = this.getData();
        
        if(eduList != null && eduList.size() != 0)
        {
        	 eduListAdapter =  new SimpleAdapter(this, eduList, R.layout.activity_education,from,to);
             setListAdapter(eduListAdapter);
        }
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}
	

   
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Education> eduList = Education.getAll(this);
		
		if(eduList == null || eduList.size() == 0)
			return null;
		
		for (Education edu : eduList)
		{		
			Map<String, Object> map = educationToMap(edu);
			list.add(map);
		}

		return list;
	}
	
	/**
	 * 把education转化为map
	 * @param edu
	 * @return
	 */
	private Map<String, Object> educationToMap(Education edu)
	{
			
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", String.valueOf(edu.getId()));
		map.put("fromdate", edu.getFromDate());
		map.put("date_separator", Global.edu_date_separator);
		map.put("overdate", edu.getOverDate());
		map.put("school", edu.getSchool());
		map.put("degree", edu.getDegree());
		map.put("major", edu.getMajor());
		
		return map;
	}
	
	
	
	/**
	 * 教育经历对话框，用于修改和删除
	 */
	private void showEduDialog() 
	{
		Builder build=new AlertDialog.Builder(this);
		build.setItems(items, new clickToShowEduDialogAction());
		build.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		build.show();
	}
	

   
   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
       switch (item.getItemId()) {
	       case R.id.action_add:
	    	   ActivityUtils.gotoActivity(this, AddEducationActivity.class);
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

   /**
    * 删除edu
    * @param id
    */
   private void deleteEdu(String id) {
	   Education.deleteById(this, id);
	   ActivityUtils.refreshActivity(this);
   }

   
   private void modifyEdu() {
	   	String id = eduData.get("id").toString();
		String fromDate = eduData.get("fromdate").toString();
		String overDate = eduData.get("overdate").toString();
		String school = eduData.get("school").toString();
		String degree = eduData.get("degree").toString();
		String major = eduData.get("major").toString();
    	
    	Education edu = new Education(Long.parseLong(id), fromDate, overDate, school, degree, major);
    	Bundle bundle=new Bundle();  
 	   	bundle.putParcelable(EXTRA_EDUCATION, edu);  
 	   	Intent intent=new Intent(this, ModifyEducationActivity.class);  
 	   	intent.putExtras(bundle);  
 	   	startActivity(intent); 
   }


@Override
   protected Dialog onCreateDialog(int id) {
       switch (id) {

       case CONFIRM_DELETE_DIALOG:
           if (confirmDeleteEduDialog == null) {
               AlertDialog.Builder builder = new AlertDialog.Builder(this);
               builder.setMessage("确定要删除该教育经历？");
               builder.setCancelable(true);
               builder.setPositiveButton("确定", new deleteEduListener());
               builder.setNegativeButton("取消", cancelListener());
               confirmDeleteEduDialog = builder.create();
           }
           return confirmDeleteEduDialog;
       default:
           return null;
       }
   }
   
   
   @Override
   protected void onListItemClick(ListView listView, View view, int position, long id) {

	   positionOfEducation = position;
	   eduData = (Map<String, Object>) eduListAdapter.getItem(positionOfEducation);

	   showEduDialog();

   }

   
  


   
   protected DialogInterface.OnClickListener cancelListener() {
       return new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
               dialog.cancel();
           }
       };
   }
   
   
   public AlertDialog getConfirmDeleteEduDialog() {
       return (AlertDialog) confirmDeleteEduDialog;
   }
   
   
	private class clickToShowEduDialogAction implements DialogInterface.OnClickListener
    {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case 0:
				//删除
				showDialog(CONFIRM_DELETE_DIALOG);
				break;
			case 1:
				//修改
				modifyEdu();
				break;
			}
		}
	}
 
	
	private class deleteEduListener implements DialogInterface.OnClickListener
	{

		@Override
		public void onClick(DialogInterface dialog, int which) {
            String eduId = (String) eduData.get("id");   
            deleteEdu(eduId);
			
		}
		
	}
	

}
