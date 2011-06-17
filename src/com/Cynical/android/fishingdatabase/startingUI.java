package com.Cynical.android.fishingdatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class startingUI extends Activity {
	private static final int PRIMARY_MENU = 0;
	private static final int SECONDARY_MENU = 1;
	private static final int MISC_MENU = 2;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);
        
        ListView primList = (ListView) findViewById(R.id.main_list_primary_actions);
        ListView secList = (ListView) findViewById(R.id.main_list_secondary_actions);
        ListView miscList = (ListView) findViewById(R.id.main_list_misc_actions);
                                
        primList.setAdapter(createMenuAdapter(PRIMARY_MENU));
        secList.setAdapter(createMenuAdapter(SECONDARY_MENU));
        miscList.setAdapter(createMenuAdapter(MISC_MENU));
        
                
        
        /*Button addLure = (Button) findViewById(R.id.add_lure_button);
        Button addLureColor = (Button) findViewById(R.id.add_lurecolor_button);
        Button newFish = (Button) findViewById(R.id.new_fish_button);
        Button addSpecies = (Button) findViewById(R.id.add_species_button);
        Button addLake = (Button) findViewById(R.id.add_lake_button);
        Button miscButton = (Button) findViewById(R.id.main_misc_button);
        
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
				FishingDatabaseAdapter fda = new FishingDatabaseAdapter(startingUI.this);
				fda.deleteDatabase(startingUI.this);
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
        
        miscButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(startingUI.this, FishMap.class);
				startActivity(i);
			}
		});*/
                
    }
    
    private SimpleAdapter createMenuAdapter(int menuSection)
    {
    	ArrayList<HashMap<String, String>> menuList = 
        	new ArrayList<HashMap<String,String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        
        String[] from = new String[] {"primary", "secondary"};
        int[] to = new int[]{R.id.main_list_row_primary_text, R.id.main_list_row_secondary_text};
        int rowLayout = R.layout.main_list_row;
        
        SimpleAdapter sa;
        
        switch(menuSection)
        {
        case startingUI.PRIMARY_MENU:
        	map.put(from[0], "New Fish");
            map.put(from[1], "Add a new catch to the database");
            menuList.add(map);
            map = new HashMap<String, String>();
            map.put(from[0], "View/Edit Fish");
            map.put(from[1], "View all your fish and edit them if you'd like");
            menuList.add(map);
            map = new HashMap<String, String>();
            map.put(from[0], "View Lure Collection");
            map.put(from[1], "Take a look at your lure collection and make some changes");
            menuList.add(map);
            break;
        case startingUI.SECONDARY_MENU:
        	map.put(from[0], "Add Lake");
        	map.put(from[1], "Add a new fishing hole to the database");
        	menuList.add(map);
        	map = new HashMap<String, String>();
        	map.put(from[0], "Add Lure");
        	map.put(from[1], "Add your new favorite lure to the database");
        	menuList.add(map);
        	map = new HashMap<String, String>();
        	map.put(from[0], "Add Lure Color");
        	map.put(from[1], "Add a new lure color to the database");
        	menuList.add(map);
        	map = new HashMap<String, String>();
        	map.put(from[0], "Add Fish Species");
        	map.put(from[1], "Add a new fish species to the database");
        	menuList.add(map);
        	break;
        case startingUI.MISC_MENU:
        	map.put(from[0], "Check Weather");
        	map.put(from[1], "Check the current weather based on your location");
        	menuList.add(map);
        	break;
        }
        sa = new SimpleAdapter(this, menuList, rowLayout, from, to);
        return sa;
    }
}