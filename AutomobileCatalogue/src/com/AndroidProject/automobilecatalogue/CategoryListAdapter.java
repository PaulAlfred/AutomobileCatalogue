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

    public CategoryListAdapter(Context context, ArrayList<ModelCategory> categories) {
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
        ViewHolderItem mViewHolder;


        if (convertView == null) {
            convertView = inflater.inflate(R.layout.row_category, parent, false);

            mViewHolder = new ViewHolderItem();
            mViewHolder.textName = (TextView) convertView.findViewById(R.id.textViewCategoryName);
            mViewHolder.textDescription = (TextView) convertView.findViewById(R.id.editTextCategoryDescription);

            convertView.setTag(mViewHolder);
        } else {

            mViewHolder = (ViewHolderItem) convertView.getTag();

        }

        ModelCategory category = categories.get(position);

        if (category != null) {
            mViewHolder.textName.setText(category.getName());
            mViewHolder.textDescription.setText(category.getDescription());
        }

        return convertView;
    }

    private class ViewHolderItem{
        TextView textName;
        TextView textDescription;
    }
}
