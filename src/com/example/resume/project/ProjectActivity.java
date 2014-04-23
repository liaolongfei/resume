package com.example.resume.project;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.resume.ActivityUtils;
import com.example.resume.MainActivity;
import com.example.resume.R;
import com.example.resume.model.Project;

public class ProjectActivity extends ListActivity {
	public final static String EXTRA_PROJECT = "com.example.resume.eucation.PROJECT";
    private SimpleAdapter projectListAdapter = null;
    private List<Map<String, Object>> projectList= null;
    private Integer positionOfSelectedItem = null;
    private String[] items = new String[] { "删除", "修改" };
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_project);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setListViewAdapter();
	}
	
	public void setListViewAdapter()
	{
		String[] from = new String[]{"id","fromdate","overdate","projectName","title","describtion","duty",
				"fromdateText","overdateText","projectNameText","titleText","describtionText","dutyText",};
        int[] to = new int[]{R.id.projectId, R.id.fromdate,R.id.overate, R.id.projectname, R.id.title, R.id.describtion, R.id.duty,
        		R.id.fromdateText,R.id.overateText, R.id.projectnameText, R.id.titleText, R.id.describtionText,R.id.dutyText};
        projectList = this.getData();
        
        if(projectList != null && projectList.size() != 0)
        {
        	 projectListAdapter =  new SimpleAdapter(this, projectList, R.layout.activity_project,from,to);
             setListAdapter(projectListAdapter);
        }
	}
	
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		List<Project> proList = Project.getAll(this);

		if(proList == null || proList.size() == 0)
			return null;
		
		for (Project pro : proList)
		{		
			Map<String, Object> map = proToMap(pro);
			list.add(map);
		}

		return list;
	}
	
	/**
	 * 把education转化为map
	 * @param pro
	 * @return
	 */
	private Map<String, Object> proToMap(Project pro)
	{
			
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", String.valueOf(pro.getId()));
		map.put("fromdate", pro.getFromDate());
		map.put("overdate", pro.getOverDate());
		map.put("projectName", pro.getProjectName());
		map.put("title", pro.getTitle());
		map.put("describtion", pro.getDescribtion());
		map.put("duty", pro.getDuty());
		map.put("fromdateText", "开始时间：");
		map.put("overdateText", "结束时间：");
		map.put("projectNameText", "项目名称：");
		map.put("titleText", "担任职位：");
		map.put("describtionText", "项目描述：");
		map.put("dutyText", "工作职责：");
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
	    	   ActivityUtils.gotoActivity(this, AddProjectActivity.class);
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
	   positionOfSelectedItem = position;
	   showEditProjectDialog();

   }


	//用于选择修改或者删除
	private void showEditProjectDialog() 
	{
		Builder build=new AlertDialog.Builder(this);
		build.setItems(items, new clickToShowProjectDialogAction());
		build.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		build.show();
	}

	
	   private void modify() {
		    Map<String, Object> projectData = (Map<String, Object>) projectListAdapter.getItem(positionOfSelectedItem);
		   	String id = projectData.get("id").toString();
			String fromDate = projectData.get("fromdate").toString();
			String overDate = projectData.get("overdate").toString();
			String projectName = projectData.get("projectName").toString();
			String title = projectData.get("title").toString();
			String describtion = projectData.get("describtion").toString();
			String duty = projectData.get("duty").toString();
	
			Project pro = new Project(Long.parseLong(id), fromDate, overDate, projectName, title, describtion, duty);
	    	Bundle bundle=new Bundle();  
	 	   	bundle.putParcelable(EXTRA_PROJECT, pro);  
	 	   	Intent intent=new Intent(this, ModifyProjectActivity.class);  
	 	   	intent.putExtras(bundle);  
	 	   	startActivity(intent); 
		
	   }

	   
		private void showConfirmDeleteDialog()
		{
			  AlertDialog.Builder builder = new AlertDialog.Builder(this);
	          builder.setMessage("确定要删除该项？");
	          builder.setCancelable(true);
	          builder.setPositiveButton("确定", new deleteProjectListener());
	          builder.setNegativeButton("取消",  new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
	          builder.show();
		}
	   
		private class clickToShowProjectDialogAction implements DialogInterface.OnClickListener
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
					modify();
					break;
				}
			}
		}
	 
		private class deleteProjectListener implements DialogInterface.OnClickListener
	    {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				Map<String, Object> langData = (Map<String, Object>) projectListAdapter.getItem(positionOfSelectedItem);
				String id = (String) langData.get("id");   
				Project.deleteById(ProjectActivity.this, id);
				ActivityUtils.refreshActivity(ProjectActivity.this);
//		        Language.deleteById(LanguageActivity.this, id);
//		        ActivityUtils.refreshActivity(LanguageActivity.this);
			}
		}
		
}
