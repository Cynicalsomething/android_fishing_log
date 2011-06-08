package com.Cynical.android.fishingdatabase;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddSpecies extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addspecies);
		Button save = (Button) findViewById(R.id.save_species_button);
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				EditText etSpecies = (EditText) findViewById(R.id.addspecies_edittext_species);
				String species = etSpecies.getText().toString();
				if(species == "")
				{
					Toast.makeText(AddSpecies.this, "You didn't type in a species name...",
							Toast.LENGTH_LONG).show();
				}
				else
				{
					FishingDatabaseAdapter fda = new FishingDatabaseAdapter(AddSpecies.this);
					fda.open();
					Cursor c = fda.getSpecies();
					if(c.moveToFirst());
					{
						for(int i = 0; i < c.getCount(); i++)
						{
							String querySpecies = c.getString(0);
							if(querySpecies.equalsIgnoreCase(species))
							{
								Toast.makeText(AddSpecies.this, 
										"That species is already in the database...",
										Toast.LENGTH_LONG).show();
								AddSpecies.this.finish();
								return;
							}
							c.moveToNext();
						}
					}
					fda.addSpecies(species);
					fda.close();
					AddSpecies.this.finish();
				}
			}
		});
	}
	

}
