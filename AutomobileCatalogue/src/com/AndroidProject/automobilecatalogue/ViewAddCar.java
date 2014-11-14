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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class ViewAddCar extends Activity{
	
	//variables for widgets
	private TextView addEditTitle;
	private Button cancel,addEdit;
	private EditText mName,  mHorsepower;
	private Spinner mManufacturer, mCategory;
	//Model and Controller Objects
	private ModelCar car;
	private ModelCarList cars;
	private ControllerCar controllerCar;
	private ControllerManufacturer manufacturers;
	private ControllerCategory categories;
	private Intent i;
	//receive jsonobject names for the spinner
	private ArrayList<String> categoryList;
	private ArrayList<String> manufacturerList;
	//intent values
	public static final String Name = "name";
	public static final String Manufacturer = "manufacturer";
	public static final String Horsepower = "horsepower";
	public static final String Category = "category";
	public static final String isEdit = "isEdit";
	public static final String mPosition = "position";
	public static final String mManufacturerNo = "manufacturerNo";
	public static final String mCategoryNo = "categoryNo";
	//for better readability of listOfObject.get(position).getType();
	private String name;
	private String manufacturer;
	private String type;
	private String horsepower;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_car);
		manufacturerList = new ArrayList<String>();
		categoryList = new ArrayList<String>();
		i = getIntent();
		try{
			controllerCar = new ControllerCar(getApplicationContext(), "Cars.json");
			manufacturers = new ControllerManufacturer(getApplicationContext(), "Manufacturers.json");
			categories = new ControllerCategory(getApplicationContext(), "Categories.json");
			for(ModelManufacturer m : manufacturers.loadManufacturers()){
				manufacturerList.add(m.getName());
			}
			for(ModelCategory c: categories.loadCategories()){
				categoryList.add(c.getName());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		cars = new ModelCarList(ViewAddCar.this);
		
		addEditTitle = (TextView) findViewById(R.id.addOrEditCarTitle);
		cancel = (Button) findViewById(R.id.Cancel);
		addEdit = (Button) findViewById(R.id.add_edit_car_info);
		mName = (EditText) findViewById(R.id.editTextCarName);
		mCategory = (Spinner) findViewById(R.id.spinnerCategory);
		mHorsepower = (EditText) findViewById(R.id.editTextHorsepower);
		mManufacturer = (Spinner) findViewById(R.id.spinnerManufacturer);
		
		
		
		
		mName.setText(i.getStringExtra(ViewAddCar.Name));
		mHorsepower.setText(i.getStringExtra(ViewAddCar.Horsepower));
		
		ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categoryList);
		categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mCategory.setAdapter(categoryAdapter);
			
		ArrayAdapter<String> manufacturerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,manufacturerList);
		manufacturerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		mManufacturer.setAdapter(manufacturerAdapter);

		if(i.getExtras().getBoolean(ViewAddCar.isEdit))
			editLabels();
		else{
			addLabels();
			setSpinners();
		}
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}

			
		});
		addEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(i.getExtras().getBoolean(ViewAddCar.isEdit))
					edit();
				else
					add();
					
			}
		});
	}
	//Labels for the widgets to determine what state of functionality it is in
	private void addLabels() {
		addEdit.setText("Add");
		addEditTitle.setText("Add Car");
		
	}
	private void editLabels() {
		addEdit.setText("Edit");
		addEditTitle.setText("Edit Car");		
	}
	//actions for different functionality add or edit
	private void add(){

		setCarValues();
		car = new ModelCar(name, manufacturer, horsepower ,type);
		cars.addCar(car);	
		finish();
	}
	private void edit(){
		try {
			setCarValues();
			car = new ModelCar(name, manufacturer, horsepower, type);
			controllerCar.editCar(car, i.getExtras().getInt(ViewAddCar.mPosition));
			finish();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//method to hide the setting of values of Strings
	private void setCarValues() {
		name = mName.getText().toString();
		manufacturer = mManufacturer.getSelectedItem().toString();
		horsepower = mHorsepower.getText().toString();
		type = mCategory.getSelectedItem().toString();
		
		if(name.equals("")){
			name = "Generic White Vehicle";
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
	//method to hide the setting of spinner items
	private void setSpinners(){
		mCategory.setSelection(i.getExtras().getInt(ViewAddCar.mCategoryNo));
		mCategory.setEnabled(false);
		mCategory.setClickable(false);
		mManufacturer.setSelection(i.getExtras().getInt(ViewAddCar.mManufacturerNo));
		mManufacturer.setEnabled(false);
		mManufacturer.setClickable(false);
	}


}
