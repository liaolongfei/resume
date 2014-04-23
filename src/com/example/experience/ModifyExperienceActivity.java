package com.example.experience;

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

import com.example.resume.ActivityUtils;
import com.example.resume.R;
import com.example.resume.model.Experience;
import com.example.resume.utils.MyDate;

public class ModifyExperienceActivity extends Activity {
	
	private Experience exp;
	private TableRow fromDateRow;
	private TableRow OverDateRow ;
	private EditText companyEditText = null;
	private EditText occupationEditText = null;
	private EditText describtionEditText = null;
	
	private TextView fromDateText ;
	private TextView overDateText ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_experience);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		exp = getExpData();
		prepareElements();
		initStatus();
		addListener();
	}
	
	private Experience getExpData()
	{
		Intent in = getIntent();
		Experience exp = in.getParcelableExtra(ExperiencesActivity.EXTRA_EXPRIENCE);
		return exp;
	}

	private void prepareElements() {
		fromDateRow = (TableRow) findViewById(R.id.from_date_row);
		OverDateRow = (TableRow) findViewById(R.id.over_date_row) ;
		companyEditText = (EditText) findViewById(R.id.company);
		occupationEditText = (EditText) findViewById(R.id.occupation);
		describtionEditText = (EditText) findViewById(R.id.describtion);
		
		fromDateText = (TextView) findViewById(R.id.fromdate) ;
		overDateText = (TextView) findViewById(R.id.overdate);
		
	}

	private void initStatus() {
		fromDateText.setText(exp.getFromDate());
		overDateText.setText(exp.getOverDate());
		companyEditText.setText(exp.getCompany());
		occupationEditText.setText(exp.getOccupation());
		describtionEditText.setText(exp.getDescribtion());
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
		  ActivityUtils.gotoActivity(this, ExperiencesActivity.class);
		  return true;
	  }
	  return super.onKeyDown(keyCode, event);
	}

	
	private void save() {
		Experience newExp = formDataToExp();
		if(newExp.isValid().getFlag() == true)
		{
			newExp.update(this);
			ActivityUtils.gotoActivity(this, ExperiencesActivity.class);
		}else
		{
			ActivityUtils.showTip(this, newExp.isValid().getMsg());
		}
		
	}

	private Experience formDataToExp() {
		String fromDate = fromDateText.getText().toString().trim();
		String overDate = overDateText.getText().toString().trim();
		String company = companyEditText.getText().toString().trim();
		String occupation = occupationEditText.getText().toString().trim();
		String describtion = describtionEditText.getText().toString().trim();
		return new Experience(exp.getId(),fromDate, overDate, company, occupation, describtion);
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
