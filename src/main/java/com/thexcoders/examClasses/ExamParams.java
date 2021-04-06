package com.thexcoders.examClasses;

import org.json.JSONException;
import org.json.JSONObject;

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
	private boolean allInOne;

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
		this.allInOne = true;
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
		this.allInOne = true;
	}

	public ExamParams(int note, int qstCount, int dispQuestions, boolean random, boolean showResults, boolean showReport, boolean showAnswers, boolean allowFeedback, boolean fraud, boolean goodgood, boolean canadien, boolean randomRep, boolean allInOne) {
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
		this.allInOne = allInOne;
	}

	public ExamParams(JSONObject booleans, String note, int qstCount, String qstDisp) throws JSONException {

		this.note = Integer.parseInt(note);
		this.qstCount = qstCount;
		this.dispQuestions 	= Integer.parseInt(qstDisp);
		this.allowFeedback 	= booleans.getBoolean("feedback");
		this.showReport    	= booleans.getBoolean("finalReport");
		this.random    			= booleans.getBoolean("random");
		this.showResults    = booleans.getBoolean("mark");
		this.showAnswers    = booleans.getBoolean("correctAns");
		this.fraud    			= booleans.getBoolean("fraude");
		this.goodgood   		= booleans.getBoolean("goodgood");
		this.canadien   	 	= booleans.getBoolean("canadien");
		this.randomRep    	= booleans.getBoolean("randomRep");
		this.allInOne = true;
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

	public boolean isAllowFeedback() {
		return allowFeedback;
	}

	public void setAllowFeedback(boolean allowFeedback) {
		this.allowFeedback = allowFeedback;
	}

	public boolean isFraud() {
		return fraud;
	}

	public void setFraud(boolean fraud) {
		this.fraud = fraud;
	}

	public boolean isGoodgood() {
		return goodgood;
	}

	public void setGoodgood(boolean goodgood) {
		this.goodgood = goodgood;
	}

	public boolean isCanadien() {
		return canadien;
	}

	public void setCanadien(boolean canadien) {
		this.canadien = canadien;
	}

	public boolean isRandomRep() {
		return randomRep;
	}

	public void setRandomRep(boolean randomRep) {
		this.randomRep = randomRep;
	}

	public boolean isAllInOne() {
		return allInOne;
	}

	public void setAllInOne(boolean allInOne) {
		this.allInOne = allInOne;
	}
}
