package com.AndroidProject.automobilecatalogue;

import org.json.JSONException;
import org.json.JSONObject;

public class ModelManufacturer{
	//To be put on a jsonObject
	//values
	private String mName;
	private String mFounded;
	private String mOrigin;
	private String mRevenue;
	//json variables
	private static final String JSON_NAME = "name";
	private static final String JSON_FOUNDED = "founded";
	private static final String JSON_ORIGIN = "origin";
	private static final String JSON_REVENUE = "revenue";
	
	
	//getters of values
	public String getmName() {
		return mName;
	}

	public String getmFounded() {
		return mFounded;
	}

	public String getmOrigin() {
		return mOrigin;
	}

	public String getmRevenue() {
		return mRevenue;
	}



	//constructor that sets the values of the car
	public ModelManufacturer(String Name, String Founded, String Origin, String Revenue){
		this.mName = Name;
		this.mFounded = Founded;
		this.mOrigin = Origin;
		this.mRevenue = Revenue;
	}
	//gets the value of the jsonobject
	public ModelManufacturer(JSONObject json) throws JSONException {
		mName = json.getString(JSON_NAME);
		mFounded = json.getString(JSON_FOUNDED);
		mOrigin = json.getString(JSON_ORIGIN);
		mRevenue = json.getString(JSON_REVENUE);
		
	}
	//puts the "variables":"values" in a jsonobject
	public JSONObject toJSON() throws JSONException{
		JSONObject item = new JSONObject();
		item.put("name", this.mName);
		item.put("founded", this.mFounded);
		item.put("origin", this.mOrigin);
		item.put("revenue", this.mRevenue);
		return item;
	}

	




}
