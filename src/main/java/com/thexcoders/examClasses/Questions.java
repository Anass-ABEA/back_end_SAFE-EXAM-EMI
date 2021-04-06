package com.thexcoders.examClasses;

import org.json.JSONObject;

public class Questions {
	private String Body,type;
	private int note;
	private Answers answers;
	public static final String LONG = "long";
	public static final String SHORT = "short";
	public static final String MULTIPLE = "multiple";
	public static final String SINGLE = "single";


	public Questions() {
	}

	public Questions(JSONObject data) {
		try{
			this.Body = data.getString("body");
			this.type = data.getString("type");
			this.note = Integer.parseInt(data.getString("note"));
			switch (this.type){
				case "multiple":
					this.answers = new MultiCheckAnswers(data.getJSONArray("answers"));
					break;
				case "single":
					this.answers = new SingleCheckAnswers(data.getJSONArray("answers"));
					break;
				default :
					this.answers = null;
			}
		}catch (Exception e){
			e.printStackTrace();
		}
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
