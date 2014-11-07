package com.AndroidProject.automobilecatalogue;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class CategoryActivity extends Activity
{
	CategoryListAdapter category_list;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
		
		try {
			MyJSONClass MainObj = new MyJSONClass(getAssets().open("Categories.txt"));
			category_list = new CategoryListAdapter(CategoryActivity.this, MainObj.getJSONObject());	        
		} catch (IOException e) {
			e.printStackTrace();
		} 
		ListView categoryList = (ListView) findViewById(R.id.categoryList);
		categoryList.setAdapter(category_list);
		categoryList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position,
					long s) {
				Intent intent = new Intent(CategoryActivity.this,CarActivity.class);
				try{
				startActivity(intent);
				} catch (Exception e){
					Toast.makeText(getApplicationContext(), e.getClass().getName(),Toast.LENGTH_SHORT).show();
				}
			}
		});
		

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
}
