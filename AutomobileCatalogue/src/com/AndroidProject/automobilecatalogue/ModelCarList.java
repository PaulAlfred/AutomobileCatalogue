package com.AndroidProject.automobilecatalogue;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONException;

import android.content.Context;
//Acts as a bridge of ControllerCar and ModelCar
public class ModelCarList implements Serializable {

	private final String mFilename = "Cars.json";
	
	private ArrayList<ModelCar> mCarView;
	private ArrayList<ModelCar> mCar;
	private ControllerCar mCarSerializer;
	
	public static ModelCarList sCar;
	private Context mAppcontext;
	
	public ModelCarList(Context appContext) {
		mAppcontext = appContext;
		mCar = new ArrayList<ModelCar>();
		mCarSerializer = new ControllerCar(mAppcontext, mFilename);
		try {
			mCarView = new ArrayList<ModelCar>();
			mCarView = mCarSerializer.loadCars();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<ModelCar> getCarView() {
		return mCarView;
	}

	public void setCarView(ArrayList<ModelCar> mCarView) {
		this.mCarView = mCarView;
	}


	public void addCar(ModelCar c) {
		mCar.add(c);
		saveCar();
	}
	
	private void saveCar(){
		try{
			mCarSerializer.saveCars(mCar);
		} catch (Exception e) {
			
		}
	}
	public void loadCar(){
		try {
			mCarSerializer.loadCars();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
