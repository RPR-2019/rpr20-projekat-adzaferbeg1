package ba.unsa.etf.rpr.model;

public class PhDStudent extends Student {
    private int gpaBsc, gpaMsc;

    public PhDStudent() {
    }

    public PhDStudent(int id, String name, String surname, String date, String sex, String index, String branch, int yearOfStudy, String username, String password, String email, int gpaBsc, int gpaMsc) {
        super(id, name, surname, date, sex, index, branch, yearOfStudy, username, password, email);
        this.gpaBsc = gpaBsc;
        this.gpaMsc = gpaMsc;
    }

    public int getGpaBsc() {
        return gpaBsc;
    }

    public void setGpaBsc(int gpaBsc) {
        this.gpaBsc = gpaBsc;
    }

    public int getGpaMsc() {
        return gpaMsc;
    }

    public void setGpaMsc(int gpaMsc) {
        this.gpaMsc = gpaMsc;
    }
}
