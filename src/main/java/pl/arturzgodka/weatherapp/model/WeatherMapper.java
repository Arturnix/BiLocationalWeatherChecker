package pl.arturzgodka.weatherapp.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class WeatherMapper {

    public WeatherDataModel fetchWeatherToDataModel(String weatherUrl) {

        String weatherData = WeatherClient.fetchAPIResourceRequest(weatherUrl);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode node = null;

        try {
            node = objectMapper.readTree(weatherData);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        return new WeatherDataModel(
                node.get("main").get("temp").asInt(),
                node.get("main").get("pressure").asInt(),
                node.get("main").get("humidity").asInt(),
                node.get("weather").get(0).get("description").asText(),
                node.get("name").asText(),
                node.get("dt").asLong(),
                node.get("timezone").asInt(),
                node.get("weather").get(0).get("icon").asText()
        );
    }
}
