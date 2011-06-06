package com.Cynical.android.fishingdatabase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class FishingDatabaseAdapter extends SQLiteOpenHelper {
	
	// Database Constants
	private static final String DATABASE_NAME =  "data";
	private static final String DATABASE_FISH_TABLE = "fish";
	private static final String DATABASE_LURE_TABLE = "lure";
	private static final String DATABASE_LAKE_TABLE = "lake";
	private static final String DATABASE_SPECIES_TABLE = "species";
	private static final int DATABASE_VERSION = 1;
	
	// Names of "fish" database table columns
	public static final String FISH_KEY_ROWID = "fishid";
	public static final String FISH_KEY_SPECIES = "species";
	public static final String FISH_KEY_LENGTH = "length";
	public static final String FISH_KEY_WEIGHT = "weight";
	public static final String FISH_KEY_DEPTH = "depth";
	public static final String FISH_KEY_LURETYPE = "luretype";
	public static final String FISH_KEY_LURECOLOR = "lurecolor";
	public static final String FISH_KEY_LAKENAME = "lakename";
	public static final String FISH_KEY_WATERTEMP = "watertemp";
	public static final String FISH_KEY_LATITUDE = "latitude";
	public static final String FISH_KEY_LONGITUDE = "longitude";
	
	// Names of "lure" database table columns
	public static final String LURE_KEY_ROWID = "lureid";
	public static final String LURE_KEY_LURETYPE = "luretype";
	public static final String LURE_KEY_LURECOLOR = "lurecolor";
	
	// Names of "lake" database table columns
	public static final String LAKE_KEY_ROWID = "lakeid";
	public static final String LAKE_KEY_LAKENAME = "lakename";
	public static final String LAKE_KEY_WEATHERSTATION = "weatherstationid";
	
	// Names of "species" database table columns
	public static final String SPECIES_KEY_ROWID = "speciesid";
	public static final String SPECIES_KEY_SPECIESNAME = "speciesname";
	
	// Create the string used to create the "fish" table
	private final String CREATE_TABLE_FISH = 
		"CREATE TABLE " + DATABASE_FISH_TABLE + "(" +
		FISH_KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
		FISH_KEY_SPECIES + " TEXT NULL , " + 
		FISH_KEY_LENGTH + " REAL NULL , " + 
		FISH_KEY_WEIGHT + " REAL NULL , " + 
		FISH_KEY_DEPTH + " REAL NULL , " + 
		FISH_KEY_LURETYPE + " TEXT NULL , " + 
		FISH_KEY_LURECOLOR + " TEXT NULL , " + 
		FISH_KEY_LAKENAME + " TEXT NULL , " + 
		FISH_KEY_WATERTEMP + " REAL NULL , " + 
		FISH_KEY_LATITUDE + " REAL NULL , " + 
		FISH_KEY_LONGITUDE + " REAL NULL );";
	
	// Create the string used to create the "lure" table
	private final String CREATE_TABLE_LURE = 
		"CREATE TABLE " + DATABASE_LURE_TABLE + " (" +
		LURE_KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + 
		LURE_KEY_LURETYPE + " TEXT NOT NULL , " + 
		LURE_KEY_LURECOLOR + " TEXT NOT NULL );";
	
	// Create the string used to create the "lake" table
	private final String CREATE_TABLE_LAKE = 
		"CREATE TABLE " + DATABASE_LAKE_TABLE + " (" + 
		LAKE_KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT , " + 
		LAKE_KEY_LAKENAME + " TEXT NOT NULL , " + 
		LAKE_KEY_WEATHERSTATION + " TEXT NULL );";
	
	// Create the string used to create the "species" table
	private final String CREATE_TABLE_SPECIES = 
		"CREATE TABLE " + DATABASE_SPECIES_TABLE + " (" + 
		SPECIES_KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
		SPECIES_KEY_SPECIESNAME + " TEXT NOT NULL );";


	public FishingDatabaseAdapter(Context context, String name,
			CursorFactory factory, int version) 
	{
		super(context, name, factory, version);
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		//TODO change the CREATE string
		db.execSQL("Create database");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}
	


}
