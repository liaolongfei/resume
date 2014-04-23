package com.example.resume.dao;

import static android.provider.BaseColumns._ID;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public abstract class DAOHelper extends SQLiteOpenHelper implements DatabaseConstants {

    protected static final String USER_TABLE_NAME = "user";
    protected static final String USER_KEY = "key";
    protected static final String USER_VALUE = "value";
    protected static final String[] USER_ALL_COLUMS = { _ID, USER_KEY, USER_VALUE};

    protected static final String EDUCATION_TABLE_NAME = "education";
    protected static final String EDUCATION_FROM_DATE = "from_date";
    protected static final String EDUCATION_OVER_DATE = "over_date";
    protected static final String EDUCATION_SCHOOL = "school";
    protected static final String EDUCATION_DEGREE = "degree";
    protected static final String EDUCATION_MAJOR = "major";
    protected static final String[] EDUCATION_ALL_COLUMS = { _ID, EDUCATION_FROM_DATE, EDUCATION_OVER_DATE, EDUCATION_SCHOOL,EDUCATION_DEGREE,EDUCATION_MAJOR};

    
    protected static final String LANGUAGE_TABLE_NAME = "language";
    protected static final String LANGUAGE_TYPE = "type";
    protected static final String LANGUAGE_SKILL = "skill";
    protected static final String LANGUAGE_READ_WRITE = "read_write";
    protected static final String LANGUAGE_SPEAK_LISTEN = "speak_listen";
    protected static final String[] LANGUAGE_ALL_COLUMS = { _ID, LANGUAGE_TYPE, LANGUAGE_SKILL,LANGUAGE_READ_WRITE,LANGUAGE_SPEAK_LISTEN};
    
    // EXPERIENCE
    protected static final String EXPERIENCE_TABLE_NAME = "experience";
    protected static final String EXPERIENCE_FROM_DATE = "from_date";
    protected static final String EXPERIENCE_OVER_DATE = "over_date";
    protected static final String EXPERIENCE_COMPANY = "company";
    protected static final String EXPERIENCE_OCCUPATION = "occupation";
    protected static final String EXPERIENCE_DESCRIBTION = "describtion";
    protected static final String[] EXPERIENCE_ALL_COLUMS = { _ID, EXPERIENCE_FROM_DATE, EXPERIENCE_OVER_DATE, EXPERIENCE_COMPANY,EXPERIENCE_OCCUPATION,EXPERIENCE_DESCRIBTION};

    
    // PROJECT
    protected static final String PROJECT_TABLE_NAME = "project";
    protected static final String PROJECT_FROM_DATE = "from_date";
    protected static final String PROJECT_OVER_DATE = "over_date";
    protected static final String PROJECT_NAME = "name";
    protected static final String PROJECT_TITLE = "title";
    protected static final String PROJECT_DESCRIBTION = "describtion";
    protected static final String PROJECT_DUTY = "duty";
    protected static final String[] PROJECT_ALL_COLUMS = { _ID, PROJECT_FROM_DATE, PROJECT_OVER_DATE, PROJECT_NAME, PROJECT_TITLE, PROJECT_DESCRIBTION, PROJECT_DUTY};

    
    // ABILITY
    protected static final String ABILITY_TABLE_NAME = "ability";
    protected static final String ABILITY_SKILL = "skill";
    protected static final String[] ABILITY__ALL_COLUMS = { _ID, ABILITY_SKILL};

    
    
    public DAOHelper(Context context, String name, CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + USER_TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                USER_KEY + " TEXT NOT NULL," +  
                USER_VALUE + " TEXT NOT NULL"+
                ");");

        db.execSQL("CREATE TABLE " + EDUCATION_TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EDUCATION_FROM_DATE + " TEXT NOT NULL, " +
                EDUCATION_OVER_DATE + " TEXT NOT NULL, " +
                EDUCATION_SCHOOL + " TEXT NOT NULL, " +
                EDUCATION_DEGREE + " TEXT NOT NULL, " +
                EDUCATION_MAJOR + " TEXT " +
                ");");
        
        db.execSQL("CREATE TABLE " + LANGUAGE_TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LANGUAGE_TYPE + " TEXT NOT NULL, " +
                LANGUAGE_SKILL + " TEXT NOT NULL, " +
                LANGUAGE_READ_WRITE + " TEXT NOT NULL, " +
                LANGUAGE_SPEAK_LISTEN + " TEXT NOT NULL" +
                ");");

        
        db.execSQL("CREATE TABLE " + EXPERIENCE_TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                EXPERIENCE_FROM_DATE + " TEXT NOT NULL, " +
                EXPERIENCE_OVER_DATE + " TEXT NOT NULL, " +
                EXPERIENCE_COMPANY + " TEXT NOT NULL, " +
                EXPERIENCE_OCCUPATION + " TEXT NOT NULL, " +
                EXPERIENCE_DESCRIBTION + " TEXT " +
                ");");
          
        db.execSQL("CREATE TABLE " + PROJECT_TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                PROJECT_FROM_DATE + " TEXT NOT NULL, " +
                PROJECT_OVER_DATE + " TEXT NOT NULL, " +
                PROJECT_NAME + " TEXT NOT NULL, " +
                PROJECT_TITLE + " TEXT NOT NULL, " +
                PROJECT_DESCRIBTION + " TEXT, " +
                PROJECT_DUTY + " TEXT " +
                ");");
        
        db.execSQL("CREATE TABLE " + ABILITY_TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ABILITY_SKILL + " TEXT NOT NULL " +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EDUCATION_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + LANGUAGE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EXPERIENCE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + PROJECT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + ABILITY_TABLE_NAME);
        onCreate(db);
    }

    protected void closeCursor(Cursor cursor) {
        if (cursor != null) {
            cursor.close();
        }
    }
}
