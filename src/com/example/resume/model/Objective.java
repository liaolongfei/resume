package com.example.resume.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Objective implements Parcelable {
	
	private String jobType = null;
	private String jobAddress = null;
	private String industry = null;
	private String occupation = null;
	private String dutytime = null;
	private String salary = null;
	public Objective(String jobType, String jobAddress, String industry,
			String occupation, String dutytime, String salary) {
		this.jobType = jobType;
		this.jobAddress = jobAddress;
		this.industry = industry;
		this.occupation = occupation;
		this.dutytime = dutytime;
		this.salary = salary;
	}
	public String getJobType() {
		return jobType;
	}
	public void setJobType(String jobType) {
		this.jobType = jobType;
	}
	public String getJobAddress() {
		return jobAddress;
	}
	public void setJobAddress(String jobAddress) {
		this.jobAddress = jobAddress;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getDutytime() {
		return dutytime;
	}
	public void setDutytime(String dutytime) {
		this.dutytime = dutytime;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	
	public String getProvince() {
		return jobAddress.split("-")[0];
	}
	
	public String getCity() {
		return jobAddress.split("-")[1];
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeString(this.jobType);
		parcel.writeString(this.jobAddress);
		parcel.writeString(this.industry);
		parcel.writeString(this.occupation);
		parcel.writeString(this.dutytime);
		parcel.writeString(this.salary);
	}
	
	//添加一个静态成员,名为CREATOR,该对象实现了Parcelable.Creator接口  
    public static final Parcelable.Creator<Objective> CREATOR = new Parcelable.Creator<Objective>(){  
          
        //从Parcel中读取数据,返回worker对象  
        @Override  
        public Objective createFromParcel(Parcel source) {  
            return new Objective(source.readString(), source.readString(),source.readString(),source.readString(),source.readString(),source.readString());  
        }  
        @Override  
        public Objective[] newArray(int size) {  
            return new Objective[size];  
        }  
    }; 
	
	
	
	

}
