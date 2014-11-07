package com.AndroidProject.automobilecatalogue;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

public class ManufacturerListAdapter extends GeneralListAdapter{

	public ManufacturerListAdapter(Context context, JSONObject MainJSON,
			String ArrayName) {
		super(context, MainJSON, ArrayName);
		setLayout(R.layout.row);
	}
	public void setText1(String text1, int id) {
		super.setText1(text1, id);
	}
	@Override
	public void setText2(String text2, int id) {
		super.setText2(text2, id);
	}
	@Override
	public void setText3(String text3, int id) {
		super.setText3(text3, id);
	}
	@Override
	public void setText4(String text4, int id) {
		super.setText4(text4, id);
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {	
		return super.getView(position, convertView, parent);
	}
	@Override
	public void setwidgetInfo(int position) {
		try {
			setText1(getSortedjObj().get(position).get("name").toString(), R.id.titleText);
			setText2(getSortedjObj().get(position).get("origin").toString(), R.id.description1);
			setText3(getSortedjObj().get(position).get("founded").toString(), R.id.description2);
			setText4(getSortedjObj().get(position).get("revenue").toString(), R.id.description3);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void setLayout(int layout) {
		super.setLayout(layout);
	}
	@Override
	public ArrayList<JSONObject> getSortedjObj() {

		return super.getSortedjObj();
	}

	
}