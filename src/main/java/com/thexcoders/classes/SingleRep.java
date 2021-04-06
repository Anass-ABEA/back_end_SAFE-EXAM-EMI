package com.thexcoders.classes;

public class SingleRep extends StuRep {
	String value;

	public SingleRep(String value,int index,double note, double total) {
		super(index,note,total);
		this.value = value;
	}

	public SingleRep() {
		super(0,0,0);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
