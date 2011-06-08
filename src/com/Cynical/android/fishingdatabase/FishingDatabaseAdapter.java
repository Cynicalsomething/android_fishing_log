package com.Cynical.android.fishingdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class FishingDatabaseAdapter {
	
	// Database Constants
	private static final String DATABASE_NAME =  "data";
	private static final String DATABASE_FISH_TABLE = "fish";
	private static final String DATABASE_LURE_TABLE = "lure";
	private static final String DATABASE_LAKE_TABLE = "lake";
	private static final String DATABASE_SPECIES_TABLE = "species";
	private static final String DATABASE_LURETYPES_TABLE = "luretypes";
	private static final int DATABASE_VERSION = 1;
	
	// Names of "fish" database table columns
	public static final String FISH_KEY_ROWID = "fishid";
	public static final String FISH_KEY_DATE_TIME = "datetime";
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
	public static final String FISH_KEY_TEMP = "temperature";
	public static final String FISH_KEY_CONDITIONS = "conditions";
	public static final String FISH_KEY_HUMIDITY = "humidity";
	public static final String FISH_KEY_WINDSPEED = "windspeed";
	public static final String FISH_KEY_WINDDIR = "winddir";
	public static final String FISH_KEY_BARPRESSURE = "barpressure";
	public static final String FISH_KEY_DEWPOINT = "dewpoint";
	
	// Names of "lure" database table columns
	public static final String LURE_KEY_ROWID = "lureid";
	public static final String LURE_KEY_LURETYPE = "luretype";
	public static final String LURE_KEY_LURECOLOR = "lurecolor";
	
	// Names of "lake" database table columns
	public static final String LAKE_KEY_ROWID = "lakeid";
	public static final String LAKE_KEY_LAKENAME = "lakename";
	
	// Names of "species" database table columns
	public static final String SPECIES_KEY_ROWID = "speciesid";
	public static final String SPECIES_KEY_SPECIESNAME = "speciesname";
	
	//Names of "luretypes" database table columns
	public static final String LURETYPES_KEY_ROWID = "luretypeid";
	public static final String LURETYPES_KEY_LURETYPE = "luretype";
	
	// Create the string used to create the "fish" table
	private final String CREATE_TABLE_FISH = 
		"CREATE TABLE " + DATABASE_FISH_TABLE + "(" +
		FISH_KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
		FISH_KEY_DATE_TIME + " TEXT NULL , " +
		FISH_KEY_SPECIES + " TEXT NULL , " + 
		FISH_KEY_LENGTH + " REAL NULL , " + 
		FISH_KEY_WEIGHT + " REAL NULL , " + 
		FISH_KEY_DEPTH + " REAL NULL , " + 
		FISH_KEY_LURETYPE + " TEXT NULL , " + 
		FISH_KEY_LURECOLOR + " TEXT NULL , " + 
		FISH_KEY_LAKENAME + " TEXT NULL , " + 
		FISH_KEY_WATERTEMP + " REAL NULL , " + 
		FISH_KEY_LATITUDE + " REAL NULL , " + 
		FISH_KEY_LONGITUDE + " REAL NULL , " + 
		FISH_KEY_TEMP + " REAL NULL , " + 
		FISH_KEY_CONDITIONS + " TEXT NULL , " + 
		FISH_KEY_HUMIDITY + " INTEGER NULL , " + 
		FISH_KEY_WINDSPEED + " INTEGER NULL , " + 
		FISH_KEY_WINDDIR + " TEXT NULL , " +
		FISH_KEY_BARPRESSURE + " REAL NULL , " + 
		FISH_KEY_DEWPOINT + " INTEGER NULL );";
	
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
		LAKE_KEY_LAKENAME + " TEXT NOT NULL );";
	
	// Create the string used to create the "species" table
	private final String CREATE_TABLE_SPECIES = 
		"CREATE TABLE " + DATABASE_SPECIES_TABLE + " (" + 
		SPECIES_KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
		SPECIES_KEY_SPECIESNAME + " TEXT NOT NULL );";
	
	//Create the string used to create the "luretypes" tables
	private final String CREATE_TABLE_LURETYPES = 
		"CREATE TABLE " + DATABASE_LURETYPES_TABLE + " (" + 
		LURETYPES_KEY_ROWID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
		LURETYPES_KEY_LURETYPE + " TEXT NOT NULL );";

	private final Context ctx;
	private FishingDatabaseHelper dbHelper;
	private SQLiteDatabase db;
	
	
	public FishingDatabaseAdapter(Context mCtx)
	{
		this.ctx = mCtx;
	}
	
	/**
	 * Opens the database for writing
	 * @return A FishingDatabaseAdapter for easy interaction with the database
	 */
	public FishingDatabaseAdapter open()
	{
		this.dbHelper = new FishingDatabaseHelper(ctx, DATABASE_NAME, null, DATABASE_VERSION);
		this.db = dbHelper.getWritableDatabase();
		return this;
	}
	
	/**
	 * Closes the database
	 */
	public void close()
	{
		this.dbHelper.close();
	}
	
	//For testing purposes
	public void deleteDatabase(Context mCtx)
	{
		mCtx.deleteDatabase(DATABASE_NAME);
	}
	/**
	 * Adds a new lure to the "luretypes" database table
	 * @param luretype = the name of the Lure Type to add. (e.g. Buzzbait, 
	 * Texas-rigged worm, etc.)
	 * @return = the id number of the row that was added to the database table.
	 */
	public long addLure(String luretype)
	{
		ContentValues cv = new ContentValues();
		cv.put(LURETYPES_KEY_LURETYPE, luretype);
		long id = this.db.insert(DATABASE_LURETYPES_TABLE, null, cv);
		return id;
	}
	
	public Cursor getLureTypes()
	{
		String columns[] = new String[] {LURETYPES_KEY_LURETYPE};
		return this.db.query(DATABASE_LURETYPES_TABLE, columns,
				null, null, null, null, null);
	}
	
	/**
	 * Adds a new lure to the "lure" database table
	 * @param type = The type of lure to add. (e.g. Buzzbait, Texas-rigged worm, etc.)
	 * @param color = The color of the lure. (e.g. Green, Blue/Black, etc.)
	 * @return The id number of the row that was added to the database table.
	 */
	public long addLureWithColor(String type, String color)
	{
		ContentValues cv = new ContentValues();
		cv.put(LURE_KEY_LURETYPE, type);
		cv.put(LURE_KEY_LURECOLOR, color);
		long id = this.db.insert(DATABASE_LURE_TABLE, null, cv);
		return id;
	}
	
	/**
	 * Retrieves all lures in the database table
	 * @return A Cursor object that contains all lures in the database table
	 */
	public Cursor getLures()
	{
		String[] columns = new String[] {LURE_KEY_LURETYPE, LURE_KEY_LURECOLOR};
		return this.db.query(DATABASE_LURE_TABLE, columns, null, null, null, null, null);
	}
	
	/**
	 * Retrieves all colors of a given Luretype.
	 * @param luretype = The name of the Lure Type to find colors for.
	 * @return = The Cursor that contains all the colors of the given luretype.
	 */
	public Cursor getLureTypeColors(String luretype)
	{
		String[] columns = new String[] {LURE_KEY_LURECOLOR};
		return this.db.query(CREATE_TABLE_LURE, columns, LURE_KEY_LURETYPE + "=" + luretype,
				null, null, null, null);
	}
	
	/**
	 * Adds a new lake to the "lake" database table
	 * @param lakeName = The name of the lake to add (e.g. Circle Lake, Cedar Lake, etc.)
	 * @param weatherStationId = The 4-letter NOAA NWS weather station id of the closest
	 * weather station.
	 * @return The id number of the row that was added to the database table. 
	 */
	public long addLake(String lakeName)
	{
		ContentValues cv = new ContentValues();
		cv.put(LAKE_KEY_LAKENAME, lakeName);
		long id = this.db.insert(DATABASE_LAKE_TABLE, null, cv);
		return id;
	}
	
	/**
	 * Adds a new species to the "species" database table.
	 * @param speciesName = The name of the species to add (e.g. Northern Pike, Largemouth Bass, etc.)
	 * @return The id number of the row that was added to the database table.
	 */
	public long addSpecies(String speciesName)
	{
		ContentValues cv = new ContentValues();
		cv.put(SPECIES_KEY_SPECIESNAME, speciesName);
		long id = this.db.insert(DATABASE_SPECIES_TABLE, null, cv);
		return id;
	}
	
	public Cursor getSpecies()
	{
		String[] columns = new String[1];
		columns[0] = SPECIES_KEY_SPECIESNAME;
		return this.db.query(DATABASE_SPECIES_TABLE, columns, null, null, null, null, null);
	}
	
	public class FishingDatabaseHelper extends SQLiteOpenHelper
	{		
	
		public FishingDatabaseHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, null, version);
			
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			
			// Create the database tables
			db.execSQL(CREATE_TABLE_FISH);
			db.execSQL(CREATE_TABLE_LAKE);
			db.execSQL(CREATE_TABLE_LURE);
			db.execSQL(CREATE_TABLE_SPECIES);
			db.execSQL(CREATE_TABLE_LURETYPES);
		}
	
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			
		}
	
	}

}
