package com.Cynical.android.fishingdatabase.maps;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import com.readystatesoftware.mapviewballoons.BalloonItemizedOverlay;
/**
 * Uses android-mapviewballoons by jgilfelt (Jeff Gilfelt) of jeffgilfelt.com
 * @author Adam
 *
 */
public class FishBalloonItemizedOverlay extends BalloonItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mCtx;
	
	public FishBalloonItemizedOverlay(Drawable defaultMarker, MapView mapView) {
		super(boundCenterBottom(defaultMarker), mapView);
		this.mCtx = mapView.getContext();
	}
	
	/**
	 * Adds an overlay to the mOverlays ArrayList object and calls populate().
	 * @param overlay = The overlay to add to the map.
	 */
	public void addOverlay(OverlayItem overlay)
	{
		mOverlays.add(overlay);
		populate();
	}
	
	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}

	@Override
	protected boolean onBalloonTap(int index, OverlayItem item) {
		Toast.makeText(mCtx, "Ballon tapped for overlay index " + index, 
				Toast.LENGTH_LONG).show();
		return true;
		
	}
	
	
	
}
