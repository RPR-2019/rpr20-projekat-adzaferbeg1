package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.model.StudentSubject;
import ba.unsa.etf.rpr.model.UserDAO;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class GradeSubjectController {
    private final StudentSubject student;
    private final String subject;
    private final UserDAO model = UserDAO.getInstance();

    @FXML
    private TextField fldHomework,fldExam,fldOther,fldGrade;
    @FXML
    private Label label;
    private final SimpleStringProperty textTotal = new SimpleStringProperty();


    public GradeSubjectController(StudentSubject studentSubject, String subject) {
        this.student=studentSubject;
        this.subject=subject;
    }

    @FXML
    public void initialize(){
        label.textProperty().bind(textTotal);
        fldExam.setText("0"); fldGrade.setText("Nije ocijenjeno"); fldHomework.setText("0"); fldOther.setText("0");
        new Thread(() -> {
            try {
                while (true) {

                        try {
                            double total = Double.parseDouble(fldHomework.getText()) + Double.parseDouble(fldExam.getText()) +
                                    Double.parseDouble(fldOther.getText());
                            Platform.runLater(() -> textTotal.setValue("= " + total+" / 100"));
                            Thread.sleep(200);

                        }catch (Exception e){
                            Thread.sleep(3);
                        }

                }} catch (InterruptedException ignored) {

            }
        }).start();

    }
    public void update(ActionEvent actionEvent){
        try {
            model.updateStudentSubject(Integer.parseInt(fldHomework.getText()) + Integer.parseInt(fldExam.getText()) + Integer.parseInt(
                    fldOther.getText()), fldGrade.getText(), student.getStudent(),subject,Integer.parseInt(fldExam.getText()),
                    Integer.parseInt(fldHomework.getText()),Integer.parseInt(fldOther.getText()));
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }catch (Exception e){
            System.out.println("Nije uspjelo");
        }
    }
}
