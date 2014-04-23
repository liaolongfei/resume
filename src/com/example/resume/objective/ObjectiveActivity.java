package com.example.resume.objective;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.resume.ActivityUtils;
import com.example.resume.MainActivity;
import com.example.resume.R;
import com.example.resume.model.Objective;
import com.example.resume.prefrence.ObjectivePrefrence;

public class ObjectiveActivity extends Activity {
	
    private TextView jobTypeText = null;
    private TextView jobAddressText = null;
    private TextView industryText = null;
    private TextView occupationText = null;
    private TextView dutytimeText = null;
    private TextView salaryText = null;
	
	public final static String EXTRA_OBJECTIVE = "com.example.resume.OBJECTIVE";
	
	
	Objective obj = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_objective);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		obj = getObjective();
		prepareElements();
		initStatus();
		addlistener();
	}
	
	private Objective getObjective()
	{
		String jobType = ObjectivePrefrence.getJobTypePreference();
		String jobAddress = ObjectivePrefrence.getJobAddressPreference();
		String industry = ObjectivePrefrence.getIndustryPreference();
		String occupation = ObjectivePrefrence.getOccupationPreference();
		String dutytime = ObjectivePrefrence.getDutytimetPreference();
		String salary = ObjectivePrefrence.getSalaryPreference();
		return new Objective(jobType, jobAddress, industry, occupation, dutytime, salary);
	}
	
	
	  /**
	   * 对元素进行初始化操作
	   */
	private void prepareElements()
	{
		//jobTypeRow = (TableRow)findViewById(R.id.job_type);
		jobTypeText = (TextView)findViewById(R.id.myjob_type);
		
		//jobAddressRow = (TableRow)findViewById(R.id.job_address);
		jobAddressText = (TextView)findViewById(R.id.myjob_address);
		
		//industryRow = (TableRow)findViewById(R.id.industry);
		industryText = (TextView)findViewById(R.id.myindustry);
		
		//occupationRow = (TableRow)findViewById(R.id.occupation);
		occupationText = (TextView)findViewById(R.id.myoccupation);
		
		//dutytimeRow = (TableRow)findViewById(R.id.dutytime);
		dutytimeText = (TextView)findViewById(R.id.mydutytime);
		
		//salaryRow = (TableRow)findViewById(R.id.salary);
		salaryText = (TextView)findViewById(R.id.mysalary);
	}
	 
	private void initStatus()
	{
		jobTypeText.setText(obj.getJobType());
		jobAddressText.setText(obj.getJobAddress());
		industryText.setText(obj.getIndustry());
		occupationText.setText(obj.getOccupation());
		dutytimeText.setText(obj.getDutytime());
		salaryText.setText(obj.getSalary());
	}
	
	private void addlistener()
	{
		
	}
	
	private void translateDataToActivity()
	{
		 Bundle bundle=new Bundle();  
         bundle.putParcelable(EXTRA_OBJECTIVE, obj);  
         Intent intent=new Intent(this, ModifyObjectiveActivity.class);  
         intent.putExtras(bundle);  
         startActivity(intent); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modify, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch (id)
		{
			case R.id.action_modify:
				translateDataToActivity();
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
		  ActivityUtils.gotoActivity(this, MainActivity.class);
		  return true;
	  }
	  return super.onKeyDown(keyCode, event);
	}

}
