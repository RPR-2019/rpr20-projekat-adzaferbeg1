package ba.unsa.etf.rpr.model;

public class Subject {
    private int id;
    private String title, ects;
    private int classYear, points;
    private String subjectTeacher, branch, grade="";
    private int semester;
    private int studentExam=0, studentHomework=0, studentOther=0;

    public Subject() {
    }

    public Subject(int id, String title, String ects, int classYear, String subjectTeacher, String branch, int semester) {
        this.id = id;
        this.title = title;
        this.ects = ects;
        this.classYear = classYear;
        this.subjectTeacher = subjectTeacher;
        this.branch = branch;
        this.semester = semester;
        //this.grade ="Nije ocijenjeno";
        this.points =0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEcts() {
        return ects;
    }

    public void setEcts(String ects) {
        this.ects = ects;
    }

    public int getClassYear() {
        return classYear;
    }

    public void setClassYear(int classYear) {
        this.classYear = classYear;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getSubjectTeacher() {
        return subjectTeacher;
    }

    public void setSubjectTeacher(String subjectTeacher) {
        this.subjectTeacher = subjectTeacher;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getStudentExam() {
        return studentExam;
    }

    public void setStudentExam(int studentExam) {
        this.studentExam = studentExam;
    }

    public int getStudentHomework() {
        return studentHomework;
    }

    public void setStudentHomework(int studentHomework) {
        this.studentHomework = studentHomework;
    }

    public int getStudentOther() {
        return studentOther;
    }

    public void setStudentOther(int studentOther) {
        this.studentOther = studentOther;
    }
}
