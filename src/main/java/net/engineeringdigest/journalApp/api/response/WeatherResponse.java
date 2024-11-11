package net.engineeringdigest.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WeatherResponse {

    private Current current;

    public class Current{
        private int temperature;
        @JsonProperty("weather_description")
        private List<String> weatherDescription;
        private int feelslike;
        // Other fields like humidity, wind speed, etc. can be added as per the API response
    }
}
