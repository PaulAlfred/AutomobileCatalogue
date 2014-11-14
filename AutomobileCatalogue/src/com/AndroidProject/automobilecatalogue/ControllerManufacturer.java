package com.AndroidProject.automobilecatalogue;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import android.content.Context;
import android.util.Log;


public class ControllerManufacturer {
	
	private Context context;
	private String mFilename;
	private ArrayList<ModelManufacturer> manufacturers;
	private ArrayList<ModelCar> cars;
	private ArrayList<ModelCar> modifiedCars;
	private ControllerCar carController;
	public ControllerManufacturer(Context c, String f){
		context = c;
		mFilename = f;
		carController = new ControllerCar(context, "Cars.json");
		try {
			manufacturers = loadManufacturers();
			cars = new ArrayList<ModelCar>();
			modifiedCars = new ArrayList<ModelCar>();
			cars = carController.loadCars();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//saves the cars to the Manufacturer.json file, that was previously added to ModelManufacturer
	//or creates a new cars.json, then stores
	//to the default location with Filename = mFilename
	public void saveManufacturers(ArrayList<ModelManufacturer> manufacturers){
		try {
			manufacturers.addAll(loadManufacturers());
			partialSave(manufacturers);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//loads the cars form mFilename
	public ArrayList<ModelManufacturer> loadManufacturers() throws IOException, JSONException{
		ArrayList<ModelManufacturer> manufacturers = new ArrayList<ModelManufacturer>();
		BufferedReader reader = null;
		try{
			InputStream in = context.openFileInput(mFilename);
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder jsonString = new StringBuilder();
			String line = null;
			while((line = reader.readLine()) != null){
				jsonString.append(line);
			}
			JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
			for(int i=0; i<array.length(); i++){
				manufacturers.add(new ModelManufacturer(array.getJSONObject(i)));
			}
			Log.d("LoadArray",array.toString());
			
			if(reader != null)
				reader.close();

		} catch (FileNotFoundException e){

		}
		return manufacturers;
	}
	//deletes the manufacturer on the jsonFile
	public void deleteManufacturer(int position) throws IOException, JSONException{
		
		for(ModelCar c : cars)
			if(!c.getManufacturer().equals(manufacturers.get(position).getName()))
				modifiedCars.add(c);
		
		manufacturers.remove(position);
		partialSave(manufacturers);
		carController.partialSave(modifiedCars);
	}
	//edits the manufactuer information on the jsonfile
	public void editManufacturer(ModelManufacturer model, int position) throws IOException, JSONException{
		manufacturers.get(position).setFounded(model.getFounded());
		manufacturers.get(position).setName(model.getName());
		manufacturers.get(position).setOrigin(model.getOrigin());
		manufacturers.get(position).setRevenue(model.getRevenue());
		partialSave(manufacturers);
	}
	//part of save functionality that overwrites the jsonfile	
	private void partialSave(ArrayList<ModelManufacturer> manufacturers) throws JSONException
	{
		JSONArray array = new JSONArray();
		for (ModelManufacturer m : manufacturers)
			array.put(m.toJSON());

		Writer writer = null;
		try{
			OutputStream out = context.openFileOutput(mFilename, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(array.toString());
		} catch(IOException e){
			e.printStackTrace();
		}
		finally {
			if(writer != null)
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}
}
