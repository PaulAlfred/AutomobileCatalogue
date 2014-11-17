package com.AndroidProject.automobilecatalogue;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import android.content.Context;
//Acts as a bridge of ControllerCar and ModelCar
public class ModelCarList {

	private final String mFilename = "Cars.json";
	
	private ArrayList<ModelCar> mCar;
	private ControllerCar mCarSerializer;
	
	public static ModelCarList sCar;
	private Context mAppcontext;
	
	public ModelCarList(Context appContext) {
		mAppcontext = appContext;
		mCar = new ArrayList<ModelCar>();
		mCarSerializer = new ControllerCar(mAppcontext, mFilename);
		loadCar();
	}

	public void addCar(ModelCar c) {
		mCar.add(c);
	}
	
	public void saveCar(ArrayList<ModelCar> mCar){
		try{
			mCarSerializer.saveCars(mCar);
		} catch (Exception e) {
			
		}
	}
	private void loadCar(){
		try {
			mCar = mCarSerializer.loadCars();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<ModelCar> getCar() {
		return mCar;
	}	
}
