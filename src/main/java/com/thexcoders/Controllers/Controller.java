package com.thexcoders.Controllers;

import com.thexcoders.classes.*;
import com.thexcoders.classes.Class;
import com.thexcoders.examClasses.*;
import com.thexcoders.holders.*;
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

    public Controller(StudentRepository studentrepo, ExamRepository examRepo, TeacherRepo teacherRepo, QuestionRepository questionRepo, AllStudentsRepository allstudentsRepo) {
        this.studentrepo = studentrepo;
        this.examRepo = examRepo;
        this.teacherRepo = teacherRepo;
        this.questionRepo = questionRepo;
        this.allStudentsRepo = allstudentsRepo;
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
            json.put("pic", stu.getStudent().getPic());
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
    public boolean updatecommentaire(@PathVariable("studentID") String idstudent, @PathVariable("id") String idexam, @RequestBody String body) {
        Remark remark = new Remark(idstudent, body);
        ExamHolder exam = this.examRepo.findById(idexam).get();
        if (exam == null) return false;
        if (exam.getExam().hasElement(body)) return false;
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
        return this.examRepo.findById(id).get();
    }

    // getting the 3 incomming exams to display to the student
    @GetMapping("/exams/3examsSorted/{studentID}")
    public String get3ExamsSorted(@PathVariable("studentID") String id) throws JSONException {
        ArrayList<ExamHolder> exams = (ArrayList<ExamHolder>) this.getAllExams();
        System.err.println("exam size = " + exams.size());
        Collections.sort(exams, Collections.reverseOrder());
        int i = 0;
        exams.removeIf(exam -> exam.getExam().getStart().compareTo(new Date()) < 0);
        System.err.println("exam sorted and cleaned = " + exams.size());
        JSONArray js = new JSONArray();
        Class studentClass = this.studentrepo.findById(id).get().getStudent().getClasse();
        String spec = studentClass.getSpecialty();
        for (ExamHolder exam : exams) {
            if (!exam.isVisible()) continue;
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

    @GetMapping("/exams/getMinDetails/{idExam}")
    public String getMinExamDetails(@PathVariable("idExam") String idExam) {
        JSONObject res = new JSONObject();

        Exam exam = this.examRepo.findById(idExam).get().getExam();

        try {
            res.put("title", exam.getTitle());
            res.put("profName", this.teacherRepo.findById(exam.getCreatedBy()).get().getTeacher().profName());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return res.toString();
    }


    // getting sorted exams for a specific user and sorting them to display on the calendar
    @GetMapping("/exams/StudentExams/{studentID}")
    public String getFutureExamsSorted(@PathVariable("studentID") String id) throws JSONException {
        ArrayList<ExamHolder> exams = (ArrayList<ExamHolder>) this.getAllExams();
        JSONArray js = new JSONArray();
        Class studentClass = this.studentrepo.findById(id).get().getStudent().getClasse();
        String spec = studentClass.getSpecialty();
        for (ExamHolder exam : exams) {
            if (!exam.isVisible()) continue;
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
    public boolean startExam(@PathVariable("examId") String examId, @RequestBody String visibility) {
        ExamHolder exam = this.examRepo.findById(examId).get();
        if (exam == null) return false;
        exam.setStarted(Boolean.parseBoolean(visibility));
        this.examRepo.save(exam);
        return true;
    }

    @GetMapping("/exam/isExamStarted/{examId}")
    public String isExamStarted(@PathVariable("examId") String examId) throws JSONException {
        JSONObject res = new JSONObject();
        res.put("isOpen", this.examRepo.findById(examId).get().isStarted());
        res.put("isgoodgood", this.examRepo.findById(examId).get().getExam().getParams().isGoodgood());
        return res.toString();
    }

    @GetMapping("/exam/isPassed/{studentId}/{examId}")
    public boolean isExamStarted(@PathVariable("examId") String examId, @PathVariable("studentId") String id) throws JSONException {
        return this.studentrepo.findById(id).get().getStudent().indexOfExam(examId) == -1;
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
        for (String id : examIds) {
            ExamHolder mexam = this.examRepo.findById(id).get();
            HomeTeacherExam home = new HomeTeacherExam(mexam);
            JSONObject json = new JSONObject(home.toString());
            exams.add(json.toString());
        }
        //System.out.println(exams.toString());
        return exams.toString();
    }

    @PostMapping("/exams/visibility/{examId}")
    public boolean changeExamVisibility(@PathVariable("examId") String examId, @RequestBody String visibility) {
        ExamHolder exam = this.examRepo.findById(examId).get();
        if (exam == null) return false;
        exam.setVisible(Boolean.parseBoolean(visibility));
        this.examRepo.save(exam);
        return true;
    }

    @GetMapping("exam/getCoStudents/{examId}")
    public String getCoStud(@PathVariable("examId") String examId) throws JSONException {
        JSONArray res = new JSONArray();
        ArrayList<ConnectedStudent> students = this.examRepo.findById(examId).get().getExam().getConnectedStudents();

        for (ConnectedStudent costu : students) {
            costu.sortResponces();
            JSONObject object = new JSONObject();
            object.put("isbanned", costu.isBanned());
            object.put("start", String.join("", costu.getStartDate().toString().split(" WET")));
            object.put("id", costu.getId());
            object.put("name", this.studentrepo.findById(costu.getId()).get().getStudent().fullName());
            int note = 0;
            JSONArray answers = new JSONArray();
            for (StuRep rep : costu.getReponses()) {
                note += rep.getTotal();
                JSONObject temp = new JSONObject();
                temp.put("isFraud", rep.isCheated());
                answers.put(temp);
            }

            object.put("note", note);
            if (costu.getEndDate() != null) {
                object.put("qstCourante", costu.getReponses().size());
            } else {
                object.put("qstCourante", costu.getCurrentQst());
            }

            if (costu.getEndDate() == null) {
                object.put("end", null);
            } else {

                object.put("end", String.join("", costu.getEndDate().toString().split(" WET")));
            }
            object.put("answers", answers);
            res.put(object);
        }
        return res.toString();
    }

    @GetMapping("exam/getExamInfo/{examId}")
    public String ExamData(@PathVariable("examId") String examId) throws JSONException {
        ExamHolder examHolder = this.examRepo.findById(examId).get();
        Exam exam = examHolder.getExam();
        JSONObject res = new JSONObject();
        res.put("title", exam.getTitle());

        JSONObject target = new JSONObject();
        target.put("promo", exam.getClasse().get(0).getYear());
        target.put("genie", exam.getClasse().get(0).getSpecialty());
        String grps = "";
        for (Object s : exam.getClasse().get(0).getGroups().toArray()) {
            grps += s.toString();
        }
        target.put("grps", grps);

        res.put("target", target);
        res.put("dateTime", exam.getStart());
        QuestionHolder qestions = this.questionRepo.findById(examId).get();
        res.put("qstCount", qestions.getQuestions().size());
        int note = 0;
        for (Questions o : qestions.getQuestions()) {
            note += o.getNote();
        }
        res.put("noteGlobal", note);
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
            json.put("pic", stu.getTeacher().getPic());
            return json.toString();
        } catch (JSONException e) {
            return e.toString();
        }
    }

    @PostMapping("/newExam/{teacherID}")
    public String createNewExam(@PathVariable("teacherID") String teacherId, @RequestBody String examData) throws JSONException {
        JSONObject result = new JSONObject();
        try {
            JSONObject data = new JSONObject(examData);
            JSONObject length = data.getJSONObject("values").getJSONObject("length");
            ExamParams params = new ExamParams(data.getJSONObject("booleans"), data.getJSONObject("values").getString("note")
                    , data.getJSONArray("questions").length(), data.getJSONObject("values").getString("display"));

            ArrayList<Class> classes = new ArrayList<>();
            JSONArray jsonGenies = data.getJSONArray("genies");
            JSONArray jsonGrps = data.getJSONArray("groups");
            String promo = data.getJSONObject("values").getString("promo");

            for (int i = 0; i < jsonGenies.length(); i++) {
                String genie = jsonGenies.get(i).toString();
                HashSet set = new HashSet();

                for (int j = 0; j < jsonGrps.length(); j++) {
                    String grp = jsonGrps.get(i).toString();
                    set.add(grp);
                }
                classes.add(new Class(promo, genie, set));
            }

            String examName = data.getJSONObject("values").getString("examName");
            String d = data.getJSONObject("values").getString("date");
            String t = data.getJSONObject("values").getString("time");
            Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(d + " " + t);
            String len = length.getString("h") + "h" + length.getString("m");
            String id = data.getJSONObject("values").getString("hashed");
            Exam exam = new Exam(examName,
                    teacherId, date, len
                    , id, params, classes, new ArrayList<ConnectedStudent>(), new ArrayList<Remark>());
            this.examRepo.save(new ExamHolder(data.getJSONObject("values").getString("hashed"), exam, data.getJSONObject("booleans").getBoolean("isVisible")));
            QuestionHolder q = new QuestionHolder(data.getJSONObject("values").getString("hashed"),
                    data.getJSONArray("questions"));
            this.questionRepo.save(q);

            TeacherHolder teacherHolder = this.teacherRepo.findById(teacherId).get();
            if (!teacherHolder.getTeacher().addExam(id)) {
                result.put("isAdded", false);
                result.put("Error", "Un examen du meme type existe déjà");
                result.put("ErrType", 0);
                return result.toString();
            }
            this.teacherRepo.save(teacherHolder);
        } catch (Exception e) {
            result.put("isAdded", false);
            result.put("Error", "Une erreure est survenue, merci d'essayer plustards");
            result.put("ErrType", -1);
            //ERROR TYPE -1 ERROR IN PARSING
            //ERROR TYPE 0 ERROR IN ADDING (exsts already)
            return result.toString();
        }

        result.put("isAdded", true);
        return result.toString();
    }

    //getting exam's first Data
    @RequestMapping(value = "/exams/firstData/{examId}", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String getExamDetails(@PathVariable("examId") String examId) throws JSONException {
        ExamHolder examHolder = this.examRepo.findById(examId).get();
        Exam exam = examHolder.getExam();
        JSONObject res = new JSONObject();

        res.put("title", exam.getTitle());
        res.put("len", exam.getLength());
        res.put("tries", "1");
        res.put("nbrQuestions", exam.getParams().getDispQuestions());
        res.put("profName", this.teacherRepo.findById(exam.getCreatedBy()).get().getTeacher().profName());
        res.put("isFraudOn", exam.getParams().isFraud());
        return res.toString();
    }

    @PostMapping("/exams/updateresult/{ExamId}")
    public void postReponse(@RequestBody String connectedStudent, @PathVariable String ExamId) throws JSONException, ParseException {
        JSONObject data = new JSONObject(connectedStudent);
        ArrayList<StuRep> reponses = new ArrayList<>();
        JSONArray table = data.getJSONArray("reponses");
        for (int i = 0; i < table.length(); i++) {
            JSONObject json = table.getJSONObject(i);
            int type = json.getInt("type");
            switch (type) {
                case 1:
                    reponses.add(new TextRep(json.getString("value"), json.getInt("index"), json.getDouble("note"), json.getDouble("total"), json.getBoolean("isCheating")));
                    break;
                case 2:
                    reponses.add(new MultipleRep(json.getJSONArray("value"), json.getInt("index"), json.getDouble("note"), json.getDouble("total"), json.getBoolean("isCheating")));
                    break;
                case 0:
                    reponses.add(new TextRep(json.getString("value"), json.getInt("index"), json.getDouble("note"), json.getDouble("total"), json.getBoolean("isCheating")));
                    break;
                case 3:
                    reponses.add(new SingleRep(json.getString("value"), json.getInt("index"), json.getDouble("note"), json.getDouble("total"), json.getBoolean("isCheating")));
                    break;
            }
        }
        Date start = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(data.getString("startDate"));

        Date end = null;
        try {
            end = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(data.getString("endDate"));
            StudentHolder stu = this.studentrepo.findById(data.getString("id")).get();
            stu.getStudent().UpdateExamStatus(ExamId, StudentExams.FINISHED);
            this.studentrepo.save(stu);
        } catch (Exception e) {
            System.err.println("date error");
        }
        System.err.println("start " + start);
        ConnectedStudent coStu = new ConnectedStudent(
                data.getString("id"),
                start,
                end,
                reponses,
                data.getString("currentQst"),
                false
        );

        ExamHolder examHolder = this.examRepo.findById(ExamId).get();
        Exam exam = examHolder.getExam();
        System.err.println("current qst is = " + data.getString("currentQst"));
        exam.updateValue(data.getString("id"), reponses, start, end, data.getString("currentQst"));
        this.examRepo.save(examHolder);
    }

    @GetMapping("exams/addToStudent/{studId}/{examId}")
    public void addIt(@PathVariable("studId") String id, @PathVariable("examId") String exam) {
        StudentHolder stu = this.studentrepo.findById(id).get();
        stu.getStudent().getExams().add(new StudentExams(exam, new Date().toString(), new Date().toString(), StudentExams.IN_PROGRESS));
        this.studentrepo.save(stu);
    }

    @GetMapping("exams/isbanned/{studId}/{examId}")
    public String isBanned(@PathVariable("studId") String id, @PathVariable("examId") String exam) {
        try {
            return "" + this.examRepo.findById(exam).get().getExam().getConnectedStudent(id).isBanned();
        } catch (Exception e) {
            return "false";
        }
    }

    @GetMapping("exams/ban/{studId}/{examId}")
    public void banStudent(@PathVariable("studId") String id, @PathVariable("examId") String exam) {
        System.err.println("banning student " + id + " from exam " + exam);
        ExamHolder exh = this.examRepo.findById(exam).get();
        exh.getExam().getConnectedStudent(id).setBanned(true);
        this.examRepo.save(exh);
        System.err.println("" + this.examRepo.findById(exam).get().getExam().getConnectedStudent(id).isBanned());
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
        for (Questions qst : this.questionRepo.findById(examHolder.getId()).get().getQuestions()) {
            JSONObject question = new JSONObject();
            switch (qst.getType()) {
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
            question.put("i", index);
            res.put(question);
            index++;
        }
        return res.toString();
    }

    private JSONObject getTxtReply(Questions qst) throws JSONException {
        JSONObject res = new JSONObject();
        res.put("body", qst.getBody());
        int type = 0;
        if (qst.getType().equals("long")) type++;
        res.put("type", type);
        res.put("points", qst.getNote());
        return res;
    }

    private JSONObject getCheckReply(Questions qst) throws JSONException {
        JSONObject res = new JSONObject();
        res.put("body", qst.getBody());
        int type = 2;
        if (qst.getType().equals("single")) {
            res.put("type", (type + 1));
            res.put("points", qst.getNote());
            JSONArray array = new JSONArray();
            SingleCheckAnswers ans = (SingleCheckAnswers) qst.getAnswers();
            for (MultiElement elem : ans.getAnswers()) {
                array.put(elem.toJSONObject());
            }
            res.put("rep", array);
        } else {
            res.put("type", type);
            res.put("points", qst.getNote());
            JSONArray array = new JSONArray();
            MultiCheckAnswers ans = (MultiCheckAnswers) qst.getAnswers();
            for (MultiElement elem : ans.getAnswers()) {
                array.put(elem.toJSONObject());
            }
            res.put("rep", array);
        }

        return res;
    }

    @PostMapping("/addGroup/{groupName}")
    public boolean addGroup(@RequestBody List<String> ids, @PathVariable("groupName") String groupName) {
        for (String id : ids) {
            StudentHolder stud = this.studentrepo.findById(id).get();
            stud.getStudent().getClasse().getGroups().add(groupName);
            this.studentrepo.save(stud);
        }
        return true;
    }

    @GetMapping("/student/profileDetails/{userId}")
    public String getStudentProfileDetails(@PathVariable("userId") String userId) {
        JSONObject res = new JSONObject();
        Student stud = studentrepo.findById(userId).get().getStudent();
        try {
            res.put("fname", stud.getFname());
            res.put("lname", stud.getLname());
            res.put("groupe", stud.getClasse().primaryGroup());
            res.put("year", stud.getClasse().getYear());
            res.put("genie", stud.getClasse().getSpecialty());
            res.put("pic", stud.getPic());
            res.put("email", userId + "@student.emi.ac.ma");
        } catch (JSONException e) {
            return null;
        }
        return res.toString();
    }

    @PostMapping("/students/Register/{Groups}")
    public boolean registerStudent(@RequestBody StudentHolder student, @PathVariable("Groups") String Groups) {
        if (this.studentrepo.existsById(student.getId())) {
            return false;
        }
        student.getStudent().getClasse().setGroups(new HashSet<>(Arrays.asList(Groups)));
        student.getStudent().setExams(new ArrayList<>());
        this.studentrepo.save(student);


        String geniePromo = student.getStudent().getClasse().getSpecialty() + student.getStudent().getClasse().getYear();
        System.err.println(geniePromo);
        AllStudentsHolder allStudent;
        try {
            allStudent = allStudentsRepo.findById(geniePromo).get();
        } catch (Exception e) {
            allStudent = new AllStudentsHolder(geniePromo, new ArrayList<>(), new ArrayList<>());
        }
        if (Groups.equals("A")) {
            allStudent.addNewStudent(true, student.getId());
        } else {
            allStudent.addNewStudent(false, student.getId());
        }
        this.allStudentsRepo.save(allStudent);

        return true;
    }

    @GetMapping("/exams/responses/{idExam}/{idStudent}")
    public ArrayList<StuRep> getResponses(@PathVariable("idExam") String idExam, @PathVariable("idStudent") String idStudent) {
        String res = "";

        ConnectedStudent student = this.examRepo.findById(idExam).get().getExam().getConnectedStudent(idStudent);
        if (student == null) return null;

        ArrayList<StuRep> listRep = student.getReponses();

        return listRep;
    }

    @GetMapping("/exams/responses2/{idExam}/{idStudent}")
    public String getResponses2(@PathVariable("idExam") String idExam, @PathVariable("idStudent") String idStudent) {
        String res = "[";

        ConnectedStudent student = this.examRepo.findById(idExam).get().getExam().getConnectedStudent(idStudent);
        if (student == null) return null;

        ArrayList<StuRep> listRep = student.getReponses();
        for (StuRep rep : listRep) {
            res += rep.stringifty();
        }
        res += "]";

        return res;
    }

    @GetMapping("/exams/MyProfExams/{idProf}")
    public String getProfExams(@PathVariable("idProf") String idProf) {
        JSONArray holder = new JSONArray();
        JSONArray olld = new JSONArray();
        JSONArray neew = new JSONArray();

        Date now = new Date();

        for (String examId : this.teacherRepo.findById(idProf).get().getTeacher().getExamList()) {
            ExamHolder eh = this.examRepo.findById(examId).get();
            JSONObject object = new JSONObject();
            try {
                object.put("id", examId);
                object.put("title", eh.getExam().getTitle());
                object.put("start", eh.getExam().getStart());
                object.put("length", eh.getExam().getLength());

                JSONObject classe = new JSONObject();
                classe.put("speciality", eh.getExam().getClasse().get(0).getSpecialty());
                classe.put("groups", eh.getExam().getClasse().get(0).getListGroups());
                classe.put("year", eh.getExam().getClasse().get(0).getYear());

                object.put("class", classe);
                object.put("visible", eh.isVisible());
                object.put("started", eh.isStarted());

                if (now.compareTo(eh.getExam().getStart()) < 0) {
                    neew.put(object);
                } else {
                    olld.put(object);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        holder.put(neew);
        holder.put(olld);

        return holder.toString();
    }

    @PostMapping("/profs/Register")
    public boolean registerTeacher(@RequestBody TeacherHolder teacher) {
        if (this.teacherRepo.existsById(teacher.getId())) {
            return false;
        }
        teacher.getTeacher().setExamList(new ArrayList<>());
        teacher.getTeacher().setCustomGroups(new ArrayList<>());
        this.teacherRepo.save(teacher);
        return true;
    }

    // updating student photo
    @PostMapping("/students/connect/{email}")
    public Boolean updatePhoto(@PathVariable("email") String email, @RequestBody String body) {
        StudentHolder stu = this.studentrepo.findById(email).get();

        stu.getStudent().setPic(body);
        System.err.println(body.length());
        this.studentrepo.save(stu);
        return true;
    }

    @GetMapping("/professor/profileDetails/{userId}")
    public String getProfessorProfileDetails(@PathVariable("userId") String userId) {
        JSONObject res = new JSONObject();
        Teacher stud = teacherRepo.findById(userId).get().getTeacher();
        try {
            res.put("fname", stud.getFname());
            res.put("lname", stud.getLname());
            res.put("nbrExam", stud.getExamList().size());
            res.put("pic", stud.getPic());
            res.put("email", userId + "@emi.ac.ma");
        } catch (JSONException e) {
            return null;
        }
        return res.toString();
    }

    @PostMapping("/teachers/update/{studentID}")
    public boolean updateProf(@PathVariable("studentID") String id, @RequestBody String password) {
        TeacherHolder stud = this.teacherRepo.findById(id).get();
        try {
            if (password.length() != 32) throw new Exception("PASS INCORRECT");
            stud.getTeacher().setPassword(password);
            this.teacherRepo.save(stud);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @PostMapping("/teachers/updateImage/{email}")
    public Boolean updatePhotoTeacher(@PathVariable("email") String email, @RequestBody String body) {
        TeacherHolder stu = this.teacherRepo.findById(email).get();

        stu.getTeacher().setPic(body);
        System.err.println(body.length());
        this.teacherRepo.save(stu);
        return true;
    }

    @GetMapping("students/getExamDetails/{examID}")
    public String getExamData(@PathVariable("examID") String idExam) {
        ExamHolder exh = this.examRepo.findById(idExam).get();
        JSONObject json = new JSONObject();

        try {
            Calendar c = Calendar.getInstance();
            c.setTime(exh.getExam().getStart());
            String date = c.get(Calendar.DATE) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR);
            String time = "";
            if (c.get(Calendar.HOUR) < 10) {
                time += "0";
            }
            time += c.get(Calendar.HOUR) + ":";
            if (c.get(Calendar.MINUTE) < 10) {
                time += "0";
            }
            time += c.get(Calendar.MINUTE);

            JSONObject length = new JSONObject();
            String h = exh.getExam().getLength().split("h")[0];
            if (h.length() == 1) {
                length.put("h", "0" + h);
            }
            String m = exh.getExam().getLength().split("h")[1];
            if (m.length() == 1) {
                length.put("m", "0" + m);
            }
            json.put("canSeeRes", exh.getExam().getParams().isShowResults());
            json.put("length", length);
            json.put("title", exh.getExam().getTitle());
            json.put("date", date);
            json.put("time", time);
            json.put("prof", this.teacherRepo.findById(exh.getExam().getCreatedBy()).get().getTeacher().profName());
            json.put("profEmail", exh.getExam().getCreatedBy() + "@emi.ac.ma");
            json.put("note", 0);
            json.put("total", exh.getExam().getParams().getNote());
            json.put("nbrQuestions", exh.getExam().getParams().getDispQuestions());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return json.toString();
    }

    @GetMapping("student/Scores/{studentID}")
    public String getExamsByStudentID(@PathVariable("studentID") String id) {
        JSONObject res = new JSONObject();
        try {
            res.put("nomEtud", this.studentrepo.findById(id).get().getStudent().fullName());
            JSONArray array = new JSONArray();
            ArrayList<StudentExams> ex = this.studentrepo.findById(id).get().getStudent().getExams();
            //ex.remove(0);
            float total = 0, note = 0;
            for (StudentExams exam : ex) {
                String idEXAM = exam.getId();
                Exam myExam = this.examRepo.findById(idEXAM).get().getExam();
                ConnectedStudent stud = this.examRepo.findById(idEXAM).get().getExam().getConnectedStudent(id);
                JSONObject json = new JSONObject();
                json.put("nomeeaxam", myExam.getTitle());
                json.put("dateexam", myExam.getStart());
                json.put("prof", this.teacherRepo.findById(myExam.getCreatedBy()).get().getTeacher().profName());

                for (StuRep o : stud.getReponses()) {
                    total += o.getNote();
//					System.err.println(total);
                    note += o.getTotal();
//					System.err.println(note);
                }

                json.put("idConnectedStu", stud);
                json.put("idExam", idEXAM);
                json.put("note", note);
                json.put("bareme", total);
                array.put(json);
            }
            res.put("listeExamens", array);
        } catch (JSONException e) {
            e.printStackTrace();
        }

//		return res.toString();
        return res.toString();
    }


    //getting all the finished exams creating by "createdBy" instructor
    @GetMapping("/examsss/{createdBy}")
    public ArrayList<ExamHolder> getExamsProf(@PathVariable("createdBy") String createdby) {
        ArrayList<ExamHolder> allexams = (ArrayList<ExamHolder>) this.examRepo.findAll();
        ArrayList<ExamHolder> concernedExam = new ArrayList<>();
        for (ExamHolder examholder : allexams) {
            if (examholder.getExam().getCreatedBy().equals(createdby) && examholder.getExam().getConnectedStudents().size() >= 1) {
                concernedExam.add(examholder);
            }
        }
        return concernedExam;
    }


    @GetMapping("/getExamToUpdate/{examId}")
    public QuestionHolder editMyExam(@PathVariable("examId") String examId) throws JSONException {
        QuestionHolder questionHolder = this.questionRepo.findById(examId).get();
		/*JSONArray res = new JSONArray();
		for(Questions qst : questionHolder.getQuestions()){
			JSONObject object = new JSONObject();
			object.put("body",qst.getBody());
			object.put("type",qst.getType());
			qst.
		}
*/

        return questionHolder;
    }

    @PostMapping("/updateExamQuestions/{examId}")
    public boolean saveExam(@PathVariable("examId") String examId, @RequestBody String questionHolder) throws JSONException {
        JSONObject data = new JSONObject(questionHolder);

        QuestionHolder q = new QuestionHolder(examId,
                data.getJSONArray("questions"), false);
        try {
            this.questionRepo.save(q);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @GetMapping("/getGroups/{genie}/{promo}")
    public String getgrps(@PathVariable("genie") String genie,@PathVariable("promo") String promo) throws JSONException {
        JSONArray res = new JSONArray();
        AllStudentsHolder allStudentsHolder = this.allStudentsRepo.findById(genie+promo).get();
        JSONArray grpA = new JSONArray();
        JSONArray grpB = new JSONArray();
        for(String str : allStudentsHolder.getIdsGrpA()){
            JSONObject obj = new JSONObject();
            obj.put("id",str);
            Student sttudent = this.studentrepo.findById(str).get().getStudent();
            obj.put("name",sttudent.getFname()+" "+sttudent.getLname().toUpperCase());
            grpA.put(obj);
        }

        for(String str : allStudentsHolder.getIdsGrpB()){
            JSONObject obj = new JSONObject();
            obj.put("id",str);
            Student sttudent = this.studentrepo.findById(str).get().getStudent();
            obj.put("name",sttudent.getFname()+" "+sttudent.getLname().toUpperCase());
            grpB.put(obj);
        }
        res.put(grpA);
        res.put(grpB);
        return res.toString();

    }



}
