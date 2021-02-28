package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.model.StudentSubject;
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
@ExtendWith(ApplicationExtension.class)
class GradeSubjectControllerTest {
    @Start
    void start(Stage stage) throws IOException {
        GradeSubjectController ctrl = new GradeSubjectController(new StudentSubject("Ajla Džaferbegović","DM",0,"Nije ocijenjeno"),"DM");
        Locale.setDefault(new Locale("bs"));
        ResourceBundle bundle = ResourceBundle.getBundle("translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/gradeSubject.fxml"),bundle);
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Test");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
        stage.toFront();
    }
    @Test
    void checkIfThere(FxRobot robot){
        robot.clickOn("#fldHomework");
        robot.clickOn("#fldExam");
        robot.clickOn("#fldOther");
        robot.clickOn("#fldGrade");
    }
}