package com.thexcoders.Controllers;

import com.thexcoders.classes.*;
import com.thexcoders.classes.Class;

import java.util.Date;

public class AllExams {
	private String id;
	private String title;
	private String email;
	private String length;
	private String start;
	private Class classe;
	private boolean isPassed;
	private String proFname;
	private String proLname;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProFname() {
		return proFname;
	}

	public void setProFname(String proFname) {
		this.proFname = proFname;
	}

	public String getProLname() {
		return proLname;
	}

	public void setProLname(String proLname) {
		this.proLname = proLname;
	}

	public AllExams(String id, Exam exam, StudentExams homeExam, String fname, String lname) {
		this.title=exam.getTitle();
		this.email=exam.getCreatedBy();
		this.length = exam.getLength();
		this.start = exam.getStart().toString();
		this.classe = exam.getClasse();
		this.id = id;
		this.isPassed = homeExam.getState() == StudentExams.FINISHED;
		this.proFname = fname;
		this.proLname = lname;

	}

	@Override
	public String toString() {
		return "{" +
			"id:'" + id + '\'' +
			", title:'" + title + '\'' +
			", email:'" + email + '\'' +
			", length:'" + length + '\'' +
			", start:'" + start + '\'' +
			", classe:" + classe +
			", isPassed:" + isPassed +
			", tFname:" + proFname +
			", tLname:" + proLname +
			'}';
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public Class getClasse() {
		return classe;
	}

	public void setClasse(Class classe) {
		this.classe = classe;
	}

	public boolean isPassed() {
		return isPassed;
	}

	public void setPassed(boolean passed) {
		isPassed = passed;
	}




}
