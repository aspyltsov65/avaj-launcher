package avaj.simulator.weather;

import avaj.simulator.vehicles.Coordinates;

public class WeatherProvider {
    private static WeatherProvider weatherProvider;
    private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {}

    public static WeatherProvider getProvider() {
        return weatherProvider;
    }

    public String getCurrenrWeather(Coordinates coord) {
        int randomly_weather = Math.abs( 2 * (coord.getLatitude() + coord.getLongitude())
                + coord.getLatitude() * coord.getLongitude() - coord.getHeight()) % 4;
        return weather[randomly_weather];
    }

}
