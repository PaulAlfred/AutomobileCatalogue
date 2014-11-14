package com.AndroidProject.automobilecatalogue;

import java.io.IOException;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ViewAddManufacturer extends Activity {

	//variables for widgets
	private Button addEdit, cancel;
	private TextView addEditLabel;
	private EditText name;
	private EditText founded;
	private EditText revenue;
	private EditText origin;
	//Model and Controller Objects
	private ModelManufacturer manufacturer;
	private ModelManufacturerList manufacturers;
	private ControllerManufacturer manufacturerController;
	private Intent i;
	//intent values
	public static final String mName = "name";
	public static final String mFounded = "founded";
	public static final String mOrigin = "origin";
	public static final String mRevenue = "revenue";
	public static final String isEdit = "isEdit";
	public static final String mPosition = "position";
	//for better readability of listOfObject.get(position).getType();
	private String Name;
	private String Founded;
	private String Revenue;
	private String Origin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_manufacturer);
		
		i = getIntent();
		
		name = (EditText) findViewById(R.id.editTextCompanyName);
		founded = (EditText) findViewById(R.id.editTextYear);
		revenue = (EditText) findViewById(R.id.editTextRevenue);
		origin = (EditText) findViewById(R.id.editTextOrigin);
		addEditLabel = (TextView) findViewById(R.id.add_edit_man_info);	
		addEdit = (Button) findViewById(R.id.add_edit_man);
		cancel = (Button) findViewById(R.id.cancel);
		
		name.setText(i.getStringExtra(ViewAddManufacturer.mName));
		founded.setText(i.getStringExtra(ViewAddManufacturer.mFounded));
		revenue.setText(i.getStringExtra(ViewAddManufacturer.mRevenue));
		origin.setText(i.getStringExtra(ViewAddManufacturer.mOrigin));
		
		
		if(i.getExtras().getBoolean(ViewAddManufacturer.isEdit))
			editLabel();
		else
			addLabel();
		
		
		manufacturerController = new ControllerManufacturer(MainActivity.getAppContext(), "Manufacturers.json");
		manufacturers = new ModelManufacturerList(MainActivity.getAppContext());
		addEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(i.getExtras().getBoolean(ViewAddManufacturer.isEdit))
					edit();
				else
					add();
				
			}

				
				
			
		});
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}
	//Labels for the widgets to determine what state of functionality it is in
	private void addLabel() {
		addEdit.setText("Add");
		addEditLabel.setText("Add Manufacturer");
		
	}
	private void editLabel() {
		addEdit.setText("Edit");
		addEditLabel.setText("Edit Manufacturer");
		
	}
	//actions for different functionality add or edit	
	private void add() {
		setValues();
		manufacturer = new ModelManufacturer(Name, Founded, Origin, Revenue);
		manufacturers.addManufacturer(manufacturer);
		finish();				
	}
	private void edit() {
		setValues();
		manufacturer = new ModelManufacturer(Name, Founded, Origin, Revenue);
		try {
			manufacturerController.editManufacturer(manufacturer, i.getExtras().getInt(ViewAddManufacturer.mPosition));
			finish();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//method to hide the setting of helper strings
	private void setValues() {
		Name = name.getText().toString();
		Founded = founded.getText().toString();
		Origin = origin.getText().toString();
		Revenue = revenue.getText().toString();
		
		if(Name.equals(""))
			Name = "Generic Automobile Company";
		if(Founded.equals(""))
			Founded = "2000";
		if(Origin.equals(""))
			Origin = "Europe";
		if(Revenue.equals(""))
			Revenue = "$500M";
	}
}
