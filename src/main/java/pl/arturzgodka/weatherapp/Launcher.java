package pl.arturzgodka.weatherapp;


import javafx.application.Application;
import javafx.stage.Stage;
import pl.arturzgodka.weatherapp.model.WeatherClient;
import pl.arturzgodka.weatherapp.view.ViewFactory;

import java.io.IOException;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ViewFactory viewFactory = new ViewFactory();
        viewFactory.showMainWindow(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}