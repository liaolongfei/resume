package com.example.resume.model;

public class ResumeMsg {
	private boolean flag = true;
	private String msg = null;
	public void put(boolean flag, String msg)
	{
		this.flag = flag;
		this.msg = msg;
	}
	public boolean getFlag() {
		return flag;
	}
	public String getMsg() {
		return msg;
	}
}
