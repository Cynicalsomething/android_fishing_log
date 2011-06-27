package com.Cynical.android.fishingdatabase;

import android.app.ExpandableListActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleCursorTreeAdapter;

public class ViewLureCollection extends ExpandableListActivity {
	
	private String[] groupColumns = {FishingDatabaseAdapter.LURETYPES_KEY_ROWID, 
			FishingDatabaseAdapter.LURETYPES_KEY_LURETYPE};
	private String[] childColumns = {FishingDatabaseAdapter.LURE_KEY_ROWID, 
			FishingDatabaseAdapter.LURE_KEY_LURECOLOR};
	
	private int groupLayout = android.R.layout.simple_expandable_list_item_1;
	private int childLayout = android.R.layout.simple_expandable_list_item_1;
	
	private int[] groupTo = {android.R.id.empty, android.R.id.text1};
	private int[] childTo = {android.R.id.empty, android.R.id.text1};
	
	private FishingDatabaseAdapter fda;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_lure_collection);
		
		fda = new FishingDatabaseAdapter(this);
		fda.open();
		Cursor c = fda.getLureTypes();
		
		ExpandableListView elv = (ExpandableListView) findViewById(android.R.id.list);
		
		LureExpandableListAdapter lelv = new LureExpandableListAdapter(this, c, 
				groupLayout, groupColumns, groupTo, childLayout, childColumns, childTo);
		
		elv.setAdapter(lelv);
	}


	@Override
	protected void onDestroy() {
		super.onDestroy();
		fda.close();
	}


	private class LureExpandableListAdapter extends SimpleCursorTreeAdapter
	{

		public LureExpandableListAdapter(Context context, Cursor cursor,
				int groupLayout, String[] groupFrom, int[] groupTo,
				int childLayout, String[] childFrom, int[] childTo) {
			super(context, cursor, groupLayout, groupFrom, groupTo, childLayout, childFrom,
					childTo);
		}

		@Override
		protected Cursor getChildrenCursor(Cursor groupCursor) {
			int lureTypeIndex = groupCursor.getColumnIndex(
					FishingDatabaseAdapter.LURETYPES_KEY_LURETYPE);
			String lureType = groupCursor.getString(lureTypeIndex);
			Cursor c = fda.getLureTypeColors(lureType);
			startManagingCursor(c);
			return c;
		}
		
	}

}
