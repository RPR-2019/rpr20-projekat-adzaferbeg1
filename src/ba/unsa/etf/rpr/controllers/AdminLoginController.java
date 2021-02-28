package ba.unsa.etf.rpr.controllers;


import ba.unsa.etf.rpr.model.ILogout;
import ba.unsa.etf.rpr.model.IWindow;
import ba.unsa.etf.rpr.model.UserDAO;
import ba.unsa.etf.rpr.reports.ProfessorReport;
import ba.unsa.etf.rpr.reports.StudentReport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import net.sf.jasperreports.engine.JRException;

import java.util.Locale;

public class AdminLoginController implements ILogout, IWindow {
    private final UserDAO model = UserDAO.getInstance();
    public Button sReport;
    public Button pReport;
    final Locale locale = Locale.getDefault();

    @FXML

    @Override
    public void logout(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
        openWindow("login", locale);
    }

    public void addStudent(ActionEvent actionEvent) {
        openWindow("adminStudent", locale);
    }

    public void addProfessor(ActionEvent actionEvent) {
        openWindow("adminProfessorEmployee", locale);

    }

    public void addSubject(ActionEvent actionEvent) {
        openWindow("adminSubject", locale);
    }

    public void showStudentList(ActionEvent actionEvent) {
        try {
            new StudentReport().showReport(model.getConn());
        } catch (JRException e1) {
            System.out.println("Loading...");
        }
    }

    public void showProfessorList(ActionEvent actionEvent) {
        try {
            new ProfessorReport().showReport(model.getConn());
        } catch (JRException e1) {
            System.out.println("Loading...");
        }
    }



}
