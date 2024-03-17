package pl.arturzgodka.weatherapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import pl.arturzgodka.weatherapp.APICredentials;
import pl.arturzgodka.weatherapp.model.WeatherClient;
import pl.arturzgodka.weatherapp.model.WeatherMapper;

public class MainWindowController {
    @FXML
    private TextField destinationCityInput;

    @FXML
    private TextField presentCityInput;

    @FXML
    void checkWeatherBtnAction() {

        WeatherMapper weatherMapper = new WeatherMapper();
        System.out.println(weatherMapper.fetchWeatherToDataModel((buildPresentCityUrl())));
        System.out.println(weatherMapper.fetchWeatherToDataModel((buildDestinationCityUrl())));
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
