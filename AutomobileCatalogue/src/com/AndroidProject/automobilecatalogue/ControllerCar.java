package com.AndroidProject.automobilecatalogue;

import org.json.JSONArray;
import org.json.JSONObject;

public class ControllerCar extends ControllerBasicOperations {

	@Override
	public JSONArray addToOwnJSON(JSONObject jObj, JSONArray jArr) {
		return super.addToOwnJSON(jObj, jArr);
	}

	@Override
	public JSONArray addToMainJSON(JSONObject jObj, JSONArray jArr) {
		return super.addToMainJSON(jObj, jArr);
	}

	@Override
	public JSONArray delete() {
		return super.delete();
	}

	@Override
	public JSONArray edit() {
		return super.edit();
	}

	@Override
	public JSONObject select() {
		return super.select();
	}

}
