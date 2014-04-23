package com.example.resume.language;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.resume.ActivityUtils;
import com.example.resume.R;
import com.example.resume.edu.EducationActivity;
import com.example.resume.info.InfoActivity;
import com.example.resume.model.Education;
import com.example.resume.model.Language;

public class AddLanguageActivity extends Activity {

	private Spinner languageSpinner = null;
	private Spinner skillSpinner = null;
	private Spinner readwriteSpinner = null;
	private Spinner speaklistenSpinner = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_language);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		prepareElements();
		initStaus();
	}

	
	private void prepareElements() {
		// TODO Auto-generated method stub
		languageSpinner = (Spinner) findViewById(R.id.type);
		skillSpinner = (Spinner) findViewById(R.id.skill);
		readwriteSpinner = (Spinner) findViewById(R.id.readwrite);
		speaklistenSpinner = (Spinner) findViewById(R.id.speaklisten);
	}
	
	
	private void initStaus() {
		// TODO Auto-generated method stub
		loadSpinner(R.array.language_arry, languageSpinner);
		loadSpinner(R.array.skill_arry, skillSpinner);
		loadSpinner(R.array.skill_arry, readwriteSpinner);
		loadSpinner(R.array.skill_arry, speaklistenSpinner);
	}
	
	
	
	private void loadSpinner(int arrId, Spinner spinner) {
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,arrId, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.save, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		 switch(item.getItemId() )
	        {
	        	case android.R.id.home:
	        		ActivityUtils.gotoActivity(this, LanguageActivity.class);
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
		  ActivityUtils.gotoActivity(this, LanguageActivity.class);
		  return true;
	  }
	  return super.onKeyDown(keyCode, event);
	}
	
	private void save()
	{
		Language lang = formDataToLanguage();

		lang.save(this);
		ActivityUtils.gotoActivity(this, LanguageActivity.class);
		
	}
	
	
	private Language formDataToLanguage()
	{
		String type = languageSpinner.getSelectedItem().toString().trim();
		String skill = skillSpinner.getSelectedItem().toString().trim();;
		String readwrite = readwriteSpinner.getSelectedItem().toString().trim();//学校
		String speaklisten = speaklistenSpinner.getSelectedItem().toString().trim(); //学历
		return new Language(type, skill, readwrite, speaklisten);
	}
	



}
