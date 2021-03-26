package com.thexcoders.classes;

import java.util.ArrayList;

public class Teacher {
	private String fname;
	private String lname;
	private String password;
	private ArrayList<ProfExam> examList;
	private ArrayList<String> customGroups;

	public Teacher(String fname, String lname,String password, ArrayList<ProfExam> examList,ArrayList<String> grps) {
		this.setFname(fname);
		this.setLname(lname);
		this.examList = examList;
		this.password = password;
		this.customGroups = grps;
	}

	public ArrayList<String> getCustomGroups() {
		return customGroups;
	}

	public void setCustomGroups(ArrayList<String> customGroups) {
		this.customGroups = customGroups;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public ArrayList<ProfExam> getExamList() {
		return examList;
	}

	public void setExamList(ArrayList<ProfExam> examList) {
		this.examList = examList;
	}

	public Teacher() {
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname.substring(0,1).toUpperCase()+fname.substring(1).toLowerCase();
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String[] profInfo() {
		return new String[]{this.fname,this.lname};
	}
}
