package com.example.resume.dao;

import android.content.Context;

public class DAOFactory {
    private static DAOFactory instance = null;

    private Context globalContext = null;
    private boolean cacheDAOInstances = false;
    private LanguageDao cachedLangDAO = null;
    private EducationDAO cachedEduDAO = null;
    private ExperienceDao cachedExpDAO = null;
    private ProjectDao cachedProDAO = null;
    private AbilityDao cachedAbilityDAO = null;
    public static DAOFactory getInstance() {
        if (instance == null) {
            instance = new DAOFactory();
        }
        return instance;
    }

    private DAOFactory() {
    }

    public LanguageDao getLanguageDAO(Context context) {
        if (cacheDAOInstances) {
            if (cachedLangDAO == null) {
                cachedLangDAO = new LanguageDao(getProperDAOContext(context));
            }
            return cachedLangDAO;
        } else {
            return new LanguageDao(getProperDAOContext(context));
        }
    }

    public EducationDAO getEducationDAO(Context context) {
        if (cacheDAOInstances) {
            if (cachedEduDAO == null) {
            	cachedEduDAO = new EducationDAO(getProperDAOContext(context));
            }
            return cachedEduDAO;
        } else {
            return new EducationDAO(getProperDAOContext(context));
        }
    }
    
    public ProjectDao getProjectDAO(Context context) {
        if (cacheDAOInstances) {
            if (cachedProDAO == null) {
            	cachedProDAO = new ProjectDao(getProperDAOContext(context));
            }
            return cachedProDAO;
        } else {
            return new ProjectDao(getProperDAOContext(context));
        }
    }
    
    public ExperienceDao getExperienceDAO(Context context) {
        if (cacheDAOInstances) {
            if (cachedExpDAO == null) {
            	cachedExpDAO = new ExperienceDao(getProperDAOContext(context));
            }
            return cachedExpDAO;
        } else {
            return new ExperienceDao(getProperDAOContext(context));
        }
    }
    public AbilityDao getAbilityDAO(Context context) {
        if (cacheDAOInstances) {
            if (cachedAbilityDAO == null) {
            	cachedAbilityDAO = new AbilityDao(getProperDAOContext(context));
            }
            return cachedAbilityDAO;
        } else {
            return new AbilityDao(getProperDAOContext(context));
        }
    }


    public void setGlobalContext(Context context) {
        globalContext = context;
    }

    public void setCacheDAOInstances(boolean cacheDAOInstances) {
        this.cacheDAOInstances = cacheDAOInstances;
    }

    private Context getProperDAOContext(Context context) {
        if (globalContext != null) {
            return globalContext;
        } else {
            return context;
        }
    }
}
