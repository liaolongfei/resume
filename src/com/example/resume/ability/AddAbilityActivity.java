package com.example.resume.ability;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.resume.ActivityUtils;
import com.example.resume.R;
import com.example.resume.model.Ability;

public class AddAbilityActivity extends Activity {

	private EditText skillEditText = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_add_ability);
		prepareElements();
		initStaus();

	}



	private void prepareElements() {
		skillEditText = (EditText) findViewById(R.id.skill);
		
	}
	private void initStaus() {
		
		
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
	        		ActivityUtils.gotoActivity(this, AbilityActivity.class);
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
		  ActivityUtils.gotoActivity(this, AbilityActivity.class);
		  return true;
	  }
	  return super.onKeyDown(keyCode, event);
	}


	private void save()
	{
		Ability ability = formDataToAbility();

		if(ability.isValid().getFlag() == true)
		{
			ability.save(this);
			ActivityUtils.gotoActivity(this, AbilityActivity.class);
		}

		
	}
	
	
	private Ability formDataToAbility()
	{
		String skill = skillEditText.getText().toString().trim();;
		return new Ability(skill);
	}
	



}
