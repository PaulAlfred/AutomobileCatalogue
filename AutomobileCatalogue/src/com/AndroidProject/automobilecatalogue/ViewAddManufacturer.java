package com.AndroidProject.automobilecatalogue;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ViewAddManufacturer extends Activity {
	
	Button add;
	
	AutoCompleteTextView name, founded, revenue, origin;
	ModelManufacturer manufacturer;
	ModelManufacturerList list_of_company = new ModelManufacturerList(MainActivity.getAppContext());
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_manufacturer);

		
		
		add = (Button) findViewById(R.id.add);
		name = (AutoCompleteTextView) findViewById(R.id.edit_company);
		founded = (AutoCompleteTextView) findViewById(R.id.edit_year);
		revenue = (AutoCompleteTextView) findViewById(R.id.edit_revenue);
		origin = (AutoCompleteTextView) findViewById(R.id.edit_origin);
		
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				manufacturer = new ModelManufacturer(name.getText().toString(), founded.getText().toString(), origin.getText().toString(), revenue.getText().toString());
				list_of_company.addManufacturer(manufacturer);
				list_of_company.saveManufacturers();
			}
		});
	}
	
	
}
