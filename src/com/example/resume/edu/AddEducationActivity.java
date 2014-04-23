package com.example.resume.edu;

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
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.resume.ActivityUtils;
import com.example.resume.R;
import com.example.resume.ability.AbilityActivity;
import com.example.resume.model.Education;
import com.example.resume.model.ResumeMsg;
import com.example.resume.utils.MyDate;
import com.example.resume.utils.MyStringUtils;




public class AddEducationActivity extends Activity {

	private TableRow fromDateRow = null;
	private TableRow OverDateRow = null;
	private EditText schoolEditText = null;
	private Spinner degreeSpinner = null;
	private EditText majorEditText = null;
	
	private TextView fromDateText = null;
	private TextView overDateText = null;


	private ArrayAdapter<CharSequence> degreeAdapter = null;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_education);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		prepareElements();
		loadSpinner();
		
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
	
	 /**
	  * 初始化元素以及添加监听
	  */
	private void prepareElements()
	{
		fromDateRow = (TableRow) findViewById(R.id.from_date_row);
		OverDateRow = (TableRow) findViewById(R.id.over_date_row);
		schoolEditText = (EditText) findViewById(R.id.school);
		degreeSpinner = (Spinner) findViewById(R.id.degree);
		majorEditText = (EditText) findViewById(R.id.major);
		
		fromDateText = (TextView) findViewById(R.id.from_date);
		overDateText = (TextView) findViewById(R.id.over_date);
		
		
		
		fromDateText.setText(MyDate.getCurrentDate());
		overDateText.setText(MyDate.getCurrentDate());
		
		
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
	
	
	
	
	private void loadSpinner() {
		degreeAdapter = ArrayAdapter.createFromResource(this,R.array.degrees_arry, android.R.layout.simple_spinner_item);
		degreeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		degreeSpinner.setAdapter(degreeAdapter);
		degreeSpinner.setSelection(5); //默认选择本科，从0开始，选择第5项
		// 添加监听
//		degreeSpinner.setOnItemSelectedListener(new DegreeOnItemSelectedListener());

	}


	/**
	 * 点击保存
	 */
	private void save()
	{
		Education edu = formDataToEducation();
		if(isValidEducation(edu))
		{
			saveEducation(edu);
			ActivityUtils.gotoActivity(this, EducationActivity.class);
		}
	}
	
	/**
	 * 存储到数据库
	 * @param edu
	 */
	private void saveEducation(Education edu)
	{
		edu.save(this);
		
	}
	
	/**
	 * 把表单数据转化为education
	 * @return
	 */
	private Education formDataToEducation()
	{
		String fromDate = fromDateText.getText().toString().trim();
		String overDate = overDateText.getText().toString().trim();;
		String school = schoolEditText.getText().toString().trim();//学校
		String degree = degreeSpinner.getSelectedItem().toString().trim(); //学历
		String major = majorEditText.getText().toString().trim();
		
		return new Education(fromDate, overDate, school, degree, major);
	}
	
	/**
	 * 判断education是否有效
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
