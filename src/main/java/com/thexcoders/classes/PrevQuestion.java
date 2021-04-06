package com.thexcoders.classes;

public class PrevQuestion {
	private String pos;
	private boolean isCorrect;
	private String totalPoints;
	private String pointsReceived;

	public PrevQuestion() {
	}

	public PrevQuestion(String pos, boolean isCorrect, String totalPoints, String pointsReceived) {
		this.pos = pos;
		this.isCorrect = isCorrect;
		this.totalPoints = totalPoints;
		this.pointsReceived = pointsReceived;
	}

	public String getPos() {
		return pos;
	}

	public void setPos(String pos) {
		this.pos = pos;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean correct) {
		isCorrect = correct;
	}

	public String getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(String totalPoints) {
		this.totalPoints = totalPoints;
	}

	public String getPointsReceived() {
		return pointsReceived;
	}

	public void setPointsReceived(String pointsReceived) {
		this.pointsReceived = pointsReceived;
	}
}
