package com.thexcoders.seeder;

import com.thexcoders.classes.*;
import com.thexcoders.classes.Class;
import com.thexcoders.examClasses.*;
import com.thexcoders.holders.*;
import com.thexcoders.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DatabaseSeeder implements CommandLineRunner {
	private final ExamRepository examrepo;
	private final StudentRepository studentRepository;
	private final TeacherRepo teacherRepo;
	private final QuestionRepository questionRepo;
	private final AllStudentsRepository allstudentsRepo;


	public DatabaseSeeder(StudentRepository studentRepository, ExamRepository examrepo, TeacherRepo teacherRepo, QuestionRepository questionRepo, AllStudentsRepository allstudentsRepo) {
		this.studentRepository = studentRepository;
		this.examrepo = examrepo;
		this.teacherRepo = teacherRepo;
		this.questionRepo = questionRepo;
		this.allstudentsRepo = allstudentsRepo;
	}

	@Override
	public void run(String... args) {

		//70647bb1b46cf31e5d439e10b6759aze
		//70647bb1b46cf31e5d439e10b675e9ac
		/*AllStudentsHolder allStud = new AllStudentsHolder("INF2020",new ArrayList<>(),new ArrayList<>());
		allStud.addNewStudent(true,"anassaitbenelarbi");
		this.allstudentsRepo.save(allStud);
		this.allstudentsRepo.save(new AllStudentsHolder("IND2020",new ArrayList<>(),new ArrayList<>()));

		ArrayList<StudentExams> list = new ArrayList<>();
		list.add(new StudentExams("id1", "today", "now", 0));
		HashSet<String> set = new HashSet<>();
		set.add("A");
		set.add("jdoe_ratt_SE");
		Class classe = new Class("2020", "INF", set);
		Student student = new Student("Anass", "AIT BEN EL ARBI", "cb08ca4a7bb5f9683c19133a84872ca7", classe
			, new ArrayList<>(
			Arrays.asList(
				new StudentExams("70647bb1b46cf31e5d439e10b675e9ac", new Date().toString(), new Date().toString(), StudentExams.FINISHED)
			)
		));
		this.studentRepository.deleteAll();
		this.examrepo.deleteAll();
		this.studentRepository.save(new StudentHolder("anassaitbenelarbi", student));
		HashSet sett = new HashSet();
		sett.add("A");


		Date d = new Date("03/28/2021 08:00");
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DAY_OF_MONTH, -1);
		d = c.getTime();
		this.examrepo.save(new ExamHolder("70647bb1b46cf31e5d439e10b675e9ac",
			new Exam("JEE", "johndoe", d, "01h00", "a5dbba953b4b3020022c7b5a26a3f705"
				, new ExamParams(20, 48, 30, true, true, true, true),
				new Class("2020", "INF", sett), new ArrayList<>(), new ArrayList<>()
			),true));

		d = new Date("04/05/2021 10:00");
		c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DAY_OF_MONTH, 1);
		d = c.getTime();

		this.examrepo.save(new ExamHolder("70647bb1b46cf31e5d439e10b675e9ad",
			new Exam("Cyber Sec", "johndoe3", d, "01h00", "a5dbba953b4b3020022c7b5a26a3f705"
				, new ExamParams(20, 48, 30, true, true, true, true),
				new Class("2020", "INF", sett), new ArrayList<>(), new ArrayList<>()
			),false));

		d = new Date("04/06/2021 16:00");
		c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DAY_OF_MONTH, 1);
		d = c.getTime();

		sett = new HashSet();
		sett.add("B");

		this.examrepo.save(new ExamHolder("70647bb1b46cf31e5d439e10b675e9af",
			new Exam("IoT", "johndoe3", d, "01h00", "a5dbba953b4b3020022c7b5a26a3f705"
				, new ExamParams(20, 48, 30, true, true, true, true),
				new Class("2020", "INF", sett), new ArrayList<>(), new ArrayList<>()
			),true));

		d = new Date("03/31/2021 14:00");
		c = Calendar.getInstance();
		c.setTime(d);
		c.add(Calendar.DAY_OF_MONTH, 1);
		d = c.getTime();
		sett = new HashSet();
		sett.add("A");


		this.examrepo.save(new ExamHolder("70647bb1b46cf31e5d439e10b6759aze",
			new Exam("Gestion de Projet", "johndoe", d, "01h00", "a5dbba953b4b3020022c7b5a26a3f705"
				, new ExamParams(20, 48, 30, true, true, true, true),
				new Class("2020", "INF", sett), new ArrayList<>(), new ArrayList<>()
			),true));

		this.teacherRepo.deleteAll();
		Teacher t = new Teacher("john", "DOE","cb08ca4a7bb5f9683c19133a84872ca7", new ArrayList<>(Arrays.asList("70647bb1b46cf31e5d439e10b6759aze","70647bb1b46cf31e5d439e10b675e9af")),new ArrayList<>());
		teacherRepo.save(new TeacherHolder("johndoe", t));
		t = new Teacher("james", "DOE","cb08ca4a7bb5f9683c19133a84872ca7", new ArrayList<>(),new ArrayList<>());
		teacherRepo.save(new TeacherHolder("jamesdoe", t));

		this.questionRepo.deleteAll();
		Questions q = new Questions("Que signifie HTML?","short",2,null);
		this.questionRepo.save(new QuestionHolder("a5dbba953b4b3020022c7qsd26a3f705",new ArrayList<>(Arrays.asList(q))));
		Answers answers = new SingleCheckAnswers(new ArrayList<MultiElement>(
			Arrays.asList(
				new MultiElement("Système d'emission",false),
				new MultiElement("Système d'exploitation",true))));
		Questions q1 = new Questions("Que signifie SE?","single",2,answers);

		this.questionRepo.save(new QuestionHolder("70647bb1b46cf31e5d439e10b6759aze",new ArrayList<>(Arrays.asList(q,q1))));*/
	}

}
