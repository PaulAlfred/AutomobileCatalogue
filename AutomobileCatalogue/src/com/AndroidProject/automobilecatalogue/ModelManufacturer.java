package com.AndroidProject.automobilecatalogue;

public class ModelManufacturer extends ModelBasicOperations{

	String name, founded, origin, revenue;
	
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFounded() {
		return founded;
	}

	public void setFounded(String founded) {
		this.founded = founded;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getRevenue() {
		return revenue;
	}

	public void setRevenue(String revenue) {
		this.revenue = revenue;
	}

	@Override
	public void addToJSON() {
		// TODO Auto-generated method stub
		super.addToJSON();
	}

	/*@Override
	public void delete() {
		// TODO Auto-generated method stub
		super.delete();
	}
*/
	@Override
	public void edit() {
		// TODO Auto-generated method stub
		super.edit();
	}

	@Override
	public void select() {
		// TODO Auto-generated method stub
		super.select();
	}

	@Override
	public void setValues() {
		// TODO Auto-generated method stub
		super.setValues();
	}

}
