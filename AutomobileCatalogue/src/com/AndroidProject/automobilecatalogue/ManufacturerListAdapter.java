package com.AndroidProject.automobilecatalogue;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ManufacturerListAdapter extends BaseAdapter{


	private Context context;
	private LayoutInflater inflater;
	private ArrayList<ModelManufacturer> mManufacturers = new ArrayList<ModelManufacturer>();

	public ManufacturerListAdapter(Context context, ArrayList<ModelManufacturer> manufacturers){
		this.context = context;
		this.mManufacturers = manufacturers;
		inflater = LayoutInflater.from(this.context);
	}
	@Override
	public int getCount() {
		return this.mManufacturers.size();
	}

	@Override
	public Object getItem(int position) {
		return this.mManufacturers.get(position);
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
			mViewHolder.mOrigin = (TextView) convertView.findViewById(R.id.description1);
			mViewHolder.mFounded = (TextView) convertView.findViewById(R.id.description2);
			mViewHolder.mRevenue = (TextView) convertView.findViewById(R.id.description3);

			convertView.setTag(mViewHolder);
		} else {

			mViewHolder = (ViewHolderItem) convertView.getTag();

		}

		ModelManufacturer manufacturer = mManufacturers.get(position);

		if(manufacturer != null){

			mViewHolder.mName.setText(manufacturer.getName());
			mViewHolder.mFounded.setText(manufacturer.getFounded());
			mViewHolder.mOrigin.setText(manufacturer.getOrigin());
			mViewHolder.mRevenue.setText(manufacturer.getRevenue());

		}

		return convertView;
	}

	private class ViewHolderItem{
		TextView mName, mOrigin, mFounded, mRevenue;
	}





}
