package com.example.resume.prefrence;

import java.util.Map;

import android.content.SharedPreferences;

public class ResumePreferences {
	
	SharedPreferences perferences = null;
	
	
	public ResumePreferences(SharedPreferences perferences)
	{
		this.perferences = perferences;
		
	}

	/**
	 * 判断某个key是否存在
	 * @param key
	 * @return
	 */
	public boolean isPreferencesExist(String key)
	{
		String flag = this.getPreference(key);
		if(flag == null || flag.equals("null"))
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	/**
	 * 插入key-value
	 * @param key
	 * @param value
	 */
	public void insertPreference(String key , String value)
	{
		perferences.edit().putString(key, value).commit();  
	}
	
	
	/**
	 * 通过key，获取value
	 * @param key
	 * @return
	 */
	public String getPreference(String key)
	{
		return perferences.getString(key, null);
	}
	
	public void getAllPreferenceValue()
	{
		 Map<String, ?> map = perferences.getAll();
	}
	
}
