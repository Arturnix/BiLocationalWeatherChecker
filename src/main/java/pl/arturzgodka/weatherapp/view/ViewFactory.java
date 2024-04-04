package pl.arturzgodka.weatherapp.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import pl.arturzgodka.weatherapp.Launcher;

import java.io.IOException;

public class ViewFactory {

    public void showMainWindow(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("view/MainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setTitle("Sprawdz pogode");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
}
