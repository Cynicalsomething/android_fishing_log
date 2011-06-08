package com.Cynical.android.fishingdatabase;

import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;

public class NewFish extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editfish);
		LocationManager lm = 
			(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		FDLocationListener fdll = new FDLocationListener();
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, fdll);
		
	}
	
}
