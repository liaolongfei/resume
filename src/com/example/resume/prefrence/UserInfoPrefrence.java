package com.example.resume.prefrence;

import com.example.resume.model.ResumeMsg;
import com.example.resume.utils.MyStringUtils;

public class UserInfoPrefrence {
	
	public static  ResumePreferences preferences = null;//new ResumePreferences();

	public static String getNamePrefence()
	{
		String name = preferences.getPreference(PrefrencesKeys.name);

		if(name == null || name.equals(""))
		{
			preferences.insertPreference(PrefrencesKeys.name, PrefrencesValues.name);
			name = PrefrencesValues.name;
		}
		return name;
	}
	
	public static String getGenderPrefence()
	{
		String gender = preferences.getPreference(PrefrencesKeys.gender);
		if(gender == null || gender.equals(""))
		{
			preferences.insertPreference(PrefrencesKeys.gender, PrefrencesValues.gender);
			gender = PrefrencesValues.gender;
		}
		return gender;
	}
	
	
	public static String getBirthdayPrefence()
	{
		String birthday = preferences.getPreference(PrefrencesKeys.birthday);
		if(birthday == null || birthday.equals(""))
		{
			preferences.insertPreference(PrefrencesKeys.birthday, PrefrencesValues.birthday);
			birthday = PrefrencesValues.birthday;
		}
		return birthday;
	}
	
	public static String getMobilePrefence()
	{
		String mobile = preferences.getPreference(PrefrencesKeys.mobile);
		if(mobile == null || mobile.equals(""))
		{
			preferences.insertPreference(PrefrencesKeys.mobile, PrefrencesValues.mobile);
			mobile = PrefrencesValues.mobile;
		}
		return mobile;
	}
	
	
	public static String getEmailPrefence()
	{
		String email = preferences.getPreference(PrefrencesKeys.email);
		if(email == null || email.equals(""))
		{
			preferences.insertPreference(PrefrencesKeys.email, PrefrencesValues.email);
			email = PrefrencesValues.email;
		}
		return email;
	}
	
	
	public static String getAddressPrefence()
	{
		String address = preferences.getPreference(PrefrencesKeys.address);
		if(address == null || address.equals(""))
		{
			preferences.insertPreference(PrefrencesKeys.address, PrefrencesValues.address);
			address = PrefrencesValues.address;
		}
		return address;
	}
	
	public static String getWorkyearPrefence()
	{
		String workyear = preferences.getPreference(PrefrencesKeys.workyear);
		if(workyear == null || workyear.equals(""))
		{
			preferences.insertPreference(PrefrencesKeys.workyear, PrefrencesValues.workyear);
			workyear = PrefrencesValues.workyear;
		}
		return workyear;
	}
	
	
	public static String getEvaluationPrefrence()
	{
		String evaluationPrefrence = preferences.getPreference(PrefrencesKeys.evalution);
		
		if(evaluationPrefrence == null || evaluationPrefrence.equals(""))
		{
			preferences.insertPreference(PrefrencesKeys.evalution, PrefrencesValues.evalution);
			evaluationPrefrence = PrefrencesValues.evalution;
		}
		
		return evaluationPrefrence;
	}

	

	public static void setNamePrefence(String name)
	{

		preferences.insertPreference(PrefrencesKeys.name, name);

	}
	
	public static void setGenderPrefence(String gender)
	{

		preferences.insertPreference(PrefrencesKeys.gender, gender);

	}
	
	
	public static void setBirthdayPrefence(String birthday)
	{

		preferences.insertPreference(PrefrencesKeys.birthday, birthday);

	}
	
	public static void setMobilePrefence(String mobile)
	{

		preferences.insertPreference(PrefrencesKeys.mobile, mobile);

	}
	
	
	public static void setEmailPrefence(String email)
	{

		preferences.insertPreference(PrefrencesKeys.email, email);

	}
	
	
	public static void setAddressPrefence(String address)
	{

		preferences.insertPreference(PrefrencesKeys.address, address);
	
	}
	
	public static void setWorkyearPrefence(String workyear)
	{

		preferences.insertPreference(PrefrencesKeys.workyear, workyear);
	
	}
	
	public static void setEvaluationPrefrence(String evalution)
	{

		preferences.insertPreference(PrefrencesKeys.evalution, evalution);

	}

	
	public static ResumeMsg isValidEmail(String email)
	{

		ResumeMsg rsg = new ResumeMsg();
		
		if(!email.equals("") && MyStringUtils.isValidEmail(email))
		{
			UserInfoPrefrence.setEmailPrefence(email);
			rsg.put(true, null);
 
		}else 
		{
			rsg.put(false, "请输入正确的邮箱");
		}
		
		
		return null;

	}
	

	
	
}
