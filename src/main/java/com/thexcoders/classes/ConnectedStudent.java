package com.thexcoders.classes;

import java.util.ArrayList;
import java.util.Date;

public class ConnectedStudent {
	private String id;
	private Date startDate,endDate;
	ArrayList<StuRep> reponses;

	public ConnectedStudent(String id, Date startDate, Date endDate, ArrayList<StuRep> reponses) {
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reponses = reponses;
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
