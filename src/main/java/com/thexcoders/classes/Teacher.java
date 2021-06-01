package com.thexcoders.classes;

import java.util.ArrayList;

public class Teacher {
	private String fname;
	private String lname;
	private String password;
	private ArrayList<String> examIds;
	private ArrayList<String> customGroups;
	private String pic = "";

	public Teacher(String fname, String lname, String password, ArrayList<String> examList, ArrayList<String> grps) {
		this.setFname(fname);
		this.setLname(lname);
		this.examIds = examList;
		this.password = password;
		this.customGroups = grps;
	}
	public Teacher(String fname, String lname, String password, ArrayList<String> examList, ArrayList<String> grps, String pic) {
		this.setFname(fname);
		this.setLname(lname);
		this.examIds = examList;
		this.password = password;
		this.customGroups = grps;
		this.pic = pic;
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

	public ArrayList<String> getExamList() {
		return examIds;
	}

	public void setExamList(ArrayList<String> examList) {
		this.examIds = examList;
	}

	public Teacher() {
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname.substring(0, 1).toUpperCase() + fname.substring(1).toLowerCase();
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String[] profInfo() {
		return new String[]{this.fname, this.lname};
	}

	public boolean addExam(String id) {
		if(this.examIds.contains(id)){
			return false;
		}
		this.examIds.add(id);
		return true;
	}


	public String profName(){
		return lname.toUpperCase()+" "+fname.substring(0,1).toUpperCase()+".";
	}

	public String getPic() {
		return this.pic;
	}

	public void addNewGroup(String grpName){
		this.customGroups.add(grpName);
	}
}
