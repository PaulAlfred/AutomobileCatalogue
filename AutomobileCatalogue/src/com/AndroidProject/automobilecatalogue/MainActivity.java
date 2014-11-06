package com.AndroidProject.automobilecatalogue;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONException;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {

	 ManufacturerListAdapter brand_list_adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        byte[] buffer;
		try {
			MyJSONClass MainObj = new MyJSONClass(getAssets().open("Manufacturers.txt"));
			
			brand_list_adapter = new ManufacturerListAdapter(getApplicationContext(),MainObj.getJSONObject());
	        
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        ListView mainList = (ListView) findViewById(R.id.mainList);
        mainList.setAdapter(brand_list_adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
