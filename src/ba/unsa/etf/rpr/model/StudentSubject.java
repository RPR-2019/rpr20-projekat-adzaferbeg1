package ba.unsa.etf.rpr.model;

public class StudentSubject {
    private String student, subject;
    private int points;
    private String grade;

    public StudentSubject() {
    }

    public StudentSubject(String student, String subject, int points, String grade) {
        this.student = student;
        this.subject = subject;
        this.points = points;
        this.grade = grade;
    }

    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


}
