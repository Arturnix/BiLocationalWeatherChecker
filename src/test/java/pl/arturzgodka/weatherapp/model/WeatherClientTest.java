package pl.arturzgodka.weatherapp.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;


@ExtendWith(MockitoExtension.class)
public class WeatherClientTest {

    private String cityName = "Warsaw";

    @Test
    public void shouldFetchCorrectApiResource() {
        //given
        String responseBody = "{\"coord\":{\"lon\":21.0118,\"lat\":52.2298},\"weather\":[{\"id\":500,\"main\":\"Rain\",\"description\":\"light rain\",\"icon\":\"10d\"}],\"base\":\"stations\",\"main\":{\"temp\":29.25,\"feels_like\":30.12,\"temp_min\":26.97,\"temp_max\":30.51,\"pressure\":1007,\"humidity\":51,\"sea_level\":1007,\"grnd_level\":996},\"visibility\":10000,\"wind\":{\"speed\":9.83,\"deg\":270,\"gust\":0},\"rain\":{\"1h\":0.27},\"clouds\":{\"all\":0},\"dt\":1723988142,\"sys\":{\"type\":2,\"id\":2032856,\"country\":\"PL\",\"sunrise\":1723951491,\"sunset\":1724003698},\"timezone\":7200,\"id\":756135,\"name\":\"Warsaw\",\"cod\":200}";
        try(MockedStatic<WeatherClient> weatherClientMock = mockStatic(WeatherClient.class)){
            //when
            weatherClientMock.when(() -> WeatherClient.fetchAPIResourceRequest("weatherUrl")).thenReturn(responseBody);
            //then
            assertThat(WeatherClient.fetchAPIResourceRequest("weatherUrl"), containsString("weather"));
            assertThat(WeatherClient.fetchAPIResourceRequest("weatherUrl"), containsString("description"));
            assertThat(WeatherClient.fetchAPIResourceRequest("weatherUrl"), containsString("temp"));
            assertThat(WeatherClient.fetchAPIResourceRequest("weatherUrl"), containsString("pressure"));
            assertThat(WeatherClient.fetchAPIResourceRequest("weatherUrl"), containsString("humidity"));
            assertThat(WeatherClient.fetchAPIResourceRequest("weatherUrl"), containsString("timezone"));
        }
    }

    @Test
    public void shouldThrowExceptionWhenResponseCodeIsNotOk() {
        //given
        try(MockedStatic<WeatherClient> weatherClientMock = mockStatic(WeatherClient.class)){
            //when
            weatherClientMock.when(() -> WeatherClient.fetchAPIResourceRequest("statusCodeIsNotOk")).thenThrow(RuntimeException.class);
            //then
            assertThrows(RuntimeException.class, () -> WeatherClient.fetchAPIResourceRequest("statusCodeIsNotOk"));
        }
    }

    @Test
    public void shouldThrowExceptionWhenProcessingIsInterrupted() {
        //given
        try(MockedStatic<WeatherClient> weatherClientMock = mockStatic(WeatherClient.class)){
            //when
            weatherClientMock.when(() -> WeatherClient.fetchAPIResourceRequest("processingInterrupted")).thenAnswer(i -> {
                throw new InterruptedException("Processing interrupted!");
            });
            //then
            assertThrows(InterruptedException.class, () -> WeatherClient.fetchAPIResourceRequest("processingInterrupted"));
        }
    }

    @Test
    public void shouldThrowExceptionWhenErrorOccurred() {
        //given
        try(MockedStatic<WeatherClient> weatherClientMock = mockStatic(WeatherClient.class)){
            //when
            weatherClientMock.when(() -> WeatherClient.fetchAPIResourceRequest("errorOccurred")).thenAnswer(i -> {
                throw new IOException("Error occurred!");
            });
            //then
            assertThrows(IOException.class, () -> WeatherClient.fetchAPIResourceRequest("errorOccurred"));
        }
    }

    @Test
    public void shouldBuildPresentCityUrl() {
        //given
        String presentCityUrl;

        //when
        presentCityUrl = WeatherClient.buildPresentCityUrl(cityName);

        //then
        assertThat(presentCityUrl, startsWith("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid="));
        assertThat(presentCityUrl, endsWith("&units=metric"));
    }

    @Test
    public void shouldBuildPresentCity5DaysForecastUrl() {
        //given
        String presentCityUrl;

        //when
        presentCityUrl = WeatherClient.buildPresentCityUrl5DaysForecast(cityName);

        //then
        assertThat(presentCityUrl, startsWith("https://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&appid="));
        assertThat(presentCityUrl, endsWith("&units=metric"));
    }

    @Test
    public void shouldBuildDestinationCityUrl() {
        //given
        String presentCityUrl;

        //when
        presentCityUrl = WeatherClient.buildDestinationCityUrl(cityName);

        //then
        assertThat(presentCityUrl, startsWith("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid="));
        assertThat(presentCityUrl, endsWith("&units=metric"));
    }

    @Test
    public void shouldBuildDestinationCity5DaysForecastUrl() {
        //given
        String presentCityUrl;

        //when
        presentCityUrl = WeatherClient.buildDestinationCityUrl5DaysForecast(cityName);

        //then
        assertThat(presentCityUrl, startsWith("https://api.openweathermap.org/data/2.5/forecast?q=" + cityName + "&appid="));
        assertThat(presentCityUrl, endsWith("&units=metric"));
    }
}
