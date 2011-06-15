package com.Cynical.android.fishingdatabase;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

import android.os.Bundle;

public class FishMap extends MapActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fishmap);
		MapView mv = (MapView) findViewById(R.id.fishmapview);
		MapController mc = mv.getController();
		mv.setBuiltInZoomControls(true);
		mv.setSatellite(true);
		GeoPoint gp = new GeoPoint((int) (44.47 * 1E6), (int) (-93.15 * 1E6));
		mc.animateTo(gp);
		
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	
}
