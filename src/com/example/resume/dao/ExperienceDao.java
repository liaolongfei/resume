package com.example.resume.dao;

import static android.provider.BaseColumns._ID;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.resume.model.Experience;

public class ExperienceDao extends DAOHelper{
	
    public ExperienceDao(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

	public Experience insert(Experience experience) {
		SQLiteDatabase db = getWritableDatabase();
    	ContentValues contentValues = getContentValues(experience);
    	long id = db.insert(EXPERIENCE_TABLE_NAME, null, contentValues);
    	return new Experience(id,experience);
		
	}

	public void delete(Experience experience) {
		 SQLiteDatabase db = getWritableDatabase();
		 db.delete(EXPERIENCE_TABLE_NAME, _ID + " = ?", new String[]{experience.getId().toString()});

	}
	
	public void deleteById(String id) {
	    SQLiteDatabase db = getWritableDatabase();
	    db.delete(EXPERIENCE_TABLE_NAME, _ID + " = ?", new String[]{id});
	}

	public void update(Experience experience) {
	   SQLiteDatabase db = getWritableDatabase();
	   ContentValues contentValues = getContentValues(experience);
	   db.update(EXPERIENCE_TABLE_NAME, contentValues, _ID + " = ?", new String[]{experience.getId().toString()});

		
	}

	

	public List<Experience> getAll() {
        List<Experience> exps = new ArrayList<Experience>();
        Cursor cursor = null;

        try {
            SQLiteDatabase db = getReadableDatabase();
            cursor = db.query (EXPERIENCE_TABLE_NAME,null,null,null,null,null,null);  
            while (cursor.moveToNext()) {
            	exps.add(createExperienceFromCursorData(cursor));
            }
        } finally {
            closeCursor(cursor);
        }
       // Collections.reverse(edus);//按原顺序的反向顺序返回list
        return exps;
	}
	
    public ContentValues getContentValues(Experience edu)
    {
    	ContentValues contentValues = new ContentValues();
        contentValues.put(EXPERIENCE_FROM_DATE, edu.getFromDate());
        contentValues.put(EXPERIENCE_OVER_DATE, edu.getOverDate());
        contentValues.put(EXPERIENCE_COMPANY, edu.getCompany());
        contentValues.put(EXPERIENCE_OCCUPATION, edu.getOccupation());
        contentValues.put(EXPERIENCE_DESCRIBTION, edu.getDescribtion());
        return contentValues;
    }
    
    private Experience createExperienceFromCursorData(Cursor cursor) {
        long id = cursor.getLong(0);
        String fromDate = cursor.getString(1);
        String overDate = cursor.getString(2);
        String company = cursor.getString(3);
        String occupation = cursor.getString(4);
        String describtion = cursor.getString(5);
        return new Experience(id, fromDate, overDate, company, occupation, describtion);
    }

}
