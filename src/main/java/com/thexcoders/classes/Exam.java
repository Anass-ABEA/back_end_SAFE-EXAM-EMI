package com.thexcoders.classes;

import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class Exam {
	private String title;
	private String createdBy;
	private Date start;
	private String length;
	private String questionsId;
	private ExamParams params;
	private ArrayList<Class> classe;
	private ArrayList<ConnectedStudent> connectedStudents;
	private ArrayList<Remark> remarksList;


	public Exam() {}

	public Exam(String title, String createdBy, Date start, String length, String questionsId, ExamParams params, ArrayList<Class> classe, ArrayList<ConnectedStudent> connectedStudents, ArrayList<Remark> remarksList) {
		this.title = title;
		this.createdBy = createdBy;
		this.start = start;
		this.length = length;
		this.questionsId = questionsId;
		this.params = params;
		this.classe = classe;
		this.connectedStudents = connectedStudents;
		this.remarksList = remarksList;
	}
	public Exam(String title, String createdBy, Date start, String length, String questionsId, ExamParams params, Class classe, ArrayList<ConnectedStudent> connectedStudents, ArrayList<Remark> remarksList) {
		this.title = title;
		this.createdBy = createdBy;
		this.start = start;
		this.length = length;
		this.questionsId = questionsId;
		this.params = params;
		this.classe = new ArrayList<>(Collections.singleton(classe));
		this.connectedStudents = connectedStudents;
		this.remarksList = remarksList;
	}


	public ArrayList<Remark> getRemarksList() {
		return remarksList;
	}

	public void setRemarksList(ArrayList<Remark> remarksList) {
		this.remarksList = remarksList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getQuestionsId() {
		return questionsId;
	}

	public void setQuestionsId(String questionsId) {
		this.questionsId = questionsId;
	}

	public ExamParams getParams() {
		return params;
	}

	public void setParams(ExamParams params) {
		this.params = params;
	}

	public ArrayList<Class> getClasse() {
		return classe;
	}

	public Class myClasse(String name){
		for(Class c:this.classe){
			if(c.getSpecialty().equals(name)){
				return c;
			}
		}
		return null;
	}

	public void setClasse(ArrayList<Class> classe) {
		this.classe = classe;
	}

	public ArrayList<ConnectedStudent> getConnectedStudents() {
		return connectedStudents;
	}

	public void setConnectedStudents(ArrayList<ConnectedStudent> connectedStudents) {
		this.connectedStudents = connectedStudents;
	}


	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}
}
