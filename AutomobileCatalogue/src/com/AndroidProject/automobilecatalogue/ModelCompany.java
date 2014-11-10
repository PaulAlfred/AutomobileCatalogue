package com.AndroidProject.automobilecatalogue;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;


public class ModelCompany extends  ModelBasicOperations{
	
	Context context;
	ModelManufacturer manufacturer = new ModelManufacturer();
	
	public ModelCompany(Context context) {
		try {
			this.context = context;
			MyJSONClass myJsonObject = new MyJSONClass(context.getAssets().open("Manufacturers.txt"));
			main_json = myJsonObject.getJSONObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	public void addManufacturer(ModelManufacturer manufacturer) {
		super.addToJSON();
		this.manufacturer = manufacturer;
		setValues();

	}
	
	@Override
	public void setValues() {
		
		try {
			item.put("name", this.manufacturer.getName());
			item.put("founded", this.manufacturer.getFounded());
			item.put("origin", this.manufacturer.getOrigin());
			item.put("revenue", this.manufacturer.getRevenue());
			items.add(item);
			
			main_json.put("manufacturers", new JSONArray(items));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}





	


  

}
