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

public class SingleFishMap extends MapActivity {

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fishmap);
		MapView mv = (MapView) findViewById(R.id.fishmapview);
		MapController mc = mv.getController();
		mv.setBuiltInZoomControls(true);
		mv.setSatellite(true);
		mc.setZoom(15);
		
		Bundle ex = this.getIntent().getExtras();
		
		long id = ex.getLong("id");
		
		FishingDatabaseAdapter fda = new FishingDatabaseAdapter(this);
		fda.open();
		Cursor c = fda.getSingleFish(id);
		startManagingCursor(c);
		if(c.moveToFirst())
		{
			int latIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_LATITUDE);
			int longIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_LONGITUDE);
			double latitude = c.getDouble(latIndex);
			double longitude = c.getDouble(longIndex);
			GeoPoint gp = new GeoPoint((int) (latitude * 1E6), (int) (longitude * 1E6));
			mc.animateTo(gp);
			
			List<Overlay> mapOverlays = mv.getOverlays();
			Drawable drawable = this.getResources().getDrawable(R.drawable.marker_orange);
			FishItemizedOverlay fio = new FishItemizedOverlay(drawable, this);
			
			int speciesIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_SPECIES);
			int lengthIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_LENGTH);
			int weightIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_WEIGHT);
			
			String species = c.getString(speciesIndex);
			String details = c.getString(lengthIndex) + "\"  " + 
				c.getString(weightIndex) + " lbs."; 
			
			OverlayItem oi = new OverlayItem(gp, species, details);
			fio.addOverlay(oi);
			mapOverlays.add(fio);
		}
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}
	
	

}
