package com.AndroidProject.automobilecatalogue;


import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
	private ModelCarList carList;
	private ArrayList<ModelCar> mCars;
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
	public static final String mObject = "object";
	public static final String mCar = "car";
	//for better readability of listOfObject.get(position).getType();
	private String name;
	private String manufacturer;
	private String type;
	private String horsepower;
	private int position;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_car);
		manufacturerList = new ArrayList<String>();
		categoryList = new ArrayList<String>();
		mCars = new ArrayList<ModelCar>();
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



		carList = new ModelCarList(ViewAddCar.this);
		
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

		Labels(i.getExtras().getBoolean(ViewAddCar.isEdit));
			
		
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}

			
		});
		addEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				addOrEdit(i.getExtras().getBoolean(ViewAddCar.isEdit));
			}
		});
	}
	//Labels for the widgets to determine what state of functionality it is in
	private void Labels(boolean isEdit) {
		if(isEdit){
			addEdit.setText("Edit");
			addEditTitle.setText("Edit Car");
			setSpinners(isEdit);
		}
		else{		
			addEdit.setText("Add");
			addEditTitle.setText("Add Car");
			setSpinners(isEdit);
		}		
	}
	
	//actions for different functionality add or edit
	private void addOrEdit(boolean isEdit){
		
		setCarValues();
		car = new ModelCar(name, manufacturer, horsepower, type);
		mCars =  (ArrayList<ModelCar>) i.getSerializableExtra(ViewAddCar.mObject);
		
		if(isEdit){
			position = i.getExtras().getInt(ViewAddCar.mPosition);
			Log.d("position",String.valueOf(position));
			ModelCar mCar = (ModelCar) i.getExtras().getSerializable(ViewAddCar.mCar);
			mCars.get(position).setHorsepower(car.getHorsepower());
			mCars.get(position).setManufacturer(car.getManufacturer());
			mCars.get(position).setName(car.getName());
			mCars.get(position).setType(car.getType());
		}
		else{
			mCars.add(car);
		}
		Intent resultIntent = new Intent();
		resultIntent.putExtra(CarActivity.mObject, mCars);
		setResult(Activity.RESULT_OK, resultIntent);
		finish();
	}
		
	//method to hide the setting of values of Strings
	private void setCarValues() {
		name = mName.getText().toString();
		manufacturer = mManufacturer.getSelectedItem().toString();
		horsepower = mHorsepower.getText().toString();
		type = mCategory.getSelectedItem().toString();
		
		if(TextUtils.isEmpty(name)){
			name = "Generic White Vehicle";
		}
		if(TextUtils.isEmpty(manufacturer)){
			manufacturer = "honda";
		}
		if(TextUtils.isEmpty(horsepower)){
			horsepower = "150hp";
		}
		if(TextUtils.isEmpty(type)){
			type = "Sedan";	
		}
	}
	
	//method to hide the setting of spinner items
	private void setSpinners(boolean isEdit){

		mCategory.setSelection(i.getExtras().getInt(ViewAddCar.mCategoryNo));
		mCategory.setEnabled(isEdit);
		mCategory.setClickable(isEdit);
		mManufacturer.setSelection(i.getExtras().getInt(ViewAddCar.mManufacturerNo));
		mManufacturer.setEnabled(isEdit);
		mManufacturer.setClickable(isEdit);
	}

}
