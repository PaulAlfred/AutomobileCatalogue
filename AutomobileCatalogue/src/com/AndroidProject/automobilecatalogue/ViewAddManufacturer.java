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

public class ViewAddManufacturer extends Activity {
	
	Intent i;
	
	Button add, edit;
	
	AutoCompleteTextView name;
	AutoCompleteTextView founded;
	AutoCompleteTextView revenue;
	AutoCompleteTextView origin;
	
	public static final String mName = "name";
	public static final String mFounded = "founded";
	public static final String mOrigin = "origin";
	public static final String mRevenue = "revenue";
	
	ModelManufacturer manufacturer;
	ModelManufacturerList list_of_company = new ModelManufacturerList(MainActivity.getAppContext());
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
			
		add = (Button) findViewById(R.id.add);
		edit = (Button) findViewById(R.id.edit);
		
		name.setText(i.getStringExtra(ViewAddManufacturer.mName));
		founded.setText(i.getStringExtra(ViewAddManufacturer.mFounded));
		revenue.setText(i.getStringExtra(ViewAddManufacturer.mRevenue));
		origin.setText(i.getStringExtra(ViewAddManufacturer.mOrigin));
		
		mControllerManufacturer = new ControllerManufacturer(MainActivity.getAppContext(), "Manufacturers.json");
		
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				manufacturer = new ModelManufacturer(name.getText().toString(), founded.getText().toString(), origin.getText().toString(), revenue.getText().toString());
				list_of_company.addManufacturer(manufacturer);	
				startActivity(new Intent(ViewAddManufacturer.this, MainActivity.class));
			}
		});
		edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				manufacturer = new ModelManufacturer(name.getText().toString(), founded.getText().toString(), origin.getText().toString(), revenue.getText().toString());
				try {
					mControllerManufacturer.editManufacturer(manufacturer, i.getStringExtra(ViewAddManufacturer.mName));
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
		startActivity(new Intent(this, MainActivity.class));
		
	}
	
}
