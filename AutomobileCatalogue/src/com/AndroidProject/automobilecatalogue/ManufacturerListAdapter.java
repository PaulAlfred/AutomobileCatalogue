package com.AndroidProject.automobilecatalogue;

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
	
	
	public ManufacturerListAdapter(Context context, JSONObject MainJSON) throws JSONException{
		/*
		//parse an Object from a random index in the JSONArray
		JSONObject anObject = jsonArray.getJSONObject(randomNum); 

		Book aBook = new Book();
		aBook.setTitle((String) anObject.get("title"));*/
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		list_of_manufacturer = MainJSON.getJSONArray("manufacturers");
		
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list_of_manufacturer.length();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		/* LayoutInflater inflater = getLayoutInflater();
         View row;
         row = inflater.inflate(R.layout.custom, parent, false);
         TextView title, detail;
         ImageView i1;
         title = (TextView) row.findViewById(R.id.title);
         detail = (TextView) row.findViewById(R.id.detail);
         i1=(ImageView)row.findViewById(R.id.img);
         title.setText(Title[position]);
         detail.setText(Detail[position]);
         i1.setImageResource(imge[position]);*/
		View row;
		if(convertView == null){

			convertView = inflater.inflate(R.layout.row, parent, false);
			name = (TextView) convertView.findViewById(R.id.titleText);
			founded = (TextView) convertView.findViewById(R.id.description1);
			origin = (TextView) convertView.findViewById(R.id.description2);
			revenue = (TextView) convertView.findViewById(R.id.description3);

			try {
				manufacturer = list_of_manufacturer.getJSONObject(position);
				name.setText("Name : " + manufacturer.get("name"));
				founded.setText("Founded : " + manufacturer.get("founded"));
				origin.setText("Origin : " + manufacturer.get("origin"));
				revenue.setText("Revenue : " + manufacturer.get("revenue"));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return convertView;
	}

}
