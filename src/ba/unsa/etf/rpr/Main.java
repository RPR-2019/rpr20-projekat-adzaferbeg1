package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.model.IWindow;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.Locale;


public class Main extends Application implements IWindow {
    final Locale locale = new Locale("bs");
    @Override
    public void start(Stage primaryStage) {

      openWindow("login", locale);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
