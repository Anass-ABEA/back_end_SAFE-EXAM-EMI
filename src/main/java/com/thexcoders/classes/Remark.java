package com.thexcoders.classes;

public class Remark {
	private String studentId;
	private String body;

	public Remark(String studentId, String body) {
		this.studentId = studentId;
		this.body = body;
	}

	public Remark() {
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
