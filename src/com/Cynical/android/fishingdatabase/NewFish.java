package com.Cynical.android.fishingdatabase;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class NewFish extends Activity {

	private LocationManager lm;
	private FDLocationListener fdll;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editfish);
		lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		fdll = new FDLocationListener();		
		
		Button save = (Button) findViewById(R.id.save_fish_button);
		Button cancel = (Button) findViewById(R.id.cancel_add_fish_button);
		
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// TODO Pull location and save info to database
				Location loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
				if(loc != null)
				{
					double latitude = loc.getLatitude();
					double longitude = loc.getLongitude();
					
					
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
		
		Spinner speciesSpin = (Spinner) findViewById(R.id.spinner_fishspecies);
		Spinner lureTypesSpin = (Spinner) findViewById(R.id.spinner_lure_type);
		Spinner lureColorSpin = (Spinner) findViewById(R.id.spinner_lure_color);
		FishingDatabaseAdapter fda = new FishingDatabaseAdapter(this);
		fda.open();
		
		//Populate the Fish Species spinner with species in the database
		Cursor c = fda.getSpecies();		
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
			c.close();
		}
		
		//Populate the Lure Color spinner for the default lure type
		String selectedLureType = (String) lureTypesSpin.getSelectedItem();
	}
	
	private void populateLureColorSpinner(String selectedLureType, FishingDatabaseAdapter fda)
	{
		Spinner lureColorSpinner = (Spinner) findViewById(R.id.spinner_lure_color);
		Cursor c = fda.getLureTypeColors(selectedLureType);
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
		c.close();
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
