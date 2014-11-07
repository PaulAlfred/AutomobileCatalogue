package com.AndroidProject.automobilecatalogue;

import java.io.IOException;
import java.util.ArrayList;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ViewAddManufacturer extends Activity {

	Button add;
	EditText name, founded, revenue, origin;
	ArrayList<ModelManufacturer> list_of_manufacturer = new ArrayList<ModelManufacturer>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_manufacturer);
		add = (Button) findViewById(R.id.add);
		add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				try {
					MyJSONClass MainObj = new MyJSONClass(getAssets().open("Manufacturer.txt"));
					ModelManufacturer manufacturer = new ModelManufacturer();
					//manufacturer.AddManufacturer(jObj, jArr);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
	}

}
