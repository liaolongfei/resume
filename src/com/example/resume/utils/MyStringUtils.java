package com.example.resume.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyStringUtils {
	
	public static boolean isValidName(String name)
	{
		 String regEx="[`~!@#$%^&*()+=|{}':;',//[//].<>/?~£¡@#£¤%¡­¡­&*£¨£©¡ª¡ª+|{}¡¾¡¿¡®£»£º¡±¡°¡¯¡££¬¡¢£¿1234567890]"; 
         Pattern p  =  Pattern.compile(regEx);      
         Matcher m  =  p.matcher(name);     
         
         if(m.find())
         {
        	 return false;
         }
         else
         {
        	 return true;
         }
	}

	
	
	public static boolean isValidMobile(String mobiles)
	{
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");  
		  
		Matcher m = p.matcher(mobiles);  
		  
		return m.matches();  
       
	}

	
	public static boolean isValidEmail(String email)
	{
		boolean flag = false;
		  try{
		   String check = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		   Pattern regex = Pattern.compile(check);
		   Matcher matcher = regex.matcher(email);
		   flag = matcher.matches();
		  }catch(Exception e){
		   flag = false;
		  }
		  
		  return flag;
       
	}
	
	
	public static int compareDate(String date1, String date) {
		int flag = 0;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
        try {
            Date dt1 = df.parse(date1);
            Date dt2 = df.parse(date);
            if (dt1.getTime() > dt2.getTime()) {
                flag = 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                flag = -1;
            } else if (dt1.getTime() == dt2.getTime()){
            	flag = 0;
            }
            return flag;

        } catch (Exception exception) {
            exception.printStackTrace();
        }
		return flag;
     }
	

	

}
