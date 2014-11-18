package com.AndroidProject.automobilecatalogue;

import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONException;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;


public class MainActivity extends ActionBarActivity {


    private ManufacturerListAdapter manufacturerListAdapter;
    private ModelManufacturerList mModelManufacturerList;
    private ControllerCar controllerCar;
    private ModelCarList modelCarList;
    public static final String mObject = "object";
    private int mPosition;
    private ArrayList<ModelManufacturer> mManufacturers;
    private ArrayList<ModelCar> cars;
    private boolean mIsEdit;
    //loading the cars from Manufacturers.json and putting its contents to the adapter
    //then displays the adapter in a listview
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mIsEdit = false;
        mManufacturers = new ArrayList<ModelManufacturer>();
        mModelManufacturerList = new ModelManufacturerList(getApplicationContext());
        mManufacturers = mModelManufacturerList.getManufacturers();
        cars = new ArrayList<ModelCar>();
        modelCarList = new ModelCarList(getApplicationContext());
        new ArrayList<ModelCar>();
        controllerCar = new ControllerCar(getApplicationContext(), "Cars.json");
        setContentView(R.layout.activity_main);
    }

    //inflates the add menu and icon on the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;

    }

    //starts the activity of the add menu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.action_add:
            Intent intent = new Intent(MainActivity.this, ViewAddManufacturer.class);
            intent.putExtra(ViewAddManufacturer.isEdit, mIsEdit);
            intent.putExtra(ViewAddManufacturer.mObject,mManufacturers);
            startActivityForResult(intent, 1);
            return true;
        default:
            return super.onOptionsItemSelected(item);

        }	
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        mPosition = info.position;
        menu.setHeaderTitle(mManufacturers.get(mPosition).getName());
        menu.add(Menu.NONE,0,0,"Delete");
        menu.add(Menu.NONE,1,1,"Edit");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch(item.getItemId()){
        case 0:
            removeCars(mPosition);
            mManufacturers.remove(mPosition);
            generateAdapter();
            break;
        case 1:
            startActivityForResult(editManufacturer(mPosition), 1);
            generateAdapter();
            break;

        }
        return super.onContextItemSelected(item);
    }

    //put Extras to the intent to be started for editView
    private Intent editManufacturer(int mPosition) {
        mIsEdit = true;
        Intent intent = new Intent(this, ViewAddManufacturer.class);
        intent.putExtra(ViewAddManufacturer.mName, mManufacturers.get(mPosition).getName());
        intent.putExtra(ViewAddManufacturer.mFounded, mManufacturers.get(mPosition).getFounded());
        intent.putExtra(ViewAddManufacturer.mOrigin, mManufacturers.get(mPosition).getOrigin());
        intent.putExtra(ViewAddManufacturer.mRevenue, mManufacturers.get(mPosition).getRevenue());
        intent.putExtra(ViewAddManufacturer.isEdit, mIsEdit);
        intent.putExtra(ViewAddManufacturer.mPosition, mPosition);
        intent.putExtra(ViewAddManufacturer.mObject, mManufacturers);
        return intent;

    }
    @Override
    protected void onResume() {
        mIsEdit = false;
        generateAdapter();
        super.onResume();
    }
    @Override
    protected void onPause() {
        mModelManufacturerList.saveManufacturers(mManufacturers);
        cars = modelCarList.getCar();
        super.onPause();
    }
    @SuppressWarnings("unchecked")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                mManufacturers = (ArrayList<ModelManufacturer>) data.getSerializableExtra(MainActivity.mObject);
            }
            if (resultCode == RESULT_CANCELED) {
            }
        }
    }
    //Used to simplify code
    private void  generateAdapter(){

        manufacturerListAdapter = new ManufacturerListAdapter(this, mManufacturers);  

        ListView mainList = (ListView) findViewById(R.id.activityMainList);
        mainList.setAdapter(manufacturerListAdapter);
        mainList.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                    long s) {
                Intent intent = new Intent(MainActivity.this,CategoryActivity.class);
                ModelManufacturer manufacturer = (ModelManufacturer) manufacturerListAdapter.getItem(position);
                intent.putExtra(CategoryActivity.MANUFACTURER,manufacturer.getName());
                intent.putExtra(CategoryActivity.MANUFACTURER_NO, position);
                startActivity(intent);

            }
        });
        registerForContextMenu(mainList);
    }
    //filtersCars
    public void removeCars(int position){

        Iterator<ModelCar> iter = cars.iterator();
        while(iter.hasNext()){
            ModelCar modelCar = iter.next();
            if(modelCar.getManufacturer().equals(mManufacturers.get(position).getName()))
                iter.remove();
        }

        try {
            controllerCar.partialSave(cars);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}