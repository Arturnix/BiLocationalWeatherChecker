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
import java.util.List;

import static javafx.util.Duration.millis;

public class MainWindowController {

    @FXML
    private TextField destinationCityInput, presentCityInput;

    @FXML
    private Label errorMessageOutput;

    @FXML
    private Label thatCity5DaysForecastTemperatureHeader, thatCity5DaysForecastPressureHeader, thatCity5DaysForecastDescriptionHeader, thatCity5DaysForecastDateHeader;

    @FXML
    private Label thatCityNameLabel, thatCityTemperature, thatCityPressure, thatCityHumidity, thatCityWeatherDescription, thatCityCurrentDate;

    @FXML
    private Label thatCityDay1Temp, thatCityDay1Pressure, thatCityDay1Description, thatCityDay1Date;

    @FXML
    private Label thatCityDay2Temp, thatCityDay2Pressure, thatCityDay2Description, thatCityDay2Date;

    @FXML
    private Label thatCityDay3Temp, thatCityDay3Pressure, thatCityDay3Description, thatCityDay3Date;

    @FXML
    private Label thatCityDay4Temp, thatCityDay4Pressure, thatCityDay4Description, thatCityDay4Date;

    @FXML
    private Label thatCityDay5Temp, thatCityDay5Pressure, thatCityDay5Description, thatCityDay5Date;

    @FXML
    private Label thisCity5DaysForecastTemperatureHeader, thisCity5DaysForecastPressureHeader, thisCity5DaysForecastDescriptionHeader, thisCity5DaysForecastDateHeader;

    @FXML
    private Label thisCityDay1Temp, thisCityDay1Pressure, thisCityDay1Description, thisCityDay1Date;

    @FXML
    private Label thisCityDay2Temp, thisCityDay2Pressure, thisCityDay2Description, thisCityDay2Date;

    @FXML
    private Label thisCityDay3Temp, thisCityDay3Pressure, thisCityDay3Description, thisCityDay3Date;

    @FXML
    private Label thisCityDay4Temp, thisCityDay4Pressure, thisCityDay4Description, thisCityDay4Date;

    @FXML
    private Label thisCityDay5Temp, thisCityDay5Pressure, thisCityDay5Description, thisCityDay5Date;

    @FXML
    private Label thisCityNameLabel, thisCityTemperature, thisCityPressure, thisCityHumidity, thisCityWeatherDescription, thisCityCurrentDate;

    @FXML
    private Pane thisCityTodayWeatherPane, thisCity5DaysForecastPane;

    @FXML
    private Pane thatCityTodayWeatherPane, thtCity5DaysForecastPane;

    @FXML
    void checkWeatherBtnAction() {

        WeatherMapper weatherMapper = new WeatherMapper();
        Weather5DaysForecastMapper weather5DaysForecastMapper = new Weather5DaysForecastMapper();
        List<WeatherDataModel> presentCity5DaysForecast = weather5DaysForecastMapper.fetchWeatherToDataModel(buildPresentCityUrl5DaysForecast());
        List<WeatherDataModel> destinationCity5DaysForecast = weather5DaysForecastMapper.fetchWeatherToDataModel(buildDestinationCityUrl5DaysForecast());
        WeatherDataModel presentCityDataModel = weatherMapper.fetchWeatherToDataModel(buildPresentCityUrl());
        WeatherDataModel destinationCityDataModel = weatherMapper.fetchWeatherToDataModel(buildDestinationCityUrl());
        System.out.println(presentCityDataModel.toString());
        System.out.println(destinationCityDataModel.toString());

        showCurrentWeatherFieldsInPresentCity(presentCityDataModel);
        showCurrentWeatherFieldsInDestinationCity(destinationCityDataModel);

        System.out.println(weather5DaysForecastMapper.fetchWeatherToDataModel(buildPresentCityUrl5DaysForecast()));

        showPresentCity5DaysForecast(presentCity5DaysForecast, weather5DaysForecastMapper);

        presentCityInput.setText("");
        destinationCityInput.setText("");
        thisCity5DaysForecastPane.setVisible(true);
    }

    private void showCurrentWeatherFieldsInPresentCity(WeatherDataModel presentCityDataModel) {
        //zrobic transformacje stref czasowych aby pokazywac wlasciwe wartosci obecnej godziny i daty na miesjcu
        thisCityNameLabel.setText(presentCityDataModel.getCityName());
        thisCityCurrentDate.setText(provideLocalDateAndTime(convertTimeStampToLocalTimeZoneUTC(presentCityDataModel.getTimeStamp(), presentCityDataModel.getTimezoneSecondsFromUTC())));
        thisCityTemperature.setText("Temperature: " + presentCityDataModel.getTempCelsius() + " \u2103");
        thisCityPressure.setText("Pressure: " + presentCityDataModel.getPressure() + " hPa");
        thisCityHumidity.setText("Humidity: " + presentCityDataModel.getHumidityPercentage() + "%");
        thisCityWeatherDescription.setText("Weather condition: " + presentCityDataModel.getDescription());
    }

    private void showCurrentWeatherFieldsInDestinationCity(WeatherDataModel destinationCityDataModel) {
        //zrobic transformacje stref czasowych aby pokazywac wlasciwe wartosci obecnej godziny i daty na miesjcu
        thatCityNameLabel.setText(destinationCityDataModel.getCityName());
        thatCityCurrentDate.setText(provideLocalDateAndTime(convertTimeStampToLocalTimeZoneUTC(destinationCityDataModel.getTimeStamp(), destinationCityDataModel.getTimezoneSecondsFromUTC())));
        thatCityTemperature.setText("Temperature: " + destinationCityDataModel.getTempCelsius() + " \u2103");
        thatCityPressure.setText("Pressure: " + destinationCityDataModel.getPressure() + " hPa");
        thatCityHumidity.setText("Humidity: " + destinationCityDataModel.getHumidityPercentage() + "%");
        thatCityWeatherDescription.setText("Weather condition: " + destinationCityDataModel.getDescription());
    }

    private void showPresentCity5DaysForecast(List<WeatherDataModel> presentCity5DaysForecast, Weather5DaysForecastMapper weather5DaysForecastMapper) {
        showDay1WeatherInPresentCity(presentCity5DaysForecast, weather5DaysForecastMapper);
        showDay2WeatherInPresentCity(presentCity5DaysForecast, weather5DaysForecastMapper);
        showDay3WeatherInPresentCity(presentCity5DaysForecast, weather5DaysForecastMapper);
        showDay4WeatherInPresentCity(presentCity5DaysForecast, weather5DaysForecastMapper);
        showDay5WeatherInPresentCity(presentCity5DaysForecast, weather5DaysForecastMapper);
    }

    private void showDay1WeatherInPresentCity(List<WeatherDataModel> presentCity5DaysForecast, Weather5DaysForecastMapper weather5DaysForecastMapper) {

        thisCityDay1Temp.setText(presentCity5DaysForecast.get(0).getTempCelsius() + " \u2103");
        thisCityDay1Pressure.setText(presentCity5DaysForecast.get(0).getPressure() + " hPa");
        thisCityDay1Description.setText(presentCity5DaysForecast.get(0).getDescription());
        thisCityDay1Date.setText(weather5DaysForecastMapper.fetchWeatherToDataModel(buildPresentCityUrl5DaysForecast()).get(0).getDateAndTimeStampFetched().substring(0, 10));
    }

    private void showDay2WeatherInPresentCity(List<WeatherDataModel> presentCity5DaysForecast, Weather5DaysForecastMapper weather5DaysForecastMapper) {

        thisCityDay2Temp.setText(presentCity5DaysForecast.get(1).getTempCelsius() + " \u2103");
        thisCityDay2Pressure.setText(presentCity5DaysForecast.get(1).getPressure() + " hPa");
        thisCityDay2Description.setText(presentCity5DaysForecast.get(1).getDescription());
        thisCityDay2Date.setText(weather5DaysForecastMapper.fetchWeatherToDataModel(buildPresentCityUrl5DaysForecast()).get(1).getDateAndTimeStampFetched().substring(0, 10));
    }

    private void showDay3WeatherInPresentCity(List<WeatherDataModel> presentCity5DaysForecast, Weather5DaysForecastMapper weather5DaysForecastMapper) {

        thisCityDay3Temp.setText(presentCity5DaysForecast.get(2).getTempCelsius() + " \u2103");
        thisCityDay3Pressure.setText(presentCity5DaysForecast.get(2).getPressure() + " hPa");
        thisCityDay3Description.setText(presentCity5DaysForecast.get(2).getDescription());
        thisCityDay3Date.setText(weather5DaysForecastMapper.fetchWeatherToDataModel(buildPresentCityUrl5DaysForecast()).get(2).getDateAndTimeStampFetched().substring(0, 10));
    }

    private void showDay4WeatherInPresentCity(List<WeatherDataModel> presentCity5DaysForecast, Weather5DaysForecastMapper weather5DaysForecastMapper) {

        thisCityDay4Temp.setText(presentCity5DaysForecast.get(3).getTempCelsius() + " \u2103");
        thisCityDay4Pressure.setText(presentCity5DaysForecast.get(3).getPressure() + " hPa");
        thisCityDay4Description.setText(presentCity5DaysForecast.get(3).getDescription());
        thisCityDay4Date.setText(weather5DaysForecastMapper.fetchWeatherToDataModel(buildPresentCityUrl5DaysForecast()).get(3).getDateAndTimeStampFetched().substring(0, 10));
    }

    private void showDay5WeatherInPresentCity(List<WeatherDataModel> presentCity5DaysForecast, Weather5DaysForecastMapper weather5DaysForecastMapper) {

        thisCityDay5Temp.setText(presentCity5DaysForecast.get(4).getTempCelsius() + " \u2103");
        thisCityDay5Pressure.setText(presentCity5DaysForecast.get(4).getPressure() + " hPa");
        thisCityDay5Description.setText(presentCity5DaysForecast.get(4).getDescription());
        thisCityDay5Date.setText(weather5DaysForecastMapper.fetchWeatherToDataModel(buildPresentCityUrl5DaysForecast()).get(4).getDateAndTimeStampFetched().substring(0, 10));
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
