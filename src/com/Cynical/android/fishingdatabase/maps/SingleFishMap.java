package com.Cynical.android.fishingdatabase.maps;

import java.util.List;

import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.Cynical.android.fishingdatabase.FishingDatabaseAdapter;
import com.Cynical.android.fishingdatabase.R;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

//TODO Merge this class with FishMap.java
/**
 * Activity to display a map and marker of one single fish in the database.
 * @author Adam
 *
 */
public class SingleFishMap extends MapActivity {

	private MapView mv;
	private MapController mc;
	private FishingDatabaseAdapter fda;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fishmap);
		mv = (MapView) findViewById(R.id.fishmapview);
		mc = mv.getController();
		mv.setBuiltInZoomControls(true);
		mv.setSatellite(true);
		mc.setZoom(15);
		
		Bundle extra = this.getIntent().getExtras();
		
		long id = extra.getLong("id");
		
		fda = new FishingDatabaseAdapter(this);
		fda.open();
		Cursor c = fda.getSingleFish(id);
		startManagingCursor(c);
		if(c.moveToFirst())
		{
			//Get Latitude and Longitude of the catch and move to that location 
			int latIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_LATITUDE);
			int longIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_LONGITUDE);
			double latitude = c.getDouble(latIndex);
			double longitude = c.getDouble(longIndex);
			GeoPoint gp = new GeoPoint((int) (latitude * 1E6), (int) (longitude * 1E6));
			mc.animateTo(gp);
			
			//Setup overlay objects
			List<Overlay> mapOverlays = mv.getOverlays();
			Drawable drawable = this.getResources().getDrawable(R.drawable.marker_orange);
			FishBalloonItemizedOverlay fio = new FishBalloonItemizedOverlay(drawable, mv);
			
			//Get index for details
			int speciesIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_SPECIES);
			int lengthIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_LENGTH);
			int weightIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_WEIGHT);
			int lureTypeIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_LURETYPE);
			int lureColorIndex = c.getColumnIndex(
					FishingDatabaseAdapter.FISH_KEY_LURECOLOR);
			
			//Construct detailed strings
			String species = c.getString(speciesIndex);
			String details = c.getString(lengthIndex) + "\", " + 
				c.getString(weightIndex) + " lbs. \n" + c.getString(lureTypeIndex) + " (" + 
				c.getString(lureColorIndex) + ")"; 
			
			//Add overlays to map
			OverlayItem oi = new OverlayItem(gp, species, details);
			fio.addOverlay(oi);
			mapOverlays.add(fio);
		}
		fda.close();
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	

}
