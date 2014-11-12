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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;


public class ControllerCar  extends Activity{

	

	private Context context;
	private String mFilename;
	public ControllerCar(Context c, String f) {
		context = c;
		mFilename = f;
	}
	//saves the cars to the cars.json file, that was previously added to ModelCar
	//or creates a new cars.json, then stores
	//to the default location with Filename = mFilename
	public void saveCars(ArrayList<ModelCar> cars) throws JSONException, IOException {
		cars.addAll(loadCars());
		partialSave(cars);
	}
	//loads the cars form mFilename
	public ArrayList<ModelCar> loadCars() throws IOException, JSONException{
		ArrayList<ModelCar> cars = new ArrayList<ModelCar>();
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
				cars.add(new ModelCar(array.getJSONObject(i)));
			}
			Log.d("LoadArray",array.toString());
			
			if(reader != null)
				reader.close();

		} catch (FileNotFoundException e){

		}
		return cars;
	}
	
	public void deleteCar(String mName) throws IOException, JSONException{
		
		ArrayList<ModelCar> cars = loadCars();
		
		for(int i =0; i<cars.size(); i++){
			 String name = cars.get(i).getmName();
			if(name.equals(mName)){
				cars.remove(i);
			}
		}
		partialSave(cars);
	}
	public void editCar(String mName) throws IOException, JSONException{

		ArrayList<ModelCar> cars = loadCars();

		for(int i =0; i<cars.size(); i++){
			String name = cars.get(i).getmName();
			if(name.equals(mName)){
				cars.get(i);
				Intent intent = new Intent(this, ViewAddCar.class);
				intent.putExtra(ViewAddCar.Name, cars.get(i).getmName());
				intent.putExtra(ViewAddCar.Category, cars.get(i).getmType());
				intent.putExtra(ViewAddCar.Manufacturer, cars.get(i).getmManufacturer());
				intent.putExtra(ViewAddCar.Horsepower, cars.get(i).getmHorsepower());
				context.startActivity(intent);
			}
		}
	}
	public void editCar(ModelCar model, String mName) throws IOException, JSONException{
		ArrayList<ModelCar> cars = new ArrayList<ModelCar>();
		cars = loadCars();
		for(int i = 0; i < cars.size(); i++){
			if(model.getmName().equals(cars.get(i).getmName()))
				cars.get(i).setmName(model.getmName());
				cars.get(i).setmType(model.getmType());
				cars.get(i).setmManufacturer(model.getmManufacturer());
				cars.get(i).setmHorsepower(model.getmHorsepower());
		}
		partialSave(cars);
	}
	private void partialSave(ArrayList<ModelCar> cars) throws JSONException
	{
		JSONArray array = new JSONArray();
		for (ModelCar c : cars)
			array.put(c.toJSON());

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
