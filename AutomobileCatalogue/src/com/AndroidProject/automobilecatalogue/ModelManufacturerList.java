package com.AndroidProject.automobilecatalogue;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONException;

import android.content.Context;
import android.util.Log;
 

//Acts as a bridge of ControllerManufacturer and ModelManufacturer
public class ModelManufacturerList implements Serializable{

	private final String mFilename = "Manufacturers.json";

	private ArrayList<ModelManufacturer> mManufacturers;
	private ControllerManufacturer mManSerializer;
	
	public static ModelManufacturerList sCompany;
	private Context mAppcontext;
	
	
	
	public ModelManufacturerList(Context appContext) {
		mAppcontext = appContext;
		//mManufacturers = new ArrayList<ModelManufacturer>();
		mManSerializer = new ControllerManufacturer(mAppcontext, mFilename);
		mManufacturers = new ArrayList<ModelManufacturer>();
		try {
			mManufacturers = mManSerializer.loadManufacturers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public void addManufacturer(ModelManufacturer m) {
		mManufacturers.add(m);
		saveManufacturers();
	}
	
	public void saveManufacturers(){
		try{
			mManSerializer.saveManufacturers(mManufacturers);
		} catch (Exception e) {
			
		}
	}
	private void loadManufacturers(){
		try {
			mManSerializer.loadManufacturers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
