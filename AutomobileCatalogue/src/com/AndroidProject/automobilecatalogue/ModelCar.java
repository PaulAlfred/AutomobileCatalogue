package com.AndroidProject.automobilecatalogue;

import org.json.JSONException;
import org.json.JSONObject;



public class ModelCar {

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
	
	
	//getters of values
	public String getmType() {
		return mType;
	}

	public String getmManufacturer() {
		return mManufacturer;
	}

	public String getmHorsepower() {
		return mHorsepower;
	}

	public String getmName() {
		return mName;
	}
	//constructor that sets the values of the car
	public ModelCar(String Name, String Manufacturer, String Horsepower, String Type){
		this.mName = Name;
		this.mType = Type;
		this.mManufacturer = Manufacturer;
		this.mHorsepower = Horsepower;
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






