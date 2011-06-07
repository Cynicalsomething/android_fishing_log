package com.Cynical.android.fishingdatabase;

import java.util.Vector;

import com.Cynical.android.webServices.WeatherRetriever;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class startingUI extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main2);
        
        Button addLure = (Button) findViewById(R.id.add_lure_button);
        Button addLureColor = (Button) findViewById(R.id.add_lurecolor_button);
        
        addLure.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(startingUI.this, AddLure.class);
				startActivity(i);
			}
		});
        
        addLureColor.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(startingUI.this, AddLureColor.class);
				startActivity(i);
			}
		});
        /*WeatherRetriever wr = new WeatherRetriever(44.47,-93.15);
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
        dewPtTV.setText(Integer.toString(wr.getDewPoint()) + " F");*/
        
        /*FishingDatabaseAdapter fda = new FishingDatabaseAdapter(this);
        fda.deleteDatabase(this);
        fda.open();
        fda.addLure("Buzzbait", "Blue");
        fda.close();*/
    }
}