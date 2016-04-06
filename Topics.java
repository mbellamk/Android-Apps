package com.example.midtermexam;

public class Topics {
	String type,url;

	public Topics(String type, String url) {
		super();
		this.type = type;
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return this.type;
	}
	
	
}
