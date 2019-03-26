package avaj.simulator.vehicles;

import avaj.simulator.Interface.Flyable;
import avaj.simulator.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {}

    public void updateConditions() {
        Coordinates newCoord;
        String weatherType = weatherTower.getWeather(this.coordinates);
        StringBuilder aircraftInformation = new StringBuilder();
        aircraftInformation.append(String.format("Helicopter# %s (%s):", this.name, this.id));


        switch (weatherType){
            case "SUN":
                newCoord = new Coordinates(
                        coordinates.getLongitude() + 10,
                        coordinates.getLatitude(),
                        coordinates.getHeight() + 2);
                aircraftInformation.append("Some message");
            case "RAIN":
                newCoord = new Coordinates(
                        coordinates.getLongitude() + 5,
                        coordinates.getLatitude(),
                        coordinates.getHeight());
                aircraftInformation.append("some text");
            case "FOG":
                newCoord = new Coordinates(
                        coordinates.getLongitude() + 1,
                        coordinates.getLatitude(),
                        coordinates.getHeight());
                aircraftInformation.append("Some text");
            case "SNOW":
                newCoord = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 12);
                break;
        }
        weatherTower.putDataToFile(aircraftInformation.toString());

    }
    public void registerTower(WeatherTower weatherTower) {}
}
