package com.Cynical.android.fishingdatabase;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddLure extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addlure);
		
		Button save = (Button) findViewById(R.id.addlure_save_button);
		Button cancel = (Button) findViewById(R.id.addlure_cancel_button);
		
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText etLureType = (EditText) findViewById(R.id.addlure_luretype);
				String luretype = etLureType.getText().toString();
				boolean notFoundInDatabase = true;
				FishingDatabaseAdapter fda = new FishingDatabaseAdapter(AddLure.this);
				fda.open();
				Cursor c = fda.getLureTypes();
				if(luretype.equals(""))
				{
					Toast.makeText(AddLure.this, 
							"Lure Type cannot be blank...", Toast.LENGTH_LONG).show();					
				}
				else if(c.moveToFirst())
				{
					for(int i = 0; i < c.getCount(); i++)
					{
						if(c.getString(0).equalsIgnoreCase(luretype))
						{
							Toast.makeText(AddLure.this, 
									"That lure is already in the database...",
									Toast.LENGTH_LONG).show();
							notFoundInDatabase = false;
							break;
						}
						c.moveToNext();
					}
					c.close();
				}
				if(notFoundInDatabase)
				{
					fda.addLure(luretype);
					fda.close();
					AddLure.this.finish();
					return;
				}
				
			}
		});
		
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AddLure.this.finish();
				return;
			}
		});
	}
	
}
