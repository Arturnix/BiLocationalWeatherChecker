package pl.arturzgodka.weatherapp.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;
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
    private Label thatCityTemperatureValue, thatCityPressureValue, thatCityHumidityValue, thatCityWeatherDescriptionValue;

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
    private Label thisCityTemperatureValue, thisCityPressureValue, thisCityHumidityValue, thisCityWeatherDescriptionValue;

    @FXML
    private Pane thisCityTodayWeatherPane, thisCity5DaysForecastPane;

    @FXML
    private Pane thatCityTodayWeatherPane, thatCity5DaysForecastPane;

    @FXML
    private ImageView thisCityWeatherIcon, thatCityWeatherIcon;

    @FXML
    private Pane mainPane;

    @FXML
    private Button checkWeatherBtn;

    @FXML
    void checkWeatherBtnAction() {

        setPaneVisibilityFalse();

        WeatherMapper weatherMapper = new WeatherMapper();
        Weather5DaysForecastMapper weather5DaysForecastMapper = new Weather5DaysForecastMapper();
        List<WeatherDataModel> presentCity5DaysForecast = weather5DaysForecastMapper.fetchWeatherToDataModel(WeatherClient.buildPresentCityUrl5DaysForecast(presentCityInput.getText()));
        List<WeatherDataModel> destinationCity5DaysForecast = weather5DaysForecastMapper.fetchWeatherToDataModel(WeatherClient.buildDestinationCityUrl5DaysForecast(destinationCityInput.getText()));
        WeatherDataModel presentCityDataModel = weatherMapper.fetchWeatherToDataModel(WeatherClient.buildPresentCityUrl(presentCityInput.getText()));
        WeatherDataModel destinationCityDataModel = weatherMapper.fetchWeatherToDataModel(WeatherClient.buildDestinationCityUrl(destinationCityInput.getText()));
        System.out.println(presentCityDataModel.toString());
        System.out.println(destinationCityDataModel.toString());

        showCurrentWeatherFieldsInPresentCity(presentCityDataModel);
        showCurrentWeatherFieldsInDestinationCity(destinationCityDataModel);

        System.out.println(weather5DaysForecastMapper.fetchWeatherToDataModel(WeatherClient.buildPresentCityUrl5DaysForecast(presentCityInput.getText())));

        showPresentCity5DaysForecast(presentCity5DaysForecast, weather5DaysForecastMapper);
        showDestinationCity5DaysForecast(destinationCity5DaysForecast, weather5DaysForecastMapper);

        clearTextInputFields();
        setPaneVisibilityTrue();

    }

    private void setPaneVisibilityFalse() {
        thisCityTodayWeatherPane.setVisible(false);
        thatCityTodayWeatherPane.setVisible(false);
        thisCity5DaysForecastPane.setVisible(false);
        thatCity5DaysForecastPane.setVisible(false);
    }

    private void setPaneVisibilityTrue() {
        thisCityTodayWeatherPane.setVisible(true);
        thatCityTodayWeatherPane.setVisible(true);
        thisCity5DaysForecastPane.setVisible(true);
        thatCity5DaysForecastPane.setVisible(true);
    }

    private void clearTextInputFields() {
        presentCityInput.setText("");
        destinationCityInput.setText("");
    }

    private void showCurrentWeatherFieldsInPresentCity(WeatherDataModel presentCityDataModel) {
        //zrobic transformacje stref czasowych aby pokazywac wlasciwe wartosci obecnej godziny i daty na miesjcu
        thisCityNameLabel.setText(presentCityDataModel.getCityName());
        thisCityCurrentDate.setText(provideLocalDateAndTime(convertTimeStampToLocalTimeZoneUTC(presentCityDataModel.getTimeStamp(), presentCityDataModel.getTimezoneSecondsFromUTC())));
        thisCityTemperature.setText("Temperature:");
        thisCityTemperatureValue.setText(presentCityDataModel.getTempCelsius() + " \u2103");
        thisCityPressure.setText("Pressure:");
        thisCityPressureValue.setText(presentCityDataModel.getPressure() + " hPa");
        thisCityHumidity.setText("Humidity:");
        thisCityHumidityValue.setText(presentCityDataModel.getHumidityPercentage() + "%");
        thisCityWeatherDescription.setText("Weather: ");
        thisCityWeatherDescriptionValue.setText(presentCityDataModel.getDescription());
        thisCityWeatherIcon.setImage(new Image("https://openweathermap.org/img/wn/" + presentCityDataModel.getWeatherIcon() + "@2x.png"));
    }

    private void showCurrentWeatherFieldsInDestinationCity(WeatherDataModel destinationCityDataModel) {
        //zrobic transformacje stref czasowych aby pokazywac wlasciwe wartosci obecnej godziny i daty na miesjcu
        thatCityNameLabel.setText(destinationCityDataModel.getCityName());
        thatCityCurrentDate.setText(provideLocalDateAndTime(convertTimeStampToLocalTimeZoneUTC(destinationCityDataModel.getTimeStamp(), destinationCityDataModel.getTimezoneSecondsFromUTC())));
        thatCityTemperature.setText("Temperature:");
        thatCityTemperatureValue.setText(destinationCityDataModel.getTempCelsius() + " \u2103");
        thatCityPressure.setText("Pressure:");
        thatCityPressureValue.setText(destinationCityDataModel.getPressure() + " hPa");
        thatCityHumidity.setText("Humidity:");
        thatCityHumidityValue.setText(destinationCityDataModel.getHumidityPercentage() + "%");
        thatCityWeatherDescription.setText("Weather:");
        thatCityWeatherDescriptionValue.setText(destinationCityDataModel.getDescription());
        thatCityWeatherIcon.setImage(new Image("https://openweathermap.org/img/wn/" + destinationCityDataModel.getWeatherIcon() + "@2x.png"));
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
        thisCityDay1Date.setText(weather5DaysForecastMapper.fetchWeatherToDataModel(WeatherClient.buildPresentCityUrl5DaysForecast(presentCityInput.getText())).get(0).getDateAndTimeStampFetched().substring(0, 10));
    }

    private void showDay2WeatherInPresentCity(List<WeatherDataModel> presentCity5DaysForecast, Weather5DaysForecastMapper weather5DaysForecastMapper) {

        thisCityDay2Temp.setText(presentCity5DaysForecast.get(1).getTempCelsius() + " \u2103");
        thisCityDay2Pressure.setText(presentCity5DaysForecast.get(1).getPressure() + " hPa");
        thisCityDay2Description.setText(presentCity5DaysForecast.get(1).getDescription());
        thisCityDay2Date.setText(weather5DaysForecastMapper.fetchWeatherToDataModel(WeatherClient.buildPresentCityUrl5DaysForecast(presentCityInput.getText())).get(1).getDateAndTimeStampFetched().substring(0, 10));
    }

    private void showDay3WeatherInPresentCity(List<WeatherDataModel> presentCity5DaysForecast, Weather5DaysForecastMapper weather5DaysForecastMapper) {

        thisCityDay3Temp.setText(presentCity5DaysForecast.get(2).getTempCelsius() + " \u2103");
        thisCityDay3Pressure.setText(presentCity5DaysForecast.get(2).getPressure() + " hPa");
        thisCityDay3Description.setText(presentCity5DaysForecast.get(2).getDescription());
        thisCityDay3Date.setText(weather5DaysForecastMapper.fetchWeatherToDataModel(WeatherClient.buildPresentCityUrl5DaysForecast(presentCityInput.getText())).get(2).getDateAndTimeStampFetched().substring(0, 10));
    }

    private void showDay4WeatherInPresentCity(List<WeatherDataModel> presentCity5DaysForecast, Weather5DaysForecastMapper weather5DaysForecastMapper) {

        thisCityDay4Temp.setText(presentCity5DaysForecast.get(3).getTempCelsius() + " \u2103");
        thisCityDay4Pressure.setText(presentCity5DaysForecast.get(3).getPressure() + " hPa");
        thisCityDay4Description.setText(presentCity5DaysForecast.get(3).getDescription());
        thisCityDay4Date.setText(weather5DaysForecastMapper.fetchWeatherToDataModel(WeatherClient.buildPresentCityUrl5DaysForecast(presentCityInput.getText())).get(3).getDateAndTimeStampFetched().substring(0, 10));
    }

    private void showDay5WeatherInPresentCity(List<WeatherDataModel> presentCity5DaysForecast, Weather5DaysForecastMapper weather5DaysForecastMapper) {

        thisCityDay5Temp.setText(presentCity5DaysForecast.get(4).getTempCelsius() + " \u2103");
        thisCityDay5Pressure.setText(presentCity5DaysForecast.get(4).getPressure() + " hPa");
        thisCityDay5Description.setText(presentCity5DaysForecast.get(4).getDescription());
        thisCityDay5Date.setText(weather5DaysForecastMapper.fetchWeatherToDataModel(WeatherClient.buildPresentCityUrl5DaysForecast(presentCityInput.getText())).get(4).getDateAndTimeStampFetched().substring(0, 10));
    }

    private void showDestinationCity5DaysForecast(List<WeatherDataModel> destinationCity5DaysForecast, Weather5DaysForecastMapper weather5DaysForecastMapper) {
        showDay1WeatherInDestinationCity(destinationCity5DaysForecast, weather5DaysForecastMapper);
        showDay2WeatherInDestinationCity(destinationCity5DaysForecast, weather5DaysForecastMapper);
        showDay3WeatherInDestinationCity(destinationCity5DaysForecast, weather5DaysForecastMapper);
        showDay4WeatherInDestinationCity(destinationCity5DaysForecast, weather5DaysForecastMapper);
        showDay5WeatherInDestinationCity(destinationCity5DaysForecast, weather5DaysForecastMapper);
    }

    private void showDay1WeatherInDestinationCity(List<WeatherDataModel> destinationCity5DaysForecast, Weather5DaysForecastMapper weather5DaysForecastMapper) {

        thatCityDay1Temp.setText(destinationCity5DaysForecast.get(0).getTempCelsius() + " \u2103");
        thatCityDay1Pressure.setText(destinationCity5DaysForecast.get(0).getPressure() + " hPa");
        thatCityDay1Description.setText(destinationCity5DaysForecast.get(0).getDescription());
        thatCityDay1Date.setText(weather5DaysForecastMapper.fetchWeatherToDataModel(WeatherClient.buildDestinationCityUrl5DaysForecast(destinationCityInput.getText())).get(0).getDateAndTimeStampFetched().substring(0, 10));
    }

    private void showDay2WeatherInDestinationCity(List<WeatherDataModel> destinationCity5DaysForecast, Weather5DaysForecastMapper weather5DaysForecastMapper) {

        thatCityDay2Temp.setText(destinationCity5DaysForecast.get(1).getTempCelsius() + " \u2103");
        thatCityDay2Pressure.setText(destinationCity5DaysForecast.get(1).getPressure() + " hPa");
        thatCityDay2Description.setText(destinationCity5DaysForecast.get(1).getDescription());
        thatCityDay2Date.setText(weather5DaysForecastMapper.fetchWeatherToDataModel(WeatherClient.buildDestinationCityUrl5DaysForecast(destinationCityInput.getText())).get(1).getDateAndTimeStampFetched().substring(0, 10));
    }

    private void showDay3WeatherInDestinationCity(List<WeatherDataModel> destinationCity5DaysForecast, Weather5DaysForecastMapper weather5DaysForecastMapper) {

        thatCityDay3Temp.setText(destinationCity5DaysForecast.get(2).getTempCelsius() + " \u2103");
        thatCityDay3Pressure.setText(destinationCity5DaysForecast.get(2).getPressure() + " hPa");
        thatCityDay3Description.setText(destinationCity5DaysForecast.get(2).getDescription());
        thatCityDay3Date.setText(weather5DaysForecastMapper.fetchWeatherToDataModel(WeatherClient.buildDestinationCityUrl5DaysForecast(destinationCityInput.getText())).get(2).getDateAndTimeStampFetched().substring(0, 10));
    }

    private void showDay4WeatherInDestinationCity(List<WeatherDataModel> destinationCity5DaysForecast, Weather5DaysForecastMapper weather5DaysForecastMapper) {

        thatCityDay4Temp.setText(destinationCity5DaysForecast.get(3).getTempCelsius() + " \u2103");
        thatCityDay4Pressure.setText(destinationCity5DaysForecast.get(3).getPressure() + " hPa");
        thatCityDay4Description.setText(destinationCity5DaysForecast.get(3).getDescription());
        thatCityDay4Date.setText(weather5DaysForecastMapper.fetchWeatherToDataModel(WeatherClient.buildDestinationCityUrl5DaysForecast(destinationCityInput.getText())).get(3).getDateAndTimeStampFetched().substring(0, 10));
    }

    private void showDay5WeatherInDestinationCity(List<WeatherDataModel> destinationCity5DaysForecast, Weather5DaysForecastMapper weather5DaysForecastMapper) {

        thatCityDay5Temp.setText(destinationCity5DaysForecast.get(4).getTempCelsius() + " \u2103");
        thatCityDay5Pressure.setText(destinationCity5DaysForecast.get(4).getPressure() + " hPa");
        thatCityDay5Description.setText(destinationCity5DaysForecast.get(4).getDescription());
        thatCityDay5Date.setText(weather5DaysForecastMapper.fetchWeatherToDataModel(WeatherClient.buildDestinationCityUrl5DaysForecast(destinationCityInput.getText())).get(4).getDateAndTimeStampFetched().substring(0, 10));
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
