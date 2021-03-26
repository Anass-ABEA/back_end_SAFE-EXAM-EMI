package com.thexcoders.classes;

import java.util.ArrayList;

public class FileAnswer extends Answers {
	private String fileId;

	public FileAnswer(String fileId) {
		this.fileId = fileId;
	}

	public FileAnswer() {
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
}
