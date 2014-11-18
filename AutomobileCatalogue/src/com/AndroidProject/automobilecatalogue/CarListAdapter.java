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
	private ArrayList<ModelCar> mFilteredCars = new ArrayList<ModelCar>();

	public CarListAdapter(Context context, ArrayList<ModelCar> cars, ArrayList<String> dataFilter){

		this.context = context;
		for(ModelCar c : cars){
			if(((c.getManufacturer().equals(dataFilter.get(0)))&&(c.getType().equals(dataFilter.get(1)))))
				mFilteredCars.add(c);
		}

		inflater = LayoutInflater.from(this.context);
	}
	@Override
	public int getCount() {
		return this.mFilteredCars.size();
	}

	@Override
	public Object getItem(int position) {
		return this.mFilteredCars.get(position);
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
			mViewHolder.mName = (TextView) convertView.findViewById(R.id.titleText);
			mViewHolder.mType = (TextView) convertView.findViewById(R.id.description1);
			mViewHolder.mManufacturer = (TextView) convertView.findViewById(R.id.description2);
			mViewHolder.mHorsepower = (TextView) convertView.findViewById(R.id.description3);

			convertView.setTag(mViewHolder);
		} else{
			mViewHolder = (ViewHolderItem) convertView.getTag();
		}

		ModelCar car = mFilteredCars.get(position);

		if(car != null){

			mViewHolder.mName.setText(car.getName());
			mViewHolder.mType.setText(car.getType());
			mViewHolder.mManufacturer.setText(car.getManufacturer());
			mViewHolder.mHorsepower.setText(car.getHorsepower());	
		}
		return convertView;
	}

	private class ViewHolderItem{
		TextView mName, mType, mManufacturer, mHorsepower;
	}





}
