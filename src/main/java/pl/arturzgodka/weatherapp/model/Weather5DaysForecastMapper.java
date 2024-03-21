package pl.arturzgodka.weatherapp.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class Weather5DaysForecastMapper {

    //zmienic ta metode niech zwraca liste obiektow
    public List<WeatherDataModel> fetchWeatherToDataModel(String weatherUrl) {

        String weatherData = WeatherClient.fetchAPIResourceRequest(weatherUrl);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = null;

        try {
            node = objectMapper.readTree(weatherData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        List<WeatherDataModel> weather5DaysForecastDataModels = new ArrayList<>();

        int timeStampCounter = 4;
        for(int i = 0; i < 5; i++) {
            WeatherDataModel weatherDataModel = new WeatherDataModel(
            node.get("list").get(timeStampCounter).get("main").get("temp").asInt(),
            node.get("list").get(timeStampCounter).get("main").get("pressure").asInt(),
            node.get("list").get(timeStampCounter).get("weather").get(0).get("description").asText(),
            node.get("list").get(timeStampCounter).get("dt_txt").asText()
           );
            weather5DaysForecastDataModels.add(weatherDataModel);
            timeStampCounter += 8;
        }

        return weather5DaysForecastDataModels;
    }
}
