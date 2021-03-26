package com.thexcoders.classes;

import java.util.ArrayList;

public class SingleCheckAnswers extends Answers {
	ArrayList<MultiElement> answers;

	public SingleCheckAnswers(ArrayList<MultiElement> answers) {
		this.answers = answers;
	}

	public ArrayList<MultiElement> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<MultiElement> answers) {
		this.answers = answers;
	}

	public SingleCheckAnswers() {
	}


}
