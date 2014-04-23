package com.example.resume.model;

import java.util.List;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.resume.dao.DAOFactory;
import com.example.resume.dao.ExperienceDao;
import com.example.resume.utils.MyDate;
import com.example.resume.utils.MyStringUtils;

public class Experience  implements Parcelable {
	private Long id;
	private String fromDate;
	private String overDate;
	private String company;
	private String occupation;
	private String describtion;
	
	private static DAOFactory daoFactory = DAOFactory.getInstance();
	
	public Experience(Long id, String fromDate, String overDate,
			String company, String occupation, String describtion) {
		this(fromDate, overDate, company, occupation, describtion);
		this.id = id;
	}

	public Experience(String fromDate, String overDate, String company,
			String occupation, String describtion) {
		
		this.fromDate = fromDate;
		this.overDate = overDate;
		this.company = company;
		this.occupation = occupation;
		this.describtion = describtion;
	}
	
	public Experience(Long id, Experience exp) {
		this.id = id;
		this.fromDate = exp.fromDate;
		this.overDate = exp.overDate;
		this.company = exp.company;
		this.occupation = exp.occupation;
		this.describtion = exp.describtion;
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
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getDescribtion() {
		if(describtion == null)
			return "";
		return describtion;
	}
	public void setDescribtion(String describtion) {
		this.describtion = describtion;
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
		parcel.writeString(this.company);
		parcel.writeString(this.occupation);
		parcel.writeString(this.describtion);
	}
	
	//添加一个静态成员,名为CREATOR,该对象实现了Parcelable.Creator接口  
    public static final Parcelable.Creator<Experience> CREATOR = new Parcelable.Creator<Experience>(){  
          
        //从Parcel中读取数据,返回worker对象  
        @Override  
        public Experience createFromParcel(Parcel source) {  
            return new Experience(source.readLong(),source.readString(), source.readString(),source.readString(),source.readString(),source.readString());  
        }  
        @Override  
        public Experience[] newArray(int size) {  
            return new Experience[size];  
        }  
    }; 
	
	public void save(Context context) {
		ExperienceDao dao = null;
		try {
			dao = daoFactory.getExperienceDAO(context);
			dao.insert(this);
		} catch (Exception e) {

		} finally {
			dao.close();
		}
	}

	public void delete(Context context) {
		ExperienceDao dao = null;
		try {
			dao = daoFactory.getExperienceDAO(context);
			dao.delete(this);
		} catch (Exception e) {

		} finally {
			dao.close();
		}
	}
	
	public void update(Context context) {
		ExperienceDao dao = null;
		try {
			dao = daoFactory.getExperienceDAO(context);
			dao.update(this);
		} catch (Exception e) {

		} finally {
			dao.close();
		}
	}

	public static void deleteById(Context context, String id) {
		ExperienceDao dao = null;
		try {
			dao = daoFactory.getExperienceDAO(context);
			dao.deleteById(id);
		} catch (Exception e) {

		} finally {
			dao.close();
		}
	}

	public static List<Experience> getAll(Context context) {
		ExperienceDao dao = null;
		try {
			dao = daoFactory.getExperienceDAO(context);
			return dao.getAll();
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
	
	public ResumeMsg isValid() {
		ResumeMsg rm = new ResumeMsg();
		if(MyStringUtils.compareDate(fromDate, overDate)>0)
		{
			rm.put(false, "开始日期不能大于结束日期");
		}
		if(MyStringUtils.compareDate(fromDate, MyDate.getCurrentDate())>0)
		{
			rm.put(false, "开始日期不能大于今天");
		}
		if(MyStringUtils.compareDate(overDate, MyDate.getCurrentDate())>0)
		{
			rm.put(false, "结束日期不能大于今天");
		}
		if(company.equals(""))
		{
			rm.put(false, "公司名称不能为空");
		}
		if(occupation.equals(""))
		{
			rm.put(false, "职位不能为空");
		}
		return rm;
	}

}
