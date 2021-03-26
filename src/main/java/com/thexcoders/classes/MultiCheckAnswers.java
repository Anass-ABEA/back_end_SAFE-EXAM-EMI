package com.thexcoders.classes;

import java.util.ArrayList;

public class MultiCheckAnswers extends Answers {
	ArrayList<MultiElement> answers;

	public MultiCheckAnswers(ArrayList<MultiElement> answers) {
		this.answers = answers;
	}

	public ArrayList<MultiElement> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<MultiElement> answers) {
		this.answers = answers;
	}

	public MultiCheckAnswers() {
	}


}
