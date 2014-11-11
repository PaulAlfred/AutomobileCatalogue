package com.AndroidProject.automobilecatalogue;

import java.io.IOException;

import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;


public class CarActivity extends ActionBarActivity {

	private CarListAdapter car_list;
	private ControllerCar controllerCar;
	
	//loading the cars from cars.json and putting its contents to the adapter
	//then displays the adapter in a listview
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car);
		
		
		
		try {
			controllerCar = new ControllerCar(getApplicationContext(), "Cars.json");
			car_list = new CarListAdapter(this, controllerCar.loadCars());
		} catch (IOException e) {

			e.printStackTrace();
		} catch (JSONException e) {

			e.printStackTrace();
		}
		ListView carList = (ListView) findViewById(R.id.carList);
		carList.setAdapter(car_list);
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
			Intent intent = new Intent(CarActivity.this, ViewAddCar.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}
}
