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
        Button newFish = (Button) findViewById(R.id.new_fish_button);
        Button addSpecies = (Button) findViewById(R.id.add_species_button);
        Button addLake = (Button) findViewById(R.id.add_lake_button);
        
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
        
        newFish.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(startingUI.this, NewFish.class);
				startActivity(i);
				/*FishingDatabaseAdapter fda = new FishingDatabaseAdapter(startingUI.this);
				fda.deleteDatabase(startingUI.this);*/
			}
		});
        
        addSpecies.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(startingUI.this, AddSpecies.class);
				startActivity(i);
			}
		});
        
        addLake.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(startingUI.this, AddLake.class);
				startActivity(i);
			}
		});
                
    }
}