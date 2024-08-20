package pl.arturzgodka.weatherapp.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherMapperTest {

    @Mock
    private WeatherMapper weatherMapperMock;

    private final WeatherDataModel weatherDataModel = new WeatherDataModel(22, 1015, 64,
            "moderate rain", "Zocca", 1661870592L, 7200, "10d");

    @Test
    public void shouldReturnCorrectClass() {
        //given
        //when
        lenient().when(weatherMapperMock.fetchWeatherToDataModel("getWeatherApi")).thenReturn(weatherDataModel);

        //then
        assertThat(weatherDataModel, instanceOf(WeatherDataModel.class));
    }

    @Test
    public void shouldCorrectFetchWeatherToDataModel() {
        //given
        //when
        lenient().when(weatherMapperMock.fetchWeatherToDataModel("getWeatherApi")).thenReturn(weatherDataModel);

        //then
        assertThat(weatherDataModel.getTempCelsius(), is(22));
        assertThat(weatherDataModel.getPressure(), is(1015));
        assertThat(weatherDataModel.getHumidityPercentage(), is(64));
        assertThat(weatherDataModel.getDescription(), equalTo("moderate rain"));
        assertThat(weatherDataModel.getCityName(), equalTo("Zocca"));
        assertThat(weatherDataModel.getTimeStamp(), is(1661870592L));
        assertThat(weatherDataModel.getTimezoneSecondsFromUTC(), is(7200));
        assertThat(weatherDataModel.getWeatherIcon(), equalTo("10d"));
    }

    @Test
    public void shouldThrowExceptionWhenWrongWeatherUrlProvided() {
        //given
        String wrongWeatherUrl = "";

        //when
        when(weatherMapperMock.fetchWeatherToDataModel(wrongWeatherUrl)).thenThrow(RuntimeException.class);

        //then
        assertThrows(RuntimeException.class, () -> weatherMapperMock.fetchWeatherToDataModel(wrongWeatherUrl));
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenNullWeatherUrlProvided() {
        //given
        WeatherMapper weatherMapper = new WeatherMapper();

        //when
        //then
        assertThrows(NullPointerException.class, () -> weatherMapper.fetchWeatherToDataModel(null));
    }
}
