package com.thexcoders.holders;


import com.thexcoders.examClasses.Exam;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Exams")
public class ExamHolder implements Comparable<ExamHolder> {
	@Id
	private String id;
	private Exam exam;
	private boolean isVisible;
	private boolean isStarted;

	public ExamHolder(String id, Exam exam, boolean isVisible) {
		this.id = id;
		this.exam = exam;
		this.isVisible = isVisible;
		this.isStarted = false;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public ExamHolder(String id, Exam exam, boolean isVisible, boolean isStarted) {
		this.id = id;
		this.exam = exam;
		this.isVisible = isVisible;
		this.isStarted = isStarted;
	}

	public void setStarted(boolean started) {
		isStarted = started;
	}

	public ExamHolder(String id, Exam exam) {
		this.id = id;
		this.exam = exam;
		this.isVisible = false;
		this.isStarted = false;
	}

	public ExamHolder() {
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean visible) {
		isVisible = visible;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}


	@Override
	public int compareTo(ExamHolder o) {
		return this.getExam().getStart().compareTo(o.getExam().getStart());
	}

}
