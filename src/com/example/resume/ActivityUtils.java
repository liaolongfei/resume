package com.example.resume;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ActivityUtils{
	
	public static void startActivity(Context context, Class<?> cls)
	{
		
		Intent in = new Intent(context, cls);
		context.startActivity(in);
	}

	
   public static void gotoActivity(Activity activity, Class<?> preActivity)
   {
	   startActivity(activity, preActivity);
	   activity.finish();
   }
   
   public static void refreshActivity(Activity activity)
   {
	   activity.finish();
	   startActivity(activity, activity.getClass());
   }
   
   public static void showTip(Activity activity, String msg)
   {
	   Toast.makeText(activity, msg,Toast.LENGTH_SHORT).show();
   }
   
	
}
