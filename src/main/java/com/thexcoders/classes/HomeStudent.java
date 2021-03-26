package com.thexcoders.classes;

import java.util.ArrayList;

public class HomeStudent  {
	private String fname;
	private String lname;
	private HomeValues homevalues = new HomeValues();
	private ArrayList<CloseExam> closeExams = new ArrayList<>();
	private ArrayList<BestResults> bestResults = new ArrayList<>();
	private Class classe ;

	public HomeStudent(Student student) {
		this.fname = student.getFname();
		this.lname = student.getLname();
		this.calculate(student);
		this.classe = student.getClasse();
	}

	public HomeStudent(String fname, String lname, HomeValues homevalues, ArrayList<CloseExam> closeExams, ArrayList<BestResults> bestResults, Class classe) {
		this.fname = fname;
		this.lname = lname;
		this.homevalues = homevalues;
		this.closeExams = closeExams;
		this.bestResults = bestResults;
		this.classe = classe;
	}

	public HomeStudent() {

	}

	private void calculate(Student student){  // INCOMPLETE missing AVG
		int total = student.getExams().size();
		int failed = 0;

		for(StudentExams exam : student.getExams()){
			if(exam.state==StudentExams.MISSED){
				failed--;
			}
		}

		this.homevalues.succeededExams = ""+(total+failed);
		this.homevalues.totalexams= ""+total;
		this.homevalues.avg = ""+Math.ceil((0.5+(Math.random()/2))*200)/10; // RANDOM
	}

	private class HomeValues{
		private String totalexams;
		private String succeededExams;
		private String avg;
		@Override
		public String toString() {
			return "{" +
				"succeeded:'" + succeededExams + '\'' +
				", passed:'" + totalexams + '\'' +
				", avg:'" + avg + '\'' +
				'}';
		}
	}
	private class CloseExam{
		String TName;
		String length;
		String startTime;
		String startDate;

		@Override
		public String toString() {
			return "CloseExam{" +
				"TName:'" + TName + '\'' +
				", length:'" + length + '\'' +
				", startTime:'" + startTime + '\'' +
				", startDate:'" + startDate + '\'' +
				'}';
		}
	}
	private class BestResults{
		String mark;
		String classe;
		String t_name;
		String t_email;
		String exam_length;
		String time_taken;
		String avg_qst;
		String avg_class;

		@Override
		public String toString() {
			return "{" +
				"mark:'" + mark + '\'' +
				", classe:'" + classe + '\'' +
				", t_name:'" + t_name + '\'' +
				", t_email:'" + t_email + '\'' +
				", exam_length:'" + exam_length + '\'' +
				", time_taken:'" + time_taken + '\'' +
				", avg_qst:'" + avg_qst + '\'' +
				", avg_class:'" + avg_class + '\'' +
				'}';
		}
	}

	@Override
	public String toString() {
		return "{" +
			"fname:'" + fname + '\'' +
			", lname:'" + lname + '\'' +
			", homevalues:" + homevalues +
			", closeExams:" + closeExams +
			", bestResults:" + bestResults +
			", groups:" + classe +
			'}';
	}
}
