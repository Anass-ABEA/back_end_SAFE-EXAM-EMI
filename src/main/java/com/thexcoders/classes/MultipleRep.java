package com.thexcoders.classes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MultipleRep extends StuRep  {
	private ArrayList<Boolean> value;


	public MultipleRep(JSONArray value, int index, double note, double total, boolean isCheating) throws JSONException {
		super(index, total, note,isCheating);
		this.value = new ArrayList<>();
		for (int i = 0; i < value.length(); i++) {
			this.value.add(value.getBoolean(i));
		}
	}
	public MultipleRep(){
		super();
	}
	public MultipleRep(ArrayList<Boolean> value){
		this.value = value;
	}


	public ArrayList<Boolean> getValue() {
		return value;
	}

	public void setValue(ArrayList<Boolean> value) {
		this.value = value;
	}
}
