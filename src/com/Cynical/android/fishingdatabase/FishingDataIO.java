package com.Cynical.android.fishingdatabase;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.Vector;

import android.content.Context;
import android.util.Log;

public class FishingDataIO {
	
	private final String LURESFILENAME = "lures";
	private final String LAKESFILENAME = "lakes";
	private final String SPECIESFILENAME = "species";
	
	private Context ctx;
	
	public FishingDataIO(Context ctx)
	{
		this.ctx = ctx;
	}
	
	public void saveLures(Vector<Lure> lures)
	{
		try {
			FileOutputStream fos = ctx.openFileOutput(LURESFILENAME, Context.MODE_PRIVATE);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(lures);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			Log.e("FileNotFoundException", "saveLures()");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("IOException", "saveLures()");
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Vector<Lure> loadLures()
	{
		Vector<Lure> lures = new Vector<Lure>();
		FileInputStream fis;
		ObjectInputStream ois;
		try {
			fis = ctx.openFileInput(LURESFILENAME);
			ois = new ObjectInputStream(fis);
			lures = (Vector<Lure>) ois.readObject();
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			Log.e("FileNotFoundException", "loadLures()");
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			Log.e("StreamCorruptedException", "loadLures()");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("IOException", "loadLures()");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			Log.e("ClassNotFoundException", "loadLures()");
			e.printStackTrace();
		}
		return lures;
	}
	
	public void saveLakes(Vector<Lake> lakes)
	{
		FileOutputStream fos;
		ObjectOutputStream oos;
		try {
			fos = ctx.openFileOutput(LAKESFILENAME, 0);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(lakes);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			Log.e("FileNotFoundException", "saveLakes()");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("IOException", "saveLakes()");
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Vector<Lake> loadLakes()
	{
		Vector<Lake> lakes = new Vector<Lake>();
		FileInputStream fis;
		ObjectInputStream ois;
		try {
			fis = ctx.openFileInput(LAKESFILENAME);
			ois = new ObjectInputStream(fis);
			lakes = (Vector<Lake>) ois.readObject();
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			Log.e("FileNotFoundException", "loadLakes()");
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			Log.e("StreamCorruptedException", "loadLakes()");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("IOException", "loadLakes()");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			Log.e("ClassNotFoundException", "loadLakes()");
			e.printStackTrace();
		}
		return lakes;
	}
	
	public void saveFishSpecies(Vector<FishSpecies> species)
	{
		FileOutputStream fos;
		ObjectOutputStream oos;
		try {
			fos = ctx.openFileOutput(SPECIESFILENAME, 0);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(species);
			oos.close();
			fos.close();
		} catch (FileNotFoundException e) {
			Log.e("FileNotFoundException", "saveFishSpecies()");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("IOException", "saveFishSpecies()");
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public Vector<FishSpecies> loadFishSpecies()
	{
		Vector<FishSpecies> species = new Vector<FishSpecies>();
		FileInputStream fis;
		ObjectInputStream ois;
		try {
			fis = ctx.openFileInput(SPECIESFILENAME);
			ois = new ObjectInputStream(fis);
			species = (Vector<FishSpecies>) ois.readObject();
			ois.close();
			fis.close();
		} catch (FileNotFoundException e) {
			Log.e("FileNotFoundException", "loadFishSpecies()");
			e.printStackTrace();
		} catch (StreamCorruptedException e) {
			Log.e("StreamCorruptedException", "loadFishSpecies()");
			e.printStackTrace();
		} catch (IOException e) {
			Log.e("IOException", "loadLakes()");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			Log.e("ClassNotFoundException", "loadFishSpecies()");
			e.printStackTrace();
		}
		return species;
	}

}
