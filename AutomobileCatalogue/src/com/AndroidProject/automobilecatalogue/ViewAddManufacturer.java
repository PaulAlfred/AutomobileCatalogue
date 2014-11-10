package com.AndroidProject.automobilecatalogue;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ViewAddManufacturer extends Activity {
	
	ControllerManufacturer manufacturer_controller = new ControllerManufacturer();
	
	ModelManufacturer	manufacturer_model = new ModelManufacturer();
	Button add;
	
	EditText name, founded, revenue, origin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_manufacturer);
		
		add = (Button) findViewById(R.id.add);
		name = (EditText) findViewById(R.id.edit_car_name);
		founded = (EditText) findViewById(R.id.edit_year);
		revenue = (EditText) findViewById(R.id.edit_revenue);
		origin = (EditText) findViewById(R.id.edit_origin);
		
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				manufacturer_model.setName(name.getText().toString());
				manufacturer_model.setFounded(founded.getText().toString());
				manufacturer_model.setOrigin(origin.getText().toString());
				manufacturer_model.setRevenue(revenue.getText().toString());
				manufacturer_controller.setManufacturer(manufacturer_model);				
				
			}
		});
	}

}
