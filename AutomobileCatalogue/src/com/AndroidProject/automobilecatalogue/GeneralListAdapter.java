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

public class GeneralListAdapter extends BaseAdapter{

	private JSONArray list_of_Objects;

	private LayoutInflater inflater;
	
	int layout;
	
	private TextView text1, text2, text3, text4;

	View convertView;
	public void setLayout(int layout) {
		this.layout = layout;
	}

	private int getLayout(){
		return this.layout;
		
	}
	public void setText1(String text1, int id) {
		this.text1 = (TextView) convertView.findViewById(id);
		this.text1.setText(text1);
		
	}


	public void setText2(String text2, int id) {
		this.text2 = (TextView) convertView.findViewById(id);
		this.text2.setText(text2);
		
	}


	public void setText3(String text3, int id) {
		this.text3 = (TextView) convertView.findViewById(id);
		this.text3.setText(text3);
		
	}


	public void setText4(String text4, int id) {
		this.text4 = (TextView) convertView.findViewById(id);
		this.text4.setText(text4);
		
	}

	ArrayList <JSONObject> sortedjObj = new ArrayList<JSONObject>();


	public ArrayList<JSONObject> getSortedjObj() {
		return sortedjObj;
	}
	public GeneralListAdapter(Context context, JSONObject MainJSON, String ArrayName){
		try{
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			list_of_Objects = MainJSON.getJSONArray(ArrayName);
			sortJsonArray();
		} catch (JSONException e){
			e.printStackTrace();
		}
	}
	@Override
	public int getCount() {

		return list_of_Objects.length();
	}
	@Override
	public Object getItem(int position) {
		try {
			return list_of_Objects.get(position);
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
			this.convertView = inflater.inflate(getLayout(), parent, false);
			convertView = this.convertView;
			setwidgetInfo(position);
			
		}

		return convertView;
	}
	private void sortJsonArray() throws JSONException{
		for(int i = 0; i < list_of_Objects.length(); i++){
			sortedjObj.add(list_of_Objects.getJSONObject(i));
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
	public void setwidgetInfo(int position)
	{
	}
  
	

}
