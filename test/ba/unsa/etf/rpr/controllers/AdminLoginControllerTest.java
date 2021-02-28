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
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

@ExtendWith(ApplicationExtension.class)

class AdminLoginControllerTest {
    UserDAO model=UserDAO.getInstance();
@Start
    void start(Stage stage) throws IOException {
    //StudentLoginController ctrl = new StudentLoginController("ajla");
    Locale.setDefault(new Locale("bs"));
    ResourceBundle bundle = ResourceBundle.getBundle("translation");
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/adminLogin.fxml"),bundle);
    //loader.setController(ctrl);
    Parent root = loader.load();
    stage.setTitle("Test");
    stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
    stage.show();
    stage.toFront();
}
@Test
    void viewStudents(FxRobot robot) {
    robot.clickOn("#sReport");
}
    @Test
    void viewStaff(FxRobot robot) {
        robot.clickOn("#pReport");
    }
}