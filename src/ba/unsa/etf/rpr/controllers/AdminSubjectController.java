package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.model.IAlert;
import ba.unsa.etf.rpr.model.Subject;
import ba.unsa.etf.rpr.model.UserDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class AdminSubjectController implements IAlert {
    public TextField fldTitle, fldECTS, fldYear, fldSemester;
    public ChoiceBox<String> cbBranch;
    public ChoiceBox<String> cbTeacher;
    private final UserDAO model = UserDAO.getInstance();
    public TextArea txtArea;
    public Button bttnAdd;


    public void initialize(){
        cbBranch.setItems(FXCollections.observableArrayList("Računarstvo i informatika","Automatika i elektronika",
                "Energetika","Telekomunikacije","Razvoj softvera"));
        cbTeacher.setItems(FXCollections.observableArrayList(model.getProfessors2()));
        txtArea.setText("Unesite opis kursa");
    }
    public void confirm(ActionEvent actionEvent) {
        try{
            Subject predmet=new Subject(1, fldTitle.getText(),fldECTS.getText(),Integer.parseInt(fldYear.getText()), cbTeacher.getValue(),
                    cbBranch.getValue(),Integer.parseInt(fldSemester.getText()));
            model.addSubject(predmet);
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

        }catch (Exception e){
            showAlertWarning("Sva polja moraju biti popunjena","Popunite sva polja i pokušajte opet");

        }
    }
}
