package com.Cynical.android.fishingdatabase;

import android.app.ProgressDialog;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

public class WeatherLocationListener implements LocationListener {
	
	ProgressDialog dialog;
	CurrentWeather ctx;
	
	public WeatherLocationListener(ProgressDialog dialog, CurrentWeather ctx)
	{
		this.dialog = dialog;
		this.ctx = ctx;
	}

	@Override
	public void onLocationChanged(Location location) {
		long currentTime = System.currentTimeMillis();
		long locationTime = location.getTime();
		if((currentTime - locationTime) <= 60000 )
		{
			dialog.dismiss();
			ctx.updateWeather(location);
		}
	}

	@Override
	public void onProviderDisabled(String provider) {

	}

	@Override
	public void onProviderEnabled(String provider) {

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {

	}

}
