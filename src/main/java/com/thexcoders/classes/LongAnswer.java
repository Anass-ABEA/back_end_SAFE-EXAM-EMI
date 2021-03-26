package com.thexcoders.classes;

public class LongAnswer extends Answers {
	private String longAnswer;

	public LongAnswer(String fileId) {
		this.longAnswer = fileId;
	}

	public LongAnswer() {
	}

	public String getFileId() {
		return longAnswer;
	}

	public void setFileId(String longAnswer) {
		this.longAnswer = longAnswer;
	}
}
