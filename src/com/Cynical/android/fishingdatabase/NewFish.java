package com.Cynical.android.fishingdatabase;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

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
				
				try {
					lm.removeUpdates(fdll);
				} catch (IllegalArgumentException e) {
					//Do Nothing
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
