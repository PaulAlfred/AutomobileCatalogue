package com.AndroidProject.automobilecatalogue;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ModelBasicOperations {

	JSONObject main_json;
	Collection<JSONObject> items ;
	JSONObject item;
	
	public void addToJSON(){
		
		main_json = new JSONObject();
		items = new ArrayList<JSONObject>();
		
		item = new JSONObject();
		
		
	}
	
	
	public void delete(Object object){
		update();
		//main_json.getJSONObject(name)
		

	}
	public void edit(){
		update();

	}
	public void select(){
	
	}
		
	private void update(){
		//do update to main json
	}
	public void setValues(){
		
	}


	
}
