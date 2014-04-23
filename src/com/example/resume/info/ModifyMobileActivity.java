package com.example.resume.info;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.resume.ActivityUtils;
import com.example.resume.R;
import com.example.resume.prefrence.UserInfoPrefrence;
import com.example.resume.utils.MyStringUtils;

public class ModifyMobileActivity extends Activity {

	EditText mobileEditText = null;
	String newMobile  = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_mobile);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		prepareElements();
		
	}
	
	private void prepareElements()
	{
		mobileEditText = (EditText) findViewById(R.id.newMobile);
		
	}

	/**
	 * 点击保存
	 */
	private void save()
	{
		newMobile = mobileEditText.getText().toString().trim();
		
		if(newMobile != null && !newMobile.equals("") && MyStringUtils.isValidMobile(newMobile))
		{
			UserInfoPrefrence.setMobilePrefence(newMobile);
			ActivityUtils.gotoActivity(ModifyMobileActivity.this, InfoActivity.class); 
		}else
		{
			ActivityUtils.showTip(this,"请输入有效的手机号码");
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
