package com.thexcoders.classes;

import java.util.ArrayList;

public class Student {

    String fname;
    String lname;
    String password;
    Class classe;
    ArrayList<StudentExams> exams = new ArrayList<>();

    public Student(String fname, String lname, String password, Class classe, ArrayList<StudentExams> exams) {
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.classe = classe;
        this.exams = exams;
    }

    public ArrayList<StudentExams> getExams() {
        return exams;
    }

    public void setExams(ArrayList<StudentExams> exams) {
        this.exams = exams;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Class getClasse() {
        return classe;
    }

    public void setClasse(Class classe) {
        this.classe = classe;
    }


}
