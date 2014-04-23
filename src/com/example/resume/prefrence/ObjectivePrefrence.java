package com.example.resume.prefrence;

import com.example.resume.model.Objective;

public class ObjectivePrefrence {
	
	public static  ResumePreferences preferences = null;//new ResumePreferences();

	
	
	public static void setObjectivePreference(Objective obj) {
		setJobTypePreference(obj.getJobType());
		setJobAddressPreference(obj.getJobAddress());
		setIndustryPreference(obj.getIndustry());
		setOccupationPreference(obj.getOccupation());
		setDutytimetPreference(obj.getDutytime());
		setSalary(obj.getSalary());
	}
	
	

	public static String getJobTypePreference() {
		String jobType = null;
		jobType = preferences.getPreference(PrefrencesKeys.jobType);

		if(jobType == null || jobType.equals(""))
		{
			preferences.insertPreference(PrefrencesKeys.jobType, PrefrencesValues.jobType);
			jobType = PrefrencesValues.jobType;
		}
		return jobType;
	}

	
	
	public static void setJobTypePreference(String jobType) {
		preferences.insertPreference(PrefrencesKeys.jobType, jobType);
	}

	public static String getJobAddressPreference() {
		String jobAddress = null;
		jobAddress = preferences.getPreference(PrefrencesKeys.jobAddress);

		if(jobAddress == null || jobAddress.equals(""))
		{
			preferences.insertPreference(PrefrencesKeys.jobAddress, PrefrencesValues.jobAddress);
			jobAddress = PrefrencesValues.jobAddress;
		}
		return jobAddress;
	}

	public static void setJobAddressPreference(String jobAddress) {
		preferences.insertPreference(PrefrencesKeys.jobAddress, jobAddress);
	}

	public static String getIndustryPreference() {
		String industry = null;
		industry = preferences.getPreference(PrefrencesKeys.industry);

		if(industry == null || industry.equals(""))
		{
			preferences.insertPreference(PrefrencesKeys.industry, PrefrencesValues.industry);
			industry = PrefrencesValues.industry;
		}
		return industry;
	}

	public static void setIndustryPreference(String industry) {
		preferences.insertPreference(PrefrencesKeys.industry, industry);
	}

	public static String getOccupationPreference() {
		String occupation= null;
		occupation = preferences.getPreference(PrefrencesKeys.occupation);

		if(occupation == null || occupation.equals(""))
		{
			preferences.insertPreference(PrefrencesKeys.occupation, PrefrencesValues.occupation);
			occupation = PrefrencesValues.occupation;
		}
		return occupation;
	}

	public static void setOccupationPreference(String occupation) {
		preferences.insertPreference(PrefrencesKeys.occupation, occupation);
	}

	public static String getDutytimetPreference() {
		String dutytime = null;
		dutytime = preferences.getPreference(PrefrencesKeys.dutytime);

		if(dutytime == null || dutytime.equals(""))
		{
			preferences.insertPreference(PrefrencesKeys.dutytime, PrefrencesValues.dutytime);
			dutytime = PrefrencesValues.dutytime;
		}
		return dutytime;
	}

	public static void setDutytimetPreference(String dutytime) {
		preferences.insertPreference(PrefrencesKeys.dutytime, dutytime);
	}

	public static String getSalaryPreference() {
		String salary = null;
		salary = preferences.getPreference(PrefrencesKeys.salary);

		if(salary == null || salary.equals(""))
		{
			preferences.insertPreference(PrefrencesKeys.salary, PrefrencesValues.salary);
			salary = PrefrencesValues.salary;
		}
		return salary;
	}

	public static void setSalary(String salary) {
		preferences.insertPreference(PrefrencesKeys.salary, salary);
		
	}


}
