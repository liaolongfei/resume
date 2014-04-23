package com.example.resume.edu;

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
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.resume.ActivityUtils;
import com.example.resume.R;
import com.example.resume.model.Education;
import com.example.resume.model.ResumeMsg;
import com.example.resume.utils.MyDate;

public class ModifyEducationActivity extends Activity {

	private TableRow fromDateRow = null;
	private TableRow OverDateRow = null;
	private EditText schoolEditText = null;
	private Spinner degreeSpinner = null;
	private EditText majorEditText = null;
	
	private TextView fromDateText = null;
	private TextView overDateText = null;

	private ArrayAdapter<CharSequence> degreeAdapter = null;
	private Education edu = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_education);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		edu = getEduData();
		prepareElements();
		loadSpinner();
		setElementStatus();
	}
	
	
	/**
	 * 把传递过来的数据转化为education
	 * @return
	 */
	public Education getEduData(){
		Intent in = getIntent();
		Education edu = in.getParcelableExtra(EducationActivity.EXTRA_EDUCATION);
		return edu;
	}
	
	private void prepareElements()
	{
		fromDateRow = (TableRow) findViewById(R.id.from_date_row);
		OverDateRow = (TableRow) findViewById(R.id.over_date_row);
		schoolEditText = (EditText) findViewById(R.id.school);
		degreeSpinner = (Spinner) findViewById(R.id.degree);;
		majorEditText = (EditText) findViewById(R.id.major);
		
		fromDateText = (TextView) findViewById(R.id.from_date);
		overDateText = (TextView) findViewById(R.id.over_date);
		

		fromDateRow.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				showFromDateDialog();
			}
	    	
		});
		OverDateRow.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				showOverDateDialog();
			}
	    	
		});
		
	}
	


	/**
	 * 开始日期对话框
	 */
	private void showFromDateDialog() 
	{
		String date = fromDateText.getText().toString();//每次都获取最新的数据
		showDateDialog(date,  new setFromDateAction());
	
	}
	
	
	/**
	 * 结束日期对话框
	 */
	private void showOverDateDialog() 
	{
		String date = overDateText.getText().toString();//每次都获取最新的数据
        showDateDialog(date,  new setOverDateAction());
	}
	
	
	public void showDateDialog(String date, OnDateSetListener dateListener)
	{
		MyDate d = new MyDate(date);
		Dialog dialog = null;
	    dialog = new DatePickerDialog(this, dateListener, d.getIntegerYear(), d.getIntegerMonth()-1, d.getIntegerDay());  
	    dialog.show();
	}
	
	
	private void loadSpinner() {
		degreeAdapter = ArrayAdapter.createFromResource(this,R.array.degrees_arry, android.R.layout.simple_spinner_item);
		degreeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		degreeSpinner.setAdapter(degreeAdapter);

	}
	
	
	private int getDegreeSpinnerIdByText(String degree)
	{
		String[] degrees = getResources().getStringArray(R.array.degrees_arry);
		for (int i = 0; i< degrees.length; i++)
		{
			if(degrees[i].equals(degree))
			{
				return i;
			}
		}
		return 0;
		
	}


	private void setElementStatus()
	{
		fromDateText.setText(edu.getFromDate());
		overDateText.setText(edu.getOverDate());
		
		int degreeId = this.getDegreeSpinnerIdByText(edu.getDegree());
		degreeSpinner.setSelection(degreeId); //默认选择本科，从0开始，选择第5项
		
		schoolEditText.setText(edu.getSchool());
		majorEditText.setText(edu.getMajor());
		
	}
	
	/**
	 * 点击保存
	 */
	private void save()
	{
		Education newEdu = formDataToEducation();
		if(isValidEducation(newEdu))
		{
			saveToDataBase(newEdu);
			ActivityUtils.gotoActivity(this, EducationActivity.class);
		}
	}
	
	/**
	 * 保存到数据库
	 * @param newEdu
	 */
	private void saveToDataBase(Education newEdu)
	{
		
		Education education = new Education(newEdu, edu.getId());
		education.update(this);
	}
	
	/**
	 * 表单数据转化为education
	 * 
	 * @return
	 */
	public Education formDataToEducation()
	{
		String fromDate = fromDateText.getText().toString().trim();
		String overDate = overDateText.getText().toString().trim();;
		String school = schoolEditText.getText().toString().trim();//学校
		String degree = degreeSpinner.getSelectedItem().toString().trim(); //学历
		String major = majorEditText.getText().toString().trim();;
		return  new Education(fromDate, overDate, school, degree, major);
	}
	
	/**
	 * 判断是否是有效的education
	 * @param edu
	 * @return
	 */
	private boolean isValidEducation(Education edu)
	{
		ResumeMsg rm = edu.isValid();
		if(rm.getFlag() == false)
		{
			ActivityUtils.showTip(this, rm.getMsg());
			return false;
		}
		return true;
	}
	

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.save, menu);
		return true;
	}
	
	 @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId() )
        {
        	case android.R.id.home:
        		ActivityUtils.gotoActivity(this, EducationActivity.class);
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
		  ActivityUtils.gotoActivity(this, EducationActivity.class);
		  return true;
	  }
	  return super.onKeyDown(keyCode, event);
	}

	private class setFromDateAction implements OnDateSetListener
	{

		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
			String date = year + "-" + (monthOfYear+1) + "-" + dayOfMonth;
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
