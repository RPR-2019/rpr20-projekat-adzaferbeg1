package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.model.Student;
import ba.unsa.etf.rpr.model.UserDAO;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
class AdminStudentControllerTest {
    final UserDAO model=UserDAO.getInstance();
    @Start
    void start(Stage stage) throws IOException {
        Locale.setDefault(new Locale("bs"));
        ResourceBundle bundle = ResourceBundle.getBundle("translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminStudent.fxml"),bundle);
        Parent root = loader.load();
        stage.setTitle("Test");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
        stage.toFront();
    }
    @Test
    void addStudent(FxRobot robot){
        robot.clickOn("#fldName").write("Probni");
        robot.clickOn("#fldSurname").write("Probni");
        robot.clickOn("#fldSex").write("Probni");
        robot.clickOn("#fldUsername").write("Probni");
        robot.clickOn("#fldPassword").write("Probni");
        robot.clickOn("#fldEmail").write("Probni");
        robot.clickOn("#fldIndex").write("Probni");
        robot.clickOn("#fldYear").write("2");
        robot.clickOn("#cbBranch").clickOn("Energetika");
        robot.clickOn("#bttnAdd");
        Student student=model.getStudent("Probni");
        assertEquals("Probni", student.getName());
        model.deleteStudent(student.getName());

    }
}