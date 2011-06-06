package com.Cynical.android.fishingdatabase;

import java.io.Serializable;

public class Lake implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String weatherStation;
	
	public Lake(String name, String weatherStation)
	{
		this.name = name;
		this.weatherStation = weatherStation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWeatherStation() {
		return weatherStation;
	}

	public void setWeatherStation(String weatherStation) {
		this.weatherStation = weatherStation;
	}
	
	

}
