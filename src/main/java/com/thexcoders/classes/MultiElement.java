package com.thexcoders.classes;

public class MultiElement {
		String body;
		boolean isCorrect;

	public MultiElement(String body, boolean isCorrect) {
		this.body = body;
		this.isCorrect = isCorrect;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean correct) {
		isCorrect = correct;
	}
}
