package com.AndroidProject.automobilecatalogue;

import org.json.JSONArray;
import org.json.JSONObject;

public class Car extends BasicOperations{

	String name, manufacturer, category, horsepower;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;

	}

	public String getHorsepower() {
		return horsepower;
	}

	public void setHorsepower(String horsepower) {
		this.horsepower = horsepower;
	}

	@Override
	public JSONArray addToMainJSON(JSONObject jObj, JSONArray jArr) {
		// TODO Auto-generated method stub
		return super.addToMainJSON(jObj, jArr);
	}

	@Override
	public JSONArray delete() {
		// TODO Auto-generated method stub
		return super.delete();
	}

	@Override
	public JSONArray edit() {
		// TODO Auto-generated method stub
		return super.edit();
	}

	@Override
	public JSONObject select() {
		// TODO Auto-generated method stub
		return super.select();
	}

}
