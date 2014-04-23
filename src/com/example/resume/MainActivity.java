package com.example.resume;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TableRow;

import com.example.experience.ExperiencesActivity;
import com.example.resume.ability.AbilityActivity;
import com.example.resume.edu.EducationActivity;
import com.example.resume.evaluation.EvaluationActivity;
import com.example.resume.info.InfoActivity;
import com.example.resume.language.LanguageActivity;
import com.example.resume.objective.ObjectiveActivity;
import com.example.resume.prefrence.ObjectivePrefrence;
import com.example.resume.prefrence.PrefrencesFactory;
import com.example.resume.prefrence.UserInfoPrefrence;
import com.example.resume.project.ProjectActivity;

public class MainActivity extends Activity {
	
	TableRow infoRow = null;
	TableRow abilityRow = null;
	TableRow eduRow = null;
	TableRow experienceRow = null;
	TableRow projectRow = null;
	TableRow evalutionRow = null;
	TableRow languageRow = null;
	TableRow objectiveRow = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		prepareElements();
		initPreferences();

	}


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	
	 @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // TODO Auto-generated method stub
        if(item.getItemId() == R.id.test)
        {
        	ActivityUtils.gotoActivity(this, TestActivity.class);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
		
	 
	 private void showDialog()
	 {
		 Resources res =getResources();
		 String[] occus=res.getStringArray(R.array.industry_arry);
		 
		 new AlertDialog.Builder(this).setTitle("复选框").setMultiChoiceItems(
				 occus, null, null)
				 .setPositiveButton("确定", null)
				 .setNegativeButton("取消", null).show();

	 }
	
	private void prepareElements()
	{
		infoRow = (TableRow) findViewById(R.id.personalinfo);
		infoRow.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				
	        	ActivityUtils.gotoActivity(MainActivity.this, InfoActivity.class);
			}
		});
		
		
		abilityRow = (TableRow) findViewById(R.id.ability);
		abilityRow.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				
	        	ActivityUtils.gotoActivity(MainActivity.this, AbilityActivity.class);
			}
		});
		
		
		eduRow = (TableRow) findViewById(R.id.education);
		eduRow.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				
	        	ActivityUtils.gotoActivity(MainActivity.this, EducationActivity.class);
			}
		});
		
		experienceRow = (TableRow) findViewById(R.id.experience);
		experienceRow.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				
	        	ActivityUtils.gotoActivity(MainActivity.this, ExperiencesActivity.class);
			}
		});
		
		projectRow = (TableRow) findViewById(R.id.project);
		projectRow.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				
	        	ActivityUtils.gotoActivity(MainActivity.this, ProjectActivity.class);
			}
		});
		
		evalutionRow = (TableRow) findViewById(R.id.evaluation);
		evalutionRow.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				
	        	ActivityUtils.gotoActivity(MainActivity.this, EvaluationActivity.class);
			}
		});
		
		languageRow = (TableRow) findViewById(R.id.type);
		languageRow.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				
	        	ActivityUtils.gotoActivity(MainActivity.this, LanguageActivity.class);
			}
		});
		
		objectiveRow = (TableRow) findViewById(R.id.objective);
		objectiveRow.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				
	        	ActivityUtils.gotoActivity(MainActivity.this, ObjectiveActivity.class);
			}
		});
		
		
	}
	
	
	private void initPreferences()
	{
		SharedPreferences perferences = getSharedPreferences("resume", Context.MODE_PRIVATE); 
		UserInfoPrefrence.preferences = PrefrencesFactory.getUserInfoPreferences(perferences);
		ObjectivePrefrence.preferences = PrefrencesFactory.getUserInfoPreferences(perferences);
	}
	
	

}
