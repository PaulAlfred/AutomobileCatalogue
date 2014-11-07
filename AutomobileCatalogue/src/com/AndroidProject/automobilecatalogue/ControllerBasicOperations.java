package com.AndroidProject.automobilecatalogue;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ControllerBasicOperations {

	JSONArray jArr = new JSONArray();
	ArrayList<Object> list = new ArrayList<Object>();
	
	
	public JSONArray addToOwnJSON(JSONObject jObj, JSONArray jArr){
		
		this.jArr = jArr;
		this.jArr.put(jObj);
		return this.jArr;
	}
	
	public JSONArray addToMainJSON(JSONObject jObj, JSONArray jArr){
			
		this.jArr = jArr;
		this.jArr.put(jObj);
		return this.jArr;
	}
	
	
	public JSONArray delete(){
		update();
		return this.jArr;
	}
	public JSONArray edit(){
		update();
		return this.jArr;
	}
	public JSONObject select(){
		try {
			return this.jArr.getJSONObject(0);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	private void update(){
		//do update to main json
	}

}
