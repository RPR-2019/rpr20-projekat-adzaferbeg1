package ba.unsa.etf.rpr.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;

public interface IWindow {
    default void openWindow(String fxml, Locale locale) {
        try {
            Stage newStage;
            try {
                Locale.setDefault(locale);
                ResourceBundle bundle = ResourceBundle.getBundle("translation");
                String file = "/fxml/"+fxml+".fxml";
                FXMLLoader loader = new FXMLLoader(getClass().getResource(file), bundle);
                Parent root = loader.load();
                newStage = new Stage();
                newStage.setResizable(false);
                newStage.setTitle("E-index 2020 ");
                newStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

                newStage.show();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } catch (Exception ignored) {

        }
    }

    default void openWindowController(Object ctrl, String fxml, Locale locale) {
        try {
            Stage newStage;
            try {
                Locale.setDefault(locale);
                ResourceBundle bundle = ResourceBundle.getBundle("translation");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+fxml+".fxml"), bundle);
                loader.setController(ctrl);
                Parent root = loader.load();
                newStage = new Stage();
                newStage.setResizable(false);
                newStage.setTitle("E-index 2020 ");
                newStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));

                newStage.show();
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        } catch (Exception ignored) {

        }
    }

      default void setLanguage(Object ctrl, String fxml, Locale locale, Stage newStage) throws IOException {
        Locale l = Locale.getDefault();
        if(l.equals(locale))return;
          Locale.setDefault(locale);
        ResourceBundle resourceBundle = ResourceBundle.getBundle("translation");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+fxml+".fxml"), resourceBundle);
        loader.setController(ctrl);
        Parent root = loader.load();
        newStage.setResizable(false);
        newStage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        newStage.setTitle("E-index 2020");
        newStage.show();
    }
}
