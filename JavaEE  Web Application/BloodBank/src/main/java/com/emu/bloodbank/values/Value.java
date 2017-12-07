package com.emu.bloodbank.values;

import java.util.ArrayList;
import java.util.List;

public class Value {
	
	private List<String> cities = new ArrayList<String>();
	private List<String> bloodTypes = new ArrayList<String>();
	private List<Integer> years = new ArrayList<Integer>();
	private List<String> sexes = new ArrayList<String>();
	private List<String> isDonor = new ArrayList<String>();
	
	public Value() {
		cities.add("Ankara");
		cities.add("Antalya");
		cities.add("Istanbul");
		cities.add("Izmir");
		cities.add("Kocaeli");
		cities.add("Trabzon");
		
		bloodTypes.add("0 Rh-");
		bloodTypes.add("0 Rh+");
		bloodTypes.add("A Rh-");
		bloodTypes.add("A Rh+");
		bloodTypes.add("B Rh-");
		bloodTypes.add("B Rh+");
		bloodTypes.add("AB Rh-");
		bloodTypes.add("AB Rh+");
		
		for(int i=1990; i < 2000; i++)
			years.add(i);
		
		sexes.add("Male");
		sexes.add("Female");
		
		isDonor.add("Yes");
		isDonor.add("No");
	}
	
	public List<String> getCities(){
		return this.cities;
	}
	
	public List<String> getBloodTypes(){
		return this.bloodTypes;
	}
	
	public List<Integer> getYears(){
		return this.years;
	}
	
	public List<String> getSexes(){
		return this.sexes;
	}
	
	public List<String> getIsDonor(){
		return this.isDonor;
	}
}
