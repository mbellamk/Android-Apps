package com.example.inclass06;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Assignment No: InClass06 File Name: Json500pxUtil.java
 * 
 * Team Members: 
 * 1. Manju Raghavendra Bellamkonda 
 * 2. Prem kumar Murugesan 
 * 3. Madhavi bhat
 */
public class Json500pxUtil {

	static class JsonParser
	{
		public static ArrayList<Photo> parseJson(String in) throws JSONException
		{
			ArrayList<Photo> photos=new ArrayList<Photo>();
			JSONObject photosObject=new JSONObject(in);
			JSONArray photoArray=photosObject.getJSONArray("photos");
			for(int i=0;i<photoArray.length();i++)
			{
				JSONObject photoObj=photoArray.getJSONObject(i);
				Photo photo=Photo.createPhotoObject(photoObj);
				photos.add(photo);
			}
			return photos;
		}
	}
}
