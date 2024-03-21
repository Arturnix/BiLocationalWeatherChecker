package pl.arturzgodka.weatherapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import pl.arturzgodka.weatherapp.APICredentials;
import pl.arturzgodka.weatherapp.model.Weather5DaysForecastMapper;
import pl.arturzgodka.weatherapp.model.WeatherClient;
import pl.arturzgodka.weatherapp.model.WeatherDataModel;
import pl.arturzgodka.weatherapp.model.WeatherMapper;

import java.time.Instant;
import java.time.LocalDate;

import static javafx.util.Duration.millis;

public class MainWindowController {
    @FXML
    private TextField destinationCityInput;

    @FXML
    private Label errorMessageOutput;

    @FXML
    private TextField presentCityInput;

    @FXML
    private Label thatCityCurrentDate;

    @FXML
    private Label thatCityHumidity;

    @FXML
    private Label thatCityNameLabel;

    @FXML
    private Label thatCityPressure;

    @FXML
    private Label thatCityTemperature;

    @FXML
    private Pane thatCityTodayWeatherPane;

    @FXML
    private Label thatCityWeatherDescription;

    @FXML
    private Label thisCityCurrentDate;

    @FXML
    private Label thisCityHumidity;

    @FXML
    private Label thisCityNameLabel;

    @FXML
    private Label thisCityPressure;

    @FXML
    private Label thisCityTemperature;

    @FXML
    private Pane thisCityTodayWeatherPane;

    @FXML
    private Label thisCityWeatherDescription;

    @FXML
    void checkWeatherBtnAction() {

        WeatherMapper weatherMapper = new WeatherMapper();
        Weather5DaysForecastMapper weather5DaysForecastMapper = new Weather5DaysForecastMapper();
        /*WeatherDataModel presentCityDataModel = weatherMapper.fetchWeatherToDataModel(buildPresentCityUrl());
        WeatherDataModel destinationCityDataModel = weatherMapper.fetchWeatherToDataModel(buildDestinationCityUrl());
        System.out.println(presentCityDataModel.toString());
        System.out.println(destinationCityDataModel.toString());

        //zrobic transformacje stref czasowych aby pokazywac wlasciwe wartosci obecnej godziny i daty na miesjcu
        thisCityNameLabel.setText(presentCityDataModel.getCityName());
        thisCityCurrentDate.setText(provideLocalDateAndTime(convertTimeStampToLocalTimeZoneUTC(presentCityDataModel.getTimeStamp(), presentCityDataModel.getTimezoneSecondsFromUTC())));
        thisCityTemperature.setText("Temperature: " + presentCityDataModel.getTempCelsius() + " \u2103");
        thisCityPressure.setText("Pressure: " + presentCityDataModel.getPressure() + " hPa");
        thisCityHumidity.setText("Humidity: " + presentCityDataModel.getHumidityPercentage() + "%");
        thisCityWeatherDescription.setText("Weather condition: " + presentCityDataModel.getDescription());

        thatCityNameLabel.setText(destinationCityDataModel.getCityName());
        thatCityCurrentDate.setText(provideLocalDateAndTime(convertTimeStampToLocalTimeZoneUTC(destinationCityDataModel.getTimeStamp(), destinationCityDataModel.getTimezoneSecondsFromUTC())));
        thatCityTemperature.setText("Temperature: " + destinationCityDataModel.getTempCelsius() + " \u2103");
        thatCityPressure.setText("Pressure: " + destinationCityDataModel.getPressure() + " hPa");
        thatCityHumidity.setText("Humidity: " + destinationCityDataModel.getHumidityPercentage() + "%");
        thatCityWeatherDescription.setText("Weather condition: " + destinationCityDataModel.getDescription());
*/
        System.out.println(weather5DaysForecastMapper.fetchWeatherToDataModel(buildPresentCityUrl5DaysForecast()));
        presentCityInput.setText("");
        destinationCityInput.setText("");
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

    private String buildPresentCityUrl5DaysForecast() {

        StringBuilder apiRequestUrlPresentCity = new StringBuilder();
        apiRequestUrlPresentCity.append("https://api.openweathermap.org/data/2.5/forecast?q=")
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

    private String buildDestinationCityUrl5DaysForecast() {

        StringBuilder apiRequestUrlDestinationCity = new StringBuilder();
        apiRequestUrlDestinationCity.append("https://api.openweathermap.org/data/2.5/forecast?q=")
                .append(destinationCityInput.getText())
                .append("&appid=")
                .append(APICredentials.APIKey)
                .append("&units=metric");

        return apiRequestUrlDestinationCity.toString();
    }

    public static Instant fromTimestamp(long input) {
        return Instant.ofEpochSecond(input);
    }

    private String provideLocalDateAndTime(Long unixDateAndTime) {
        return fromTimestamp(unixDateAndTime).toString().replaceAll("[TZ]", " ");
    }

    private long convertTimeStampToLocalTimeZoneUTC(long unixDateAndTime, int timeShiftUTC) {
       return unixDateAndTime + timeShiftUTC;
    }
}
