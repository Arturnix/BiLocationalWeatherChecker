package pl.arturzgodka.weatherapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class MainWindowController {
    @FXML
    private TextField destinationCityInput;

    @FXML
    private TextField presentCityInput;

    @FXML
    void checkWeatherBtnAction() {
        System.out.println("Pogoda!");
        System.out.println(presentCityInput.getText());
        System.out.println(destinationCityInput.getText());
    }
}
