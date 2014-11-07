package com.AndroidProject.automobilecatalogue;

import java.io.IOException;

import com.AndroidProject.automobilecatalogue.R;
import com.AndroidProject.automobilecatalogue.R.id;
import com.AndroidProject.automobilecatalogue.R.layout;
import com.AndroidProject.automobilecatalogue.R.menu;

import android.content.Context;
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


public class ManufacturerActivity extends ActionBarActivity {

	ManufacturerListAdapter brand_list_adapter;
	final Context context = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		try {
			MyJSONClass MainObj = new MyJSONClass(getAssets().open("Manufacturers.txt"));
			brand_list_adapter = new ManufacturerListAdapter(getApplicationContext(),MainObj.getJSONObject(),"manufacturers");   
		} catch (IOException e) {
			e.printStackTrace();
		}
		ListView mainList = (ListView) findViewById(R.id.mainList);
		mainList.setAdapter(brand_list_adapter);
		mainList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position,
					long s) {
				Intent intent = new Intent(ManufacturerActivity.this,CategoryActivity.class);
				startActivity(intent);
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.add_menu, menu);
		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_add:
			Intent intent = new Intent(ManufacturerActivity.this, ViewAddManufacturer.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}
}
