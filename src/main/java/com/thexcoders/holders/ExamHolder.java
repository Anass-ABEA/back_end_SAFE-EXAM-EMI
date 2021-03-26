package com.thexcoders.holders;


import com.thexcoders.classes.Exam;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Exams")
public class ExamHolder implements Comparable<ExamHolder> {
	@Id
	private String id;
	private Exam exam;

	public ExamHolder(String id, Exam exam) {
		this.id = id;
		this.exam = exam;
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
