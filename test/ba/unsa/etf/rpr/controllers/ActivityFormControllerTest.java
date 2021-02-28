package ba.unsa.etf.rpr.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
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

class ActivityFormControllerTest {
    @Start
    void start(Stage stage) throws IOException {
        Locale.setDefault(new Locale("bs"));
        ActivityFormController ctrl = new ActivityFormController("Ispit");
        ResourceBundle bundle = ResourceBundle.getBundle("translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/activityForm.fxml"),bundle);
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Test");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.show();
        stage.toFront();
    }
    @Test
    void checkLabel(FxRobot robot){
        Text label = robot.lookup("#txtWelcome").queryText();
        assertEquals("Unosite aktivnost: Ispit", label.getText());
    }
    @Test
    void checkIfThere(FxRobot robot){
        robot.clickOn("#txtTitle");
        robot.clickOn("#dpDate");
        robot.clickOn("#txtArea");
    }
}