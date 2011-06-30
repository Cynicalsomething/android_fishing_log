package com.Cynical.android.fishingdatabase;

import com.Cynical.android.fishingdatabase.maps.FishMap;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.view_edit_fish_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i;
		switch(item.getItemId())
		{
		case R.id.view_50_on_map:
			i = new Intent(this, FishMap.class);
			startActivity(i);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		Log.i("Fish Click", "Id number: " + id);
		Intent i = new Intent(this, ViewFishDetails.class);
		i.putExtra("id", id);
		startActivity(i);
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
