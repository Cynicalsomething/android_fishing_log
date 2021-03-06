package com.Cynical.android.fishingdatabase;

import java.util.ArrayList;
import java.util.HashMap;

import com.Cynical.android.fishingdatabase.maps.FishMap;
import com.commonsware.cwac.merge.MergeAdapter;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
	public boolean onCreateOptionsMenu(Menu menu) {
    	MenuInflater mi = getMenuInflater();
        mi.inflate(R.menu.home_menu, menu);
        return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId())
		{
		case R.id.menu_settings:
			Intent i = new Intent(this, MainPreferences.class);
			startActivity(i);
			return true;
		default:
			return false;
		}
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
    	final int NEW_FISH = 1;
    	final int VIEW_EDIT_FISH = 2;
    	final int VIEW_FISH_FROM_LAKE = 3;
    	final int VIEW_LURE_COLLECTION = 4;
    	final int ADD_LAKE = 6;
    	final int ADD_LURE = 7;
    	final int ADD_LURE_COLOR = 8;
    	final int ADD_SPECIES = 9;
    	final int CHECK_WEATHER = 11;
    	
		TextView rowTitle = (TextView) v.findViewById(R.id.main_list_row_primary_text);
    	Log.i("Item Clicked", "View: " + 
				 rowTitle.getText() +
				" ID: " + position);
    	/**
    	 * Switch to determine what the user clicked. Then start the associated activity.
    	 */
    	Intent i;
    	switch(position)
    	{
    	case NEW_FISH:
    		i = new Intent(this, NewFish.class);
    		startActivity(i);
    		break;
    	case VIEW_EDIT_FISH:
    		i = new Intent(this, ViewFish.class);
    		startActivity(i);
    		break;
    	case VIEW_FISH_FROM_LAKE:
    		FishingDatabaseAdapter fda = new FishingDatabaseAdapter(this);
    		fda.open();
    		AlertDialog.Builder adb = new AlertDialog.Builder(this);
    		adb.setTitle("Which Lake?");
    		adb.setCancelable(true);
    		Cursor c = fda.getLakes();
    		startManagingCursor(c);
    		adb.setCursor(c, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					FishingDatabaseAdapter fda = 
						new FishingDatabaseAdapter(startingUI.this);
					fda.open();
					Cursor c = fda.getLakes();
					if(c.moveToPosition(which))	
					{
						String lake = c.getString(c.getColumnIndex(
								FishingDatabaseAdapter.LAKE_KEY_LAKENAME));
						Intent i = new Intent(startingUI.this, FishMap.class);
						i.putExtra("lake", lake);
			    		startActivity(i);
					}
					else
					{
						Log.e("DialogInterface.OnClickListener()",
								"List position not reachable");
						dialog.cancel();
					}
					fda.close();
				}
			}, FishingDatabaseAdapter.LAKE_KEY_LAKENAME);
    		AlertDialog dialog = adb.create();
    		dialog.show();
    		fda.close();
    		break;
    	case VIEW_LURE_COLLECTION:
    		i = new Intent(this, ViewLureCollection.class);
    		startActivity(i);
    		break;
    	case ADD_LAKE:
    		i = new Intent(this, AddLake.class);
    		startActivity(i);
    		break;
    	case ADD_LURE:
    		i = new Intent(this, AddLure.class);
    		startActivity(i);
    		break;
    	case ADD_LURE_COLOR:
    		i = new Intent(this, AddLureColor.class);
    		startActivity(i);
    		break;
    	case ADD_SPECIES:
    		i = new Intent(this, AddSpecies.class);
    		startActivity(i);
    		break;
    	case CHECK_WEATHER:
    		i = new Intent(this, CurrentWeather.class);
    		startActivity(i);
    		break;
    	default:
    		break;
    	}
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
        
        /**
         * Sets up the main menu
         */
        SimpleAdapter sa;
        
        switch(menuSection)
        {
        case startingUI.PRIMARY_MENU:
        	map.put(from[0], "New Fish");
            map.put(from[1], "Add a new catch to the database");
            map.put(from[2], R.drawable.ic_fish_smile);
            
            menuList.add(map);
            
            map = new HashMap<String, Object>();
            map.put(from[0], "View/Edit Fish");
            map.put(from[1], "View all your fish and edit them if you'd like");
            map.put(from[2], R.drawable.ic_fish_smile);
            
            menuList.add(map);
            
            map = new HashMap<String, Object>();
            map.put(from[0], "View Fish from Lake");
            map.put(from[1], "View your catches on a certain lake on a map");
            
            menuList.add(map);
            
            map = new HashMap<String, Object>();
            map.put(from[0], "View Lure Collection");
            map.put(from[1], "Take a look at your lure collection and make some changes");
            
            menuList.add(map);
            
            break;
        case startingUI.SECONDARY_MENU:
        	map.put(from[0], "Add Lake");
        	map.put(from[1], "Add a new fishing hole to the database");
        	map.put(from[2], R.drawable.ic_menu_anchor);
        	
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
        	map.put(from[2], R.drawable.ic_menu_cloud);
        	
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