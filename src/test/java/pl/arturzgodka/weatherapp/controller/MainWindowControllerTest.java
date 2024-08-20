package pl.arturzgodka.weatherapp.controller;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class MainWindowControllerTest {

    @Test
    public void shouldCalculateCorrectDateAndTimeFromUnixTime() {
        //given
        Long unixDateAndTime = 1661870592L;

        //when
        String dateAndTime = MainWindowController.fromTimestamp(unixDateAndTime).toString();

        //then
        assertThat(dateAndTime, equalTo("2022-08-30T14:43:12Z"));
    }
}
