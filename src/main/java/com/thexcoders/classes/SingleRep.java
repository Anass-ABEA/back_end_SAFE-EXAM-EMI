package com.thexcoders.classes;

import org.json.JSONArray;
import org.json.JSONException;

public class SingleRep extends StuRep {
	String value;

	public SingleRep(String value, int index, double note, double total, boolean isCheating) {
		super(index,note,total,isCheating);
		this.value = value;
	}

	public SingleRep() {
	}

	public SingleRep(String value) {
		this.value = value;
	}

	public SingleRep(int index, double total, double note, String value) {
		super(index, total, note);
		this.value = value;
	}

	public SingleRep(int index, double total, double note, boolean cheated, String value) {
		super(index, total, note, cheated);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
