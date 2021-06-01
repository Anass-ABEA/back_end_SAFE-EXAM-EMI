package com.thexcoders.holders;

import com.thexcoders.examClasses.Questions;
import org.json.JSONArray;
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

	public QuestionHolder(String id,JSONArray questions) {
		this.id = id;
		this.questions = new ArrayList<>();
		for (int i = 0; i<questions.length();i++){
			try{
				this.questions.add(new Questions(questions.getJSONObject(i)));
			}catch (Exception e){
				e.printStackTrace();
			}
		}
	}

	public QuestionHolder(String id,JSONArray questions,boolean b) {
		this.id = id;
		this.questions = new ArrayList<>();
		for (int i = 0; i<questions.length();i++){
			try{
				this.questions.add(new Questions(questions.getJSONObject(i),false));
			}catch (Exception e){
				e.printStackTrace();
			}
		}
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
