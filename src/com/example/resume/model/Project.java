package com.example.resume.model;

import java.util.List;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.resume.dao.DAOFactory;
import com.example.resume.dao.ProjectDao;
import com.example.resume.utils.MyDate;
import com.example.resume.utils.MyStringUtils;

public class Project  implements Parcelable {
	private Long id;
	private String fromDate;
	private String overDate;
	private String projectName;
	private String title;
	private String describtion;
	private String duty;
	
	private static DAOFactory daoFactory = DAOFactory.getInstance();
	
	public Project(Long id ,String fromDate, String overDate, String projectName, String title,
			String describtion, String duty) {
		this(fromDate, overDate, projectName, title, describtion, duty);
		this.id = id;
	}

	public Project(String fromDate, String overDate, String projectName, String title,
			String describtion, String duty) {
		
		this.fromDate = fromDate;
		this.overDate = overDate;
		this.projectName = projectName;
		this.title = title;
		this.describtion = describtion;
		this.duty = duty;
	}
	
	public Project(Long id, Project pro) {
		this.id = id;
		this.fromDate = pro.fromDate;
		this.overDate = pro.overDate;
		this.projectName = pro.projectName;
		this.title = pro.title;
		this.describtion = pro.describtion;
		this.duty = pro.duty;
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

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescribtion() {
		return describtion;
	}

	public void setDescribtion(String describtion) {
		this.describtion = describtion;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
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
		parcel.writeString(this.projectName);
		parcel.writeString(this.title);
		parcel.writeString(this.describtion);
		parcel.writeString(this.duty);
	}
	
	//添加一个静态成员,名为CREATOR,该对象实现了Parcelable.Creator接口  
    public static final Parcelable.Creator<Project> CREATOR = new Parcelable.Creator<Project>(){  
          
        //从Parcel中读取数据,返回worker对象  
        @Override  
        public Project createFromParcel(Parcel source) {  
            return new Project(source.readLong(),source.readString(), source.readString(), source.readString(),source.readString(),source.readString(),source.readString());  
        }  
        @Override  
        public Project[] newArray(int size) {  
            return new Project[size];  
        }  
    }; 
	
	public void save(Context context) {
		ProjectDao dao = null;
		try {
			dao = daoFactory.getProjectDAO(context);
			dao.insert(this);
		} catch (Exception e) {

		} finally {
			dao.close();
		}
	}

	public void delete(Context context) {
		ProjectDao dao = null;
		try {
			dao = daoFactory.getProjectDAO(context);
			dao.delete(this);
		} catch (Exception e) {

		} finally {
			dao.close();
		}
	}
	
	public void update(Context context) {
		ProjectDao dao = null;
		try {
			dao = daoFactory.getProjectDAO(context);
			dao.update(this);
		} catch (Exception e) {

		} finally {
			dao.close();
		}
	}

	public static void deleteById(Context context, String id) {
		ProjectDao dao = null;
		try {
			dao = daoFactory.getProjectDAO(context);
			dao.deleteById(id);
		} catch (Exception e) {

		} finally {
			dao.close();
		}
	}

	public static List<Project> getAll(Context context) {
		ProjectDao dao = null;
		try {
			dao = daoFactory.getProjectDAO(context);
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
		if(projectName.equals(""))
		{
			rm.put(false, "项目名称不能为空");
		}
		if(title.equals(""))
		{
			rm.put(false, "职位不能为空");
		}
		
		return rm;
	}

}
