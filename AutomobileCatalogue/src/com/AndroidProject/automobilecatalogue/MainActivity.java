package com.AndroidProject.automobilecatalogue;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

	
	private static Context mContext;
	private ManufacturerListAdapter manufacturerListAdapter;
	private ControllerManufacturer mControllerManufacturer;	
	private ModelManufacturerList modelManufacturerList;
	
	private int mPosition;
	private ArrayList<ModelManufacturer> mManufacturers;
	private Intent i;
	
	public static final String Class_Object = "classObject";
	
	//loading the cars from Manufacturers.json and putting its contents to the adapter
	//then displays the adapter in a listview
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.mContext = getApplicationContext();
		mManufacturers = new ArrayList<ModelManufacturer>();
		modelManufacturerList = new ModelManufacturerList(getApplicationContext());
		setContentView(R.layout.activity_main);
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
			intent.putExtra(ViewAddManufacturer.isEdit, false);
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
	//Used to simplify code
	private void  generateAdapter(){
		mControllerManufacturer = new ControllerManufacturer(MainActivity.getAppContext(), "Manufacturers.json");
		try {
			mManufacturers = mControllerManufacturer.loadManufacturers();
			manufacturerListAdapter = new ManufacturerListAdapter(this, mManufacturers);   
		} catch (Exception e ){
		}
		ListView mainList = (ListView) findViewById(R.id.activityMainList);
		mainList.setAdapter(manufacturerListAdapter);
		mainList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v, int position,
					long s) {
				
				Intent intent = new Intent(MainActivity.this,CategoryActivity.class);
				intent.putExtra(CategoryActivity.mManufacturer,mManufacturers.get(position).getName());
				intent.putExtra(CategoryActivity.mManfacturerNo, position);
				startActivity(intent);
			
				
			}
		});
		registerForContextMenu(mainList);
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
		mPosition = info.position;
		menu.setHeaderTitle(mManufacturers.get(mPosition).getName());
		menu.add(Menu.NONE,0,0,"Delete");
		menu.add(Menu.NONE,1,1,"Edit");

		super.onCreateContextMenu(menu, v, menuInfo);
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch(item.getItemId()){
		case 0:
			try {
				mControllerManufacturer.deleteManufacturer(mPosition);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			generateAdapter();
			break;
		case 1:
			startActivity(editManufacturer(mPosition));
			generateAdapter();
			break;
		
		}
		return super.onContextItemSelected(item);
	}
	//put Extras to the intent to be started for editView
	private Intent editManufacturer(int mPosition) {
		Intent intent = new Intent(this, ViewAddManufacturer.class);
		intent.putExtra(ViewAddManufacturer.mName, mManufacturers.get(mPosition).getName());
		intent.putExtra(ViewAddManufacturer.mFounded, mManufacturers.get(mPosition).getFounded());
		intent.putExtra(ViewAddManufacturer.mOrigin, mManufacturers.get(mPosition).getOrigin());
		intent.putExtra(ViewAddManufacturer.mRevenue, mManufacturers.get(mPosition).getRevenue());
		intent.putExtra(ViewAddManufacturer.isEdit, true);
		intent.putExtra(ViewAddManufacturer.mPosition, mPosition);
		return intent;
		
	}
	@Override
	protected void onResume() {
		generateAdapter();
		super.onResume();
	}
	
	
}