package com.example.inclass05;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

/**
 * Assignment No:  InClass05 
 * File Name: MyXmlPullParser.java 
 * 
 * Team Members: 
 * 1. Manju Raghavendra Bellamkonda 
 * 2. Prem kumar Murugesan
 * 3. Madhavi bhat
 */
public class MyXmlPullParser {

	static class MyFlickrXmlPullParser {
		static ArrayList<String> parseFlickrData(InputStream in)
				throws XmlPullParserException, IOException {
			Log.d("InClass05", "Pulling Data from XMLPull");
			ArrayList<String> imgUrlList = null;
			XmlPullParser parser = XmlPullParserFactory.newInstance()
					.newPullParser();
			parser.setInput(in, "UTF-8");
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {
				switch (event) {
				case XmlPullParser.START_DOCUMENT:
					imgUrlList = new ArrayList<String>();
					break;
				case XmlPullParser.START_TAG:
					if (parser.getName().equals("photo")) {
						imgUrlList.add(parser.getAttributeValue(null, "url_m"));
					}

					break;
				}
				event=parser.next();
			}
			return imgUrlList;

		}
	}
}
