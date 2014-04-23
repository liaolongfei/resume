package com.example.resume.info;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.resume.ActivityUtils;
import com.example.resume.R;
import com.example.resume.model.ResumeMsg;
import com.example.resume.prefrence.UserInfoPrefrence;

public class ModifyEmaiActivity extends Activity {

	EditText emailEditText = null;
	String oldEmail  = null;
	String newEmail  = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_email);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		prepareElements();
		
	}
	
	private void prepareElements()
	{
		Intent intent = getIntent();
		oldEmail = intent.getStringExtra(InfoActivity.EXTRA_EMAIL);
		emailEditText = (EditText) findViewById(R.id.newEmail);
		emailEditText.setText(oldEmail);
	}

	/**
	 * µã»÷±£´æ
	 */
	private void save()
	{
		newEmail = emailEditText.getText().toString().trim();
		
		ResumeMsg msg = UserInfoPrefrence.isValidEmail(newEmail);
		if(msg.getFlag() == true)
		{
			UserInfoPrefrence.setEmailPrefence(newEmail);
			ActivityUtils.gotoActivity(ModifyEmaiActivity.this, InfoActivity.class);
		}else if(newEmail.equals(oldEmail.trim()))
		{
			ActivityUtils.gotoActivity(ModifyEmaiActivity.this, InfoActivity.class);
		}
		else
		{
			ActivityUtils.showTip(this, msg.getMsg());
		}
		
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
        		 ActivityUtils.gotoActivity(this, InfoActivity.class);
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
		  ActivityUtils.gotoActivity(this, InfoActivity.class);
		  return true;
	  }
	  return super.onKeyDown(keyCode, event);
	}

}
