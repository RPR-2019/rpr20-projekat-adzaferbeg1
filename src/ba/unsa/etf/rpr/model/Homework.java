package ba.unsa.etf.rpr.model;

public class Homework extends Activity {
    private String subName, date;

    public Homework() {
    }

    public Homework(String subName, String date) {
        this.subName = subName;
        this.date = date;
    }

    public String getSubName() {
        return subName;
    }

    public void setSubName(String subName) {
        this.subName = subName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Objavljena zadaća iz predmeta: "+subName+", krajnji rok: "+date;
    }
}
