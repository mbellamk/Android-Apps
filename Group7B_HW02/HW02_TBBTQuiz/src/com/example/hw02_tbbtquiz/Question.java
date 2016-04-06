package com.example.hw02_tbbtquiz;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Assignment No:  HomeWork02 
 * File Name: Question.java 
 * 
 * Team Members: 
 * 1. Manju Raghavendra Bellamkonda 
 * 2. Prem kumar Murugesan
 * 3. Madhavi bhat
 */
public class Question implements Parcelable {
	String question;
	String url;
	ArrayList<String> multipleChoices;
	Integer answer;

	public Question(String question, String url,
			ArrayList<String> multipleChoices, Integer answer) {
		super();
		this.question = question;
		this.url = url;
		this.multipleChoices = multipleChoices;
		this.answer = answer;
	}

	public Question(Parcel source) {
		this.question = source.readString();
		this.url = source.readString();
		multipleChoices=new ArrayList<String>();
		source.readStringList(multipleChoices);
		this.answer = source.readInt();
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(question);
		dest.writeString(url);
		dest.writeStringList(multipleChoices);
		dest.writeInt(answer);
	}

	public static final Parcelable.Creator<Question> CREATOR = new Parcelable.Creator<Question>() {

		@Override
		public Question createFromParcel(Parcel source) {
			return new Question(source);
		}

		@Override
		public Question[] newArray(int size) {
			return new Question[size];
		}
	};

	
	public String getQuestion() {
		return question;
	}

	public String getUrl() {
		return url;
	}

	public ArrayList<String> getMultipleChoices() {
		return multipleChoices;
	}

	public Integer getAnswer() {
		return answer;
	}

	@Override
	public String toString() {
		return "Question [question=" + question + ", url=" + url
				+ ", multipleChoices=" + multipleChoices + ", answer=" + answer
				+ "]";
	}

}
