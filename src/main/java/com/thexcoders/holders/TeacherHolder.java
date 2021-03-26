package com.thexcoders.holders;

import com.thexcoders.classes.Teacher;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Teachers")
public class TeacherHolder {
	private String id;
	private Teacher teacher;

	public TeacherHolder(String id, Teacher teacher) {
		this.setId(id);
		this.teacher = teacher;
	}

	public TeacherHolder() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id.toLowerCase();
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
}
