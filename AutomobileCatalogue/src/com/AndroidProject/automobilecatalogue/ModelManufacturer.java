package com.AndroidProject.automobilecatalogue;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

public class ModelManufacturer implements Serializable{
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
	
	//setter of values
	public void setName(String mName) {
		this.mName = mName;
	}

	public void setFounded(String mFounded) {
		this.mFounded = mFounded;
	}

	public void setOrigin(String mOrigin) {
		this.mOrigin = mOrigin;
	}

	public void setRevenue(String mRevenue) {
		this.mRevenue = mRevenue;
	}

	//getters of values
	public String getName() {
		return mName;
	}

	public String getFounded() {
		return mFounded;
	}

	public String getOrigin() {
		return mOrigin;
	}

	public String getRevenue() {
		return mRevenue;
	}



	//constructor that sets the values of the manufacturer
	public ModelManufacturer(String name, String founded, String origin, String revenue){
		this.mName = name;
		this.mFounded = founded;
		this.mOrigin = origin;
		this.mRevenue = revenue;
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
