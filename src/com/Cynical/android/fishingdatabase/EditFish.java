package com.Cynical.android.fishingdatabase;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class EditFish extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editfish);
	}

	@Override
	protected void onStart() {
		super.onStart();
		Spinner spin = (Spinner) findViewById(R.id.spinner_fishspecies);
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
			ArrayAdapter<String> ad = new ArrayAdapter<String>(this, 
					android.R.layout.simple_spinner_item, species);
			ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spin.setAdapter(ad);
		}
		
		//Populate the Lure Type spinner with Lure types in the database
		//c = fda.getLures();
		
		fda.close();		
	}
	
}
