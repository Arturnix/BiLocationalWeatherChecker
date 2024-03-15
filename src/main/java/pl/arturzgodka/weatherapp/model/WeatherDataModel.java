package pl.arturzgodka.weatherapp.model;

public class WeatherDataModel {
    private final float tempCelsius;
    private final int pressure;
    private final int humidityPercentage;
    private final String description;

    public WeatherDataModel(float tempCelsius, int pressure, int humidityPercentage, String description) {
        this.tempCelsius = tempCelsius;
        this.pressure = pressure;
        this.humidityPercentage = humidityPercentage;
        this.description = description;
    }
}
