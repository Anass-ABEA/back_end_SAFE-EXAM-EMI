package com.thexcoders.classes;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

public class Class {
	String year;
	String specialty;
	HashSet Groups;

	public Class() {
	}

	public Class(String year, String specialty, HashSet groups) {
		this.year = year;
		this.specialty = specialty;
		Groups = groups;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public HashSet getGroups() {
		return Groups;
	}

	public void setGroups(HashSet groups) {
		Groups = groups;
	}

	@Override
	public String toString() {
		JSONObject json = new JSONObject();
		try {
			json.put("class",this.Groups);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		String res = "{" +
			"year:'" + year + '\'' +
			", specialty:'" + specialty + '\'' +
			", Groups: "+ this.Groups+ "}";
		return res;
	}
}
