package com.AndroidProject.automobilecatalogue;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class CategoryListAdapter extends GeneralListAdapter {

	public CategoryListAdapter(Context context, JSONObject MainJSON,
			String ArrayName) {
		super(context, MainJSON, ArrayName);
		setLayout(R.layout.row_category);
	}

	@Override
	public void setLayout(int layout) {
		super.setLayout(layout);
	}
	@Override
	public void setText1(String text1, int id) {
		super.setText1(text1, id);
	}
	@Override
	public void setText2(String text2, int id) {
		super.setText2(text2, id);
	}
	public ArrayList<JSONObject> getSortedjObj() {
		return super.getSortedjObj();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		return super.getView(position, convertView, parent);
	}

	@Override
	public void setwidgetInfo(int position) {
		try {
			setText1(getSortedjObj().get(position).get("name").toString(),R.id.category_title);
			setText2(getSortedjObj().get(position).get("description").toString(),R.id.category_description);
		} catch (JSONException e) {

			e.printStackTrace();
		}
		
		super.setwidgetInfo(position);
	}

	
}
