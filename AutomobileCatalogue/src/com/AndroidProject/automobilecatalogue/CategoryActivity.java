package com.AndroidProject.automobilecatalogue;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class CategoryActivity extends ActionBarActivity
{
	private CategoryListAdapter categoryListAdapter;
	private ModelCategoryList modelCategoryList;
	private ArrayList<ModelCategory> mCategories;
	public static final String MANUFACTURER = "manufacturer";
	public static final String MANUFACTURER_NO = "manufacturerNo";

	private Intent i;
	//displays all of the categories
	//starts intent of the cars activity
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		i = getIntent();
		mCategories = new ArrayList<ModelCategory>();
		modelCategoryList = new ModelCategoryList(getApplicationContext());
		ListView categoryList = (ListView) findViewById(R.id.activityCategoryList);
		mCategories.add(new ModelCategory("Sedan", "Average Four Door Car"));
		mCategories.add(new ModelCategory("Van", "Big Vehicle used for mass transportation"));
		mCategories.add(new ModelCategory("Truck","Used for heavy tool transportation"));
		mCategories.add(new ModelCategory("Sports","Cars that are build for speed"));
		categoryListAdapter = new CategoryListAdapter(getApplicationContext(),mCategories);

		for(ModelCategory c: mCategories){
			modelCategoryList.addCategory(c);
		}


		modelCategoryList.saveCategory();

		categoryList.setAdapter(categoryListAdapter);
		categoryList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position,
					long s) {
				Intent intent = new Intent(CategoryActivity.this,CarActivity.class);
				intent.putExtra(CarActivity.MANUFACTURER, i.getStringExtra(CategoryActivity.MANUFACTURER));
				intent.putExtra(CarActivity.CATEGORY, mCategories.get(position).getName());
				intent.putExtra(CarActivity.MANUFACTURER_NO, i.getExtras().getInt(CategoryActivity.MANUFACTURER_NO));
				intent.putExtra(CarActivity.CATEGORY_NO, position);


				startActivity(intent);

			}
		});

	}

}
