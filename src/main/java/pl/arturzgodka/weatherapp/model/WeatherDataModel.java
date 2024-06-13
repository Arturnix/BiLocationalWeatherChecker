package pl.arturzgodka.weatherapp.model;

public class WeatherDataModel {
    private final int tempCelsius;
    private final int pressure;
    private int humidityPercentage;
    private final String description;
    private String cityName;
    private long timeStamp;
    private int timezoneSecondsFromUTC;
    private String dateAndTimeStampFetched;
    private String weatherIcon;


    public WeatherDataModel(int tempCelsius, int pressure, int humidityPercentage, String description, String cityName, long timeStamp, int timezoneSecondsFromUTC, String weatherIcon) {
        this.tempCelsius = tempCelsius;
        this.pressure = pressure;
        this.humidityPercentage = humidityPercentage;
        this.description = description;
        this.cityName = cityName;
        this.timeStamp = timeStamp;
        this.timezoneSecondsFromUTC = timezoneSecondsFromUTC;
        this.weatherIcon = weatherIcon;
    }

    public WeatherDataModel(int tempCelsius, int pressure, String description, String dateAndTimeStampFetched) {
        this.tempCelsius = tempCelsius;
        this.pressure = pressure;
        this.description = description;
        this.dateAndTimeStampFetched = dateAndTimeStampFetched;
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

    public int getTimezoneSecondsFromUTC() {
        return timezoneSecondsFromUTC;
    }

    public String getDateAndTimeStampFetched() {
        return dateAndTimeStampFetched;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    @Override
    public String toString() {
        return "WeatherDataModel{" +
                "tempCelsius=" + tempCelsius +
                ", pressure=" + pressure +
                ", humidityPercentage=" + humidityPercentage +
                ", description='" + description + '\'' +
                ", cityName='" + cityName + + '\'' +
                ", timeStamp='" + timeStamp +
                ", icon='" + weatherIcon + '\'' +
                '}';
    }
}
