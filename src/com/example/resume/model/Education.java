package com.example.resume.model;

import java.util.List;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.resume.dao.DAOFactory;
import com.example.resume.dao.EducationDAO;
import com.example.resume.utils.MyDate;
import com.example.resume.utils.MyStringUtils;

public class Education  implements Parcelable {
	private Long id = null;
	private String fromDate = null;
	private String overDate = null;
	private String school = null;// 学校
	private String degree = null; // 学历
	private String major = null;

	private static DAOFactory daoFactory = DAOFactory.getInstance();

	public Education(Education edu, Long id) {
		this.id = id;
		this.fromDate = edu.getFromDate();
		this.overDate = edu.getOverDate();
		this.school = edu.getSchool();
		this.degree = edu.getDegree();
		this.major = edu.getMajor();

	}

	public Education(Long id, String fromDate, String overDate, String school,
			String degree, String major) {
		this(fromDate, overDate, school, degree, major);
		this.id = id;
	}

	public Education(String fromDate, String overDate, String school,
			String degree, String major) {
		this.fromDate = fromDate;
		this.overDate = overDate;
		this.school = school;
		this.degree = degree;
		this.major = major;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFromDate() {
		return fromDate;
	}

	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}

	public String getOverDate() {
		return overDate;
	}

	public void setOverDate(String overDate) {
		this.overDate = overDate;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public void writeToParcel(Parcel parcel, int flags) {
		parcel.writeLong(this.id);
		parcel.writeString(this.fromDate);
		parcel.writeString(this.overDate);
		parcel.writeString(this.school);
		parcel.writeString(this.degree);
		parcel.writeString(this.major);
	}
	
	//添加一个静态成员,名为CREATOR,该对象实现了Parcelable.Creator接口  
    public static final Parcelable.Creator<Education> CREATOR = new Parcelable.Creator<Education>(){  
          
        //从Parcel中读取数据,返回worker对象  
        @Override  
        public Education createFromParcel(Parcel source) {  
            return new Education(source.readLong(),source.readString(), source.readString(),source.readString(),source.readString(),source.readString());  
        }  
        @Override  
        public Education[] newArray(int size) {  
            return new Education[size];  
        }  
    }; 
	
	
	public void save(Context context) {
		EducationDAO dao = null;
		try {
			dao = daoFactory.getEducationDAO(context);
			dao.insert(this);
		} catch (Exception e) {

		} finally {
			dao.close();
		}
	}

	public void delete(Context context) {
		EducationDAO dao = null;
		try {
			dao = daoFactory.getEducationDAO(context);
			dao.delete(this);
		} catch (Exception e) {

		} finally {
			dao.close();
		}
	}
	
	public void update(Context context) {
		EducationDAO dao = null;
		try {
			dao = daoFactory.getEducationDAO(context);
			dao.update(this);
		} catch (Exception e) {

		} finally {
			dao.close();
		}
	}

	public static void deleteById(Context context, String id) {
		EducationDAO dao = null;
		try {
			dao = daoFactory.getEducationDAO(context);
			dao.deleteById(id);
		} catch (Exception e) {

		} finally {
			dao.close();
		}
	}

	public static List<Education> getAll(Context context) {
		EducationDAO dao = null;
		try {
			dao = daoFactory.getEducationDAO(context);
			return dao.getAll();
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
	
	
	/**
	 * 判断education是否有效
	 * @param edu
	 * @return
	 */
	public ResumeMsg isValid()
	{
		ResumeMsg rm = new ResumeMsg();
		String errorMsg = null;
		//判断结束日期是否大于开始日期
		if(MyStringUtils.compareDate(getFromDate(), getOverDate())>=0)
		{
			errorMsg = "起始时间必须小于结束时间";
			rm.put(false, errorMsg);

		}
		
		//判断开始日期是否大于今天
		if(MyStringUtils.compareDate(getFromDate(), MyDate.getCurrentDate())>0)
		{
			errorMsg = "起始时间不能大于今天";
			rm.put(false, errorMsg);
		}
		
		//判断结束日期是否大于今天
		if(MyStringUtils.compareDate(getOverDate(), MyDate.getCurrentDate())>0)
		{
			errorMsg = "结束时间不能大于今天";
			rm.put(false, errorMsg);
		}
		
		if(getSchool().equals(""))
		{
			errorMsg = "学校不能为空";
			rm.put(false, errorMsg);
		}
		return rm;
	}
	
	

}
