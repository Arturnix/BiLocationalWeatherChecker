package pl.arturzgodka.weatherapp.model;

public class WeatherDataModel {
    private final int tempCelsius;
    private final int pressure;
    private final int humidityPercentage;
    private final String description;
    private final String cityName;
    private final long timeStamp;


    public WeatherDataModel(int tempCelsius, int pressure, int humidityPercentage, String description, String cityName, long timeStamp) {
        this.tempCelsius = tempCelsius;
        this.pressure = pressure;
        this.humidityPercentage = humidityPercentage;
        this.description = description;
        this.cityName = cityName;
        this.timeStamp = timeStamp;
    }

    public int getTempCelsius() {
        return tempCelsius;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidityPercentage() {
        return humidityPercentage;
    }

    public String getDescription() {
        return description;
    }

    public String getCityName() {
        return cityName;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String toString() {
        return "WeatherDataModel{" +
                "tempCelsius=" + tempCelsius +
                ", pressure=" + pressure +
                ", humidityPercentage=" + humidityPercentage +
                ", description='" + description +
                ", cityName='" + cityName +
                ", timeStamp='" + timeStamp + '\'' +
                '}';
    }
}
