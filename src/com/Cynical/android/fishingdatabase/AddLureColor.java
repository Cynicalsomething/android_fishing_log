package com.Cynical.android.fishingdatabase;

import android.app.Activity;
import android.database.Cursor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddLureColor extends Activity {
	
	@Override
	protected void onStart() {
		super.onStart();
		setContentView(R.layout.addlurecolor);
		Spinner spin = (Spinner) findViewById(R.id.spinner_addlure_luretype);
		FishingDatabaseAdapter fda = new FishingDatabaseAdapter(this);
		fda.open();
		Cursor c = fda.getLureTypes();
		if(c.moveToFirst())
		{
			String[] lureTypes = new String[c.getCount()];
			int lureTypeColumnIndex = 
				c.getColumnIndex(FishingDatabaseAdapter.LURETYPES_KEY_LURETYPE);
			for(int i = 0; i < lureTypes.length; i++)
			{
				lureTypes[i] = c.getString(lureTypeColumnIndex);
				c.moveToNext();
			}
			ArrayAdapter<String> aa = 
				new ArrayAdapter<String>(this, 
						android.R.layout.simple_spinner_item, lureTypes);
			aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
			spin.setAdapter(aa);
		}
		else
		{
			Toast.makeText(this, 
					"You must add a Lure before adding a color...",
					Toast.LENGTH_LONG).show();
			fda.close();
			this.finish();
			return;
		}
		
		Button save = (Button) findViewById(R.id.save_lure_button);
		Button cancel = (Button) findViewById(R.id.cancel_add_lure_button);
		
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText etLureColor = (EditText) findViewById(R.id.lurecolor_edittext);
				Spinner s = (Spinner) findViewById(R.id.spinner_addlure_luretype);
				String lureType = (String) s.getSelectedItem();
				String lureColor = etLureColor.getText().toString();
				FishingDatabaseAdapter fdaCV = new FishingDatabaseAdapter(AddLureColor.this);
				fdaCV.open();
				fdaCV.addLureWithColor(lureType, lureColor);
				fdaCV.close();
				AddLureColor.this.finish();
				return;
			}
		});
		
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AddLureColor.this.finish();
				return;
			}
		});
		c.close();
		fda.close();
	}
	
}
