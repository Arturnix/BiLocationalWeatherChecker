package pl.arturzgodka.weatherapp.model;

import pl.arturzgodka.weatherapp.APICredentials;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherClient {

    private static final HttpClient httpClient = HttpClient.newHttpClient();

    public static String fetchAPIResourceRequest(String weatherUrl) {

        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(weatherUrl))
                    .GET()
                    .header("Content-Type", "application/json;charset=UTF-8")
                    .build();

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            if(response.statusCode() != HttpURLConnection.HTTP_OK) {
                throw new RuntimeException("Nie znaleziono takiego miejsca na ziemi!\nPodaj poprawna nazwe.\n" + "HttpResponseCode: " + response.statusCode());
            } else {
                return response.body();
            }

        } catch (InterruptedException e) {
            System.out.println("Processing interrupted!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error occurred!");
            e.printStackTrace();
        }
        return "";
    }

    //zrobic refactor, metod ponizej. Powtorzenia w append.
    public static String buildPresentCityUrl(String presentCityInput) {

        StringBuilder apiRequestUrlPresentCity = new StringBuilder();
        apiRequestUrlPresentCity.append("https://api.openweathermap.org/data/2.5/weather?q=")
                .append(presentCityInput)
                .append("&appid=")
                .append(APICredentials.APIKey)
                .append("&units=metric");

        return apiRequestUrlPresentCity.toString();
    }

    public static String buildPresentCityUrl5DaysForecast(String presentCityInput) {

        StringBuilder apiRequestUrlPresentCity = new StringBuilder();
        apiRequestUrlPresentCity.append("https://api.openweathermap.org/data/2.5/forecast?q=")
                .append(presentCityInput)
                .append("&appid=")
                .append(APICredentials.APIKey)
                .append("&units=metric");

        return apiRequestUrlPresentCity.toString();
    }

    public static String buildDestinationCityUrl(String destinationCityInput) {

        StringBuilder apiRequestUrlDestinationCity = new StringBuilder();
        apiRequestUrlDestinationCity.append("https://api.openweathermap.org/data/2.5/weather?q=")
                .append(destinationCityInput)
                .append("&appid=")
                .append(APICredentials.APIKey)
                .append("&units=metric");

        return apiRequestUrlDestinationCity.toString();
    }

    public static String buildDestinationCityUrl5DaysForecast(String destinationCityInput) {

        StringBuilder apiRequestUrlDestinationCity = new StringBuilder();
        apiRequestUrlDestinationCity.append("https://api.openweathermap.org/data/2.5/forecast?q=")
                .append(destinationCityInput)
                .append("&appid=")
                .append(APICredentials.APIKey)
                .append("&units=metric");

        return apiRequestUrlDestinationCity.toString();
    }
}
