package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.model.IAlert;
import ba.unsa.etf.rpr.model.Student;
import ba.unsa.etf.rpr.model.UserDAO;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AdminStudentController implements IAlert {
    private final UserDAO model = UserDAO.getInstance();
    public Button bttnAdd;
    @FXML
    private ChoiceBox<String> cbBranch;
    @FXML
    private TextField fldName, fldSurname, fldSex, fldUsername, fldPassword, fldEmail, fldIndex, fldYear;
    @FXML
    private DatePicker dpDate;



    @FXML
    public void initialize(){
        cbBranch.setItems(FXCollections.observableArrayList("Računarstvo i informatika","Automatika i elektronika",
                "Energetika","Telekomunikacije","Razvoj softvera"));
        dpDate.setValue(LocalDate.of(1998,11,13));
    }
    @FXML
    public void confirm(ActionEvent actionEvent){
        try{

            Student student = new Student(1, fldName.getText(), fldSurname.getText(), dpDate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")), fldSex.getText(),
                    fldIndex.getText(), cbBranch.getValue(),Integer.parseInt(fldYear.getText()),fldUsername.getText(),fldPassword.getText(),fldEmail.getText());
            model.addStudent(student);
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        }catch (Exception e){
            showAlertWarning("Sva polja moraju biti popunjena","Popunite sva polja i pokušajte opet");
        }
    }
}
