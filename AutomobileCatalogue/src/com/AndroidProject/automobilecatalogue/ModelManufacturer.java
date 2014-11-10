package com.AndroidProject.automobilecatalogue;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

public class ModelManufacturer{

	private ModelCar car = new ModelCar();
	
	
	private String mName;
	private String mFounded;
	private String mOrigin;
	private String mRevenue;
	
	
	private static final String JSON_NAME = "name";
	private static final String JSON_FOUNDED = "founded";
	private static final String JSON_ORIGIN = "origin";
	private static final String JSON_REVENUE = "revenue";
	
	
	public ModelManufacturer(String Name, String Founded, String Origin, String Revenue){
		this.mName = Name;
		this.mFounded = Founded;
		this.mOrigin = Origin;
		this.mRevenue = Revenue;
	}
	
	public ModelManufacturer(JSONObject json) throws JSONException {
		mName = json.getString(JSON_NAME);
		mFounded = json.getString(JSON_FOUNDED);
		mOrigin = json.getString(JSON_ORIGIN);
		mRevenue = json.getString(JSON_REVENUE);
		
	}


	public JSONObject toJSON() throws JSONException{
		JSONObject item = new JSONObject();
		item.put("name", this.mName);
		item.put("founded", this.mFounded);
		item.put("origin", this.mOrigin);
		item.put("revenue", this.mRevenue);
		return item;
	}

	




}
