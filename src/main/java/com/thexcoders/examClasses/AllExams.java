package com.thexcoders.examClasses;

import com.thexcoders.classes.Class;
import com.thexcoders.classes.StudentExams;

public class AllExams {
	private String id;
	private String title;
	private String tLname;
	private String tFname;
	private String email;
	private Class classe;
	private String start;
	private String length;
	private boolean isPassed;



	public AllExams(String id, Exam mExam, StudentExams studentExams, String fname, String lname, Class classe) {
		this.id = id;
		this.title = mExam.getTitle();
		this.tLname = lname;
		this.tFname = fname;
		this.email = mExam.getCreatedBy();
		this.start = studentExams.getDate();
		this.length = mExam.getLength();
		for (Class clas : mExam.getClasse()){
			if(clas.getSpecialty().equals(classe.getSpecialty()) && clas.getYear().equals(classe.getYear())){
				this.classe = clas;
				break;
			}
		}
		this.isPassed = (studentExams.getState() == StudentExams.FINISHED);
	}

	public AllExams() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String gettLname() {
		return tLname;
	}

	public void settLname(String tLname) {
		this.tLname = tLname;
	}

	public String gettFname() {
		return tFname;
	}

	public void settFname(String tFname) {
		this.tFname = tFname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Class getClasse() {
		return classe;
	}

	public void setClasse(Class classe) {
		this.classe = classe;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public boolean isPassed() {
		return isPassed;
	}

	public void setPassed(boolean passed) {
		isPassed = passed;
	}

	@Override
	public String toString() {
		return "{" +
			"id:'" + id + '\'' +
			", title:'" + title + '\'' +
			", tLname:'" + tLname + '\'' +
			", tFname:'" + tFname + '\'' +
			", email:'" + email + '\'' +
			", classe:" + classe +
			", start:'" + start + '\'' +
			", length:'" + length + '\'' +
			", isPassed:" + isPassed +
			'}';
	}
}
