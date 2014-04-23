package com.example.resume.dao;

import static android.provider.BaseColumns._ID;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.resume.model.Education;

public class EducationDAO extends DAOHelper {

    public EducationDAO(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

    
    public Education insert(Education edu)
    {
    	SQLiteDatabase db = getWritableDatabase();
    	ContentValues contentValues = getContentValues(edu);
    	long id = db.insert(EDUCATION_TABLE_NAME, null, contentValues);
    	return new Education(edu,id);
    }
    
    
    public void delete(Education edu) {

	    SQLiteDatabase db = getWritableDatabase();
	    db.delete(EDUCATION_TABLE_NAME, _ID + " = ?", new String[]{edu.getId().toString()});

    }
    
    public void deleteById(String id) {

	    SQLiteDatabase db = getWritableDatabase();
	    db.delete(EDUCATION_TABLE_NAME, _ID + " = ?", new String[]{id});

    }
    
    public void update(Education edu) {

	    SQLiteDatabase db = getWritableDatabase();
	    ContentValues contentValues = getContentValues(edu);
	    db.update(EDUCATION_TABLE_NAME, contentValues, _ID + " = ?", new String[]{edu.getId().toString()});

    }
    
    
    public List<Education> getAll() {
        List<Education> edus = new ArrayList<Education>();
        Cursor cursor = null;

        try {
            SQLiteDatabase db = getReadableDatabase();
            cursor = db.query (EDUCATION_TABLE_NAME,null,null,null,null,null,null);  
            while (cursor.moveToNext()) {
            	edus.add(createEducationFromCursorData(cursor));
            }
        } finally {
            closeCursor(cursor);
        }
       // Collections.reverse(edus);//按原顺序的反向顺序返回list
        return edus;
    }
    
    
    
    public ContentValues getContentValues(Education edu)
    {
    	ContentValues contentValues = new ContentValues();
        contentValues.put(EDUCATION_FROM_DATE, edu.getFromDate());
        contentValues.put(EDUCATION_OVER_DATE, edu.getOverDate());
        contentValues.put(EDUCATION_SCHOOL, edu.getSchool());
        contentValues.put(EDUCATION_DEGREE, edu.getDegree());
        contentValues.put(EDUCATION_MAJOR, edu.getMajor());
        return contentValues;
    }
    
    private Education createEducationFromCursorData(Cursor cursor) {
        long id = cursor.getLong(0);
        String fromDate = cursor.getString(1);
        String overDate = cursor.getString(2);
        String school = cursor.getString(3);
        String degree = cursor.getString(4);
        String major = cursor.getString(5);
        return new Education(id, fromDate, overDate, school, degree, major);
    }
  
}
