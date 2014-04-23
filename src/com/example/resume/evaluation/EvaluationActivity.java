package com.example.resume.evaluation;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.resume.ActivityUtils;
import com.example.resume.MainActivity;
import com.example.resume.R;
import com.example.resume.prefrence.UserInfoPrefrence;

public class EvaluationActivity extends Activity {

	public final static String EXTRA_EVALUTION = "com.example.resume.EVALUTION";
	String evaluationPrefrence = null;
	TextView evalutionText = null;
	private String[] items = new String[] { "修改"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_evaluation);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		evaluationPrefrence = UserInfoPrefrence.getEvaluationPrefrence();
		prepareElements();
		
	}
	
	
	private void prepareElements()
	{
		evalutionText = (TextView) findViewById(R.id.evaluation);
		evalutionText.setText(evaluationPrefrence);
		evalutionText.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				showModifyDialog();
			}
			
		});
	}
	
	private void modifyEvalution()
	{
		Intent in = new Intent(EvaluationActivity.this, ModifyEvalutionActivity.class); 
		in.putExtra(EXTRA_EVALUTION, evaluationPrefrence);
    	startActivityForResult(in, 0);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.modify, menu);
		return true;
	}
	
	
	/**
	 * 显示删除对话框
	 */
	private void showModifyDialog() 
	{
		Builder build=new AlertDialog.Builder(this);
		build.setItems(items, new clickToModifyDialogAction());
		build.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		build.show();
	}
	
	
	 @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch(item.getItemId() )
        {
        	case android.R.id.home:
        		ActivityUtils.gotoActivity(this, MainActivity.class);
                return true; 
        	case R.id.action_modify:
        		modifyEvalution();
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


	private class clickToModifyDialogAction implements DialogInterface.OnClickListener
    {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
			case 0:
				modifyEvalution();
				break;
			}
		}
	}
	
}
