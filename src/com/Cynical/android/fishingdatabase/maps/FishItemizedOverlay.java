package com.Cynical.android.fishingdatabase.maps;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;
/**
 * A sub-class of ItemizedOverlay that allows us to have collection of overlay items
 * to add to a map.
 * @author Adam
 *
 */
public class FishItemizedOverlay extends ItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mCtx;
	
	public FishItemizedOverlay(Drawable defaultMarker, Context ctx) {
		super(boundCenterBottom(defaultMarker));
		this.mCtx = ctx;
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
	protected boolean onTap(int index) {
		OverlayItem item = mOverlays.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(mCtx);
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());
		dialog.show();
		return true;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mOverlays.get(i);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}
	
	

}
