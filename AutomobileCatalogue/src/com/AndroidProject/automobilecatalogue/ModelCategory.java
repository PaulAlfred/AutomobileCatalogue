package com.AndroidProject.automobilecatalogue;

import org.json.JSONException;
import org.json.JSONObject;

public class ModelCategory{
	
	private static final String JSON_NAME = "name";
	private static final String JSON_DESCRIPTION = "description";
	
	String mName, mDescription;
	
	public ModelCategory(String Name, String Description) {
		this.mName = Name;
		this.mDescription = Description;
	}
	
	public String getmName() {
		return mName;
	}

	public String getmDescription() {
		return mDescription;
	}

	public ModelCategory(JSONObject json) throws JSONException {
		mName = json.getString(JSON_NAME);
		mDescription = json.getString(JSON_DESCRIPTION);
		
	}


	public JSONObject toJSON() throws JSONException{
		JSONObject item = new JSONObject();
		item.put("name", this.mName);
		item.put("description", this.mDescription);
		return item;
	}


}
