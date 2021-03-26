package com.thexcoders.Controllers;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.thexcoders.classes.*;
import com.thexcoders.classes.Class;
import com.thexcoders.holders.ExamHolder;
import com.thexcoders.holders.StudentHolder;
import com.thexcoders.holders.TeacherHolder;
import com.thexcoders.repositories.ExamRepository;
import com.thexcoders.repositories.TeacherRepo;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.thexcoders.repositories.StudentRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
public class Controller {
	private StudentRepository studentrepo;
	private ExamRepository examRepo;
	private TeacherRepo teacherRepo;

	public Controller(StudentRepository studentrepo, ExamRepository examRepo,TeacherRepo teacherRepo) {
		this.studentrepo = studentrepo;
		this.examRepo = examRepo;
		this.teacherRepo=teacherRepo;
	}

	// getting all students
	@GetMapping("/students/all")
	public List<StudentHolder> getAllStudents() {
		return this.studentrepo.findAll();
	}

	/*TODOS
	 * redirect to the needed URL
	 * add dispatchers ( forwarding )
	 * */

	//adding a new student
	@PutMapping("/students/add/")
	public boolean insert(@RequestBody StudentHolder student) {
		if (this.studentrepo.existsById(student.getId())) {
			return false;
		}
		this.studentrepo.insert(student);
		return true;
	}

	// updating student info ( testing )
	@PostMapping("/students/update")
	public boolean update(@RequestBody StudentHolder student) {

		if (this.studentrepo.existsById(student.getId())) {
			this.studentrepo.save(student);
			return true;
		}
		return true;
	}

	@PostMapping("/students/update/{studentID}")
	public boolean update(@PathVariable("studentID") String id,@RequestBody String password) {
		StudentHolder stud = this.studentrepo.findById(id).get();
		try{
			if(password.length()!=32) throw new Exception("PASS INCORRECT");
			stud.getStudent().setPassword(password);
			this.studentrepo.save(stud);
		}catch (Exception e){
			return false;
		}
		return true;
	}

	//checking student Email to connect
	@GetMapping("/students/connect/{email}")
	public boolean checkEmail(@PathVariable("email") String email) {
		return this.studentrepo.existsById(email);
	}

	// validating student data to authenticate
	@GetMapping("/students/connect/{email}/{password}")
	public Boolean login(@PathVariable("email") String email, @PathVariable("password") String pass) {
		StudentHolder stu = this.studentrepo.findById(email).get();

		if (stu.getStudent().getPassword().equals(pass)) {
			return true;
		}
		return null;
	}

	// getting student Data to display on the home page
	@RequestMapping(value = "/students/data/{email}", method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public String data(@PathVariable("email") String email) {
		StudentHolder stu = this.studentrepo.findById(email).get();
		HomeStudent home = new HomeStudent(stu.getStudent());
		try {
			return new JSONObject(home.toString()).toString();
		} catch (JSONException e) {

			return "";
		}
	}

	// getting student name for the header
	@RequestMapping(value = "/students/name/{email}", method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public String getName(@PathVariable("email") String email) {
		StudentHolder stu = this.studentrepo.findById(email).get();
		JSONObject json = new JSONObject();
		String name = stu.getStudent().getFname() + " " + stu.getStudent().getLname().substring(0, 1) + ".";
		try {
			json.put("name", name);
			return json.toString();
		} catch (JSONException e) {
			return e.toString();
		}
	}

	// getting all exams  (testing)
	@GetMapping("/exams/all")
	public List<ExamHolder> getAllExams() {
		return this.examRepo.findAll();
	}


	// getting all exams from a student ( testing )
	@GetMapping("/exams/allSorted/{studentID}")
	public List<ExamHolder> getAllExamsSorted(@PathVariable("studentID") String id) {
		ArrayList<ExamHolder> exams = (ArrayList<ExamHolder>) this.getAllExams();
		Collections.sort(exams, Collections.reverseOrder());

		List<ExamHolder> examHolder2 = new ArrayList<>();
		Class studentClass = this.studentrepo.findById(id).get().getStudent().getClasse();
		String spec = studentClass.getSpecialty();
		for (ExamHolder exam : exams) {
			if (exam.getExam().myClasse(spec).getYear().equals(studentClass.getYear())
				&& exam.getExam().myClasse(spec).getSpecialty().equals(studentClass.getSpecialty())) {
				for (Object str : exam.getExam().myClasse(spec).getGroups().toArray()) {
					if (studentClass.getGroups().contains((String) str)) {
						examHolder2.add(exam);
					}
				}
			}
		}
		return examHolder2;
	}

	// deleting exam by ID ( testing )
	@DeleteMapping("/exams/{id}")
	public boolean detelteExamByID(@PathVariable("id") String id) {
		ExamHolder exam = this.examRepo.findById(id).get();
		if (exam != null) {
			this.examRepo.deleteById(id);
			return true;
		}
		return false;
	}

	// getting the 3 incomming exams to display to the student
	@GetMapping("/exams/3examsSorted/{studentID}")
	public String get3ExamsSorted(@PathVariable("studentID") String id) throws JSONException {
		ArrayList<ExamHolder> exams = (ArrayList<ExamHolder>) this.getAllExams();
		Collections.sort(exams, Collections.reverseOrder());
		int i = 0;
		exams.removeIf(exam -> exam.getExam().getStart().compareTo(new Date()) < 0);
		JSONArray js = new JSONArray();
		Class studentClass = this.studentrepo.findById(id).get().getStudent().getClasse();
		String spec = studentClass.getSpecialty();
		for (ExamHolder exam : exams) {
			if (exam.getExam().myClasse(spec).getYear().equals(studentClass.getYear())
				&& exam.getExam().myClasse(spec).getSpecialty().equals(studentClass.getSpecialty())) {
				for (Object str : exam.getExam().myClasse(spec).getGroups().toArray()) {
					if (studentClass.getGroups().contains((String) str)) {
						js.put(new JSONObject(new HomeExam(exam).toString()));
						i++;
						if (i >= 3) {
							break;
						}
					}
				}
			}
		}
		return js.toString();
	}



	// getting sorted exams for a specific user and sorting them to display on the calendar
	@GetMapping("/exams/StudentExams/{studentID}")
	public String getFutureExamsSorted(@PathVariable("studentID") String id) throws JSONException {
		ArrayList<ExamHolder> exams = (ArrayList<ExamHolder>) this.getAllExams();
		JSONArray js = new JSONArray();
		Class studentClass = this.studentrepo.findById(id).get().getStudent().getClasse();
		String spec = studentClass.getSpecialty();
		for (ExamHolder exam : exams) {
			if (exam.getExam().myClasse(spec).getYear().equals(studentClass.getYear())
				&& exam.getExam().myClasse(spec).getSpecialty().equals(studentClass.getSpecialty())) {
				for (Object str : exam.getExam().myClasse(spec).getGroups().toArray()) {
					if (studentClass.getGroups().contains((String) str)) {
						js.put(new JSONObject(new HomeExam(exam).toString()));
					}
				}
			}
		}
		return js.toString();
	}


	// getting all exams to display on the "Mes Examens" page
	@GetMapping("/exams/getExams/{studentID}")
	public String getStudentExams(@PathVariable("studentID") String id) throws JSONException {
		Student student = this.studentrepo.findById(id).get().getStudent();
		ArrayList<StudentExams> examList = student.getExams();

		JSONArray result = new JSONArray();

		for(StudentExams stdexam : examList){
			Exam mExam = this.examRepo.findById(stdexam.getId()).get().getExam();

			try{
				Teacher teacher = this.teacherRepo.findById(mExam.getCreatedBy()).get().getTeacher();
				String[] profInfo = teacher.profInfo();
				AllExams obj = new AllExams(stdexam.getId(),mExam,stdexam,profInfo[0],profInfo[1]);
				result.put(new JSONObject(obj.toString()));
			}catch (Exception e){
				System.err.println("ERROR "+e.getMessage()+"\n in Professor : "+mExam.getCreatedBy());
			}
		}

		return result.toString();



	/*	//getting student class
		Class studentClass = this.studentrepo.findById(id).get().getStudent().getClasse();

		//getting all exams
		ArrayList<ExamHolder> exams = (ArrayList<ExamHolder>) this.getAllExams();
		JSONArray js = new JSONArray();

		for (ExamHolder exam : exams) {
			//if exam matches student Classe
			if (exam.getExam().getClasse().getYear().equals(studentClass.getYear())
				&& exam.getExam().getClasse().getSpecialty().equals(studentClass.getSpecialty())) {
				for (Object str : exam.getExam().getClasse().getGroups().toArray()) {
					// if grp matches student's
					if (studentClass.getGroups().contains((String) str)) {
						js.put(new JSONObject(new AllExams(exam).toString()));
						System.out.println(new JSONObject(new AllExams(exam).toString()));
					}
				}
			}
		}
		return js.toString();*/
	}
	@GetMapping("/teachers/all")
	public List<TeacherHolder> getAllTeachers() {
		return this.teacherRepo.findAll();
	}

	@GetMapping("/teachers/connect/{email}")
	public boolean checkEmailProf(@PathVariable("email") String email) {
		return this.teacherRepo.existsById(email);
	}

	// validating student data to authenticate
	@GetMapping("/teachers/connect/{email}/{password}")
	public Boolean loginteacher(@PathVariable("email") String email, @PathVariable("password") String pass) {
		TeacherHolder stu = this.teacherRepo.findById(email).get();
		if(stu == null) return false;
		if (stu.getTeacher().getPassword().equals(pass)) {
			return true;
		}
		return null;
	}

	//getting teacher's name
	@RequestMapping(value = "/teachers/name/{email}", method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public String getNameTeacher(@PathVariable("email") String email) {
		TeacherHolder stu = this.teacherRepo.findById(email).get();
		JSONObject json = new JSONObject();
		String name = stu.getTeacher().getFname() + " " + stu.getTeacher().getLname().substring(0, 1) + ".";
		try {
			json.put("name", name);
			return json.toString();
		} catch (JSONException e) {
			return e.toString();
		}
	}


	// getting student Data to display on the home page
	/*@RequestMapping(value = "/teachers/data/{email}", method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public String dataTeacher(@PathVariable("email") String email) {
		TeacherHolder stu = this.teacherRepo.findById(email).get();
		HomeTeacher home = new HomeTeacher(stu.getTeacher());
		try {
			return new JSONObject(home.toString()).toString();
		} catch (JSONException e) {

			return "";
		}
	}*/
}
