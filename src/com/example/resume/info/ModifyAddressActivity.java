package com.example.resume.info;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.resume.ActivityUtils;
import com.example.resume.R;
import com.example.resume.evaluation.EvaluationActivity;
import com.example.resume.model.MyAddress;
import com.example.resume.model.SetAddress;
import com.example.resume.prefrence.UserInfoPrefrence;

public class ModifyAddressActivity extends Activity {
	private Spinner provinceSpinner;
	private Spinner citySpinner;
	private Spinner countySpinner;
	private String newAddress;
	// 市，自治区集合

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modify_address);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		loadSpinner();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.save, menu);
		return true;
	}
	
	/**
	 * 点击保存
	 */
	private void save()
	{
		String strProvince = provinceSpinner.getSelectedItem().toString();
		String strCity = citySpinner.getSelectedItem().toString();
		String strCounty = countySpinner.getSelectedItem().toString();
		newAddress = strProvince + "-" + strCity + "-" + strCounty;
		Log.d("resume", newAddress);
		UserInfoPrefrence.setAddressPrefence(newAddress);
		ActivityUtils.gotoActivity(this, InfoActivity.class);
		
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

	private void loadSpinner() {
		provinceSpinner = (Spinner) findViewById(R.id.province_spinner);
		citySpinner = (Spinner) findViewById(R.id.city_spinner);
		countySpinner = (Spinner) findViewById(R.id.county_spinner);
		new SetAddress(this, provinceSpinner, citySpinner, countySpinner);
		
		
	}



}
