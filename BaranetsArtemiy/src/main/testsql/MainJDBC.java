package testsql;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainJDBC extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("JDBC mySQL base");
        Parent view = FXMLLoader.load(getClass().getResource("/JDBC/main.fxml"));
        primaryStage.setScene(new Scene(view));
        primaryStage.show();
    }
}
