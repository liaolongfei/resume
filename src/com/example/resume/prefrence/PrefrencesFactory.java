package com.example.resume.prefrence;

import android.content.SharedPreferences;


public class PrefrencesFactory {
	
	private static ResumePreferences rf = null;
	
	public static ResumePreferences getUserInfoPreferences(SharedPreferences perferences)
	{
		if(rf == null)
		{
			rf = new ResumePreferences(perferences);
		}
		return rf;
	}
	
	public static ResumePreferences getObjectiveInfoPreferences(SharedPreferences perferences)
	{
		if(rf == null)
		{
			rf = new ResumePreferences(perferences);
		}
		return rf;
	}

}
