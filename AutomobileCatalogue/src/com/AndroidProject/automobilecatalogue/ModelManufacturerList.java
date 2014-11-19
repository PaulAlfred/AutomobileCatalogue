package com.AndroidProject.automobilecatalogue;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.json.JSONException;

import android.content.Context;


//Acts as a bridge of ControllerManufacturer and ModelManufacturer
public class ModelManufacturerList implements Serializable {


    private static final long serialVersionUID = 7609809664710096575L;

    private final String mFilename = "Manufacturers.json";

    private ArrayList<ModelManufacturer> mManufacturers;
    private ControllerManufacturer manSerializer;

    public static ModelManufacturerList sCompany;
    private Context mAppcontext;

    public ModelManufacturerList(Context appContext) {
        mAppcontext = appContext;
        mManufacturers = new ArrayList<ModelManufacturer>();
        manSerializer = new ControllerManufacturer(mAppcontext, mFilename);
        loadManufacturers();
    }

    public void addManufacturer(ModelManufacturer m) {
        mManufacturers.add(m);
    }

    public void saveManufacturers(ArrayList<ModelManufacturer> mManufacturers) {
        manSerializer.saveManufacturers(mManufacturers);
    }

    private void loadManufacturers() {
        try {
            mManufacturers = manSerializer.loadManufacturers();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ModelManufacturer> getManufacturers() {
        return mManufacturers;
    }

    public void setManufacturers(ArrayList<ModelManufacturer> mManufacturers) {
        this.mManufacturers = mManufacturers;
    }
}
