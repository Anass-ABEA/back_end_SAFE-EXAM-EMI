package com.thexcoders.classes;

import java.util.ArrayList;

public class ConnectedStudent {
	private String id;
	private String questionNumber;
	private String QuestionAnswered;
	private String currentMark;
	private ArrayList<Integer> previousQuestions;

	public ConnectedStudent() {
	}



	public ConnectedStudent(String id, String questionNumber, String questionAnswered, String currentMark, ArrayList<Integer> previousQuestions) {
		this.id = id;
		this.questionNumber = questionNumber;
		QuestionAnswered = questionAnswered;
		this.currentMark = currentMark;
		this.previousQuestions = previousQuestions;
	}

	public ArrayList<Integer> getPreviousQuestions() {
		return previousQuestions;
	}

	public void setPreviousQuestions(ArrayList<Integer> previousQuestions) {
		this.previousQuestions = previousQuestions;
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
