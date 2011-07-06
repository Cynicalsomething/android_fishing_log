package com.Cynical.android.fishingdatabase;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class EditFish extends Activity {

	private Spinner speciesSpin;
	private Spinner lakeSpin;
	private Spinner lureTypesSpin;
	private Spinner lureColorSpin;
	private FishingDatabaseAdapter fda;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.newfish);		
		
		Button save = (Button) findViewById(R.id.save_fish_button);
		Button cancel = (Button) findViewById(R.id.cancel_add_fish_button);
		
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//update row in database
			}
		});
		
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditFish.this.finish();
			}
		});
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		speciesSpin = (Spinner) findViewById(R.id.spinner_fishspecies);
		lakeSpin = (Spinner) findViewById(R.id.spinner_lakename);
		lureTypesSpin = (Spinner) findViewById(R.id.spinner_lure_type);
		lureColorSpin = (Spinner) findViewById(R.id.spinner_lure_color);
		fda = new FishingDatabaseAdapter(this);
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
		startManagingCursor(c);
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
		startManagingCursor(c);
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
					populateLureColorSpinner(selectedLureType, fda);
				}

				@Override
				public void onNothingSelected(AdapterView<?> arg0) {
					
				}
			});
		}
		
		
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
	protected void onPause() {
		super.onPause();
		fda.close();
	}

	@Override
	protected void onStop() {
		super.onStop();
		
		try {
			fda.close();
		} catch (IllegalArgumentException e) {
			//Do Nothing
		}
	}
	
}
