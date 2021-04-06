package com.thexcoders.teacherHelperClasses;

import com.thexcoders.classes.Class;
import com.thexcoders.examClasses.Exam;
import com.thexcoders.holders.ExamHolder;

import java.util.Calendar;

public class HomeTeacherExam {
	private String id;
	private String title;
	private String dept;
	private String promo;
	private String groups;
	private MTime startTime = new MTime();
	private MTime length = new MTime();
	private String start;
	private String end;
	private boolean isVisible;
	private boolean isStarted;

	public HomeTeacherExam(ExamHolder examHolder) {
		this.isStarted = examHolder.isStarted();
		this.isVisible = examHolder.isVisible();
		this.id = examHolder.getId();
		Exam exam = examHolder.getExam();
		this.title = exam.getTitle();
		this.dept = "";
		for(Class c : exam.getClasse()){
			this.dept+=","+c.getSpecialty();
			this.groups = c.getListGroups();
		}
		this.dept = this.dept.substring(1);
		this.promo = exam.getClasse().get(0).getYear();
		this.start = exam.getStart().toString();
		String val = exam.getLength();
		String h = val.split("h")[0];
		String m = val.split("h")[1];
		this.length.h = h;
		this.length.m = m;
		Calendar c = Calendar.getInstance();
		c.setTime(exam.getStart());

		this.startTime.h = ""+c.get(Calendar.HOUR);
		this.startTime.m = ""+c.get(Calendar.MINUTE);
		c = Calendar.getInstance();
		c.setTime(exam.getStart());
		c.add(Calendar.HOUR,Integer.parseInt(h));
		c.add(Calendar.MINUTE,Integer.parseInt(m));
		this.end = c.getTime().toString();

	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean visible) {
		isVisible = visible;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean started) {
		isStarted = started;
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

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getPromo() {
		return promo;
	}

	public void setPromo(String promo) {
		this.promo = promo;
	}

	public String getGroups() {
		return groups;
	}

	public void setGroups(String groups) {
		this.groups = groups;
	}

	public MTime getStartTime() {
		return startTime;
	}

	public void setStartTime(MTime startTime) {
		this.startTime = startTime;
	}

	public MTime getLength() {
		return length;
	}

	public void setLength(MTime length) {
		this.length = length;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getEnd() {
		return end;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	@Override
	public String toString() {
		return "{" +
			"id:'" + id + '\'' +
			", title:'" + title + '\'' +
			", dept:'" + dept + '\'' +
			", promo:'" + promo + '\'' +
			", groups:'" + groups + '\'' +
			", startTime:" + startTime +
			", length:" + length +
			", start:'" + start + '\'' +
			", end:'" + end + '\'' +
			", isVisible:" + isVisible +
			", isStarted:" + isStarted +
			'}';
	}

	private class MTime{
		String h,m;

		@Override
		public String toString() {
			return "{" +
				"h:'" + h + '\'' +
				", m:'" + m + '\'' +
				'}';
		}
	}

}
