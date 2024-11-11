package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {

    private static final String apikey ="8b0ee596ac49fdd19b7a96d0dbe3bd73";
    private static final String baseURL = "http://api.weatherstack.com/current?access_key=ACCESS_KEY&query=CITY";

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeatherByCity(String city) {
        String url = baseURL.replace("ACCESS_KEY", apikey).replace("CITY", city);
        return restTemplate.getForObject(url, WeatherResponse.class);
    }
}
