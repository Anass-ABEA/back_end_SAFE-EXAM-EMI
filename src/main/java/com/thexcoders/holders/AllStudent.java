package com.thexcoders.holders;

import java.util.ArrayList;

public class AllStudent {
	String group ; // either A or B
	ArrayList<String> ids=null; // list of students ids

	public AllStudent(String group, ArrayList<String> ids) {
		this.group = group;
		this.ids = ids;
	}

	public boolean addNEwStudent(String id){
		if(this.ids==null){
			this.ids = new ArrayList<>();
		}
		if(this.ids.contains(id)){
			return false; // already exists
		}
		this.ids.add(id);
		return true;
	}

	public AllStudent() {
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public ArrayList<String> getIds() {
		return ids;
	}

	public void setIds(ArrayList<String> ids) {
		this.ids = ids;
	}
}
