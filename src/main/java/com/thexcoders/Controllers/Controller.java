package com.thexcoders.Controllers;

import com.thexcoders.classes.*;
import com.thexcoders.classes.Class;
import com.thexcoders.examClasses.*;
import com.thexcoders.holders.ExamHolder;
import com.thexcoders.holders.QuestionHolder;
import com.thexcoders.holders.StudentHolder;
import com.thexcoders.holders.TeacherHolder;
import com.thexcoders.repositories.*;
import com.thexcoders.teacherHelperClasses.HomeTeacherExam;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static com.thexcoders.examClasses.Questions.*;

@RestController
@CrossOrigin("*")
public class Controller {


	private StudentRepository studentrepo;
	private ExamRepository examRepo;
	private TeacherRepo teacherRepo;
	private QuestionRepository questionRepo;
	private AllStudentsRepository allStudentsRepo;

	public Controller(StudentRepository studentrepo, ExamRepository examRepo, TeacherRepo teacherRepo,QuestionRepository questionRepo,AllStudentsRepository allstudentsRepo) {
		this.studentrepo = studentrepo;
		this.examRepo = examRepo;
		this.teacherRepo = teacherRepo;
		this.questionRepo = questionRepo;
		this.allStudentsRepo= allstudentsRepo;
	}

	// getting all students
	@GetMapping("/students/all")
	public List<StudentHolder> getAllStudents() {
		return this.studentrepo.findAll();
	}

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
	public boolean update(@PathVariable("studentID") String id, @RequestBody String password) {
		StudentHolder stud = this.studentrepo.findById(id).get();
		try {
			if (password.length() != 32) throw new Exception("PASS INCORRECT");
			stud.getStudent().setPassword(password);
			this.studentrepo.save(stud);
		} catch (Exception e) {
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

	//comment exam
	@PostMapping("/exams/{id}/feedback/{studentID}")
	public boolean updatecommentaire(@PathVariable("studentID") String idstudent,@PathVariable("id") String idexam,@RequestBody String body){
		Remark remark = new Remark(idstudent, body);
		ExamHolder exam = this.examRepo.findById(idexam).get();
		if(exam==null) return false;
		if(exam.getExam().hasElement(body)) return false;
		exam.getExam().getRemarksList().add(remark);
		this.examRepo.save(exam);
		return true;
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

	// gett exam by ID ( testing )
	@GetMapping("/exams/{id}")
	public ExamHolder getExamById(@PathVariable("id") String id) {
		return  this.examRepo.findById(id).get();
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
			if(!exam.isVisible()) continue;
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
			if(!exam.isVisible())continue;
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

	@PostMapping("/exams/BeginExam/{examId}")
	public boolean startExam(@PathVariable("examId") String examId,@RequestBody String visibility){
		ExamHolder exam = this.examRepo.findById(examId).get();
		if(exam==null) return false;
		exam.setStarted(Boolean.parseBoolean(visibility));
		this.examRepo.save(exam);
		return true;
	}

	@GetMapping("/exam/isExamStarted/{examId}")
	public String isExamStarted(@PathVariable("examId") String examId) throws JSONException {
		JSONObject res = new JSONObject();
		res.put("isOpen",this.examRepo.findById(examId).get().isStarted());
		res.put("isgoodgood",this.examRepo.findById(examId).get().getExam().getParams().isGoodgood());
		return res.toString();
	}

	@GetMapping("/exam/isPassed/{studentId}/{examId}")
	public boolean isExamStarted(@PathVariable("examId") String examId,@PathVariable("studentId") String id) throws JSONException {
		return this.studentrepo.findById(id).get().getStudent().indexOfExam(examId)==-1;
	}

	// getting all exams to display on the "Mes Examens" page
	@GetMapping("/exams/getExams/{studentID}")
	public String getStudentExams(@PathVariable("studentID") String id) throws JSONException {
		Student student = this.studentrepo.findById(id).get().getStudent();
		ArrayList<StudentExams> examList = student.getExams();

		JSONArray result = new JSONArray();

		for (StudentExams stdexam : examList) {
			Exam mExam = this.examRepo.findById(stdexam.getId()).get().getExam();
			try {
				Teacher teacher = this.teacherRepo.findById(mExam.getCreatedBy()).get().getTeacher();
				String[] profInfo = teacher.profInfo();
				AllExams obj = new AllExams(stdexam.getId(), mExam, stdexam, profInfo[0], profInfo[1], student.getClasse());
				result.put(new JSONObject(obj.toString()));
			} catch (Exception e) {
				System.err.println("ERROR " + e.getMessage() + "\n in Professor : " + mExam.getCreatedBy());
			}
		}

		return result.toString();
	}
	@GetMapping("/exams/TeacherExams3/{teacherId}")
	public String getTeacherExamsHome(@PathVariable("teacherId") String teacherId) throws JSONException {
		Teacher teacher = this.teacherRepo.findById(teacherId).get().getTeacher();
		ArrayList<String> examIds = teacher.getExamList();
		List<String> exams = new ArrayList<>();
		for(String id : examIds){
			ExamHolder mexam = this.examRepo.findById(id).get();
			HomeTeacherExam home = new HomeTeacherExam(mexam);
			JSONObject json = new JSONObject(home.toString());
			exams.add(json.toString());
		}
		//System.out.println(exams.toString());
		return exams.toString();
	}

	@PostMapping("/exams/visibility/{examId}")
	public boolean changeExamVisibility(@PathVariable("examId") String examId,@RequestBody String visibility){
		ExamHolder exam = this.examRepo.findById(examId).get();
		if(exam==null)return false;
		exam.setVisible(Boolean.parseBoolean(visibility));
		this.examRepo.save(exam);
		return true;
	}
	@GetMapping("exam/getCoStudents/{examId}")
	public String getCoStud(@PathVariable("examId") String examId) throws JSONException {
		JSONArray res = new JSONArray();
		ArrayList<ConnectedStudent> students =  this.examRepo.findById(examId).get().getExam().getConnectedStudents();

		for (ConnectedStudent costu :students){
			costu.sortResponces();
			JSONObject object = new JSONObject();
			object.put("isbanned",false);
			object.put("start",String.join("", costu.getStartDate().toString().split(" WET")));
			object.put("id",costu.getId());
			object.put("name",this.studentrepo.findById(costu.getId()).get().getStudent().fullName());
			int note = 0;
			JSONArray answers = new JSONArray();
			for(StuRep rep : costu.getReponses()){
				note+=rep.getTotal();
				JSONObject temp = new JSONObject();
				temp.put("isFraud",rep.isCheated());
				answers.put(temp);
			}

			object.put("note",note);
			if(costu.getEndDate()!=null){
				object.put("qstCourante",costu.getReponses().size());
			}else{
				object.put("qstCourante",costu.getCurrentQst());
			}

			if(costu.getEndDate()==null){
				object.put("end",null);
			}else{

				object.put("end",String.join("", costu.getEndDate().toString().split(" WET")));
			}
			object.put("answers",answers);
			res.put(object);
		}
		return res.toString();
	}
	@GetMapping("exam/getExamInfo/{examId}")
	public String ExamData(@PathVariable("examId") String examId) throws JSONException {
		ExamHolder examHolder = this.examRepo.findById(examId).get();
		Exam exam = examHolder.getExam();
		JSONObject res = new JSONObject();
		res.put("title",exam.getTitle());

		JSONObject target = new JSONObject();
		target.put("promo",exam.getClasse().get(0).getYear());
		target.put("genie",exam.getClasse().get(0).getSpecialty());
		String grps ="";
		for (Object s:exam.getClasse().get(0).getGroups().toArray()){
			grps+=s.toString();
		}
		target.put("grps",grps);

		res.put("target",target);
		res.put("dateTime",exam.getStart());
		QuestionHolder qestions= this.questionRepo.findById(examId).get();
		res.put("qstCount",qestions.getQuestions().size());
		int note = 0;
		for (Questions o: qestions.getQuestions()){
			note+=o.getNote();
		}
		res.put("noteGlobal",note);
		return res.toString();
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
		if (stu == null) return false;
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

	@PostMapping("/newExam/{teacherID}")
	public String createNewExam(@PathVariable("teacherID") String teacherId,@RequestBody String examData) throws JSONException {
		JSONObject result = new JSONObject();
		try {
		JSONObject data = new JSONObject(examData);
		JSONObject length = data.getJSONObject("values").getJSONObject("length");
		ExamParams params = new ExamParams(data.getJSONObject("booleans"),data.getJSONObject("values").getString("note")
			,data.getJSONArray("questions").length(),data.getJSONObject("values").getString("display"));

		ArrayList<Class> classes = new ArrayList<>();
		JSONArray jsonGenies = data.getJSONArray("genies");
		JSONArray jsonGrps = data.getJSONArray("groups");
		String promo = data.getJSONObject("values").getString("promo");

		for(int i = 0; i<jsonGenies.length(); i++){
			String genie = jsonGenies.get(i).toString();
			HashSet set = new HashSet();

			for(int j = 0; j<jsonGrps.length(); j++){
				String grp = jsonGrps.get(i).toString();
				set.add(grp);
			}
			classes.add(new Class(promo,genie,set));
		}

		String examName = data.getJSONObject("values").getString("examName");
		String d  = data.getJSONObject("values").getString("date");
		String t = data.getJSONObject("values").getString("time");
		Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(d+" "+t);
		String len = length.getString("h")+"h"+length.getString("m");
		String id = data.getJSONObject("values").getString("hashed");
		Exam exam = new Exam(examName,
			teacherId,date,len
			,id,params,classes,new ArrayList<ConnectedStudent>(),new ArrayList<Remark>());
		this.examRepo.save(new ExamHolder(data.getJSONObject("values").getString("hashed"),exam,data.getJSONObject("booleans").getBoolean("isVisible")));
			QuestionHolder q = new QuestionHolder(data.getJSONObject("values").getString("hashed"),
				data.getJSONArray("questions"));
			this.questionRepo.save(q);

		TeacherHolder teacherHolder = this.teacherRepo.findById(teacherId).get();
		if(!teacherHolder.getTeacher().addExam(id)){
			result.put("isAdded",false);
			result.put("Error","Un examen du meme type existe déjà");
			result.put("ErrType",0);
			return result.toString();
		}
		this.teacherRepo.save(teacherHolder);
		}catch (Exception e){
			result.put("isAdded",false);
			result.put("Error","Une erreure est survenue, merci d'essayer plustards");
			result.put("ErrType",-1);
			//ERROR TYPE -1 ERROR IN PARSING
			//ERROR TYPE 0 ERROR IN ADDING (exsts already)
			return result.toString();
		}

		result.put("isAdded",true);
		return result.toString();
	}

	//getting exam's first Data
	@RequestMapping(value = "/exams/firstData/{examId}", method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public String getExamDetails(@PathVariable("examId") String examId) throws JSONException {
		ExamHolder examHolder = this.examRepo.findById(examId).get();
		Exam exam = examHolder.getExam();
		JSONObject res = new JSONObject();

		res.put("title",exam.getTitle());
		res.put("len",exam.getLength());
		res.put("tries","1");
		res.put("nbrQuestions",exam.getParams().getDispQuestions());
		res.put("profName",this.teacherRepo.findById(exam.getCreatedBy()).get().getTeacher().profName());
		res.put("isFraudOn",exam.getParams().isFraud());
		return res.toString();
	}

	@PostMapping("/exams/updateresult/{ExamId}")
	public void postReponse(@RequestBody String connectedStudent, @PathVariable String ExamId) throws JSONException, ParseException {
		JSONObject data = new JSONObject(connectedStudent);
		ArrayList<StuRep> reponses = new ArrayList<>();
		JSONArray table = data.getJSONArray("reponses");
		for(int i = 0; i<table.length();i++){
			JSONObject json = table.getJSONObject(i);
			int type = json.getInt("type");
			switch (type){
				case 1:
					reponses.add(new TextRep(json.getString("value"),json.getInt("index"),json.getDouble("note"),json.getDouble("total"),json.getBoolean("isCheating")));
					break;
				case 2:
					reponses.add(new MultipleRep(json.getJSONArray("value"),json.getInt("index"),json.getDouble("note"),json.getDouble("total"),json.getBoolean("isCheating")));
					break;
				case 0:
					reponses.add(new TextRep(json.getString("value"),json.getInt("index"),json.getDouble("note"),json.getDouble("total"),json.getBoolean("isCheating")));
					break;
				case 3:
					reponses.add(new SingleRep(json.getString("value"),json.getInt("index"),json.getDouble("note"),json.getDouble("total"),json.getBoolean("isCheating")));
					break;
			}
		}
		Date start = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(data.getString("startDate"));

		Date end = null;
		try{
			end = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(data.getString("endDate"));
			StudentHolder stu = this.studentrepo.findById(data.getString("id")).get();
			stu.getStudent().UpdateExamStatus(ExamId,StudentExams.FINISHED);
			this.studentrepo.save(stu);
		}catch (Exception e){
			System.err.println("date error");
		}
		System.err.println("start "+start);
		ConnectedStudent coStu = new ConnectedStudent(
			data.getString("id"),
			start,
			end,
			reponses,
			data.getString("currentQst")
		);

		ExamHolder examHolder = this.examRepo.findById(ExamId).get();
		Exam exam = examHolder.getExam();
		System.err.println("current qst is = "+data.getString("currentQst"));
		exam.updateValue(data.getString("id"),reponses,start,end,data.getString("currentQst"));
		this.examRepo.save(examHolder);
	}
	@GetMapping("exams/addToStudent/{studId}/{examId}")
	public void addIt(@PathVariable("studId") String id , @PathVariable("examId") String exam){
		StudentHolder stu = this.studentrepo.findById(id).get();
		stu.getStudent().getExams().add(new StudentExams(exam,new Date().toString(),new Date().toString(),StudentExams.IN_PROGRESS));
		this.studentrepo.save(stu);
	}

	//getting exam's questions and answers
	@RequestMapping(value = "/exams/questions/{examId}", method = RequestMethod.GET,
		produces = MediaType.APPLICATION_JSON_VALUE)
	public String getExamQuestions(@PathVariable("examId") String examId) throws JSONException {
		System.err.println(examId);
		ExamHolder examHolder = this.examRepo.findById(examId).get();
		Exam exam = examHolder.getExam();
		JSONArray res = new JSONArray();
		int index = 0;
		for(Questions qst : this.questionRepo.findById(examHolder.getId()).get().getQuestions()){
			JSONObject question = new JSONObject();
			switch (qst.getType()){
				case LONG:
					question = getTxtReply(qst);
					break;
				case SHORT:
					question = getTxtReply(qst);
					break;
				case SINGLE:
					question = getCheckReply(qst);
					break;
				case MULTIPLE:
					question = getCheckReply(qst);
					break;
			}
			question.put("i",index);
			res.put(question);
			index++;
		}
		return res.toString();
	}

	private JSONObject getTxtReply(Questions qst) throws JSONException {
		JSONObject res = new JSONObject();
		res.put("body",qst.getBody());
		int type = 0;
		if(qst.getType().equals("long")) type++;
		res.put("type",type);
		res.put("points",qst.getNote());
		return res;
	}
	private JSONObject getCheckReply(Questions qst) throws JSONException {
		JSONObject res = new JSONObject();
		res.put("body",qst.getBody());
		int type = 2;
		if(qst.getType().equals("single")){
			res.put("type",(type+1));
			res.put("points",qst.getNote());
			JSONArray array = new JSONArray();
			SingleCheckAnswers ans = (SingleCheckAnswers) qst.getAnswers();
			for(MultiElement elem : ans.getAnswers()){
				array.put(elem.toJSONObject());
			}
			res.put("rep",array);
		}else{
			res.put("type",type);
			res.put("points",qst.getNote());
			JSONArray array = new JSONArray();
			MultiCheckAnswers ans = (MultiCheckAnswers) qst.getAnswers();
			for(MultiElement elem : ans.getAnswers()){
				array.put(elem.toJSONObject());
			}
			res.put("rep",array);
		}

		return res;
	}

}
