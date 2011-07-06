package com.Cynical.android.fishingdatabase;

import com.Cynical.android.fishingdatabase.maps.SingleFishMap;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ViewFishDetails extends Activity {
	
	private long id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle extras = this.getIntent().getExtras();
		if(!extras.isEmpty())
		{
			id = extras.getLong("id");
			setContentView(R.layout.fish_details);
			
			FishingDatabaseAdapter fda = new FishingDatabaseAdapter(this);
			fda.open();
			Cursor c = fda.getSingleFish(id);
			startManagingCursor(c);
			if(c.moveToFirst())
			{
				int speciesIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_SPECIES);
				int lakeIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_LAKENAME);
				int datetimeIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_DATE_TIME);
				int lengthIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_LENGTH);
				int weightIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_WEIGHT);
				int depthIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_DEPTH);
				int luretypeIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_LURETYPE);
				int lurecolorIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_LURECOLOR);
				int watertempIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_WATERTEMP);
				int outTempIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_TEMP);
				int conditionsIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_CONDITIONS);
				int humidityIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_HUMIDITY);
				int windDirIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_WINDDIR);
				int windSpeedIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_WINDSPEED);
				int barPressureIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_BARPRESSURE);
				int dewPtIndex = c.getColumnIndex(FishingDatabaseAdapter.FISH_KEY_DEWPOINT);
				
				TextView species = (TextView) findViewById(R.id.fish_details_species);
				TextView lake = (TextView) findViewById(R.id.fish_details_lake);
				TextView datetime = (TextView) findViewById(R.id.fish_details_datetime);
				TextView length = (TextView) findViewById(R.id.fish_details_length);
				TextView weight = (TextView) findViewById(R.id.fish_details_weight);
				TextView depth = (TextView) findViewById(R.id.fish_details_depth);
				TextView lure = (TextView) findViewById(R.id.fish_details_lure);
				TextView watertemp = (TextView) findViewById(R.id.fish_details_water_temp);
				TextView outTemp = (TextView) findViewById(R.id.fish_details_temp);
				TextView conditions = (TextView) findViewById(R.id.fish_details_conditions);
				TextView humidity = (TextView) findViewById(R.id.fish_details_humidity);
				TextView wind = (TextView) findViewById(R.id.fish_details_wind);
				TextView barPressure = (TextView) findViewById(R.id.fish_details_bar_pressure);
				TextView dewPt = (TextView) findViewById(R.id.fish_details_dew_pt);
				
				species.setText(c.getString(speciesIndex));
				lake.setText(c.getString(lakeIndex));
				datetime.setText(c.getString(datetimeIndex));
				length.setText(c.getString(lengthIndex) + " in.");
				weight.setText(c.getString(weightIndex) + " lbs.");
				depth.setText(c.getString(depthIndex) + " ft.");
				lure.setText(c.getString(luretypeIndex) + " / " + 
						c.getString(lurecolorIndex));
				watertemp.setText(c.getString(watertempIndex) + '\u00B0' + "F");
				outTemp.setText(c.getString(outTempIndex) + '\u00B0' + "F");
				conditions.setText(c.getString(conditionsIndex));
				humidity.setText(c.getString(humidityIndex) + "%");
				wind.setText(c.getString(windSpeedIndex) + "mph " + c.getString(windDirIndex));
				barPressure.setText(c.getString(barPressureIndex) + "\"");
				dewPt.setText(c.getString(dewPtIndex) + '\u00B0' + "F");
			}
		}
		else
		{
			Log.e("ViewFishDetails.onCreate()", "Bundle empty");
			Toast.makeText(this, "Error! Fish not found...", Toast.LENGTH_LONG);
			this.finish();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.menu_fish_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		Intent i;
		switch(item.getItemId())
		{
		case R.id.menu_view_map:
			i = new Intent(this, SingleFishMap.class);
			i.putExtra("id", id);
			startActivity(i);
			break;
		case R.id.menu_edit_fish:
			i = new Intent(this, EditFish.class);
			i.putExtra("id", id);
			startActivity(i);
			break;
		case R.id.menu_delete_fish:
			AlertDialog.Builder adb = new AlertDialog.Builder(this);
			adb.setMessage("Are you sure you want to remove this fish?");
			adb.setPositiveButton("Yes", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					FishingDatabaseAdapter fda = 
						new FishingDatabaseAdapter(ViewFishDetails.this);
					fda.open();
					boolean success = fda.removeFish(id);
					fda.close();
					if(success)
					{
						Toast.makeText(ViewFishDetails.this, 
								"Fish removed successfully!", Toast.LENGTH_LONG).show();
					}
					else
					{
						Toast.makeText(ViewFishDetails.this, 
								"Fish could not be removed...", Toast.LENGTH_LONG).show();
					}
					finish();
				}
			});
			adb.setCancelable(false);
			adb.setNegativeButton("Cancel", new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
			AlertDialog dialog = adb.create();
			dialog.show();
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	
}
