package com.AndroidProject.automobilecatalogue;


import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;

public class ViewAddCar extends Activity{

	Button add,edit;

	AutoCompleteTextView mName, mCategory, mHorsepower;
	Spinner mManufacturer;

	ModelCar car;
	ModelCarList cars;
	ControllerCar controllerCar;
	ControllerManufacturer list_of_controllers;
	
	Intent i;
	
	public static final String Name = "name";
	public static final String Manufacturer = "manufacturer";
	public static final String Horsepower = "horsepower";
	public static final String Category = "category";

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_car);
		ArrayList<String> list = new ArrayList<String>();

		i = getIntent();
		try{
			controllerCar = new ControllerCar(getApplicationContext(), "Cars.json");
			list_of_controllers = new ControllerManufacturer(getApplicationContext(), "Manufacturers.json");
			for(ModelManufacturer m : list_of_controllers.loadManufacturers())
			{
				list.add(m.getmName());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		cars = new ModelCarList(ViewAddCar.this);
		add = (Button) findViewById(R.id.add_category);
		edit = (Button) findViewById(R.id.edit_car_info);
		
		mName = (AutoCompleteTextView) findViewById(R.id.edit_car_model);
		mCategory = (AutoCompleteTextView) findViewById(R.id.edit_category2);
		mHorsepower = (AutoCompleteTextView) findViewById(R.id.edit_horsepower);
		mManufacturer = (Spinner) findViewById(R.id.manufacturer_spin);

		mName.setText(i.getStringExtra(ViewAddCar.Name));
		mCategory.setText(i.getStringExtra(ViewAddCar.Category));
		mHorsepower.setText(i.getStringExtra(ViewAddCar.Horsepower));
		

		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mManufacturer.setAdapter(dataAdapter);

		/*for(int j = 0; j < list.size(); j++){
			if(list.get(j).equals(ViewAddCar.Manufacturer))
				mManufacturer.setSelection(j);
		}*/

		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				car = new ModelCar(mName.getText().toString(), mManufacturer.getSelectedItem().toString(), mHorsepower.getText().toString(), mCategory.getText().toString());
				cars.addCar(car);	
				startActivity(new Intent(ViewAddCar.this, CarActivity.class));
			}
		});
		edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					car = new ModelCar(mName.getText().toString(), mManufacturer.getSelectedItem().toString(), mHorsepower.getText().toString(), mCategory.getText().toString());
					controllerCar.editCar(car, i.getStringExtra(ViewAddCar.Name));
					onBackPressed();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}


	@Override
	public void onBackPressed() {
		super.onBackPressed();
		startActivity(new Intent(this, CarActivity.class));

	}

}
