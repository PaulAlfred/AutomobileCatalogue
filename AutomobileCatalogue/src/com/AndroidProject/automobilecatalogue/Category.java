package com.AndroidProject.automobilecatalogue;

import org.json.JSONArray;
import org.json.JSONObject;

public class Category extends BasicOperations {


	public JSONArray addToCategories(JSONObject jObj, JSONArray jArr) {
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
