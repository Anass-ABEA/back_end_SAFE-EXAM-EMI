package com.thexcoders.Controllers;

import com.thexcoders.holders.ExamHolder;
import com.thexcoders.repositories.ExamRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/examsssss")
public class ExamsController {
	private ExamRepository examRepo;

	public ExamsController(ExamRepository examRepo) {
		this.examRepo = examRepo;
	}

	@GetMapping("/all")
	public List<ExamHolder> getAll() {
		return this.examRepo.findAll();
	}
}
