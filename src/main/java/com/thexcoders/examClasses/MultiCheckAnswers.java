package com.thexcoders.examClasses;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MultiCheckAnswers extends Answers {
	ArrayList<MultiElement> answers;

	public MultiCheckAnswers(ArrayList<MultiElement> answers) {
		this.answers = answers;
	}

	public MultiCheckAnswers(JSONArray answers) {
		super();
		this.answers = new ArrayList<>();
		for(int i = 0; i<answers.length();i++){
			try {
				JSONObject ans = answers.getJSONObject(i);
				this.answers.add(new MultiElement(ans.getString("body"),ans.getBoolean("isCorrect")));
			} catch (JSONException e) {
				e.printStackTrace();
			}

		}
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
