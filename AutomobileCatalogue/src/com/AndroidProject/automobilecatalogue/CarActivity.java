package com.AndroidProject.automobilecatalogue;

import java.util.ArrayList;

import org.json.JSONException;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class CarActivity extends ActionBarActivity {
	
	private CarListAdapter carListAdapter;
	private ControllerCar controllerCar;	
	private ModelCarList mModelCarlist;
	
	private int mPosition;
	private ArrayList<ModelCar> mCars;
	private ArrayList<String> carFilters;
	private ArrayList<ModelCar> modifiedCars;
	private Intent i;
	
	public static final String mObject = "object";
	public static final String mManufacturer = "manufacturer";
	public static final String mCategory = "category";
	public static final String mManufacturerNo = "manufacturerNo";
	public static final String mCategoryNo = "categoryNo";
	
	private boolean mIsEdit;
	
	//loading the cars from cars.json and putting its contents to the adapter
	//then displays the adapter in a listview
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car);
		
		modifiedCars = new ArrayList<ModelCar>();
		i = getIntent();
		mIsEdit = false;
		mCars = new ArrayList<ModelCar>();
		carFilters = new ArrayList<String>();
		mModelCarlist = new ModelCarList(getApplicationContext());
		mCars = mModelCarlist.getCar();
		
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
			intent.putExtra(ViewAddCar.isEdit, mIsEdit);
			intent.putExtra(ViewAddCar.mCategoryNo, i.getExtras().getInt(CarActivity.mCategoryNo));
			intent.putExtra(ViewAddCar.mManufacturerNo, i.getExtras().getInt(CarActivity.mManufacturerNo));
			intent.putExtra(ViewAddCar.mObject, mCars);
			startActivityForResult(intent, 1);
			return true;
		default:
			return super.onOptionsItemSelected(item);

		}
	}
	//Refreshes the Adapter information every Resume
	@Override
	protected void onResume() {
		super.onResume();
		mIsEdit = false;
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
		
		
		switch(item.getItemId()){
		case 0:
			mCars.remove(carListAdapter.getItem(mPosition));
			removeCars(mCars.indexOf(carListAdapter.getItem(mPosition)));
			generateAdapter();
			break;
		case 1:
			startActivityForResult(editCar((mCars.indexOf(carListAdapter.getItem(mPosition)))),1);
			generateAdapter();
			break;
		}
		
		return super.onContextItemSelected(item);
	}
	//put Extras to the intent to be started for editView
	private Intent editCar(int position) {
		mIsEdit = true;
		Intent intent = new Intent(this, ViewAddCar.class);
		intent.putExtra(ViewAddCar.Name,mCars.get(position).getName());
		intent.putExtra(ViewAddCar.Category,mCars.get(position).getType());
		intent.putExtra(ViewAddCar.Manufacturer,mCars.get(position).getManufacturer());
		intent.putExtra(ViewAddCar.Horsepower,mCars.get(position).getHorsepower());
		intent.putExtra(ViewAddCar.isEdit, true);
		intent.putExtra(ViewAddCar.mPosition, position);
		Log.d("position",String.valueOf(position));
		intent.putExtra(ViewAddCar.mCategoryNo, i.getExtras().getInt(CarActivity.mCategoryNo));
		intent.putExtra(ViewAddCar.mManufacturerNo, i.getExtras().getInt(CarActivity.mManufacturerNo));
		intent.putExtra(ViewAddCar.mObject, mCars);
		return intent;
		
	}
	private void removeCars(int position){
		for(ModelCar c : mCars){
			if(!c.getManufacturer().equals(mCars.get(position).getName()))
				modifiedCars.add(c);
		}
		try {
			controllerCar.partialSave(mCars);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//generates the Adapter information
	private void generateAdapter(){
			
		carFilters.add(i.getExtras().getString(CarActivity.mManufacturer));
		carFilters.add(i.getExtras().getString(CarActivity.mCategory));
		carListAdapter = new CarListAdapter(this, mCars ,carFilters);
		
		ListView carList = (ListView) findViewById(R.id.activityCarList);
		carList.setAdapter(carListAdapter);
		registerForContextMenu(carList);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == 1) {
	        if(resultCode == RESULT_OK){
	        	mCars = (ArrayList<ModelCar>) data.getSerializableExtra(CarActivity.mObject);
	            Log.d("result", "resultcaptured");
	        }
	        if (resultCode == RESULT_CANCELED) {
	            Log.d("result", "noresultcaptured");
	        }
	    }
	}
	@Override
	protected void onDestroy() {
		mModelCarlist.saveCar(mCars);
		super.onDestroy();
	}
}
