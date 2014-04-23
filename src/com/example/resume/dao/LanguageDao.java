package com.example.resume.dao;

import static android.provider.BaseColumns._ID;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.resume.model.Language;

public class LanguageDao extends DAOHelper  {
	
    public LanguageDao(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

	public Language insert(Language language) {
		SQLiteDatabase db = getWritableDatabase();
    	ContentValues contentValues = getContentValues(language);
    	long id = db.insert(LANGUAGE_TABLE_NAME, null, contentValues);
    	return new Language(id, language);
	}

	public void delete(Language language) {
	    SQLiteDatabase db = getWritableDatabase();
	    db.delete(LANGUAGE_TABLE_NAME, _ID + " = ?", new String[]{language.getId().toString()});
		
	}

	public void deleteById(String id) {
	    SQLiteDatabase db = getWritableDatabase();
	    db.delete(LANGUAGE_TABLE_NAME, _ID + " = ?", new String[]{id});

		
	}

	public List<Language> getAll() {
        List<Language> langs = new ArrayList<Language>();
        Cursor cursor = null;

        try {
            SQLiteDatabase db = getReadableDatabase();
            cursor = db.query (LANGUAGE_TABLE_NAME,null,null,null,null,null,null);  
            while (cursor.moveToNext()) {
            	langs.add(createLanguageFromCursorData(cursor));
            }
        } finally {
            closeCursor(cursor);
        }
       // Collections.reverse(edus);//按原顺序的反向顺序返回list
        return langs;
	}

    public ContentValues getContentValues(Language language)
    {
    	ContentValues contentValues = new ContentValues();
        contentValues.put(LANGUAGE_TYPE, language.getType());
        contentValues.put(LANGUAGE_SKILL, language.getSkill());
        contentValues.put(LANGUAGE_READ_WRITE, language.getReadwrite());
        contentValues.put(LANGUAGE_SPEAK_LISTEN, language.getSpeaklisten());
        return contentValues;
    }

    private Language createLanguageFromCursorData(Cursor cursor) {
        long id = cursor.getLong(0);
        String type = cursor.getString(1);
        String skill = cursor.getString(2);
        String readwrite = cursor.getString(3);
        String speaklisten = cursor.getString(4);

        return new Language(id, type, skill, readwrite, speaklisten);
    }

}
