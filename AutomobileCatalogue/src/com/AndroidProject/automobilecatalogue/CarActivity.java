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
	
	private CarListAdapter carListAdapter;
	private ControllerCar controllerCar;	
	
	private int mPosition;
	private ArrayList<ModelCar> mCars;
	private ArrayList<String> carFilters;
	private Intent i;
	
	public static final String mManufacturer = "manufacturer";
	public static final String mCategory = "category";
	public static final String mManufacturerNo = "manufacturerNo";
	public static final String mCategoryNo = "categoryNo";
	
	//loading the cars from cars.json and putting its contents to the adapter
	//then displays the adapter in a listview
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car);
		mCars = new ArrayList<ModelCar>();
		carFilters = new ArrayList<String>();
		i = getIntent();
		
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
			intent.putExtra(ViewAddCar.isEdit, false);
			intent.putExtra(ViewAddCar.mCategoryNo, i.getExtras().getInt(CarActivity.mCategoryNo));
			intent.putExtra(ViewAddCar.mManufacturerNo, i.getExtras().getInt(CarActivity.mManufacturerNo));
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}
	//Refreshes the Adapter information every Resume
	@Override
	protected void onResume() {
		super.onResume();
		generateAdapter();
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
		mPosition = info.position;
		menu.setHeaderTitle(mCars.get(mPosition).getName());
		menu.add(Menu.NONE,0,0,"Delete");
		menu.add(Menu.NONE,1,1,"Edit");

	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		
		try {
			switch(item.getItemId()){
			case 0:
				controllerCar.deleteCar(mPosition);
				generateAdapter();
				break;
			case 1:
				startActivity(editCar(mPosition));
				generateAdapter();
				break;
			
			}
		}catch (IOException e) {

			e.printStackTrace();
		} catch (JSONException e) {

			e.printStackTrace();
		}
		return super.onContextItemSelected(item);
	}
	//put Extras to the intent to be started for editView
	private Intent editCar(int mPosition) {
		
		Intent intent = new Intent(this, ViewAddCar.class);
		intent.putExtra(ViewAddCar.Name,mCars.get(mPosition).getName());
		intent.putExtra(ViewAddCar.Category,mCars.get(mPosition).getType());
		intent.putExtra(ViewAddCar.Manufacturer,mCars.get(mPosition).getManufacturer());
		intent.putExtra(ViewAddCar.Horsepower,mCars.get(mPosition).getHorsepower());
		intent.putExtra(ViewAddCar.isEdit, true);
		intent.putExtra(ViewAddCar.mPosition, mPosition);
		intent.putExtra(ViewAddCar.mCategoryNo, i.getExtras().getInt(CarActivity.mCategoryNo));
		intent.putExtra(ViewAddCar.mManufacturerNo, i.getExtras().getInt(CarActivity.mManufacturerNo));
		return intent;
		
	}
	//generates the Adapter information
	private void generateAdapter(){
		
		try {
			controllerCar = new ControllerCar(getApplicationContext(), "Cars.json");
			mCars = controllerCar.loadCars();
			carFilters.add(i.getExtras().getString(CarActivity.mManufacturer));
			carFilters.add(i.getExtras().getString(CarActivity.mCategory));
			carListAdapter = new CarListAdapter(this, controllerCar.loadCars(),carFilters);
		} catch (IOException e) {

			e.printStackTrace();
		} catch (JSONException e) {

			e.printStackTrace();
		}
		
		ListView carList = (ListView) findViewById(R.id.activityCarList);
		carList.setAdapter(carListAdapter);
		registerForContextMenu(carList);
	}
	
}
