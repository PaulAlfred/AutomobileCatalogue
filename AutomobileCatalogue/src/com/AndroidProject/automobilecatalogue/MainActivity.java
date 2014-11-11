package com.AndroidProject.automobilecatalogue;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

	
	private static Context context;
	private ManufacturerListAdapter brand_list_adapter;
	private ControllerManufacturer controllerManufacturer;	
	private boolean resume = false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.context = getApplicationContext();
		setContentView(R.layout.activity_main);
		controllerManufacturer = new ControllerManufacturer(MainActivity.getAppContext(), "Manufacturers.json");
		try {
			brand_list_adapter = new ManufacturerListAdapter(this, controllerManufacturer.loadManufacturers());   
		} catch (Exception e ){
			Log.d("brand_list_exception", e.getMessage());
		}
		ListView mainList = (ListView) findViewById(R.id.mainList);
		mainList.setAdapter(brand_list_adapter);
		mainList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position,
					long s) {
				Intent intent = new Intent(MainActivity.this,CategoryActivity.class);
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
			Intent intent = new Intent(MainActivity.this, ViewAddManufacturer.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}	
	}
	public static Context getAppContext() {
        return MainActivity.context;
    }

	@Override
	protected void onStart() {
		super.onStart();
		controllerManufacturer = new ControllerManufacturer(MainActivity.getAppContext(), "Manufacturers.json");
		try {
			brand_list_adapter = new ManufacturerListAdapter(this, controllerManufacturer.loadManufacturers());   
		} catch (Exception e ){
			Log.d("brand_list_exception", e.getMessage());
		}
		ListView mainList = (ListView) findViewById(R.id.mainList);
		mainList.setAdapter(brand_list_adapter);
		mainList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position,
					long s) {
				Intent intent = new Intent(MainActivity.this,CategoryActivity.class);
				startActivity(intent);
				
			}
		});
	}
	
	@Override
	protected void onPause() {
		
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		
	}
	
	@Override
	protected void onStop() {
	
		super.onStop();
	}
	
}

	

