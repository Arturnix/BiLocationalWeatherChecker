package pl.arturzgodka.weatherapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pl.arturzgodka.weatherapp.APICredentials;
import pl.arturzgodka.weatherapp.model.WeatherClient;

public class MainWindowController {
    @FXML
    private TextField destinationCityInput;

    @FXML
    private TextField presentCityInput;

    @FXML
    void checkWeatherBtnAction() {

        WeatherClient weatherClient = new WeatherClient();
        System.out.println(weatherClient.fetchAPIResourceRequest(buildPresentCityUrl()));
        System.out.println(weatherClient.fetchAPIResourceRequest(buildDestinationCityUrl()));
    }

    private String buildPresentCityUrl() {

        StringBuilder apiRequestUrlPresentCity = new StringBuilder();
        apiRequestUrlPresentCity.append("https://api.openweathermap.org/data/2.5/weather?q=")
                .append(presentCityInput.getText())
                .append("&appid=")
                .append(APICredentials.APIKey)
                .append("&units=metric");

        return apiRequestUrlPresentCity.toString();
    }

    private String buildDestinationCityUrl() {

        StringBuilder apiRequestUrlDestinationCity = new StringBuilder();
        apiRequestUrlDestinationCity.append("https://api.openweathermap.org/data/2.5/weather?q=")
                .append(destinationCityInput.getText())
                .append("&appid=")
                .append(APICredentials.APIKey)
                .append("&units=metric");

        return apiRequestUrlDestinationCity.toString();
    }
}
