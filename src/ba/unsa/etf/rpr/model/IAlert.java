package ba.unsa.etf.rpr.model;

import javafx.scene.control.Alert;

public interface IAlert {
    default void showAlertInformation(String header, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }

    default void showAlertWarning(String header, String content){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
