package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.model.*;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;


public class LoginController implements IWindow, IAlert {

public TextField fldUsername;
public PasswordField fldPassword;
public Button bttnLogin;
public final UserDAO model= UserDAO.getInstance();
public GridPane gridPane;
public RadioButton rbEn;
    Locale currentLocale = new Locale("bs");

    public void initialize(){


    fldUsername.textProperty().addListener((obs,oldName,newName) ->{
        if (!newName.isEmpty() ) {

            fldUsername.getStyleClass().removeAll("poljeNijeIspravno");
            fldUsername.getStyleClass().add("poljeIspravno");
        } else {
            fldUsername.getStyleClass().removeAll("poljeIspravno");
            fldUsername.getStyleClass().add("poljeNijeIspravno");
        }
    });

    fldPassword.textProperty().addListener((obs,oldName,newName) ->{
        if (!newName.isEmpty() ) {

            fldPassword.getStyleClass().removeAll("poljeNijeIspravno");
            fldPassword.getStyleClass().add("poljeIspravno");
        } else {
            fldPassword.getStyleClass().removeAll("poljeIspravno");
            fldPassword.getStyleClass().add("poljeNijeIspravno");
        }
    });
}
    public void login(ActionEvent actionEvent){
    String username = fldUsername.getText(); String password = fldPassword.getText();

    if(fldUsername.getText().equals(model.getAdmin().getUsername())){
        if(fldPassword.getText().equals(model.getAdmin().getPassword())){
            closeStage();
            openWindow("adminLogin", currentLocale);
        }else{ showAlertInformation("Uneseni podaci nisu ispravni", "Pokušajte ponovo");

        }
    }else{
        try{
            Student student = model.getStudent(username);
            try {
                if (!fldPassword.getText().equals(student.getPassword())) {
                    fldPassword.getStyleClass().removeAll("poljeIspravno");
                    fldPassword.getStyleClass().add("poljeNijeIspravno");
                    throw new WrongPassword("Ponovite password!");
                }
                Object ctrl = new StudentLoginController(username);
                closeStage();
                openWindowController(ctrl, "studentLogin", currentLocale);

            }catch (WrongPassword e){System.out.println(e.getMessage());}

        } catch (NullPointerException exe) {
            try {
                Professor professor = model.getProfessor(username);
                try{
                if(!fldPassword.getText().equals(professor.getPassword())) {
                    fldPassword.getStyleClass().removeAll("poljeIspravno");
                    fldPassword.getStyleClass().add("poljeNijeIspravno");
                    throw new WrongPassword("Ponovite password!");
                }
                    Object ctrl = new ProfessorLoginController(username);
                    closeStage();
                    openWindowController(ctrl,"professorLogin", currentLocale);

                }catch (WrongPassword e){System.out.println(e.getMessage());}
            } catch (Exception ex) {
                fldUsername.getStyleClass().removeAll("poljeIspravno");
                fldUsername.getStyleClass().add("poljeNijeIspravno");
                System.out.println("Greška username nije ispravan");
            }
        }
    }
 }

    public void closeStage(){
        Stage oldStage = (Stage) bttnLogin.getScene().getWindow();
        oldStage.close();
    }

    public void english(ActionEvent actionEvent) throws IOException {
        if(rbEn.isSelected()){
            currentLocale = new Locale("en", "US");
            setLanguage(null, "login", currentLocale, (Stage) bttnLogin.getScene().getWindow());
        }
    }
}


