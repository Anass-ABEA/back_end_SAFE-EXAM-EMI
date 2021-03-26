package com.thexcoders.holders;

import com.thexcoders.classes.Questions;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "Questions")
public class QuestionHolder {
	@Id
	private String id;
	private ArrayList<Questions> questions;

	public QuestionHolder(String id, ArrayList<Questions> questions) {
		this.id = id;
		this.questions = questions;
	}

	public QuestionHolder() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<Questions> getQuestions() {
		return questions;
	}

	public void setQuestions(ArrayList<Questions> questions) {
		this.questions = questions;
	}
}
