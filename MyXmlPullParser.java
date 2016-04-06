package com.example.midtermexam;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.util.Log;

/**
 * Assignment No: InClass05 File Name: MyXmlPullParser.java
 * 
 * Team Members: 1. Manju Raghavendra Bellamkonda 2. Prem kumar Murugesan 3.
 * Madhavi bhat
 */
public class MyXmlPullParser {

	static class MyBBCXmlPullParser {
		static ArrayList<NewsItem> parseFlickrData(InputStream in)
				throws XmlPullParserException, IOException {
			Log.d("MidTerm", "Pulling Data from XMLPull");
			ArrayList<NewsItem> newsItems = null;
			XmlPullParser parser = XmlPullParserFactory.newInstance()
					.newPullParser();
			parser.setInput(in, "UTF-8");
			int event = parser.getEventType();
			NewsItem item = null;
			StringBuilder text = new StringBuilder();
			while (event != XmlPullParser.END_DOCUMENT) {
				switch (event) {
				case XmlPullParser.START_DOCUMENT:
					newsItems = new ArrayList<NewsItem>();
					break;
				case XmlPullParser.START_TAG:
					if (parser.getName().equals("item")) {
						item = new NewsItem();
					} else if (item != null && parser.getName().equals("title")) {
						text = new StringBuilder();
					} else if (item != null
							&& parser.getName().equals("description")) {
						text = new StringBuilder();
					} else if (item != null && parser.getName().equals("link")) {
						text = new StringBuilder();
					} else if (item != null && parser.getName().equals("guid")) {
						text = new StringBuilder();
					} else if (item != null
							&& parser.getName().equals("pubDate")) {
						text = new StringBuilder();
					} else if (item != null
							&& parser.getName().equals("media:thumbnail")) {
						text = new StringBuilder();
					}

					break;
				case XmlPullParser.END_TAG:
					if (item != null && parser.getName().equals("title")) {
						item.setTitle(text.toString());
						newsItems.add(item);
					}else if (item != null
							&& parser.getName().equals("description")) {
						item.setDescription(text.toString());
					} else if (item != null && parser.getName().equals("link")) {
						item.setLink(text.toString());
					} else if (item != null && parser.getName().equals("guid")) {
						item.setGuid(text.toString());
					} else if (item != null
							&& parser.getName().equals("pubDate")) {
						item.setPubDate(text.toString());
					} else if (item != null
							&& parser.getName().equals("media:thumbnail")) {
						if(item.getMediaUrl()==null)
						{
							item.setMediaUrl(new ArrayList<String>());
						}
						item.addMediaUrl(text.toString());
					}
					break;
				case XmlPullParser.TEXT:
					text.append(parser.getText());
					break;
				}

				event = parser.next();
			}
/*			for(NewsItem itemTest:newsItems)
			{
				Log.d("MidTerm", "Description:" +itemTest.getDescription());	
				Log.d("MidTerm", "Link:" +itemTest.getLink());
				Log.d("MidTerm", "guid:" +itemTest.getGuid());
				Log.d("MidTerm", "pubDate:" +itemTest.getPubDate());
				Log.d("MidTerm", "media:" +itemTest.getMediaUrl().toString());
			}*/
			
			return newsItems;

		}
	}
}
