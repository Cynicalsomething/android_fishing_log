package com.Cynical.android.fishingdatabase;

import com.Cynical.android.webServices.WeatherRetriever;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class CurrentWeather extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weatherinfo);
		WeatherRetriever wr = new WeatherRetriever(44.47,-93.15);
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