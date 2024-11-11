package net.engineeringdigest.journalApp.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {

    private Current current;

    @Getter
    @Setter
    public class Current{
        private int temperature;
        @JsonProperty("weather_description")
        private List<String> weatherDescription;
        private int feelslike;
        // Other fields like humidity, wind speed, etc. can be added as per the API response
    }
}
