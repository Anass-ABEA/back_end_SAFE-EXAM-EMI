package com.thexcoders.classes;
import com.thexcoders.holders.ExamHolder;

import java.util.Calendar;

public class HomeExam {
	private String id;
	private String title;
	private String professor;
	private Length length;
	private Length startTime;
	private Date date;

	public HomeExam(ExamHolder exam) {
		this.professor = exam.getExam().getCreatedBy();
		this.id = exam.getId();
		this.setLength(exam.getExam().getLength());
		this.setStartTIme(exam.getExam().getStart());
		this.title = exam.getExam().getTitle();
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

	public String getProfessor() {
		return professor;
	}

	public void setProfessor(String professor) {
		this.professor = professor;
	}

	public Length getLength() {
		return length;
	}

	public void setLength(Length length) {
		this.length = length;
	}

	public Length getStartTime() {
		return startTime;
	}

	public void setStartTime(Length startTime) {
		this.startTime = startTime;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "{" +
			"id:'" + id + '\'' +
			", title:'" + title + '\'' +
			", professor:'" + professor + '\'' +
			", length:" + length +
			", startTime:" + startTime +
			", date:" + date +
			'}';
	}

	private void setStartTIme(java.util.Date start) {
		this.startTime = new Length();
		Calendar c = Calendar.getInstance();
		c.setTime(start);
		this.startTime.h = c.get(Calendar.HOUR);
		this.startTime.m = c.get(Calendar.MINUTE);
		this.date = new Date();
		this.date.d = c.get(Calendar.DATE);
		this.date.m = c.get(Calendar.MONTH);
		this.date.y = c.get(Calendar.YEAR);

	}

	private void setLength(String length) {
		this.length = new Length();
		this.length.h = Integer.parseInt(length.split(":")[0]);
		this.length.m = Integer.parseInt(length.split(":")[1]);
	}


	private class Length{
		@Override
		public String toString() {
			return "{" +
				"h:" + h +
				", m:" + m +
				'}';
		}

		int h,m;
	}
	private class Date{
		int d,m,y;
		public String toString() {
			return "{" +
				"d:" + d +
				", m:" + m +
				", y:" + y+
				'}';
		}
	}
}
