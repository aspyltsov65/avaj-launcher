package avaj.simulator.vehicles;

import avaj.simulator.WeatherTower;
import avaj.simulator.Interface.Flyable;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {}

    public void updateConditions() {
        Coordinates newCoord;
        String weatherType = weatherTower.getWeather(this.coordinates);
        StringBuilder aircraftInformation = new StringBuilder();
        aircraftInformation.append(String.format("JetPlane# %s (%s):", this.name, this.id));


        switch (weatherType){
            case "SUN":
                newCoord = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 10,
                        coordinates.getHeight() + 2);
                aircraftInformation.append("Some message");
            case "RAIN":
                newCoord = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 5,
                        coordinates.getHeight());
                aircraftInformation.append("some text");
            case "FOG":
                newCoord = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 1,
                        coordinates.getHeight());
                aircraftInformation.append("Some text");
            case "SNOW":
                newCoord = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 7);
                break;
        }
        weatherTower.putDataToFile(aircraftInformation.toString());
    }
    public void registerTower(WeatherTower weatherTower) {}
}
