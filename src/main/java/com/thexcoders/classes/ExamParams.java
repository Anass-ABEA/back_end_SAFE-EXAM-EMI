package com.thexcoders.classes;

public class ExamParams {
	private int note;
	private int qstCount;
	private int dispQuestions;
	private boolean random;
	private boolean showResults;
	private boolean showReport;
	private boolean showAnswers;
	private boolean allowFeedback;
	private boolean fraud;
	private boolean goodgood;
	private boolean canadien;
	private boolean randomRep;

	public ExamParams() {

	}

	public ExamParams(int note, int qstCount, int dispQuestions, boolean random, boolean showResults, boolean showReport, boolean showAnswers) {
		this.note = note;
		this.qstCount = qstCount;
		this.dispQuestions = dispQuestions;
		this.random = random;
		this.showResults = showResults;
		this.showReport = showReport;
		this.showAnswers = showAnswers;
		this.allowFeedback = false;
		this.fraud = false;
		this.goodgood = false;
		this.canadien = false;
		this.randomRep = false;
	}

	public ExamParams(int note, int qstCount, int dispQuestions, boolean random, boolean showResults, boolean showReport, boolean showAnswers, boolean allowFeedback, boolean fraud, boolean goodgood, boolean canadien, boolean randomRep) {
		this.note = note;
		this.qstCount = qstCount;
		this.dispQuestions = dispQuestions;
		this.random = random;
		this.showResults = showResults;
		this.showReport = showReport;
		this.showAnswers = showAnswers;
		this.allowFeedback = allowFeedback;
		this.fraud = fraud;
		this.goodgood = goodgood;
		this.canadien = canadien;
		this.randomRep = randomRep;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public int getQstCount() {
		return qstCount;
	}

	public void setQstCount(int qstCount) {
		this.qstCount = qstCount;
	}

	public int getDispQuestions() {
		return dispQuestions;
	}

	public void setDispQuestions(int dispQuestions) {
		this.dispQuestions = dispQuestions;
	}

	public boolean isRandom() {
		return random;
	}

	public void setRandom(boolean random) {
		this.random = random;
	}

	public boolean isShowResults() {
		return showResults;
	}

	public void setShowResults(boolean showResults) {
		this.showResults = showResults;
	}

	public boolean isShowReport() {
		return showReport;
	}

	public void setShowReport(boolean showReport) {
		this.showReport = showReport;
	}

	public boolean isShowAnswers() {
		return showAnswers;
	}

	public void setShowAnswers(boolean showAnswers) {
		this.showAnswers = showAnswers;
	}
}
