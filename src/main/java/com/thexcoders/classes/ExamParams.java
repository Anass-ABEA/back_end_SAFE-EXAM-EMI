package com.thexcoders.classes;

public class ExamParams {
	private int note;
	private int qstCount;
	private int dispQuestions;
	private boolean random;
	private boolean showResults;
	private boolean showReport;
	private boolean showAnswers;


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
