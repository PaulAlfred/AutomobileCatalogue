package com.AndroidProject.automobilecatalogue;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import android.content.Context;
import android.util.Log;
 

//Acts as a bridge of ControllerManufacturer and ModelManufacturer
public class ModelManufacturerList{

	private final String mFilename = "Manufacturers.json";
	
	private ArrayList<ModelManufacturer> mManufacturers;
	private ControllerManufacturer mManSerializer;
	
	public static ModelManufacturerList sCompany;
	private Context mAppcontext;
	
	public ModelManufacturerList(Context appContext) {
		mAppcontext = appContext;
		mManufacturers = new ArrayList<ModelManufacturer>();
		mManSerializer = new ControllerManufacturer(mAppcontext, mFilename);
	}
	

	public void addManufacturer(ModelManufacturer m) {
		mManufacturers.add(m);
		saveManufacturers();
	}
	
	private boolean saveManufacturers(){
		try{
			mManSerializer.saveManufacturers(mManufacturers);
			Log.d("Manufacturers", "saved files");
			return true;
		} catch (Exception e) {
			Log.d("Manufacturers", "files not saved");
			return false;
		}
	}
	public void loadManufacturers(){
		try {
			mManSerializer.loadManufacturers();
		} catch (IOException e) {
			Log.d("load", e.getMessage());
			e.printStackTrace();
		} catch (JSONException e) {
			Log.d("load", e.getMessage());
			e.printStackTrace();
		}
	}
	

}
