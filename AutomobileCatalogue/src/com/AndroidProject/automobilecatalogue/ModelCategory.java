package com.AndroidProject.automobilecatalogue;

import org.json.JSONException;
import org.json.JSONObject;

public class ModelCategory{
	//To be put on a jsonObject
	//json variables
	private static final String JSON_NAME = "name";
	private static final String JSON_DESCRIPTION = "description";
	//values
	private String mName, mDescription;
	//constructor that sets the values of the category
	public ModelCategory(String Name, String Description) {
		this.mName = Name;
		this.mDescription = Description;
	}
	//getters of values
	public String getmName() {
		return mName;
	}
	public String getmDescription() {
		return mDescription;
	}
	//gets the value of the jsonobject
	public ModelCategory(JSONObject json) throws JSONException {
		mName = json.getString(JSON_NAME);
		mDescription = json.getString(JSON_DESCRIPTION);
		
	}
	//puts the "variables":"values" in a jsonobject
	public JSONObject toJSON() throws JSONException{
		JSONObject item = new JSONObject();
		item.put("name", this.mName);
		item.put("description", this.mDescription);
		return item;
	}


}
