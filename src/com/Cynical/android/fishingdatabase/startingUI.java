package com.Cynical.android.fishingdatabase;

import java.util.ArrayList;
import java.util.HashMap;

import com.commonsware.cwac.merge.MergeAdapter;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class startingUI extends ListActivity {
	private static final int PRIMARY_MENU = 0;
	private static final int SECONDARY_MENU = 1;
	private static final int MISC_MENU = 2;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);
        
        ListView mainList = (ListView) findViewById(android.R.id.list);        
        mainList.setAdapter(createFinalMergeAdapter());
    }
    
    @Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		TextView rowTitle = (TextView) v.findViewById(R.id.main_list_row_primary_text);
    	Log.i("Item Clicked", "View: " + 
				 rowTitle.getText() +
				" ID: " + position);
	}    

	/**
     * Creates a SimpleAdapter for each section of the main menu.
     * @param menuSection = Which menu section to be built.
     * @return A built SimpleAdapter for the given menu section.
     */
    private SimpleAdapter createMenuAdapter(int menuSection)
    {
    	ArrayList<HashMap<String, Object>> menuList = 
        	new ArrayList<HashMap<String,Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        
        String[] from = new String[] {"primary", "secondary", "icon"};
        int[] to = new int[]{
        		R.id.main_list_row_primary_text, 
        		R.id.main_list_row_secondary_text, 
        		R.id.main_list_row_icon};
        int rowLayout = R.layout.main_list_row;
        
        SimpleAdapter sa;
        
        switch(menuSection)
        {
        case startingUI.PRIMARY_MENU:
        	map.put(from[0], "New Fish");
            map.put(from[1], "Add a new catch to the database");
            map.put(from[2], R.drawable.new_fish_icon);
            
            menuList.add(map);
            
            map = new HashMap<String, Object>();
            map.put(from[0], "View/Edit Fish");
            map.put(from[1], "View all your fish and edit them if you'd like");
            
            menuList.add(map);
            
            map = new HashMap<String, Object>();
            map.put(from[0], "View Lure Collection");
            map.put(from[1], "Take a look at your lure collection and make some changes");
            
            menuList.add(map);
            
            break;
        case startingUI.SECONDARY_MENU:
        	map.put(from[0], "Add Lake");
        	map.put(from[1], "Add a new fishing hole to the database");
        	
        	menuList.add(map);
        	
        	map = new HashMap<String, Object>();
        	map.put(from[0], "Add Lure");
        	map.put(from[1], "Add your new favorite lure to the database");
        	
        	menuList.add(map);
        	
        	map = new HashMap<String, Object>();
        	map.put(from[0], "Add Lure Color");
        	map.put(from[1], "Add a new lure color to the database");
        	
        	menuList.add(map);
        	
        	map = new HashMap<String, Object>();
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
    
    /**
     * Creates a MergeAdapter that combines all menu sections and headers into a 
     * final MergeAdapter.
     * @return A built final MergeAdapter.
     */
    private MergeAdapter createFinalMergeAdapter()
    {
    	MergeAdapter ma = new MergeAdapter();
        LayoutInflater li = this.getLayoutInflater();
        View header = li.inflate(R.layout.main_list_primary_header, null);
        ma.addView(header);
        SimpleAdapter sa = createMenuAdapter(PRIMARY_MENU);
        ma.addAdapter(sa);
        header = li.inflate(R.layout.main_list_secondary_header, null);
        ma.addView(header);
        sa = createMenuAdapter(SECONDARY_MENU);
        ma.addAdapter(sa);
        header = li.inflate(R.layout.main_list_misc_header, null);
        ma.addView(header);
        sa = createMenuAdapter(MISC_MENU);
        ma.addAdapter(sa);
        return ma;
    }
}