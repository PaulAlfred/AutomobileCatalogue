package com.AndroidProject.automobilecatalogue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BasicOperations {

	JSONArray jArr = new JSONArray();
	
	
	
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	private void update(){
		//do update to main json
	}

}
