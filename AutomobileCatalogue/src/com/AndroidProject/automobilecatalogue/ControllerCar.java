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
	private ArrayList<ModelCar> mCars;
	public ControllerCar(Context c, String f) {
		context = c;
		mFilename = f;
		try {
			mCars = loadCars();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
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
	
	public void deleteCar(int position) throws IOException, JSONException{
		
		mCars.remove(position);
		/*ArrayList<ModelCar> cars = loadCars();
		
		for(int i =0; i<cars.size(); i++){
			 String name = cars.get(i).getmName();
			if(name.equals(mName)){
				cars.remove(i);
			}
		}*/
		partialSave(mCars);
	}
	public void editCar(ModelCar car, int position) throws IOException, JSONException{

		mCars.get(position).setmHorsepower(car.getmHorsepower());
		mCars.get(position).setmManufacturer(car.getmManufacturer());
		mCars.get(position).setmName(car.getmName());
		mCars.get(position).setmType(car.getmType());
		partialSave(mCars);
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
