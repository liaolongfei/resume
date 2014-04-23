package com.example.resume.dao;

import static android.provider.BaseColumns._ID;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.resume.model.Experience;
import com.example.resume.model.Project;

public class ProjectDao extends DAOHelper{
	
    public ProjectDao(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

	public Project insert(Project pro) {
		SQLiteDatabase db = getWritableDatabase();
    	ContentValues contentValues = getContentValues(pro);
    	long id = db.insert(PROJECT_TABLE_NAME, null, contentValues);
    	return new Project(id,pro);
		
	}

	public void delete(Project pro) {
		 SQLiteDatabase db = getWritableDatabase();
		 db.delete(PROJECT_TABLE_NAME, _ID + " = ?", new String[]{pro.getId().toString()});

	}
	
	public void deleteById(String id) {
	    SQLiteDatabase db = getWritableDatabase();
	    db.delete(PROJECT_TABLE_NAME, _ID + " = ?", new String[]{id});
	}

	public void update(Project pro) {
	   SQLiteDatabase db = getWritableDatabase();
	   ContentValues contentValues = getContentValues(pro);
	   db.update(PROJECT_TABLE_NAME, contentValues, _ID + " = ?", new String[]{pro.getId().toString()});

		
	}

	

	public List<Project> getAll() {
        List<Project> projects = new ArrayList<Project>();
        Cursor cursor = null;

        try {
            SQLiteDatabase db = getReadableDatabase();
            cursor = db.query (PROJECT_TABLE_NAME,null,null,null,null,null,null);  
            while (cursor.moveToNext()) {
            	projects.add(createProjectFromCursorData(cursor));
            }
        } finally {
            closeCursor(cursor);
        }
       // Collections.reverse(edus);//按原顺序的反向顺序返回list
        return projects;
	}
	
    public ContentValues getContentValues(Project pro)
    {
    	ContentValues contentValues = new ContentValues();
        contentValues.put(PROJECT_FROM_DATE, pro.getFromDate());
        contentValues.put(PROJECT_OVER_DATE, pro.getOverDate());
        contentValues.put(PROJECT_NAME, pro.getProjectName());
        contentValues.put(PROJECT_TITLE, pro.getTitle());
        contentValues.put(PROJECT_DESCRIBTION, pro.getDescribtion());
        contentValues.put(PROJECT_DUTY, pro.getDuty());
        return contentValues;
    }
    
    private Project createProjectFromCursorData(Cursor cursor) {
        long id = cursor.getLong(0);
        String fromDate = cursor.getString(1);
        String overDate = cursor.getString(2);
        String name = cursor.getString(3);
        String title = cursor.getString(4);
        String describtion = cursor.getString(5);
        String duty = cursor.getString(6);
        return new Project(id, fromDate, overDate, name, title, describtion, duty);
    }

}
