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

public class CategoryListAdapter extends BaseAdapter {

	JSONArray list_of_categories;
	JSONObject category;

	LayoutInflater inflater;
	TextView name, description;

	ArrayList <JSONObject> sortedjObj = new ArrayList<JSONObject>();

	public CategoryListAdapter(Context context, JSONObject MainJSON){
		try{
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			list_of_categories = MainJSON.getJSONArray("categories");
			sortJsonArray();
		} catch (JSONException e){
			e.printStackTrace();
		}
	}

	@Override
	public int getCount() {
		return list_of_categories.length();
	}

	@Override
	public Object getItem(int position) {
		try {
			return list_of_categories.get(position);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
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
			convertView = inflater.inflate(R.layout.row_category, parent, false);
			name = (TextView) convertView.findViewById(R.id.category_title);
			description = (TextView) convertView.findViewById(R.id.category_description);

			try{
				category = list_of_categories.getJSONObject(position);
				name.setText(sortedjObj.get(position).get("name").toString());
				description.setText(sortedjObj.get(position).get("description").toString());
			} catch (JSONException e){
				e.printStackTrace();
			}
		}

		return convertView;
	}
	private void sortJsonArray() throws JSONException{
		for(int i = 0; i < list_of_categories.length(); i++){
			sortedjObj.add(list_of_categories.getJSONObject(i));
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
