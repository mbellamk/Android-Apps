package com.example.inclass06;

import java.io.Serializable;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Assignment No: InClass06 File Name: Photo.java
 * 
 * Team Members: 
 * 1. Manju Raghavendra Bellamkonda 
 * 2. Prem kumar Murugesan 
 * 3. Madhavi bhat
 */
public class Photo implements Serializable {

	String title, url, ownerName;

	static Photo createPhotoObject(JSONObject in) throws JSONException {
		Photo photo = new Photo();
		photo.setTitle(in.getString("name"));
		photo.setUrl(in.getString("image_url") != null ? in
				.getString("image_url") : "http://raghav.org");
		photo.setOwnerName(in.getJSONObject("user").getString("fullname"));
		return photo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	@Override
	public String toString() {
		return this.title;
	}

}
