package com.AndroidProject.automobilecatalogue;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import android.content.Context;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

	
	private static Context mContext;
	private ManufacturerListAdapter mBrand_List_Adapter;
	private ControllerManufacturer mControllerManufacturer;	
	
	private int mPosition;
	private ArrayList<ModelManufacturer> mManufacturers;
	
	//loading the cars from Manufacturers.json and putting its contents to the adapter
	//then displays the adapter in a listview
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MainActivity.mContext = getApplicationContext();
		mManufacturers = new ArrayList<ModelManufacturer>();
		
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
			mManufacturers = mControllerManufacturer.loadManufacturers();
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
				
				try {
					Intent intent = new Intent(MainActivity.this,CategoryActivity.class);
					intent.putExtra(CategoryActivity.mManufacturer,mControllerManufacturer.loadManufacturers().get(position).getmName());
					startActivity(intent);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				
			}
		});
		registerForContextMenu(mainList);
	}
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
		mPosition = info.position;
		menu.setHeaderTitle(mManufacturers.get(mPosition).getmName());
		menu.add(Menu.NONE,0,0,"Delete");
		menu.add(Menu.NONE,1,1,"Edit");

		super.onCreateContextMenu(menu, v, menuInfo);
	}
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		try {
			switch(item.getItemId()){
			case 0:
				mControllerManufacturer.deleteManufacturer(mManufacturers.get(mPosition).getmName());
				generateAdapter();
				break;
			case 1:
				startActivity(editManufacturer(mPosition));
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
	private Intent editManufacturer(int mPosition) {
		Intent intent = new Intent(this, ViewAddManufacturer.class);
		intent.putExtra(ViewAddManufacturer.mName, mManufacturers.get(mPosition).getmName());
		intent.putExtra(ViewAddManufacturer.mFounded, mManufacturers.get(mPosition).getmFounded());
		intent.putExtra(ViewAddManufacturer.mOrigin, mManufacturers.get(mPosition).getmOrigin());
		intent.putExtra(ViewAddManufacturer.mRevenue, mManufacturers.get(mPosition).getmRevenue());
		intent.putExtra(ViewAddManufacturer.isEdit, true);
		return intent;
		
	}
	@Override
	protected void onResume() {
		generateAdapter();
		super.onResume();
	}
	
	
}