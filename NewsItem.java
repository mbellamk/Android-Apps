package com.example.midtermexam;

import java.util.ArrayList;

public class NewsItem {
	String title, description, link, guid, pubDate;
	ArrayList<String> mediaUrl;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public ArrayList<String> getMediaUrl() {
		return mediaUrl;
	}

	public void setMediaUrl(ArrayList<String> mediaUrl) {
		this.mediaUrl = mediaUrl;
	}
	
	public void addMediaUrl(String url)
	{
		this.mediaUrl.add(url);
	}

	@Override
	public String toString() {
		return title;
	}

}
