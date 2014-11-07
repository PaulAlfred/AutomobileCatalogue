package com.AndroidProject.automobilecatalogue;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Toast;

public class CarActivity extends Activity {

	 CarListAdapter car_list;
	 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);
		try {
			MyJSONClass MainObj = new MyJSONClass(getAssets().open("Cars.txt"));
			car_list = new CarListAdapter(CarActivity.this,MainObj.getJSONObject());   
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), e.getClass().getName(), Toast.LENGTH_LONG).show();
			e.printStackTrace();
		}
        ListView carList = (ListView) findViewById(R.id.carList);
        carList.setAdapter(car_list);
    }
}
