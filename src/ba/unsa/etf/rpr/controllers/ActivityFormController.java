package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.time.format.DateTimeFormatter;

public class ActivityFormController implements IAlert {
    private final String activity;
    private final UserDAO model  =UserDAO.getInstance();
    @FXML
    private TextField txtTitle;
    @FXML
    private Text txtWelcome;
    @FXML
    private DatePicker dpDate;
    @FXML
    private TextArea txtArea;

    public ActivityFormController(String activity) {
        this.activity = activity;
    }

    @FXML
    public void initialize(){
        txtWelcome.setText(txtWelcome.getText()+" "+activity);
    }
    public void post(ActionEvent actionEvent){
        try {
            if (activity.equals("Ispit")) {
                Exam exam = new Exam(txtTitle.getText(), dpDate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                model.addExam(exam, activity);

            } else if (activity.equals("Zadaća")) {
                Homework homework = new Homework(txtTitle.getText(), dpDate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                model.addHomework(homework, activity);
            } else {
                Announcement announcement = new Announcement(txtArea.getText(), dpDate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                model.addAnnouncement(announcement, activity);
            }
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }catch (Exception e){
            showAlertWarning("Niste popunili sva polja", "Popunite sva polja i pokušajte opet");
        }
    }
}
