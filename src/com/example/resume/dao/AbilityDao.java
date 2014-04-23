package com.example.resume.dao;

import static android.provider.BaseColumns._ID;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.resume.model.Ability;
import com.example.resume.model.Language;

public class AbilityDao extends DAOHelper  {
	
    public AbilityDao(Context ctx) {
        super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
    }

	public Ability insert(Ability ability) {
		SQLiteDatabase db = getWritableDatabase();
    	ContentValues contentValues = getContentValues(ability);
    	long id = db.insert(ABILITY_TABLE_NAME, null, contentValues);
    	return new Ability(id, ability);
	}

	public void delete(Ability ability) {
	    SQLiteDatabase db = getWritableDatabase();
	    db.delete(ABILITY_TABLE_NAME, _ID + " = ?", new String[]{ability.getId().toString()});
		
	}

	public void deleteById(String id) {
	    SQLiteDatabase db = getWritableDatabase();
	    db.delete(ABILITY_TABLE_NAME, _ID + " = ?", new String[]{id});

		
	}

	public List<Ability> getAll() {
        List<Ability> abilitys = new ArrayList<Ability>();
        Cursor cursor = null;

        try {
            SQLiteDatabase db = getReadableDatabase();
            cursor = db.query (ABILITY_TABLE_NAME,null,null,null,null,null,null);  
            while (cursor.moveToNext()) {
            	abilitys.add(createAbilityFromCursorData(cursor));
            }
        } finally {
            closeCursor(cursor);
        }
       // Collections.reverse(edus);//按原顺序的反向顺序返回list
        return abilitys;
	}

    public ContentValues getContentValues(Ability ability)
    {
    	ContentValues contentValues = new ContentValues();
        contentValues.put(ABILITY_SKILL, ability.getSkill());
        return contentValues;
    }

    private Ability createAbilityFromCursorData(Cursor cursor) {
        long id = cursor.getLong(0);
        String skill = cursor.getString(1);

        return new Ability(id, skill);
    }

}
