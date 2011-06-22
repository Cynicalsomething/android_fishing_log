package com.Cynical.android.fishingdatabase;

import com.Cynical.android.webServices.WeatherRetriever;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

public class CurrentWeather extends Activity {
	
	LocationManager lm;
	WeatherLocationListener wll;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weatherinfo);
		lm = (LocationManager) getSystemService(LOCATION_SERVICE);
		ProgressDialog dialog = ProgressDialog.show(this, "", 
				"Getting current location...", true, true, new OnCancelListener() {
					
					@Override
					public void onCancel(DialogInterface dialog) {
						lm.removeUpdates(wll);
						
					}
				});
		wll = new WeatherLocationListener(dialog, this);
		lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 
				0, 0, wll);
	}
	
	protected void updateWeather(Location loc)
	{
		lm.removeUpdates(wll);
		double lat = loc.getLatitude();
		double lon = loc.getLongitude();
		WeatherRetriever wr = new WeatherRetriever(lat, lon);
        wr.retrieveWeather();
        TextView tempTV = (TextView) findViewById(R.id.tempTV);
        TextView conditionsTV = (TextView) findViewById(R.id.conditionsTV);
        TextView humidityTV = (TextView) findViewById(R.id.humidityTV);
        TextView windSpeedTV = (TextView) findViewById(R.id.windSpeedTV);
        TextView windDirTV = (TextView) findViewById(R.id.windDirTV);
        TextView barPressureTV = (TextView) findViewById(R.id.barPressureTV);
        TextView dewPtTV = (TextView) findViewById(R.id.dewPtTV);
        
        tempTV.setText(Double.toString(wr.getTemp()) + " F");
        conditionsTV.setText(wr.getConditions());
        humidityTV.setText(Integer.toString(wr.getHumidity()) + "%");
        windSpeedTV.setText(Integer.toString(wr.getWindSpeed()));
        windDirTV.setText(wr.getWindDir());
        barPressureTV.setText(Double.toString(wr.getBarPressure()) + " in.");
        dewPtTV.setText(Integer.toString(wr.getDewPoint()) + " F");
	}
	
}
