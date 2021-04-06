package com.thexcoders.classes;

public class StuRep {
	private int index;
	private double total,note;
	public static final int SHORT = 0;
	public static final int LONG = 1;
	public static final int MULTIPLE = 2;
	public static final int SINGLE = 3;

	public StuRep(int index, double total, double note) {
		this.index = index;
		this.total = total;
		this.note = note;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public double getNote() {
		return note;
	}

	public void setNote(double note) {
		this.note = note;
	}
}
