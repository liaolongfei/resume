package com.example.resume.project;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.experience.ExperiencesActivity;
import com.example.resume.ActivityUtils;
import com.example.resume.R;
import com.example.resume.model.Experience;
import com.example.resume.model.Project;
import com.example.resume.utils.MyDate;

public class ModifyProjectActivity extends Activity {
	
	private Project pro;
	private TableRow fromDateRow;
	private TableRow OverDateRow ;
	private EditText projectNameEditText = null;
	private EditText titleEditText = null;
	private EditText projectDescribtionEditText = null;
	private EditText dutyEditText = null;
	
	private TextView fromDateText ;
	private TextView overDateText ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_project);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		pro = getProjectData();
		prepareElements();
		initStatus();
		addListener();
	}
	
	private Project getProjectData()
	{
		Intent in = getIntent();
		Project pro = in.getParcelableExtra(ProjectActivity.EXTRA_PROJECT);
		return pro;
	}

	private void prepareElements() {
		fromDateRow = (TableRow) findViewById(R.id.from_date_row);
		OverDateRow = (TableRow) findViewById(R.id.over_date_row) ;
		projectNameEditText = (EditText) findViewById(R.id.projectname);
		titleEditText = (EditText) findViewById(R.id.title);
		projectDescribtionEditText = (EditText) findViewById(R.id.project_describtion);
		dutyEditText = (EditText) findViewById(R.id.duty);
		
		fromDateText = (TextView) findViewById(R.id.fromdate) ;
		overDateText = (TextView) findViewById(R.id.overdate);
		
	}

	private void initStatus() {
		fromDateText.setText(pro.getFromDate());
		overDateText.setText(pro.getOverDate());
		projectNameEditText.setText(pro.getProjectName());
		titleEditText.setText(pro.getTitle());
		projectDescribtionEditText.setText(pro.getDescribtion());
		dutyEditText.setText(pro.getDuty());
	}

	private void addListener() {
		fromDateRow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showFromDateDialog();
			}
		});
		OverDateRow.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showOverDateDialog();
			}
		});
		
	}
	
	/**
	 * 开始日期对话框
	 */
	private void showFromDateDialog() 
	{
		String date = fromDateText.getText().toString();
        showDateDialog(date, new setFromDateAction());
	}
	

	/**
	 * 显示对话框
	 * @param date
	 * @param dateListener
	 */
	private void showDateDialog(String date, OnDateSetListener dateListener)
	{
		MyDate d = new MyDate(date);

		Dialog dialog = null;
	    dialog = new DatePickerDialog(this, dateListener, d.getIntegerYear(), d.getIntegerMonth()-1, d.getIntegerDay());  
        dialog.show();
	}
	

	/**
	 * 结束日期对话框
	 */
	private void showOverDateDialog() 
	{
		
		String date = overDateText.getText().toString();
		showDateDialog(date, new setOverDateAction());
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.save, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch(id)
		{
			case android.R.id.home:
				ActivityUtils.gotoActivity(this, ExperiencesActivity.class);
				return true;
			case R.id.action_save:
				save();
				return true;
			default:
				return super.onOptionsItemSelected(item);	
		}
	}
	
	
	@Override 
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	  if(keyCode == KeyEvent.KEYCODE_BACK){ 
		  ActivityUtils.gotoActivity(this, ProjectActivity.class);
		  return true;
	  }
	  return super.onKeyDown(keyCode, event);
	}

	private void save() {
		Project newProject = formDataToProject();
		if(newProject.isValid().getFlag() == true)
		{
			newProject.update(this);
			ActivityUtils.gotoActivity(this, ProjectActivity.class);
		}else
		{
			ActivityUtils.showTip(this, newProject.isValid().getMsg());
		}
		
	}

	private Project formDataToProject() {
		String fromDate = fromDateText.getText().toString().trim();
		String overDate = overDateText.getText().toString().trim();
		String projectName = projectNameEditText.getText().toString().trim();
		String title = titleEditText.getText().toString().trim();
		String projectDescribtion = projectDescribtionEditText.getText().toString().trim();
		String duty = dutyEditText.getText().toString().trim();
		return new Project(pro.getId(),fromDate, overDate, projectName, title, projectDescribtion, duty);
	}

	private class setFromDateAction implements OnDateSetListener
	{

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
			String date = year + "-" + (monthOfYear+1)  + "-" + dayOfMonth;
            fromDateText.setText(date);
            
		}
	}
	private class setOverDateAction implements OnDateSetListener
	{

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
			String date = year + "-" + (monthOfYear+1) + "-" + dayOfMonth;
			overDateText.setText(date);
            
		}
		
	}

}
