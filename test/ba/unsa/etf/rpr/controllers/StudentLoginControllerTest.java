package ba.unsa.etf.rpr.controllers;

import ba.unsa.etf.rpr.model.Subject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(ApplicationExtension.class)
class StudentLoginControllerTest {

    @Start
    public void start (Stage stage) throws Exception {

        StudentLoginController ctrl = new StudentLoginController("ajla");
        Locale.setDefault(new Locale("bs"));
        ResourceBundle bundle = ResourceBundle.getBundle("translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/studentLogin.fxml"),bundle);
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Test");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
        stage.toFront();

    }

     @Test
     void exams(FxRobot robot){
        robot.clickOn("#checkbEx");
         ListView<String> list = robot.lookup("#lvActivity").queryListView();
         assertNotNull(list);
     }
    @Test
     void homeworks(FxRobot robot){
        robot.clickOn("#checkbH");
        ListView<String> list = robot.lookup("#lvActivity").queryListView();
        assertNotNull(list);
    }
    @Test
     void announcements(FxRobot robot){
        robot.clickOn("#checkbAnn");
        ListView<String> list = robot.lookup("#lvActivity").queryListView();
        assertNotNull(list);
    }
    @Test
     void subjectsView(FxRobot robot){
        robot.clickOn("#hyperArc");
        TableView<Subject> table = robot.lookup("#tableViewSubject").queryTableView();
        assertNotNull(table);
    }
    @Test
    void subjectsView2(FxRobot robot){
        robot.clickOn("#hyperAll");
        TableView<Subject> table = robot.lookup("#tableViewSubject").queryTableView();
        assertNotNull(table);
    }
    @Test
    void subjectsView3(FxRobot robot){
        robot.clickOn("#hyperAc");
        TableView<Subject> table = robot.lookup("#tableViewSubject").queryTableView();
        assertNotNull(table);
    }
}