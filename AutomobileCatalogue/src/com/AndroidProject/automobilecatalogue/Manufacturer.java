package com.AndroidProject.automobilecatalogue;

import org.json.JSONArray;
import org.json.JSONObject;

public class Manufacturer extends BasicOperations {

	String name, founded, origin, revenue;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFounded() {
		return founded;
	}
	public void setFounded(String founded) {
		this.founded = founded;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getRevenue() {
		return revenue;
	}
	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}
	public JSONArray AddCar(JSONObject jObj, JSONArray jArr) {
		// TODO Auto-generated method stub
		return super.addToOwnJSON(jObj, jArr);
	}
	public JSONArray AddManufacturer(JSONObject jObj, JSONArray jArr){

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
