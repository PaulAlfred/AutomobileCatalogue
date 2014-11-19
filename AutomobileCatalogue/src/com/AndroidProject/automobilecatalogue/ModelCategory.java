package com.AndroidProject.automobilecatalogue;

import org.json.JSONException;
import org.json.JSONObject;

public class ModelCategory{
    //To be put on a jsonObject
    //json variables
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";
    //values
    private String mName, mDescription;

    //constructor that sets the values of the category
    public ModelCategory(String Name, String Description) {
        this.mName = Name;
        this.mDescription = Description;
    }

    //getters of values
    public String getName() {
        return mName;
    }
    public String getDescription() {
        return mDescription;
    }

    //gets the value of the jsonobject
    public ModelCategory(JSONObject json) throws JSONException {
        mName = json.getString(NAME);
        mDescription = json.getString(DESCRIPTION);

    }

    //puts the "variables":"values" in a jsonobject
    public JSONObject toJSON() throws JSONException{
        JSONObject item = new JSONObject();
        item.put("name", this.mName);
        item.put("description", this.mDescription);
        return item;
    }
}