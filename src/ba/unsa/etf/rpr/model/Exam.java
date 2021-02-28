package ba.unsa.etf.rpr.model;

public class Exam extends Activity{
    private String examSubject, date;

    public Exam() {
    }

    public Exam(String examSubject, String date) {
        this.examSubject = examSubject;
        this.date = date;
    }

    public String getExamSubject() {
        return examSubject;
    }

    public void setExamSubject(String examSubject) {
        this.examSubject = examSubject;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Ispit iz predmeta "  + examSubject + " će biti održan dana " +
                 date ;
    }
}
