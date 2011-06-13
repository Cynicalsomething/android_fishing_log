package com.Cynical.android.fishingdatabase;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddLake extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addlake);
		
		Button save = (Button) findViewById(R.id.addlake_save_button);
		Button cancel = (Button) findViewById(R.id.addlake_cancel_button);
		
		save.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				EditText etLakeName = (EditText) findViewById(R.id.addlake_name);
				String lakeName = etLakeName.getText().toString();
				boolean notFoundInDatabase = true;
				FishingDatabaseAdapter fda = new FishingDatabaseAdapter(AddLake.this);
				fda.open();
				Cursor c = fda.getLakes();
				if(lakeName.equals(""))
				{
					Toast.makeText(AddLake.this, 
							"Lake name cannot be blank...", Toast.LENGTH_LONG).show();					
				}
				else if(c.moveToFirst())
				{
					for(int i = 0; i < c.getCount(); i++)
					{
						if(c.getString(0).equalsIgnoreCase(lakeName))
						{
							Toast.makeText(AddLake.this, 
									"That lake is already in the database...",
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
					fda.addLake(lakeName);
				}
				fda.close();
				AddLake.this.finish();
				return;
			}
		});
		
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AddLake.this.finish();
				return;
			}
		});
	}
	

}
