package pl.arturzgodka.weatherapp.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Weather5DaysForecastMapperTest {

    @Mock
    private Weather5DaysForecastMapper weather5DaysForecastMapperMock;

    private final List<WeatherDataModel> forecast5Days = new ArrayList<WeatherDataModel>(5);
    private final WeatherDataModel weatherDataModel1 = new WeatherDataModel(22, 1015,
            "moderate rain", "2024-08-20 16:00:00");
    private final WeatherDataModel weatherDataModel2 = new WeatherDataModel(22, 1015,
            "moderate rain", "2024-08-21 16:00:00");
    private final WeatherDataModel weatherDataModel3 = new WeatherDataModel(22, 1015,
            "moderate rain", "2024-08-22 16:00:00");
    private final WeatherDataModel weatherDataModel4 = new WeatherDataModel(22, 1015,
            "moderate rain", "2024-08-23 16:00:00");
    private final WeatherDataModel weatherDataModel5 = new WeatherDataModel(22, 1015,
            "moderate rain", "2024-08-24 16:00:00");

    @Test
    public void shouldReturnCorrectClassOfListElements() {
        //given
        forecast5Days.add(weatherDataModel1);

        //when
        lenient().when(weather5DaysForecastMapperMock.fetchWeatherToDataModel("getWeatherApi")).thenReturn(forecast5Days);

        //then
        assertThat(forecast5Days.get(0), instanceOf((WeatherDataModel.class)));
    }

    @Test
    public void shouldReturnCorrectSizeOfListWithWeatherForecast() {
        //given
        forecast5Days.addAll(Arrays.asList(weatherDataModel1, weatherDataModel2,weatherDataModel3,weatherDataModel4, weatherDataModel5));

        //when
        lenient().when(weather5DaysForecastMapperMock.fetchWeatherToDataModel("getWeatherApi")).thenReturn(forecast5Days);

        //then
        assertThat(forecast5Days, hasSize(5));
    }

    @Test
    public void shouldCorrectFetchWeatherToDataModel() {
        //given
        forecast5Days.add(weatherDataModel1);

        //when
        lenient().when(weather5DaysForecastMapperMock.fetchWeatherToDataModel("getWeatherApi")).thenReturn(forecast5Days);

        //then
        assertThat(forecast5Days.get(0).getTempCelsius(), is(22));
        assertThat(forecast5Days.get(0).getPressure(), is(1015));
        assertThat(forecast5Days.get(0).getDescription(), equalTo("moderate rain"));
        assertThat(forecast5Days.get(0).getDateAndTimeStampFetched(), equalTo("2024-08-20 16:00:00"));
    }

    @Test
    public void shouldThrowExceptionWhenWrongWeatherUrlProvidedWithMock() {
        //given
        String wrongWeatherUrl = "";

        //when
        when(weather5DaysForecastMapperMock.fetchWeatherToDataModel(wrongWeatherUrl)).thenThrow(RuntimeException.class);

        //then
        assertThrows(RuntimeException.class, () -> weather5DaysForecastMapperMock.fetchWeatherToDataModel(wrongWeatherUrl));
    }

    @Test
    public void shouldThrowExceptionWhenWrongWeatherUrlProvided() {
        //given
        Weather5DaysForecastMapper weather5DaysForecastMapper = new Weather5DaysForecastMapper();
        String wrongWeatherUrl = "";

        //when
        //then
        assertThrows(RuntimeException.class, () -> weather5DaysForecastMapper.fetchWeatherToDataModel(wrongWeatherUrl));
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenNullWeatherUrlProvided() {
        //given
        Weather5DaysForecastMapper weather5DaysForecastMapper = new Weather5DaysForecastMapper();

        //when
        //then
        assertThrows(NullPointerException.class, () -> weather5DaysForecastMapper.fetchWeatherToDataModel(null));
    }
}
