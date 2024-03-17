package pl.arturzgodka.weatherapp.model;

public class WeatherDataModel {
    private final int tempCelsius;
    private final int pressure;
    private final int humidityPercentage;
    private final String description;

    public WeatherDataModel(int tempCelsius, int pressure, int humidityPercentage, String description) {
        this.tempCelsius = tempCelsius;
        this.pressure = pressure;
        this.humidityPercentage = humidityPercentage;
        this.description = description;
    }

    @Override
    public String toString() {
        return "WeatherDataModel{" +
                "tempCelsius=" + tempCelsius +
                ", pressure=" + pressure +
                ", humidityPercentage=" + humidityPercentage +
                ", description='" + description + '\'' +
                '}';
    }
}
