package com.AndroidProject.automobilecatalogue;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;



public class ModelCar implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4852926514407088720L;
	//To be put on a jsonObject
	//values
	private String mName;
	private String mType;
	private String mManufacturer;
	private String mHorsepower;
	//json variables
	private static final String JSON_NAME = "name";
	private static final String JSON_TYPE = "category";
	private static final String JSON_MANUFACTURER = "manufacturer";
	private static final String JSON_HORSPOWER = "horsepower";
	
	//setters of values
	public void setName(String mName) {
		this.mName = mName;
	}

	public void setType(String mType) {
		this.mType = mType;
	}

	public void setManufacturer(String mManufacturer) {
		this.mManufacturer = mManufacturer;
	}

	public void setHorsepower(String mHorsepower) {
		this.mHorsepower = mHorsepower;
	}

	//getters of values
	public String getType() {
		return mType;
	}

	public String getManufacturer() {
		return mManufacturer;
	}

	public String getHorsepower() {
		return mHorsepower;
	}

	public String getName() {
		return mName;
	}
	//constructor that sets the values of the car
	public ModelCar(String name, String manufacturer, String horsepower, String type){
		this.mName = name;
		this.mType = type;
		this.mManufacturer = manufacturer;
		this.mHorsepower = horsepower;
	}
	//gets the value of the jsonobject
	public ModelCar(JSONObject json) throws JSONException {
		mName = json.getString(JSON_NAME);
		mType = json.getString(JSON_TYPE);
		mManufacturer = json.getString(JSON_MANUFACTURER);
		mHorsepower = json.getString(JSON_HORSPOWER);
		
	}
	//puts the "variables":"values" in a jsonobject
	public JSONObject toJSON() throws JSONException{
		JSONObject item = new JSONObject();
		item.put(JSON_NAME, this.mName);
		item.put(JSON_TYPE, this.mType);
		item.put(JSON_MANUFACTURER, this.mManufacturer);
		item.put(JSON_HORSPOWER, this.mHorsepower);
		return item;
	}
	

}	






