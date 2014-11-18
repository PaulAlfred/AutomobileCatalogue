package com.AndroidProject.automobilecatalogue;

import java.util.ArrayList;
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
import android.widget.ListView;
import android.widget.Toast;


public class CarActivity extends ActionBarActivity {

    private CarListAdapter carListAdapter;
    private ModelCarList modelCarlist;
    private int mPosition;
    private ArrayList<ModelCar> mCars;
    private ArrayList<String> mCarFilters;
    private boolean mIsEdit;
    private Intent i;
    private final int edit = 1;
    private final int  delete = 0;
    public static final String OBJECT = "object";
    public static final String MANUFACTURER = "manufacturer";
    public static final String CATEGORY = "category";
    public static final String MANUFACTURER_NO = "manufacturerNo";
    public static final String CATEGORY_NO = "categoryNo";

    //loading the cars from cars.json and putting its contents to the adapter
    //then displays the adapter in a listview
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car);

        new ArrayList<ModelCar>();
        i = getIntent();
        mIsEdit = false;
        mCars = new ArrayList<ModelCar>();
        mCarFilters = new ArrayList<String>();
        modelCarlist = new ModelCarList(getApplicationContext());
        mCars = modelCarlist.getCar();
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
            Intent intent = new Intent(CarActivity.this, ViewAddCar.class);
            intent.putExtra(ViewAddCar.isEdit, mIsEdit);
            intent.putExtra(ViewAddCar.mCategoryNo, i.getExtras().getInt(CarActivity.CATEGORY_NO));
            intent.putExtra(ViewAddCar.mManufacturerNo, i.getExtras().getInt(CarActivity.MANUFACTURER_NO));
            intent.putExtra(ViewAddCar.mObject, mCars);
            startActivityForResult(intent, 1);
            return true;
        default:
            return super.onOptionsItemSelected(item);

        }
    }

    //Refreshes the Adapter information every Resume
    @Override
    protected void onResume() {
        super.onResume();
        mIsEdit = false;
        generateAdapter();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;
        mPosition = info.position;
        menu.setHeaderTitle(mCars.get(mPosition).getName());
        menu.add(Menu.NONE,0,0,"Delete");
        menu.add(Menu.NONE,1,1,"Edit");

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {


        switch(item.getItemId()) {
        case delete:
            mCars.remove(carListAdapter.getItem(mPosition));
            generateAdapter();
            break;
        case edit:
            startActivityForResult(editCar((mCars.indexOf(carListAdapter.getItem(mPosition)))),1);
            generateAdapter();
            break;
        }
        
        return super.onContextItemSelected(item);
        
    }

    //put Extras to the intent to be started for editView
    private Intent editCar(int position) {
        mIsEdit = true;
        Intent intent = new Intent(this, ViewAddCar.class);
        intent.putExtra(ViewAddCar.Name,mCars.get(position).getName());
        intent.putExtra(ViewAddCar.Category,mCars.get(position).getType());
        intent.putExtra(ViewAddCar.Manufacturer,mCars.get(position).getManufacturer());
        intent.putExtra(ViewAddCar.Horsepower,mCars.get(position).getHorsepower());
        intent.putExtra(ViewAddCar.isEdit, true);
        intent.putExtra(ViewAddCar.mPosition, position);
        intent.putExtra(ViewAddCar.mCategoryNo, i.getExtras().getInt(CarActivity.CATEGORY_NO));
        intent.putExtra(ViewAddCar.mManufacturerNo, i.getExtras().getInt(CarActivity.MANUFACTURER_NO));
        intent.putExtra(ViewAddCar.mObject, mCars);
        return intent;
    }

    //generates the Adapter information
    private void generateAdapter() {

        mCarFilters.add(i.getExtras().getString(CarActivity.MANUFACTURER));
        mCarFilters.add(i.getExtras().getString(CarActivity.CATEGORY));
        carListAdapter = new CarListAdapter(this, mCars ,mCarFilters);

        ListView carList = (ListView) findViewById(R.id.activityCarList);
        carList.setAdapter(carListAdapter);
        registerForContextMenu(carList);
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                mCars = (ArrayList<ModelCar>) data.getSerializableExtra(CarActivity.OBJECT);
            } else if (resultCode == RESULT_CANCELED && mIsEdit) {
                Toast.makeText(getApplicationContext(), "Edit Car Canceled", Toast.LENGTH_SHORT).show();
            } else if (resultCode == RESULT_CANCELED && !mIsEdit) {
                Toast.makeText(getApplicationContext(), "Add Car Canceled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        modelCarlist.saveCar(mCars);
        super.onDestroy();
    }
}
