package com.thexcoders.classes;

public class ShortAnswer extends Answers {
	private String shortAns;

	public ShortAnswer(String fileId) {
		this.shortAns = fileId;
	}

	public ShortAnswer() {
	}

	public String getFileId() {
		return shortAns;
	}

	public void setFileId(String fileId) {
		this.shortAns = fileId;
	}
}
