package ba.unsa.etf.rpr.controllers;

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
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ApplicationExtension.class)
class AdminSubjectControllerTest {
    final UserDAO model=UserDAO.getInstance();
    @Start
    void start(Stage stage) throws IOException {
        Locale.setDefault(new Locale("bs"));
        ResourceBundle bundle = ResourceBundle.getBundle("translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminSubject.fxml"),bundle);
        Parent root = loader.load();
        stage.setTitle("Test");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
        stage.toFront();
    }
    @Test
    void addSubject(FxRobot robot){
        robot.clickOn("#fldTitle").write("Probni");
        robot.clickOn("#fldECTS").write("Probni");
        robot.clickOn("#cbBranch").clickOn("Energetika");
        robot.clickOn("#fldYear").write("2");
        robot.clickOn("#fldSemester").write("4");
        robot.clickOn("#cbTeacher").clickOn("Vedran Ljubović");
        robot.clickOn("#txtArea").eraseText(18).write("Opis");
        robot.clickOn("#bttnAdd");
        ArrayList<String> subjects = model.getSubjects("Energetika");
        assertTrue(subjects.contains("Probni"));
        model.deleteSubject("Probni");

    }
}