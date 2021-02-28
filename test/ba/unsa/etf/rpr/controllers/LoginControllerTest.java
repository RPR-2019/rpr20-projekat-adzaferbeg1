package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ApplicationExtension.class)
class LoginControllerTest {

    static boolean containsStyle(TextField field, String style) {
        for (String s : field.getStyleClass())
            if (s.equals(style)) return true;
        return false;
    }
    @Start
        public void start (Stage stage) throws Exception {

            Locale.setDefault(new Locale("bs"));
            ResourceBundle bundle = ResourceBundle.getBundle("translation");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"),bundle);
            //loader.setController(ctrl);
            Parent root = loader.load();
            stage.setTitle("Test");
            stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
            stage.show();
            stage.toFront();
        }

    @Test
    void loginException(FxRobot robot) {
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        robot.clickOn("#fldUsername").write("zjuric");
        robot.clickOn("#fldPassword").write("jure");
        robot.clickOn("#bttnLogin");
        assertTrue(out.size()!=0);
    }
    @Test
    void loginAdmin(FxRobot robot) {
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        robot.clickOn("#fldUsername").write("admin");
        robot.clickOn("#fldPassword").write("admin");
        robot.clickOn("#bttnLogin");
        assertEquals(0, out.size());
    }
    @Test
    void loginProfessor(FxRobot robot) {
        java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();
        System.setOut(new java.io.PrintStream(out));
        robot.clickOn("#fldUsername").write("zjuric");
        robot.clickOn("#fldPassword").write("zjuric");
        robot.clickOn("#bttnLogin");
        assertEquals(0, out.size());
    }
    @Test

    void validation(FxRobot robot){
        robot.clickOn("#fldUsername").write("lbl").eraseText(3);

        TextField field = robot.lookup("#fldUsername").queryAs(TextField.class);
        assertTrue(containsStyle(field, "poljeNijeIspravno"));
        robot.clickOn("#fldUsername").write("blabla");
        assertTrue(containsStyle(field, "poljeIspravno"));


        robot.clickOn("#fldPassword").write("lbl");

        TextField field2 = robot.lookup("#fldPassword").queryAs(TextField.class);
        assertTrue(containsStyle(field2, "poljeIspravno"));
        robot.clickOn("#fldPassword").write("blabla").eraseText(6);
        assertTrue(containsStyle(field2, "poljeNijeIspravno"));
    }

}