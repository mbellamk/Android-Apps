package com.example.inclass03;

import java.io.Serializable;

/**
 * Assignment No: InClass Assignment03  
 * File Name: Student.java 
 * 
 * Team Members: 
 * 1. Manju Raghavendra Bellamkonda 
 * 2. Prem kumar Murugesan
 * 3. Madhavi bhat
 */
public class Student implements Serializable{

	String name, emailAddress, programmingLanguage;
	Boolean accountState;
	Integer mood;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getProgrammingLanguage() {
		return programmingLanguage;
	}

	public void setProgrammingLanguage(String programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}

	public Boolean getAccountState() {
		return accountState;
	}

	public void setAccountState(Boolean accountState) {
		this.accountState = accountState;
	}

	public Integer getMood() {
		return mood;
	}

	public void setMood(Integer mood) {
		this.mood = mood;
	}

}
