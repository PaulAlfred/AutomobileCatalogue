package com.AndroidProject.automobilecatalogue;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import android.content.Context;
import android.util.Log;

public class ModelCategoryList {
	
private final String mFilename = "Categories.json";
	
	private ArrayList<ModelCategory> mCategory;
	private ControllerCategory mCatSerializer;
	
	public static ModelCategoryList sCategory;
	private Context mAppcontext;
	
	public ModelCategoryList(Context appContext) {
		mAppcontext = appContext;
		mCategory = new ArrayList<ModelCategory>();
		mCatSerializer = new ControllerCategory(mAppcontext, mFilename);
	}
	

	public void addCategory(ModelCategory c) {
		mCategory.add(c);

	}
	
	public boolean saveCategory(){
		try{
			mCatSerializer.saveCategory(mCategory);
			Log.d("Manufacturers", "saved files");
			return true;
		} catch (Exception e) {
			Log.d("Manufacturers", "files not saved");
			return false;
		}
	}
	public void loadManufacturers(){
		try {
			mCatSerializer.loadCategories();
		} catch (IOException e) {
			Log.d("load", e.getMessage());
			e.printStackTrace();
		} catch (JSONException e) {
			Log.d("load", e.getMessage());
			e.printStackTrace();
		}
	}

}
