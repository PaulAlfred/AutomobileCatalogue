package com.AndroidProject.automobilecatalogue;

import java.io.IOException;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;


public class CarActivity extends Activity {

	 CarListAdapter car_list;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
			MyJSONClass MainObj;
			try {
				MainObj = new MyJSONClass(getAssets().open("Cars.txt"));
				car_list = new CarListAdapter(CarActivity.this,MainObj.getJSONObject(),"cars");   
			} catch (IOException e) {
				e.printStackTrace();
			}

	
        ListView carList = (ListView) findViewById(R.id.carList);
        carList.setAdapter(car_list);
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
			Intent intent = new Intent(CarActivity.this, ViewAddManufacturer.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}
}
