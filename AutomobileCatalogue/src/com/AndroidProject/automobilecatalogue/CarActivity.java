package com.AndroidProject.automobilecatalogue;

import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;


public class CarActivity extends Activity {

	 CarListAdapter car_list;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
			MyJSONClass MainObj;
			try {
				MainObj = new MyJSONClass(getAssets().open("Cars.txt"));
				car_list = new CarListAdapter(CarActivity.this,MainObj.getJSONObject());   
			} catch (IOException e) {
				e.printStackTrace();
			}

	
        ListView carList = (ListView) findViewById(R.id.carList);
        carList.setAdapter(car_list);
    }
}
