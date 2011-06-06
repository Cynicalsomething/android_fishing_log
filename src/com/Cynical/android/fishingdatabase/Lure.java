package com.Cynical.android.fishingdatabase;

import java.io.Serializable;
import java.util.Vector;

public class Lure implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String brand;
	private Vector<String> colors;
	
	public Lure(String name, String brand, Vector<String> colors)
	{
		this.name = name;
		this.brand = brand;
		this.colors = colors;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public void addColor(String color)
	{
		colors.add(color);
	}

	public Vector<String> getColors() {
		return colors;
	}
	
	

}
