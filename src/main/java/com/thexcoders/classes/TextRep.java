package com.thexcoders.classes;

public class TextRep  extends StuRep {
	private String value;

	public TextRep(String value,int index,double note, double total) {
		super(index,note,total);
		this.value = value;
	}

	public TextRep() {
	}

	public TextRep(String value) {
		this.value = value;
	}

	public TextRep(int index, double total, double note, String value) {
		super(index, total, note);
		this.value = value;
	}

	public TextRep(int index, double total, double note, boolean cheated, String value) {
		super(index, total, note, cheated);
		this.value = value;
	}

	public TextRep(String value, int index, double note, double total, boolean isCheating) {
		super(index,total,note,isCheating);
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
