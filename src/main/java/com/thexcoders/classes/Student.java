package com.thexcoders.classes;

import java.util.ArrayList;

public class Student {

	String fname;
	String lname;
	String password;
	Class classe;
	ArrayList<StudentExams> exams = new ArrayList<>();

	public Student(String fname, String lname, String password, Class classe, ArrayList<StudentExams> exams) {
		this.fname = fname;
		this.lname = lname;
		this.password = password;
		this.classe = classe;
		this.exams = exams;
	}

	public ArrayList<StudentExams> getExams() {
		return exams;
	}

	public void setExams(ArrayList<StudentExams> exams) {
		this.exams = exams;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Class getClasse() {
		return classe;
	}

	public void setClasse(Class classe) {
		this.classe = classe;
	}


	public void UpdateExamStatus(String examId, int state) {
		int index = indexOfExam(examId);
		if(index!=-1){
			this.exams.get(index).setState(state);
		}
	}
	public int indexOfExam(String examId){
		int res = -1;
		for (StudentExams e : exams) {
			res++;
			if(e.getId().equals(examId))return res;
		}
		return -1;
	}

	public String fullName() {
		return this.fname.substring(0,1).toUpperCase()+this.fname.substring(1).toLowerCase()+" "+this.lname.toUpperCase();
	}
}
