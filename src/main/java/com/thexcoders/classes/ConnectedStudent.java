package com.thexcoders.classes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;

public class ConnectedStudent {
	private String id;
	private String currentQst;
	private Date startDate,endDate;
	ArrayList<StuRep> reponses;

	public void sortResponces(){
		reponses.sort(new Comparator<StuRep>() {
			@Override
			public int compare(StuRep o1, StuRep o2) {
				return o1.getIndex()-o2.getIndex();
			}
		});
	}

	public ConnectedStudent(String id, Date startDate, Date endDate, ArrayList<StuRep> reponses,String currentQst) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reponses = reponses;
		this.currentQst = currentQst;
	}

	public String getCurrentQst() {
		return currentQst;
	}

	public void setCurrentQst(String currentQst) {
		this.currentQst = currentQst;
	}

	public ConnectedStudent() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public ArrayList<StuRep> getReponses() {
		return reponses;
	}

	public void setReponses(ArrayList<StuRep> reponses) {
		this.reponses = reponses;
	}
}
