package com.AndroidProject.automobilecatalogue;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class CategoryActivity extends ActionBarActivity
{
	private ArrayList<ModelCategory> categories;
	private CategoryListAdapter category_list;
	private ModelCategoryList modelCategoryList;
	
	public static final String mManufacturer = "manufacturer";
	public static final String mManfacturerNo = "manufacturerNo";
	
	Intent i;
	//displays all of the categories
	//starts intent of the cars activity
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		i = getIntent();
		categories = new ArrayList<ModelCategory>();
		modelCategoryList = new ModelCategoryList(getApplicationContext());
		ListView categoryList = (ListView) findViewById(R.id.categoryList);
		categories.add(new ModelCategory("Sedan", "Average Four Door Car"));
		categories.add(new ModelCategory("Van", "Big Vehicle used for mass transportation"));
		categories.add(new ModelCategory("Truck","Used for heavy tool transportation"));
		categories.add(new ModelCategory("Sports","Cars that are build for speed"));
		category_list = new CategoryListAdapter(getApplicationContext(),categories);
		
		for(ModelCategory c: categories)
			modelCategoryList.addCategory(c);
		
		modelCategoryList.saveCategory();
		
		categoryList.setAdapter(category_list);
		categoryList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position,
					long s) {
				Intent intent = new Intent(CategoryActivity.this,CarActivity.class);
				intent.putExtra(CarActivity.mManufacturer, i.getStringExtra(CategoryActivity.mManufacturer));
				intent.putExtra(CarActivity.mCategory, categories.get(position).getmName());
				intent.putExtra(CarActivity.mManufacturerNo, i.getExtras().getInt(CategoryActivity.mManfacturerNo));
				intent.putExtra(CarActivity.mCategoryNo, position);
				try{
				startActivity(intent);
				} catch (Exception e){
					Toast.makeText(getApplicationContext(), e.getClass().getName(),Toast.LENGTH_SHORT).show();
				}
			}
		});
		
	}
	
}
