package com.AndroidProject.automobilecatalogue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ManufacturerListAdapter extends BaseAdapter{


	JSONArray list_of_manufacturer;
	JSONObject manufacturer;

	LayoutInflater inflater;
	TextView name, revenue, origin, founded;

	ArrayList <JSONObject> sortedjObj = new ArrayList<JSONObject>();


	public ManufacturerListAdapter(Context context, JSONObject MainJSON){
		try{
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			list_of_manufacturer = MainJSON.getJSONArray("manufacturers");
			sortJsonArray();
		} catch (JSONException e){
			e.printStackTrace();
		}
	}


	@Override
	public int getCount() {

		return list_of_manufacturer.length();
	}

	@Override
	public Object getItem(int position) {
		try {
			return list_of_manufacturer.get(position);
		} catch (JSONException e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){

			convertView = inflater.inflate(R.layout.row, parent, false);
			name = (TextView) convertView.findViewById(R.id.titleText);
			founded = (TextView) convertView.findViewById(R.id.description1);
			origin = (TextView) convertView.findViewById(R.id.description2);
			revenue = (TextView) convertView.findViewById(R.id.description3);

			try {
				manufacturer = list_of_manufacturer.getJSONObject(position);
				name.setText("Name : " + sortedjObj.get(position).get("name"));
				founded.setText("Manufacturer : " + sortedjObj.get(position).get("founded"));
				origin.setText("Horse Power : " + sortedjObj.get(position).get("origin"));
				revenue.setText("Category : " + sortedjObj.get(position).get("revenue"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return convertView;
	}

	private void sortJsonArray() throws JSONException{
		for(int i = 0; i < list_of_manufacturer.length(); i++){
			sortedjObj.add(list_of_manufacturer.getJSONObject(i));
		}
		Collections.sort(sortedjObj, new Comparator<JSONObject>() {

			@Override
			public int compare(JSONObject lhs, JSONObject rhs) {

				try {
					return (lhs.getString("name").toLowerCase().compareTo(rhs.getString("name").toLowerCase()));
				} catch (JSONException e) {
					e.printStackTrace();
					return 0;
				}
			}
		});

	}
}
