package com.thexcoders.classes;

public class StudentExams {
	public static final int IN_PROGRESS = 1;
	public static final int MISSED = -1;
	public static final int NOT_STARTED = 0;
	public static final int FINISHED = 2;

	private String id;
	private String date;
	private String time;
	int state= NOT_STARTED;



	public StudentExams() {
	}

	public StudentExams(String id, String date, String time, int state) {
		this.id = id;
		this.date = date;
		this.time = time;
		this.state = state;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "{" +
			"id:'" + id + '\'' +
			", date:'" + date + '\'' +
			", time:'" + time + '\'' +
			", state:" + state +
			'}';
	}
}
