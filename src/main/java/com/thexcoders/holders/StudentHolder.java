package com.thexcoders.holders;


import com.thexcoders.classes.Student;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Students")
public class StudentHolder {
	@Id
	private String id;
	private Student student;

	public StudentHolder(String id, Student student) {
		this.id = id;
		this.student = student;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
