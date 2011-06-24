package com.Cynical.android.fishingdatabase;

import java.util.Date;
import com.Cynical.android.webServices.WeatherRetriever;
import android.app.Activity;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class NewFish extends Activity {

	private LocationManager lm;
	private FDLocationListener fdll;
	private Spinner speciesSpin;
	private Spinner lakeSpin;
	private Spinner lureTypesSpin;
	private Spinner lureColorSpin;
	private WeatherRetriever wr;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newfish);
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		fdll = new FDLocationListener();		
		
		Button save = (Button) findViewById(R.id.save_fish_button);
		Button cancel = (Button) findViewById(R.id.cancel_add_fish_button);
		
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				if(loc != null)
				{
					double latitude = loc.getLatitude();
					double longitude = loc.getLongitude();
					wr = new WeatherRetriever(latitude, longitude);
					wr.retrieveWeather();
					
					FishingDatabaseAdapter fda = new FishingDatabaseAdapter(NewFish.this);
					fda.open();
					
					EditText fishLengthET = (EditText) findViewById(R.id.fish_length);
					EditText fishWeightET = (EditText) findViewById(R.id.fish_weight);
					EditText fishDepthET = (EditText) findViewById(R.id.fish_depth);
					EditText waterTempET = (EditText) findViewById(R.id.water_temp);
					
					double fishLength = 
						Double.parseDouble(fishLengthET.getText().toString());
					double fishWeight = 
						Double.parseDouble(fishWeightET.getText().toString());
					double fishDepth = 
						Double.parseDouble(fishDepthET.getText().toString());
					double waterTemp = 
						Double.parseDouble(waterTempET.getText().toString());
					String species = speciesSpin.getSelectedItem().toString();
					String lakeName = lakeSpin.getSelectedItem().toString();
					String lureType = lureTypesSpin.getSelectedItem().toString();
					String lureColor = lureColorSpin.getSelectedItem().toString();
					
					Date currentDate = new Date();
					
					double temp = wr.getTemp();
					String conditions = wr.getConditions();
					int humidity = wr.getHumidity();
					int windspeed = wr.getWindSpeed();
					String windDir = wr.getWindDir();
					double barPressure = wr.getBarPressure();
					int dewPoint = wr.getDewPoint();
					
					fda.addFish(species, lakeName, fishLength, fishWeight, lureType, 
							lureColor, fishDepth, waterTemp, latitude, longitude, 
							currentDate, temp, conditions, humidity, 
							windspeed, windDir, barPressure, dewPoint);					
					fda.close();
				}
				else
				{
					Toast.makeText(NewFish.this, 
							"Couldn't get location.\nFish not Saved...", 
							Toast.LENGTH_LONG).show();
				}
				
				try {
					lm.removeUpdates(fdll);
				} catch (IllegalArgumentException e) {
					//Do Nothing
				} finally {
					NewFish.this.finish();
				}
			}
		});
		
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				try {
					lm.removeUpdates(fdll);
				} catch (IllegalArgumentException e) {
					//Do Nothing
				} finally {
					NewFish.this.finish();
				}
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, fdll);
		
		speciesSpin = (Spinner) findViewById(R.id.spinner_fishspecies);
		lakeSpin = (Spinner) findViewById(R.id.spinner_lakename);
		lureTypesSpin = (Spinner) findViewById(R.id.spinner_lure_type);
		lureColorSpin = (Spinner) findViewById(R.id.spinner_lure_color);
		FishingDatabaseAdapter fda = new FishingDatabaseAdapter(this);
		fda.open();
		
		//Populate the Fish Species spinner with species in the database
		Cursor c = fda.getSpecies();
		startManagingCursor(c);
		if(c.moveToFirst())
		{
			String species[] = new String[c.getCount()];
			int speciesColumnIndex = 
				c.getColumnIndex(FishingDatabaseAdapter.SPECIES_KEY_SPECIESNAME);
			for(int i = 0; i < species.length; i++)
			{
				species[i] = c.getString(speciesColumnIndex);
				c.moveToNext();
			}
			ArrayAdapter<String> speciesAA = new ArrayAdapter<String>(this, 
					android.R.layout.simple_spinner_item, species);
			speciesAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			speciesSpin.setAdapter(speciesAA);
		}
		//Populate the Lake Name spinner with lakes in the database
		c = fda.getLakes();
		if(c.moveToFirst())
		{
			String[] lakes = new String[c.getCount()];
			int lakesColumnIndex = 
				c.getColumnIndex(FishingDatabaseAdapter.LAKE_KEY_LAKENAME);
			for(int i = 0; i < lakes.length; i++)
			{
				lakes[i] = c.getString(lakesColumnIndex);
				c.moveToNext();
			}
			ArrayAdapter<String> lakesAA = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, lakes);
			lakesAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			lakeSpin.setAdapter(lakesAA);
		}
		//Populate the Lure Type spinner with lure types in the database
		c = fda.getLureTypes();
		if(c.moveToFirst())
		{
			String lureTypes[] = new String[c.getCount()];
			int lureTypeColumnIndex = 
				c.getColumnIndex(FishingDatabaseAdapter.LURETYPES_KEY_LURETYPE);
			for(int i = 0; i < lureTypes.length; i++)
			{
				lureTypes[i] = c.getString(lureTypeColumnIndex);
				c.moveToNext();
			}
			ArrayAdapter<String> lureTypesAA = new ArrayAdapter<String>(this, 
					android.R.layout.simple_spinner_item, lureTypes);
			lureTypesAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			lureTypesSpin.setAdapter(lureTypesAA);	
			lureTypesSpin.setOnItemSelectedListener(new OnItemSelectedListener() {

				@Override
				public void onItemSelected(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					String selectedLureType = arg0.getSelectedItem().toString();
					FishingDatabaseAdapter fda = new FishingDatabaseAdapter(NewFish.this);
					fda.open();
					populateLureColorSpinner(selectedLureType, fda);
					fda.close();
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					
				}
			});
		}
		fda.close();
		
		
	}
	
	private void populateLureColorSpinner(String selectedLureType, FishingDatabaseAdapter fda)
	{
		Spinner lureColorSpinner = (Spinner) findViewById(R.id.spinner_lure_color);
		Cursor c = fda.getLureTypeColors(selectedLureType);
		startManagingCursor(c);
		if(c.moveToFirst())
		{
			String lureColors[] = new String[c.getCount()];
			int lureColorColumnIndex = 
				c.getColumnIndex(FishingDatabaseAdapter.LURE_KEY_LURECOLOR);
			for(int i = 0; i < lureColors.length; i++)
			{
				lureColors[i] = c.getString(lureColorColumnIndex);
				c.moveToNext();
			}
			ArrayAdapter<String> lureColorsAA = new ArrayAdapter<String>(this, 
					android.R.layout.simple_spinner_item, lureColors);
			lureColorsAA.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			lureColorSpinner.setAdapter(lureColorsAA);
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		
		try {
			lm.removeUpdates(fdll);
		} catch (IllegalArgumentException e) {
			//Do Nothing
		}
	}
	
}
