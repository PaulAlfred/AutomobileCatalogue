package com.AndroidProject.automobilecatalogue;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ListView;


public class CarActivity extends ActionBarActivity {

	private CarListAdapter car_list;
	private ControllerCar controllerCar;
	
	private int mPosition;
	private ArrayList<ModelCar> mCars;
	

	//loading the cars from cars.json and putting its contents to the adapter
	//then displays the adapter in a listview
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car);
		mCars = new ArrayList<ModelCar>();
		controllerCar = new ControllerCar(getApplicationContext(), "Cars.json");
		try {
			mCars = controllerCar.loadCars();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			Intent intent = new Intent(CarActivity.this, ViewAddCar.class);
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}
	private void generateAdapter(){
		try {
			
			car_list = new CarListAdapter(this, controllerCar.loadCars());
		} catch (IOException e) {

			e.printStackTrace();
		} catch (JSONException e) {

			e.printStackTrace();
		}
		ListView carList = (ListView) findViewById(R.id.carList);
		carList.setAdapter(car_list);
		registerForContextMenu(carList);
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
		mPosition = info.position;
		menu.setHeaderTitle(mCars.get(mPosition).getmName());
		menu.add(Menu.NONE,0,0,"Delete");
		menu.add(menu.NONE,1,1,"Edit");

	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		try {
			switch(item.getItemId()){
			case 0:
				controllerCar.deleteCar(mCars.get(mPosition).getmName());
				generateAdapter();
				break;
			case 1:
				startActivity(editCar(mPosition));
				generateAdapter();
				break;
			
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return super.onContextItemSelected(item);
	}
	private Intent editCar(int mPosition) {
		Intent intent = new Intent(this, ViewAddCar.class);
		intent.putExtra(ViewAddCar.Name,mCars.get(mPosition).getmName());
		intent.putExtra(ViewAddCar.Category,mCars.get(mPosition).getmType());
		intent.putExtra(ViewAddCar.Manufacturer,mCars.get(mPosition).getmManufacturer());
		intent.putExtra(ViewAddCar.Horsepower,mCars.get(mPosition).getmHorsepower());
		return intent;
		
	}
	
}
