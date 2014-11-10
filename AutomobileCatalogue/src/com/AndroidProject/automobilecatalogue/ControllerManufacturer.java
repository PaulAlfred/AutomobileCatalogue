package com.AndroidProject.automobilecatalogue;

import android.content.Context;

public class ControllerManufacturer  {
	
	Context context;
	
	private ModelCompany list_of_manufacturers = new ModelCompany(MainActivity.getAppContext());	
	
	public ControllerManufacturer() {
		this.context = MainActivity.getAppContext();
	}
	
	public void setManufacturer(ModelManufacturer manufacturer) {
		list_of_manufacturers.addManufacturer(manufacturer);
	}   
	public void deleteManufacturer(ModelManufacturer manufacturer){
		list_of_manufacturers.delete(manufacturer);
	}
}
