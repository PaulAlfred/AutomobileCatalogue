package com.AndroidProject.automobilecatalogue;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CategoryListAdapter extends BaseAdapter {

	private Context context;
	private LayoutInflater inflater;
	private ArrayList<ModelCategory> categories = new ArrayList<ModelCategory>();
	
	public CategoryListAdapter(Context context, ArrayList<ModelCategory> categories){
		this.context = context;
		this.categories = categories;
		inflater = LayoutInflater.from(this.context);
	}
	@Override
	public int getCount() {
		return this.categories.size();
	}

	@Override
	public Object getItem(int position) {
		return this.categories.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		
		View row = convertView;
		
		if(row == null){
			row = inflater.inflate(R.layout.row_category, parent, false);
		}
		
		
		ViewHolder mViewHolder = new ViewHolder();
		mViewHolder.Name = (TextView) row.findViewById(R.id.category_title);
		mViewHolder.Description = (TextView) row.findViewById(R.id.category_description);
		
		mViewHolder.Name.setText(categories.get(position).getmName());
		mViewHolder.Description.setText(categories.get(position).getmDescription());
		
		
		return row;
	}

	private class ViewHolder{
		TextView Name;
		TextView Description;
	}


	
}
