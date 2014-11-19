package com.AndroidProject.automobilecatalogue;

import java.io.Serializable;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewAddManufacturer extends Activity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 3291828332789817065L;
    //variables for widgets
    private Button addEdit, cancel;
    private TextView addEditLabel;
    private EditText name;
    private EditText founded;
    private EditText revenue;
    private EditText origin;
    //Model and Controller Objects
    private ModelManufacturer manufacturer;
    private ArrayList<ModelManufacturer> manufacturers;
    private Intent i;
    //intent values
    public static final String mName = "name";
    public static final String mFounded = "founded";
    public static final String mOrigin = "origin";
    public static final String mRevenue = "revenue";
    public static final String isEdit = "isEdit";
    public static final String mPosition = "position";
    public static final String mObject = "object";
    //for better readability of listOfObject.get(position).getType();
    private String Name;
    private String Founded;
    private String Revenue;
    private String Origin;
    private int position;
    @SuppressWarnings("unchecked")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_manufacturer);

        i = getIntent();
        i.getExtras().getBoolean(ViewAddManufacturer.isEdit);
        manufacturers = new ArrayList<ModelManufacturer>();
        manufacturers =  (ArrayList<ModelManufacturer>) i.getSerializableExtra(ViewAddManufacturer.mObject);

        name = (EditText) findViewById(R.id.editTextCompanyName);
        founded = (EditText) findViewById(R.id.editTextYear);
        revenue = (EditText) findViewById(R.id.editTextRevenue);
        origin = (EditText) findViewById(R.id.editTextOrigin);
        addEditLabel = (TextView) findViewById(R.id.add_edit_man_info);	
        addEdit = (Button) findViewById(R.id.add_edit_man);
        cancel = (Button) findViewById(R.id.cancel);

        name.setText(i.getStringExtra(ViewAddManufacturer.mName));
        founded.setText(i.getStringExtra(ViewAddManufacturer.mFounded));
        revenue.setText(i.getStringExtra(ViewAddManufacturer.mRevenue));
        origin.setText(i.getStringExtra(ViewAddManufacturer.mOrigin));
        
        position = i.getExtras().getInt(ViewAddManufacturer.mPosition);
        Labels(i.getExtras().getBoolean(ViewAddManufacturer.isEdit));
        addEdit.setOnClickListener(new OnClickListener() {

        boolean isAdd = !(i.getExtras().getBoolean(ViewAddManufacturer.isEdit));
            @Override
            public void onClick(View v) {
                
                String selectedName = i.getExtras().getString(ViewAddManufacturer.mName);
                boolean isSameName = name.getText().toString().equals(selectedName);
                boolean isExists = isNameExists(name.getText().toString());

                if (isExists && (isAdd||!isSameName)) {
                    Toast.makeText(getApplicationContext(), "The Name you have chosen has already been taken.", Toast.LENGTH_SHORT).show();
                    finish();
                } else 
                    addOrEdit(i.getExtras().getBoolean(ViewAddManufacturer.isEdit));

            }
        });
        cancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //Labels for the widgets to determine what state of functionality it is in
    private void Labels(boolean isEdit) {
        
        if (isEdit) {	
            addEdit.setText("Edit");
            addEditLabel.setText("Edit Manufacturer");
        } else {
            addEdit.setText("Add");
            addEditLabel.setText("Add Manufacturer");
        }

    }

    //actions for different functionality add or edit	
    private void addOrEdit(boolean isEdit) {

        setValues();
        manufacturer = new ModelManufacturer(Name, Founded, Origin, Revenue);

        if (isEdit) {
            manufacturers.get(position).setFounded(manufacturer.getFounded());
            manufacturers.get(position).setName(manufacturer.getName());
            manufacturers.get(position).setOrigin(manufacturer.getOrigin());
            manufacturers.get(position).setRevenue(manufacturer.getRevenue());
        } else {
            manufacturers.add(manufacturer);			
        }
        Intent resultIntent = new Intent();
        resultIntent.putExtra(MainActivity.mObject, manufacturers);
        resultIntent.putExtra(MainActivity.PREV_NAME, i.getStringExtra(ViewAddManufacturer.mName));
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }

    //method to hide the setting of helper strings
    private void setValues() {
        Name = name.getText().toString();
        Founded = founded.getText().toString();
        Origin = origin.getText().toString();
        Revenue = revenue.getText().toString();

        if (TextUtils.isEmpty(Name)) {
            Toast.makeText(getApplicationContext(), "You can not input null for name", Toast.LENGTH_SHORT).show();
            finish();
        }
        if (TextUtils.isEmpty(Founded))
            Founded = "2000";
        if (TextUtils.isEmpty(Origin))
            Origin = "Europe";
        if (TextUtils.isEmpty(Revenue))
            Revenue = "$500M";
    }

    private boolean isNameExists(String name) {
        for(ModelManufacturer m : manufacturers ) {
            if (name.toLowerCase().equals(m.getName().toLowerCase()))
                return true;
        }
        return false;
    }
}
