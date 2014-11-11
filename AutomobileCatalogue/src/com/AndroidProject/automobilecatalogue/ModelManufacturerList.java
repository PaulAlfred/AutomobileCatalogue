package com.AndroidProject.automobilecatalogue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;
 


public class ModelManufacturerList{

	private final String mFilename = "Manufacturers";
	
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

	}
	
	public boolean saveManufacturers(){
		try{
			mManSerializer.saveManufacturers(mManufacturers);
			Log.d("Message", "InputAdd");
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	

}
