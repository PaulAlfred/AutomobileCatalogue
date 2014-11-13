package com.AndroidProject.automobilecatalogue;


import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ViewAddCar extends Activity{

	TextView add_edit_title;
	Button cancel,add_edit;
	AutoCompleteTextView mName,  mHorsepower;
	Spinner mManufacturer, mCategory;

	ModelCar car;
	ModelCarList cars;
	ControllerCar controllerCar;
	ControllerManufacturer list_of_manufacturers;
	ControllerCategory list_of_category;
	Intent i;
	
	public static final String Name = "name";
	public static final String Manufacturer = "manufacturer";
	public static final String Horsepower = "horsepower";
	public static final String Category = "category";
	public static final String isEdit = "isEdit";
	
	String name;
	String manufacturer;
	String type;
	String horsepower;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_car);
		ArrayList<String> manufacturerList = new ArrayList<String>();
		ArrayList<String> categoryList = new ArrayList<String>();
		i = getIntent();
		try{
			controllerCar = new ControllerCar(getApplicationContext(), "Cars.json");
			list_of_manufacturers = new ControllerManufacturer(getApplicationContext(), "Manufacturers.json");
			list_of_category = new ControllerCategory(getApplicationContext(), "Categories.json");
			for(ModelManufacturer m : list_of_manufacturers.loadManufacturers()){
				manufacturerList.add(m.getmName());
			}
			for(ModelCategory c: list_of_category.loadCategories()){
				categoryList.add(c.getmName());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		cars = new ModelCarList(ViewAddCar.this);
		
		add_edit_title = (TextView) findViewById(R.id.add_edit_car_title);
		cancel = (Button) findViewById(R.id.Cancel);
		add_edit = (Button) findViewById(R.id.add_edit_car_info);
		mName = (AutoCompleteTextView) findViewById(R.id.edit_car_model);
		mCategory = (Spinner) findViewById(R.id.category_spinner);
		mHorsepower = (AutoCompleteTextView) findViewById(R.id.edit_horsepower);
		mManufacturer = (Spinner) findViewById(R.id.manufacturer_spin);
		
		
		if(i.getExtras().getBoolean(ViewAddCar.isEdit))
			editLabels();
		else
			addLabels();
		
		mName.setText(i.getStringExtra(ViewAddCar.Name));
		mHorsepower.setText(i.getStringExtra(ViewAddCar.Horsepower));
		
		ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryList);
		categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mCategory.setAdapter(categoryAdapter);

		ArrayAdapter<String> manufacturerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,manufacturerList);
		manufacturerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mManufacturer.setAdapter(manufacturerAdapter);

		/*for(int j = 0; j < list.size(); j++){
			if(list.get(j).equals(ViewAddCar.Manufacturer))
				mManufacturer.setSelection(j);
		}*/

		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}

			
		});
		add_edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(i.getExtras().getBoolean(ViewAddCar.isEdit))
					edit();
				else
					add();
					
			}
		});
	}
	private void addLabels() {
		add_edit.setText("Add");
		add_edit_title.setText("Add Car");
		
	}
	private void editLabels() {
		add_edit.setText("Edit");
		add_edit_title.setText("Edit Car");		
	}
	private void add(){

		setCarValues();
		car = new ModelCar(name, manufacturer, horsepower ,type);
		cars.addCar(car);	
		finish();
	}
	private void edit(){
		try {
			car = new ModelCar(mName.getText().toString(), mManufacturer.getSelectedItem().toString(), mHorsepower.getText().toString(), mCategory.getSelectedItem().toString());
			controllerCar.editCar(car, i.getStringExtra(ViewAddCar.Name));
			finish();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void setCarValues() {
		name = mName.getText().toString();
		manufacturer = mManufacturer.getSelectedItem().toString();
		horsepower = mHorsepower.getText().toString();
		type = mCategory.getSelectedItem().toString();
		
		if(name.equals("")){
			name = "Generic While Vehicle";
			Log.d("name","was null");
		}
		if(manufacturer.equals("")){
			manufacturer = "honda";
			Log.d("manufacturer","was null");
		}
		if(horsepower.equals("")){
			horsepower = "150hp";
			Log.d("horsepower","was null");
		}
		if(type.equals("")){
			type = "Sedan";
			Log.d("type","was null");
		}
	}


}
