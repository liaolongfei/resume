package com.example.resume.project;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
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

import com.example.resume.ActivityUtils;
import com.example.resume.MainActivity;
import com.example.resume.R;
import com.example.resume.model.Project;
import com.example.resume.utils.MyDate;

public class AddProjectActivity extends Activity {
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
		prepareElements();
		initStatus();
		addListener();
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
		fromDateText.setText(MyDate.getCurrentDate());
		overDateText.setText(MyDate.getCurrentDate());
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
	 * 结束日期对话框
	 */
	private void showOverDateDialog() 
	{
		
		String date = overDateText.getText().toString();
		showDateDialog(date, new setOverDateAction());
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
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.save, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
	       case R.id.action_save:
	    	   save();
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
		  ActivityUtils.gotoActivity(this, ProjectActivity.class);
		  return true;
	  }
	  return super.onKeyDown(keyCode, event);
	}
	private void save() {
		Project pro = formDataToProject();
		if(pro.isValid().getFlag() == true)
		{
			pro.save(this);
			ActivityUtils.gotoActivity(this, ProjectActivity.class);
		}else
		{
			ActivityUtils.showTip(this, pro.isValid().getMsg());
		}
		
	}
	
	private Project formDataToProject() {
		String fromDate = fromDateText.getText().toString().trim();
		String overDate = overDateText.getText().toString().trim();
		String projectName = projectNameEditText.getText().toString().trim();
		String title = titleEditText.getText().toString().trim();
		String projectDescribtion = projectDescribtionEditText.getText().toString().trim();
		String duty = dutyEditText.getText().toString().trim();
		return new Project(fromDate, overDate, projectName, title, projectDescribtion, duty);
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
