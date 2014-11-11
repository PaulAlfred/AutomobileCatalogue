package com.AndroidProject.automobilecatalogue;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ManufacturerListAdapter extends BaseAdapter{
	
	
	private Context context;
	LayoutInflater inflater;
	private ArrayList<ModelManufacturer> manufacturers = new ArrayList<ModelManufacturer>();
	
	public ManufacturerListAdapter(Context context, ArrayList<ModelManufacturer> manufacturers){
		this.context = context;
		this.manufacturers = manufacturers;
		inflater = LayoutInflater.from(this.context);
	}
	@Override
	public int getCount() {
		return this.manufacturers.size();
	}

	@Override
	public Object getItem(int position) {
		return this.manufacturers.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		
		View row = convertView;
		
		if(row == null){
			row = inflater.inflate(R.layout.row, parent, false);
		}
		
		
		ViewHolder mViewHolder = new ViewHolder();
		mViewHolder.Name = (AutoCompleteTextView) row.findViewById(R.id.titleText);
		mViewHolder.Origin = (AutoCompleteTextView) row.findViewById(R.id.description1);
		mViewHolder.Founded = (AutoCompleteTextView) row.findViewById(R.id.description2);
		mViewHolder.Revenue = (AutoCompleteTextView) row.findViewById(R.id.description3);
		
		mViewHolder.Name.setText(manufacturers.get(position).getmName());
		mViewHolder.Founded.setText(manufacturers.get(position).getmFounded());
		mViewHolder.Origin.setText(manufacturers.get(position).getmOrigin());
		mViewHolder.Revenue.setText(manufacturers.get(position).getmRevenue());
		
		return row;
	}

	private class ViewHolder{
		AutoCompleteTextView Name, Origin, Founded, Revenue;
	}
	
	
	

	
}
