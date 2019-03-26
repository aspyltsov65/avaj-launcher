package avaj.simulator;

import avaj.simulator.vehicles.Coordinates;
import avaj.simulator.weather.WeatherProvider;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates) {
        return (WeatherProvider.getProvider().getCurrenrWeather(coordinates));
    }

    void changeWeather() {

    }

}
