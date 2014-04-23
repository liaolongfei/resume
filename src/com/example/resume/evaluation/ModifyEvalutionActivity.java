package com.example.resume.evaluation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.resume.ActivityUtils;
import com.example.resume.MainActivity;
import com.example.resume.R;
import com.example.resume.prefrence.UserInfoPrefrence;

public class ModifyEvalutionActivity extends Activity {

	String evalution = null;
	String newEvalution = null;
	EditText evalutionEditText = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_evalution);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		Intent in = getIntent();
		evalution = in.getStringExtra(EvaluationActivity.EXTRA_EVALUTION);
		prepareElements();
	}
	
	
	private void prepareElements()
	{
		evalutionEditText = (EditText) findViewById(R.id.newEvalution);
		evalutionEditText.setText(evalution);
	}
	
	private void save()
	{
		newEvalution = evalutionEditText.getText().toString().trim();
		if(newEvalution.equals(""))
		{
			ActivityUtils.showTip(this, "个人评价不能为空");
		}else
		{
			UserInfoPrefrence.setEvaluationPrefrence(newEvalution);
			ActivityUtils.gotoActivity(this, EvaluationActivity.class);
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
        		ActivityUtils.gotoActivity(this, EvaluationActivity.class);
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
		  ActivityUtils.gotoActivity(this, EvaluationActivity.class);
		  return true;
	  }
	  return super.onKeyDown(keyCode, event);
	}

}
