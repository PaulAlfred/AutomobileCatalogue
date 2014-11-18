package com.AndroidProject.automobilecatalogue;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import android.content.Context;

//Acts as a bridge of ControllerCar and ModelCar
public class ModelCarList {

	private final String mFilename = "Cars.json";

	private ArrayList<ModelCar> mCar;
	private ControllerCar carSerializer;
	public static ModelCarList sCar;
	private Context mAppContext;

	public ModelCarList(Context appContext) {
		mAppContext = appContext;
		mCar = new ArrayList<ModelCar>();
		carSerializer = new ControllerCar(mAppContext, mFilename);
		loadCar();
	}

	public void addCar(ModelCar c) {
		mCar.add(c);
	}

	public void saveCar(ArrayList<ModelCar> mCar) {
		try {
			carSerializer.saveCars(mCar);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void loadCar() {
		try {
			mCar = carSerializer.loadCars();
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
