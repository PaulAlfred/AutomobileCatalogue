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
			if((c.getManufacturer().equals(dataFilter.get(0)))&&(c.getType().equals(dataFilter.get(1))))
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
		ViewHolderItem mViewHolder;
		
		if(convertView == null){
			convertView = inflater.inflate(R.layout.row, parent, false);
			
			mViewHolder = new ViewHolderItem();
			mViewHolder.Name = (TextView) convertView.findViewById(R.id.titleText);
			mViewHolder.Type = (TextView) convertView.findViewById(R.id.description1);
			mViewHolder.Manufacturer = (TextView) convertView.findViewById(R.id.description2);
			mViewHolder.Horsepower = (TextView) convertView.findViewById(R.id.description3);
			
			convertView.setTag(mViewHolder);
		} else{
			mViewHolder = (ViewHolderItem) convertView.getTag();
		}
		
		ModelCar car = cars.get(position);
		
		if(car != null){
			
			mViewHolder.Name.setText(car.getName());
			mViewHolder.Type.setText(car.getType());
			mViewHolder.Manufacturer.setText(car.getManufacturer());
			mViewHolder.Horsepower.setText(car.getHorsepower());	
		}
		return convertView;
	}

	private class ViewHolderItem{
		TextView Name, Type, Manufacturer, Horsepower;
	}





}
