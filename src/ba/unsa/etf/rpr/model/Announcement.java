package ba.unsa.etf.rpr.model;

public class Announcement extends Activity{
    private String text,date;

    public Announcement() {
    }

    public Announcement(String text, String date) {
        this.text = text;
        this.date=date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Obavijest: "+text+" / "+date;
    }
}
