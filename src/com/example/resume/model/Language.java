package com.example.resume.model;

import java.util.List;
import android.content.Context;
import com.example.resume.dao.DAOFactory;
import com.example.resume.dao.LanguageDao;

public class Language {

	private Long id = null;
	private String type = null;
	private String skill = null;
	private String readwrite = null;
	private String speaklisten = null;
	
	private static DAOFactory daoFactory = DAOFactory.getInstance();
	public Language(String type, String skill, String readwrite,
			String speaklisten) {
		this.type = type;
		this.skill = skill;
		this.readwrite = readwrite;
		this.speaklisten = speaklisten;
	}
	
	public Language(Long id, Language language) {
		this.id = id;
		this.type = language.getType();
		this.skill = language.getSkill();
		this.readwrite = language.getReadwrite();
		this.speaklisten = language.getSpeaklisten();
	}
	
	public Language(Long id, String type, String skill, String readwrite,
			String speaklisten) {
		this(type, skill, readwrite, speaklisten);
		this.id = id;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSkill() {
		return skill;
	}
	public void setSkill(String skill) {
		this.skill = skill;
	}
	public String getReadwrite() {
		return readwrite;
	}
	public void setReadwrite(String readwrite) {
		this.readwrite = readwrite;
	}
	public String getSpeaklisten() {
		return speaklisten;
	}
	public void setSpeaklisten(String speaklisten) {
		this.speaklisten = speaklisten;
	}
	
	
	public void save(Context context) {
		LanguageDao dao = null;
		try {
			dao = daoFactory.getLanguageDAO(context);
			dao.insert(this);
		} catch (Exception e) {

		} finally {
			dao.close();
		}
	}

	public void delete(Context context) {
		LanguageDao dao = null;
		try {
			dao = daoFactory.getLanguageDAO(context);
			dao.delete(this);
		} catch (Exception e) {

		} finally {
			dao.close();
		}
	}
	
	public static void deleteById(Context context, String id) {
		LanguageDao dao = null;
		try {
			dao = daoFactory.getLanguageDAO(context);
			dao.deleteById(id);
		} catch (Exception e) {

		} finally {
			dao.close();
		}
	}
	
	public static List<Language> getAll(Context context) {
		LanguageDao dao = null;
		try {
			dao = daoFactory.getLanguageDAO(context);
			return dao.getAll();
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
}
