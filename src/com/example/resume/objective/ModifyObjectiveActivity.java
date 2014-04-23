package com.example.resume.objective;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.resume.ActivityUtils;
import com.example.resume.MainActivity;
import com.example.resume.R;
import com.example.resume.model.MyAddress;
import com.example.resume.model.Objective;
import com.example.resume.model.SetAddress;
import com.example.resume.prefrence.ObjectivePrefrence;

public class ModifyObjectiveActivity extends Activity {
	private Spinner jobTypeSpinner = null;
	private Spinner provinceSpinner = null;
	private Spinner citySpinner = null;
	private Spinner industrySpinner = null;
	private Spinner occupationSpinner = null;
	private Spinner dutytimeSpinner = null;
	private Spinner salarySpinner = null;
	private Objective obj = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_objective);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		obj = getObjective();
		prepareElements();
		loadJobTypeSpinner();
		setAddress() ;
		loadIndustrySpinner();
		loadOccupationSpinner();
		loadDutytimeSpinner();
		loadSalarySpinner();
		setStatus();
		
	}
	
	private Objective getObjective()
	{
		Intent in = getIntent();
		Objective obj = (Objective)in.getParcelableExtra(ObjectiveActivity.EXTRA_OBJECTIVE);
		
		return obj;
	}
	
	
	private void setStatus()
	{
		int jobTypeId = this.getSpinnerIdByText(R.array.job_type_arry, obj.getJobType());
		jobTypeSpinner.setSelection(jobTypeId);
		

		int provinceId = this.getSpinnerIdByText(R.array.province_item, obj.getProvince());
		provinceSpinner.setSelection(provinceId);
		
		int cityId = this.getSpinnerIdByText(MyAddress.city[provinceId], obj.getCity());
		citySpinner.setSelection(cityId);
		
		
		int industryId = this.getSpinnerIdByText(R.array.industry_arry, obj.getIndustry());
		industrySpinner.setSelection(industryId);
		
		int occupationId = this.getSpinnerIdByText(R.array.occupation_arry, obj.getOccupation());
		occupationSpinner.setSelection(occupationId);
		
		int dutytimeId = this.getSpinnerIdByText(R.array.dutytime_arry, obj.getDutytime());
		dutytimeSpinner.setSelection(dutytimeId);
		
		int salaryId = this.getSpinnerIdByText(R.array.salary_arry, obj.getSalary());
		salarySpinner.setSelection(salaryId);
	}
	
	
	private int getSpinnerIdByText(int source,String target)
	{
		String[] items = getResources().getStringArray(source);
		for (int i = 0; i< items.length; i++)
		{
			if(items[i].equals(target))
			{
				return i;
			}
		}
		return 0;
		
	}
	

	private void prepareElements() {
		// TODO Auto-generated method stub
		jobTypeSpinner = (Spinner) findViewById(R.id.job_type);
		provinceSpinner = (Spinner) findViewById(R.id.province);
		citySpinner = (Spinner) findViewById(R.id.city);
		industrySpinner = (Spinner) findViewById(R.id.industry);
		occupationSpinner = (Spinner) findViewById(R.id.occupation);
		dutytimeSpinner = (Spinner) findViewById(R.id.dutytime);
		salarySpinner = (Spinner) findViewById(R.id.salary);
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
		switch (id)
		{
			case R.id.action_save:
				save();
				return true;
			case android.R.id.home:
				ActivityUtils.gotoActivity(this, MainActivity.class);
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
	
	@Override 
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	  if(keyCode == KeyEvent.KEYCODE_BACK){ 
		  ActivityUtils.gotoActivity(this, ObjectiveActivity.class);
		  return true;
	  }
	  return super.onKeyDown(keyCode, event);
	}
	
	private void save()
	{
		Objective newObj = formDataToObjective();
		ObjectivePrefrence.setObjectivePreference(newObj);
		ActivityUtils.gotoActivity(this, ObjectiveActivity.class);
	}
	
	
	private Objective formDataToObjective()
	{
		String jobType = jobTypeSpinner.getSelectedItem().toString();
		String province = provinceSpinner.getSelectedItem().toString();
		String city = citySpinner.getSelectedItem().toString();
		String industry = industrySpinner.getSelectedItem().toString();
		String occupation = occupationSpinner.getSelectedItem().toString();
		String dutytime = dutytimeSpinner.getSelectedItem().toString();
		String salary = salarySpinner.getSelectedItem().toString();
		
		String address = province +"-"+ city;
		
		return new Objective(jobType, address, industry, occupation, dutytime, salary);
	}
	
	
	private void loadJobTypeSpinner() {
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.job_type_arry, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		jobTypeSpinner.setAdapter(adapter);
	}
	
	private void setAddress() {

		new SetAddress(this,provinceSpinner,citySpinner);
	 
	 } 
	
	private void loadIndustrySpinner() {
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.industry_arry, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		industrySpinner.setAdapter(adapter);
	}
	
	private void loadOccupationSpinner() {
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.occupation_arry, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		occupationSpinner.setAdapter(adapter);
	}
	
	private void loadDutytimeSpinner() {
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.dutytime_arry, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		dutytimeSpinner.setAdapter(adapter);
	}
	
	private void loadSalarySpinner() {
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.salary_arry, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		salarySpinner.setAdapter(adapter);
	}
	
}
