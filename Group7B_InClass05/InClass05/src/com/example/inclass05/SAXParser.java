package com.example.inclass05;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;
import android.util.Xml;

/**
 * Assignment No:  InClass05 
 * File Name: SAXParser.java 
 * 
 * Team Members: 
 * 1. Manju Raghavendra Bellamkonda 
 * 2. Prem kumar Murugesan
 * 3. Madhavi bhat
 */
public class SAXParser {
	
	public static class MyImageSAXParser extends DefaultHandler
	{
		ArrayList<String> imageUrls;
		
		static ArrayList<String> parseFlickrData(InputStream in) throws IOException, SAXException
		{
			Log.d("InClass05", "Pulling Data from SAX");
			MyImageSAXParser saxParser=new MyImageSAXParser();
			Xml.parse(in, Xml.Encoding.UTF_8, saxParser);
			return saxParser.getImageUrls();
			
		}

		public ArrayList<String> getImageUrls() {
			return imageUrls;
		}

		@Override
		public void characters(char[] ch, int start, int length)
				throws SAXException {
			super.characters(ch, start, length);
		}

		@Override
		public void endDocument() throws SAXException {
			super.endDocument();
		}

		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException {
			super.endElement(uri, localName, qName);
		}

		@Override
		public void startDocument() throws SAXException {
			super.startDocument();
			imageUrls=new ArrayList<String>();
		}

		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException {
			super.startElement(uri, localName, qName, attributes);
			if(localName.equals("photo"))
			{
				imageUrls.add(attributes.getValue("url_m"));
			}
		}
		
	}

}
