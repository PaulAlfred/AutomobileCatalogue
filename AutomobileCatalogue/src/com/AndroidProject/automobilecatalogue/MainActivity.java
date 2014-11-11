package com.AndroidProject.automobilecatalogue;

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

	
	private static Context mContext;
	private ManufacturerListAdapter mBrand_List_Adapter;
	private ControllerManufacturer mControllerManufacturer;	
	//loading the cars from Manufacturers.json and putting its contents to the adapter
	//then displays the adapter in a listview
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.mContext = getApplicationContext();
		setContentView(R.layout.activity_main);
		generateAdapter();
	}
	//inflates the add menu and icon on the action bar
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.add_menu, menu);
		return true;

	}
	//starts the activity of the add menu
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
	//gets the context of MainActivity to be accessed globally
	public static Context getAppContext() {
        return MainActivity.mContext;
    }
	//refreshes the view on start of MainActivity
	@Override
	protected void onStart() {
		super.onStart();
		generateAdapter();
		
	}
	//Used to simplify code
	private void  generateAdapter(){
		mControllerManufacturer = new ControllerManufacturer(MainActivity.getAppContext(), "Manufacturers.json");
		try {
			mBrand_List_Adapter = new ManufacturerListAdapter(this, mControllerManufacturer.loadManufacturers());   
		} catch (Exception e ){
			Log.d("brand_list_exception", e.getMessage());
		}
		ListView mainList = (ListView) findViewById(R.id.mainList);
		mainList.setAdapter(mBrand_List_Adapter);
		mainList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position,
					long s) {
				Intent intent = new Intent(MainActivity.this,CarActivity.class);
				startActivity(intent);
				
			}
		});
	}
	
}

	

