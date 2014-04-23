package com.example.resume.info;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.example.resume.ActivityUtils;
import com.example.resume.R;
import com.example.resume.prefrence.UserInfoPrefrence;

public class ModifyGenderActivity extends Activity {

	RadioGroup genderGroup = null;
	RadioButton radioMale = null;
	RadioButton radioFemale = null;
	String newGender = null;
	String oldGender = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_gender);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		prepareElements();
		
	}
	
	private void prepareElements()
	{
		
		Intent intent = getIntent();
		oldGender = intent.getStringExtra(InfoActivity.EXTRA_GENDER);
		
		radioMale = (RadioButton)findViewById(R.id.radioMale); 
		radioFemale = (RadioButton)findViewById(R.id.radioFemale); 
		
		if(oldGender.equals("男"))
		{
			radioMale.setChecked(true);
		}else
		{
			radioFemale.setChecked(true);
		}

		genderGroup = (RadioGroup)findViewById(R.id.genderGroup);
		genderGroup.setOnCheckedChangeListener(new changeGenderAction());
		
		
		
	}

	private void save()
	{
		UserInfoPrefrence.setGenderPrefence(newGender);
		ActivityUtils.gotoActivity(ModifyGenderActivity.this, InfoActivity.class); 
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
	 
	private class changeGenderAction implements OnCheckedChangeListener
    {

		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			// TODO Auto-generated method stub
			//获取变更后的选中项的ID
			int radioButtonId = group.getCheckedRadioButtonId();
			//根据ID获取RadioButton的实例
			RadioButton rb = (RadioButton)findViewById(radioButtonId);
			newGender = rb.getText().toString();
			
			
		}

		
    	
    }


}
