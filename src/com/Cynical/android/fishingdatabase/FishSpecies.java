package com.Cynical.android.fishingdatabase;

import java.io.Serializable;

public class FishSpecies implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String species;
	
	public FishSpecies(String species)
	{
		this.species = species;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

}
