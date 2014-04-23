package com.example.resume.utils;

import java.util.Calendar;

public class MyDate {
	
	private String year = null;
	private String month = null;
	private String day = null;
	
	public MyDate(String date)
	{
		this.convertToArr(date);
	}
	
	public String getStringYear() {
		return year;
	}
	public String getStringMonth() {
		return month;
	}
	public String getStringDay() {
		return day;
	}
	
	public int getIntegerYear() {
		return Integer.parseInt(year);
	}
	public int getIntegerMonth() {
		return Integer.parseInt(month);
	}
	public int getIntegerDay() {
		return Integer.parseInt(day);
	}
	
	
	private void  convertToArr(String date)
	{
		year = date.split("-")[0];
		month = date.split("-")[1];
		day = date.split("-")[2];
		
	}
	
	public static String  getCurrentDate()
	{
		Calendar c  = Calendar.getInstance();
		int year = c.get(Calendar.YEAR); // 传入年份
		int month =  c.get(Calendar.MONTH)+1; // 传入月份
		int day = c.get(Calendar.DAY_OF_MONTH);// 传入天数
		
		String currentDate = year +"-"+ month +"-" + day;
		return currentDate;
	}
	
}
