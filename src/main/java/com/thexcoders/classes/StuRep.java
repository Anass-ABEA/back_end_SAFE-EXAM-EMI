package com.thexcoders.classes;

public class StuRep {
	private int index;
	private double total,note;
	private boolean cheated;

	public StuRep(){
		this.index = 0;
		this.total = 0;
		this.note = 0;
		this.cheated = false;
	}

	public StuRep(int index, double total, double note) {
		this.index = index;
		this.total = total;
		this.note = note;
		this.cheated = false;
	}

	public StuRep(int index, double total, double note, boolean cheated) {
		this.index = index;
		this.total = total;
		this.note = note;
		this.cheated = cheated;
	}

	public boolean isCheated() {
		return cheated;
	}

	public void setCheated(boolean cheated) {
		this.cheated = cheated;
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

	public String stringifty() {
		return "{" +
			"index:" + index +
			", total:" + total +
			", note:" + note +
			", cheated:" + cheated +
			'}';
	}


}
