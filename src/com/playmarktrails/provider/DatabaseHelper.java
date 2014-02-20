package com.playmarktrails.provider;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.SensorManager;

public class DatabaseHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "constants.db";

	public DatabaseHelper(Context context) {

		super(context, DATABASE_NAME, null, 1);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Cursor c = db
				.rawQuery(
						"SELECT name FROM sqlite_master WHERE type='table' AND name='constants' ",
						null);

		try {
			if (c.getCount() == 0) {
				db.execSQL(" CREATE TABLE constants (_id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, value REAL); ");
				ContentValues cv = new ContentValues();
				cv.put(Provider.Constants.TITLE, "Gravity, Death Star I");
				cv.put(Provider.Constants.VALUE, SensorManager.GRAVITY_DEATH_STAR_I);
				db.insert("constants", Provider.Constants.TITLE, cv);
				
				cv.put(Provider.Constants.TITLE, "Gravity, Earth");
				cv.put(Provider.Constants.VALUE, SensorManager.GRAVITY_EARTH);
				db.insert("constants", Provider.Constants.TITLE, cv);
				
				cv.put(Provider.Constants.TITLE, "Gravity, Jupiter");
		        cv.put(Provider.Constants.VALUE, SensorManager.GRAVITY_JUPITER);
		        db.insert("constants", Provider.Constants.TITLE, cv);
		        
		        cv.put(Provider.Constants.TITLE, "Gravity, Mars");
		        cv.put(Provider.Constants.VALUE, SensorManager.GRAVITY_MARS);
		        db.insert("constants", Provider.Constants.TITLE, cv);
		        
		        cv.put(Provider.Constants.TITLE, "Gravity, Mercury");
		        cv.put(Provider.Constants.VALUE, SensorManager.GRAVITY_MERCURY);
		        db.insert("constants", Provider.Constants.TITLE, cv);
		        
		        cv.put(Provider.Constants.TITLE, "Gravity, Moon");
		        cv.put(Provider.Constants.VALUE, SensorManager.GRAVITY_MOON);
		        db.insert("constants", Provider.Constants.TITLE, cv);
		        
		        cv.put(Provider.Constants.TITLE, "Gravity, Neptune");
		        cv.put(Provider.Constants.VALUE, SensorManager.GRAVITY_NEPTUNE);
		        db.insert("constants", Provider.Constants.TITLE, cv);
		        
		        cv.put(Provider.Constants.TITLE, "Gravity, Pluto");
		        cv.put(Provider.Constants.VALUE, SensorManager.GRAVITY_PLUTO);
		        db.insert("constants", Provider.Constants.TITLE, cv);
		        
		        cv.put(Provider.Constants.TITLE, "Gravity, Saturn");
		        cv.put(Provider.Constants.VALUE, SensorManager.GRAVITY_SATURN);
		        db.insert("constants", Provider.Constants.TITLE, cv);
		        
		        cv.put(Provider.Constants.TITLE, "Gravity, Sun");
		        cv.put(Provider.Constants.VALUE, SensorManager.GRAVITY_SUN);
		        db.insert("constants", Provider.Constants.TITLE, cv);
		        
		        cv.put(Provider.Constants.TITLE, "Gravity, The Island");
		        cv.put(Provider.Constants.VALUE, SensorManager.GRAVITY_THE_ISLAND);
		        db.insert("constants", Provider.Constants.TITLE, cv);
		        
		        cv.put(Provider.Constants.TITLE, "Gravity, Uranus");
		        cv.put(Provider.Constants.VALUE, SensorManager.GRAVITY_URANUS);
		        db.insert("constants", Provider.Constants.TITLE, cv);
		        
		        cv.put(Provider.Constants.TITLE, "Gravity, Venus");
		        cv.put(Provider.Constants.VALUE, SensorManager.GRAVITY_VENUS);
		        db.insert("constants", Provider.Constants.TITLE, cv);
			}
		} finally {
			c.close();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		db.execSQL("DROP TABLE IF EXISTS constants");
		onCreate(db);
	}

}
