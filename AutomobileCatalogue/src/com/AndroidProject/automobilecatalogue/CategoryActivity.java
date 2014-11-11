package com.AndroidProject.automobilecatalogue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class CategoryActivity extends ActionBarActivity
{
	//displays all of the categories
	//starts intent of the cars activity
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category);
	
		ListView categoryList = (ListView) findViewById(R.id.categoryList);
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
	//inflates the add menu and icon on the action bar
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.add_category, menu);
		return true;

	}
	//starts the activity of the add menu
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_add:
			Intent intent = new Intent(CategoryActivity.this, ViewAddCategory.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}
}
