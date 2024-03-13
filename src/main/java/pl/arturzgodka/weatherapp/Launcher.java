package pl.arturzgodka.weatherapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("view/MainWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Sprawdz pogode");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}