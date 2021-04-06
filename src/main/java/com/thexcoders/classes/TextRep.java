package com.thexcoders.classes;

public class TextRep  extends StuRep {
	private String value;

	public TextRep(String value,int index,double note, double total) {
		super(index,note,total);
		this.value = value;
	}

	public TextRep() {
		super(0,0,0);
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
