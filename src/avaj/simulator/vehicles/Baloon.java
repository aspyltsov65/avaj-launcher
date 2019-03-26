package avaj.simulator.vehicles;

import avaj.simulator.Interface.Flyable;
import avaj.simulator.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        Coordinates newCoord;
        String weatherType = weatherTower.getWeather(this.coordinates);
        StringBuilder aircraftInformation = new StringBuilder();
        aircraftInformation.append(String.format("Baloon# %s (%s):", this.name, this.id));


        switch (weatherType){
            case "SUN":
                newCoord = new Coordinates(
                        coordinates.getLongitude() + 2,
                        coordinates.getLatitude(),
                        coordinates.getHeight() + 4);
                aircraftInformation.append("Some message");
            case "RAIN":
                newCoord = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 5);
                aircraftInformation.append("some text");
            case "FOG":
                newCoord = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 3);
                aircraftInformation.append("Some text");
            case "SNOW":
                newCoord = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 15);
                break;
        }
        weatherTower.putDataToFile(aircraftInformation.toString());
    }

    public void registerTower(WeatherTower weatherTower) {

    }
}
