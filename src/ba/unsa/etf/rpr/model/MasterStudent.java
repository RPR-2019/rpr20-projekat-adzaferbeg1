package ba.unsa.etf.rpr.model;

public class MasterStudent extends Student {
    private int gpaBsc;

    public MasterStudent() {
    }

    public MasterStudent(int id, String name, String surname, String date, String sex, String index, String branch, int yearOfStudy, String username, String password, String email, int gpaBsc) {
        super(id, name, surname, date, sex, index, branch, yearOfStudy, username, password, email);
        this.gpaBsc = gpaBsc;
    }

    public int getGpaBsc() {
        return gpaBsc;
    }

    public void setGpaBsc(int gpaBsc) {
        this.gpaBsc = gpaBsc;
    }
}
