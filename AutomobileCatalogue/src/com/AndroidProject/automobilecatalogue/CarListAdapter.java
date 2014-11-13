package com.AndroidProject.automobilecatalogue;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


public class CarListAdapter extends BaseAdapter{

	private Context context;
	private LayoutInflater inflater;
	private ArrayList<ModelCar> cars = new ArrayList<ModelCar>();
	private ArrayList<String> dataFilter = new ArrayList<String>(2);
	private ArrayList<ModelCar> filteredCars = new ArrayList<ModelCar>();
	
	public CarListAdapter(Context context, ArrayList<ModelCar> cars, ArrayList<String> dataFilter){
		this.context = context;
		this.cars = cars;
		this.dataFilter = dataFilter;
		
		for(ModelCar c : cars)
			if((c.getmManufacturer().equals(dataFilter.get(0)))&&(c.getmType().equals(dataFilter.get(1))))
				filteredCars.add(c);
		
		
		inflater = LayoutInflater.from(this.context);
	}
	@Override
	public int getCount() {
		return this.filteredCars.size();
	}

	@Override
	public Object getItem(int position) {
		return this.filteredCars.get(position);
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
		mViewHolder.Name = (TextView) row.findViewById(R.id.titleText);
		mViewHolder.Type = (TextView) row.findViewById(R.id.description1);
		mViewHolder.Manufacturer = (TextView) row.findViewById(R.id.description2);
		mViewHolder.Horsepower = (TextView) row.findViewById(R.id.description3);
		
		mViewHolder.Name.setText(filteredCars.get(position).getmName());
		mViewHolder.Type.setText(filteredCars.get(position).getmType());
		mViewHolder.Manufacturer.setText(filteredCars.get(position).getmManufacturer());
		mViewHolder.Horsepower.setText(filteredCars.get(position).getmHorsepower());
		
		return row;
	}

	private class ViewHolder{
		TextView Name, Type, Manufacturer, Horsepower;
	}





}
