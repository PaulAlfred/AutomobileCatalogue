package com.AndroidProject.automobilecatalogue;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import android.content.Context;
//Acts as a bridge of ControllerCategory and ModelCategory
public class ModelCategoryList {

	private final String mFilename = "Categories.json";

	private ArrayList<ModelCategory> mCategory;
	private ControllerCategory catSerializer;

	public static ModelCategoryList sCategory;
	private Context mAppcontext;

	public ModelCategoryList(Context appContext) {
		mAppcontext = appContext;
		mCategory = new ArrayList<ModelCategory>();
		catSerializer = new ControllerCategory(mAppcontext, mFilename);
	}


	public void addCategory(ModelCategory c) {
		mCategory.add(c);

	}

	public void saveCategory(){

		try {
			catSerializer.saveCategory(mCategory);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
