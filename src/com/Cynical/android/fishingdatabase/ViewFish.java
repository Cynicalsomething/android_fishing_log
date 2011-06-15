package com.Cynical.android.fishingdatabase;

import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ViewFish extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewlist);
		FishingDatabaseAdapter fda = new FishingDatabaseAdapter(this);
		fda.open();
		Cursor c = fda.getFish(50);
		startManagingCursor(c);
		String[] columns = new String[] {FishingDatabaseAdapter.FISH_KEY_SPECIES,
				FishingDatabaseAdapter.FISH_KEY_LAKENAME,
				FishingDatabaseAdapter.FISH_KEY_LENGTH,
				FishingDatabaseAdapter.FISH_KEY_WEIGHT};
		int[] bind = new int[] {R.id.fish_species_list_field,
				R.id.fish_lake_list_field,
				R.id.fish_length_list_field,
				R.id.fish_weight_list_field};
		FishListCursorAdapter sca = new FishListCursorAdapter(this, R.layout.view_fish_list_row, 
				c, columns, bind);
		this.setListAdapter(sca);
		
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
	}
	
	private class FishListCursorAdapter extends SimpleCursorAdapter
	{

		public FishListCursorAdapter(Context context, int layout, Cursor c,
				String[] from, int[] to) {
			super(context, layout, c, from, to);
			
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			int speciesColumnIndex = 
				cursor.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_SPECIES);
			int lakeNameColumnIndex = 
				cursor.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_LAKENAME);
			int lengthColumnIndex = 
				cursor.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_LENGTH);
			int weightColumnIndex = 
				cursor.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_WEIGHT);
			String species = cursor.getString(speciesColumnIndex);
			String lake = cursor.getString(lakeNameColumnIndex);
			String length = Double.toString(cursor.getDouble(lengthColumnIndex));
			String weight = Double.toString(cursor.getDouble(weightColumnIndex));
			length = length + "\"";
			weight = weight + " lbs.";
			
			TextView speciesTV = (TextView) view.findViewById(R.id.fish_species_list_field);
			TextView lakeTV = (TextView) view.findViewById(R.id.fish_lake_list_field);
			TextView lengthTV = (TextView) view.findViewById(R.id.fish_length_list_field);
			TextView weightTV = (TextView) view.findViewById(R.id.fish_weight_list_field);
			
			speciesTV.setText(species);
			lakeTV.setText(lake);
			lengthTV.setText(length);
			weightTV.setText(weight);
		}
		
	}
}
