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
	public ControllerManufacturer(Context c, String f) {
		context = c;
		mFilename = f;
	}
	public void saveManufacturers(ArrayList<ModelManufacturer> manufacturers) throws JSONException, IOException {
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

		} catch (FileNotFoundException e){

		} finally{
			if(reader != null)
				reader.close();
		}
		return manufacturers;
	}

}
