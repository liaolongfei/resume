package com.example.resume.model;

import java.util.List;

import android.content.Context;

import com.example.resume.dao.AbilityDao;
import com.example.resume.dao.DAOFactory;
import com.example.resume.dao.LanguageDao;

public class Ability {
	private Long id = null;
	private String skill = null;
	private static DAOFactory daoFactory = DAOFactory.getInstance();
	public Ability(String skill) {
		this.skill = skill;
	}
	public Ability(Long id ,String skill) {
		this.id = id;
		this.skill = skill;
	}

	public Ability(Long id, Ability ability) {
		this.id = id;
		this.skill = ability.skill;
		
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}
	
	public void save(Context context) {
		AbilityDao dao = null;
		try {
			dao = daoFactory.getAbilityDAO(context);
			dao.insert(this);
		} catch (Exception e) {

		} finally {
			dao.close();
		}
	}

	public void delete(Context context) {
		AbilityDao dao = null;
		try {
			dao = daoFactory.getAbilityDAO(context);
			dao.delete(this);
		} catch (Exception e) {

		} finally {
			dao.close();
		}
	}
	
	public static void deleteById(Context context, String id) {
		AbilityDao dao = null;
		try {
			dao = daoFactory.getAbilityDAO(context);
			dao.deleteById(id);
		} catch (Exception e) {

		} finally {
			dao.close();
		}
	}
	
	public static List<Ability> getAll(Context context) {
		AbilityDao dao = null;
		try {
			dao = daoFactory.getAbilityDAO(context);
			return dao.getAll();
		} finally {
			if (dao != null) {
				dao.close();
			}
		}
	}
	
	
	public ResumeMsg isValid()
	{
		ResumeMsg rm = new ResumeMsg();
		String errorMsg = null;
		if(skill.equals(""))
		{
			errorMsg = "²»ÄÜÎª¿Õ";
			rm.put(false, errorMsg);

		}
		
		
		return rm;
	}
	

}
