package org.cit310;

import java.io.Serializable;
import java.util.HashMap;

public class Costume implements Serializable {
	private String name;
	private String type;
	private String size;
	private String rating;
	private int storageUnit;
	private String image;
	private String description;
	
	Costume(HashMap<String,String> jsonHash){
		name = jsonHash.get("name");
		type = jsonHash.get("type");
		size = jsonHash.get("size");
		rating = jsonHash.get("rating");
		storageUnit = Integer.parseInt(jsonHash.get("storageUnit"));
		image = jsonHash.get("image");
		description = jsonHash.get("description");
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public int getStorageUnit() {
		return storageUnit;
	}

	public void setStorageUnit(int storageUnit) {
		this.storageUnit = storageUnit;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
