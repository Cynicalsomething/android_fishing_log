package com.Cynical.android.fishingdatabase;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class ViewSpecies extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewspecies);
		FishingDatabaseAdapter fda = new FishingDatabaseAdapter(this);
		fda.open();
		Cursor c = fda.getSpecies();
		startManagingCursor(c);
		String[] columns = new String[] {FishingDatabaseAdapter.SPECIES_KEY_SPECIESNAME};
		int[] bind = new int[] {R.id.list_species_rowtext};
		SimpleCursorAdapter sca = new SimpleCursorAdapter(this, R.layout.viewspecies_row, 
				c, columns, bind);
		this.setListAdapter(sca);
		
		ListView lv = (ListView) findViewById(android.R.id.list);
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				
				return false;
			}
		});
	}

	
}
