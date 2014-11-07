package com.AndroidProject.automobilecatalogue;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;



public class MyJSONClass {
	
	byte[] buffer;
	String stringBuffer;
	JSONObject jObj;
	Context context;
	
	public MyJSONClass(InputStream is){
		
		try {
			buffer = new byte[is.available()];
			is.read(buffer);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 stringBuffer = new String(buffer);
	}
	public void add(){
		
	}
	public JSONObject getJSONObject(){
		try {
			this.jObj = new JSONObject(stringBuffer);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return this.jObj;
	}
}
