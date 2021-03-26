package com.thexcoders.classes;

public class Questions {
	private String Body,type;
	private int note;
	private Answers answers;

	public Questions() {
	}

	public Questions(String body, String type, int note, Answers answers) {
		Body = body;
		this.type = type;
		this.note = note;
		this.answers = answers;

		// here depending on the Answer type either get:
		/*
		new FileAnswer();
		new LongAnswer();
		new ShortAnswer();
		new MultiCheckAnswers();
		new SingleCheckAnswers();
		*/
	}

	public String getBody() {
		return Body;
	}

	public void setBody(String body) {
		Body = body;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getNote() {
		return note;
	}

	public void setNote(int note) {
		this.note = note;
	}

	public Answers getAnswers() {
		return answers;
	}

	public void setAnswers(Answers answers) {
		this.answers = answers;
	}
}
