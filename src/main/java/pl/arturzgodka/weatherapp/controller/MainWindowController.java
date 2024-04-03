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
    private TextField destinationCityInput;

    @FXML
    private Label errorMessageOutput;

    @FXML
    private TextField presentCityInput;

    @FXML
    private Label thatCity5DaysForecastDateHeader;

    @FXML
    private Label thatCity5DaysForecastDescriptionHeader;

    @FXML
    private Label thatCity5DaysForecastPressureHeader;

    @FXML
    private Label thatCity5DaysForecastTemperatureHeader;

    @FXML
    private Label thatCityCurrentDate;

    @FXML
    private Label thatCityDay1Date;

    @FXML
    private Label thatCityDay1Description;

    @FXML
    private Label thatCityDay1Pressure;

    @FXML
    private Label thatCityDay1Temp;

    @FXML
    private Label thatCityDay2Date;

    @FXML
    private Label thatCityDay2Description;

    @FXML
    private Label thatCityDay2Pressure;

    @FXML
    private Label thatCityDay2Temp;

    @FXML
    private Label thatCityDay3Date;

    @FXML
    private Label thatCityDay3Description;

    @FXML
    private Label thatCityDay3Pressure;

    @FXML
    private Label thatCityDay3Temp;

    @FXML
    private Label thatCityDay4Date;

    @FXML
    private Label thatCityDay4Description;

    @FXML
    private Label thatCityDay4Pressure;

    @FXML
    private Label thatCityDay4Temp;

    @FXML
    private Label thatCityDay5Date;

    @FXML
    private Label thatCityDay5Description;

    @FXML
    private Label thatCityDay5Pressure;

    @FXML
    private Label thatCityDay5Temp;

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
    private Label thisCity5DaysForecastDateHeader;

    @FXML
    private Label thisCity5DaysForecastDescriptionHeader;

    @FXML
    private Pane thisCity5DaysForecastPane;

    @FXML
    private Label thisCity5DaysForecastPressureHeader;

    @FXML
    private Label thisCity5DaysForecastTemperatureHeader;

    @FXML
    private Label thisCityCurrentDate;

    @FXML
    private Label thisCityDay1Date;

    @FXML
    private Label thisCityDay1Description;

    @FXML
    private Label thisCityDay1Pressure;

    @FXML
    private Label thisCityDay1Temp;

    @FXML
    private Label thisCityDay2Date;

    @FXML
    private Label thisCityDay2Description;

    @FXML
    private Label thisCityDay2Pressure;

    @FXML
    private Label thisCityDay2Temp;

    @FXML
    private Label thisCityDay3Date;

    @FXML
    private Label thisCityDay3Description;

    @FXML
    private Label thisCityDay3Pressure;

    @FXML
    private Label thisCityDay3Temp;

    @FXML
    private Label thisCityDay4Date;

    @FXML
    private Label thisCityDay4Description;

    @FXML
    private Label thisCityDay4Pressure;

    @FXML
    private Label thisCityDay4Temp;

    @FXML
    private Label thisCityDay5Date;

    @FXML
    private Label thisCityDay5Description;

    @FXML
    private Label thisCityDay5Pressure;

    @FXML
    private Label thisCityDay5Temp;

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
    private Pane thtCity5DaysForecastPane;

    @FXML
    void checkWeatherBtnAction() {

        WeatherMapper weatherMapper = new WeatherMapper();
        Weather5DaysForecastMapper weather5DaysForecastMapper = new Weather5DaysForecastMapper();
        List<WeatherDataModel> presentCity5DaysForecast = weather5DaysForecastMapper.fetchWeatherToDataModel(buildPresentCityUrl5DaysForecast());
        WeatherDataModel presentCityDataModel = weatherMapper.fetchWeatherToDataModel(buildPresentCityUrl());
        WeatherDataModel destinationCityDataModel = weatherMapper.fetchWeatherToDataModel(buildDestinationCityUrl());
        System.out.println(presentCityDataModel.toString());
        System.out.println(destinationCityDataModel.toString());

        showCurrentWeatherFieldsInPresentCity(presentCityDataModel);
        showCurrentWeatherFieldsInDestinationCity(destinationCityDataModel);

        System.out.println(weather5DaysForecastMapper.fetchWeatherToDataModel(buildPresentCityUrl5DaysForecast()));

        thisCityDay1Temp.setText(presentCity5DaysForecast.get(0).getTempCelsius() + " \u2103");
        thisCityDay1Pressure.setText(presentCity5DaysForecast.get(0).getPressure() + " hPa");
        thisCityDay1Description.setText(presentCity5DaysForecast.get(0).getDescription());
        thisCityDay1Date.setText(weather5DaysForecastMapper.fetchWeatherToDataModel(buildPresentCityUrl5DaysForecast()).get(0).getDateAndTimeStampFetched().substring(0, 10));
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
