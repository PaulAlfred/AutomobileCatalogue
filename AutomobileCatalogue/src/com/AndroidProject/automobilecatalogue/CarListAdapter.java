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

public class CarListAdapter extends BaseAdapter{
	


	JSONArray list_of_cars;
	JSONObject car;

	LayoutInflater inflater;
	TextView name, manufacturer, category, horsepower;

	ArrayList <JSONObject> sortedjObj = new ArrayList<JSONObject>();

	public CarListAdapter(Context context, JSONObject MainJSON){
		try{
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			list_of_cars = MainJSON.getJSONArray("cars");
			
			sortJsonArray();

		} catch (JSONException e){
			e.printStackTrace();
		}
	}


	@Override
	public int getCount() {

		return list_of_cars.length();
	}

	@Override
	public Object getItem(int position) {
		try {
			return list_of_cars.get(position);
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
			manufacturer = (TextView) convertView.findViewById(R.id.description1);
			category = (TextView) convertView.findViewById(R.id.description2);
			horsepower = (TextView) convertView.findViewById(R.id.description3);

			try {
				car = list_of_cars.getJSONObject(position);
				name.setText("Name : " + sortedjObj.get(position).get("name"));
				manufacturer.setText("Manufacturer : " + sortedjObj.get(position).get("manufacturer"));
				category.setText("Category : " + sortedjObj.get(position).get("category"));
				horsepower.setText("Horse Power : " + sortedjObj.get(position).get("horsepower"));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		return convertView;
	}
	
	private void sortJsonArray() throws JSONException{
		for(int i = 0; i < list_of_cars.length(); i++){
			sortedjObj.add(list_of_cars.getJSONObject(i));
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
