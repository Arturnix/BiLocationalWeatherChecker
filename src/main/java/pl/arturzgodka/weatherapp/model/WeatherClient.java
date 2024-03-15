package pl.arturzgodka.weatherapp.model;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class WeatherClient {

    private final HttpClient httpClient = HttpClient.newHttpClient();

    public String fetchAPIResourceRequest(String weatherUrl) {

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
}
