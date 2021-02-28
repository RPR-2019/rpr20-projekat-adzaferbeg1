package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.model.Employee;
import ba.unsa.etf.rpr.model.IAlert;
import ba.unsa.etf.rpr.model.Professor;
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

public class AdminProfessorEmployeeController implements IAlert {
    private final UserDAO model = UserDAO.getInstance();
    public Button bttnAdd;
    @FXML
    private ChoiceBox<String> cbUser;
    @FXML
    private TextField fldName, fldSurname, fldSex, fldUsername, fldPassword, fldEmail, fldTitle;
    @FXML
    private DatePicker dpDate;

    @FXML
    public void initialize(){
        cbUser.setItems(FXCollections.observableArrayList("Profesor", "Uposlenik SS"));
        dpDate.setValue(LocalDate.of(2000,1,1));
    }

    public void confirm(ActionEvent actionEvent) {
        try{
            if(cbUser.getValue().equals("Profesor")) {
                Professor professor = new Professor(1, fldName.getText(), fldSurname.getText(), dpDate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
                        fldSex.getText(), fldTitle.getText(), fldUsername.getText(), fldPassword.getText(), fldEmail.getText());
                model.addProfessor(professor);
                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            }else {
                if(cbUser.getValue().equals("Uposlenik SS")) {
                Employee employee = new Employee(1, fldName.getText(), fldSurname.getText(), fldTitle.getText(), fldUsername.getText(), fldPassword.getText(), fldEmail.getText(),
                        dpDate.getValue().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                model.addEmployee(employee);

                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
            }
            }

        }catch (Exception e){
            showAlertWarning("Sva polja moraju biti popunjena","Popunite sva polja i pokušajte opet");
        }
    }
}
