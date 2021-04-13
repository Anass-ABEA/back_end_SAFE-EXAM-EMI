package com.thexcoders.examClasses;

import com.thexcoders.classes.Class;
import com.thexcoders.classes.ConnectedStudent;
import com.thexcoders.classes.StuRep;

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

	public boolean hasElement(String body){
		for(Remark r :this.remarksList){
			if(r.getBody().equals(body)){
				return true;
			}
		}
		return false;
	}

	public void addConnected(ConnectedStudent connectedStudent) {
		this.connectedStudents.removeIf(stu -> stu.getId().equals(connectedStudent.getId()));
		this.connectedStudents.add(connectedStudent);
	}

    public int indexOfStud(String id) {
			int pos = -1;
			for(ConnectedStudent co : this.connectedStudents){
				pos++;
				if(co.getId().equals(id)){
					return pos;
				}
			}
			return -1;
    }

	public void updateValue(String id, ArrayList<StuRep> reponses, Date start, Date end) {
		int index = indexOfStud(id);
		if(index==-1){
			this.connectedStudents.add(new ConnectedStudent(id,start,end,reponses));
			return;
		}
		this.connectedStudents.get(index).setEndDate(end);
		this.connectedStudents.get(index).setReponses(reponses);

	}

/*	@Override
	public String toString() {
		return "{" +
			"title:'" + title + '\'' +
			", createdBy:'" + createdBy + '\'' +
			", start:'" + start +
			"', length:'" + length + '\'' +
			", questionsId:'" + questionsId + '\'' +
			", params:" + params +
			", classe:" + classe +
			", connectedStudents:" + connectedStudents +
			", remarksList:" + remarksList +
			'}';
	}*/
}
