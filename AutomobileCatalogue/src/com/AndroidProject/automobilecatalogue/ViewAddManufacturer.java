package com.AndroidProject.automobilecatalogue;

import java.io.IOException;

import org.json.JSONException;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

public class ViewAddManufacturer extends Activity {
	
	Intent i;
	
	Button add_edit, cancel;
	TextView add_edit_label;
	AutoCompleteTextView name;
	AutoCompleteTextView founded;
	AutoCompleteTextView revenue;
	AutoCompleteTextView origin;
	
	String Name;
	String Founded;
	String Revenue;
	String Origin;
	
	public static final String mName = "name";
	public static final String mFounded = "founded";
	public static final String mOrigin = "origin";
	public static final String mRevenue = "revenue";
	public static final String isEdit = "isEdit";
	public static final String mPosition = "position";
	
	ModelManufacturer manufacturer;
	ModelManufacturerList list_of_company;
	ControllerManufacturer mControllerManufacturer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_manufacturer);
		
		i = getIntent();
		
		name = (AutoCompleteTextView) findViewById(R.id.edit_company);
		founded = (AutoCompleteTextView) findViewById(R.id.edit_year);
		revenue = (AutoCompleteTextView) findViewById(R.id.edit_revenue);
		origin = (AutoCompleteTextView) findViewById(R.id.edit_origin);
		add_edit_label = (TextView) findViewById(R.id.add_edit_man_info);	
		add_edit = (Button) findViewById(R.id.add_edit_man);
		cancel = (Button) findViewById(R.id.cancel);
		
		name.setText(i.getStringExtra(ViewAddManufacturer.mName));
		founded.setText(i.getStringExtra(ViewAddManufacturer.mFounded));
		revenue.setText(i.getStringExtra(ViewAddManufacturer.mRevenue));
		origin.setText(i.getStringExtra(ViewAddManufacturer.mOrigin));
		
		
		if(i.getExtras().getBoolean(ViewAddManufacturer.isEdit))
			editLabel();
		else
			addLabel();
		
		
		mControllerManufacturer = new ControllerManufacturer(MainActivity.getAppContext(), "Manufacturers.json");
		list_of_company = new ModelManufacturerList(MainActivity.getAppContext());
		add_edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(i.getExtras().getBoolean(ViewAddManufacturer.isEdit))
					edit();
				else
					add();
				
			}

			private void add() {
				setValues();
				manufacturer = new ModelManufacturer(Name, Founded, Origin, Revenue);
				list_of_company.addManufacturer(manufacturer);	
				finish();				
			}

			private void edit() {
				setValues();
				manufacturer = new ModelManufacturer(Name, Founded, Origin, Revenue);
				try {
					mControllerManufacturer.editManufacturer(manufacturer, i.getExtras().getInt(ViewAddManufacturer.mPosition));
					finish();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

		
				
			
		});
		cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}
	private void addLabel() {
		add_edit.setText("Add");
		add_edit_label.setText("Add Manufacturer");
		
	}
	private void editLabel() {
		add_edit.setText("Edit");
		add_edit_label.setText("Edit Manufacturer");
		
	}
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
