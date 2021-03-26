package com.thexcoders.classes;

public class ConnectedStudent {
	private String id;
	private String questionNumber;
	private String QuestionAnswered;
	private String currentMark;

	public ConnectedStudent() {
	}

	public ConnectedStudent(String id, String questionNumber, String questionAnswered, String currentMark) {
		this.id = id;
		this.questionNumber = questionNumber;
		QuestionAnswered = questionAnswered;
		this.currentMark = currentMark;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(String questionNumber) {
		this.questionNumber = questionNumber;
	}

	public String getQuestionAnswered() {
		return QuestionAnswered;
	}

	public void setQuestionAnswered(String questionAnswered) {
		QuestionAnswered = questionAnswered;
	}

	public String getCurrentMark() {
		return currentMark;
	}

	public void setCurrentMark(String currentMark) {
		this.currentMark = currentMark;
	}
}
