package avaj.simulator.vehicles;

import avaj.simulator.WeatherTower;
import avaj.simulator.Interface.Flyable;

public class JetPlane extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String weatherType = weatherTower.getWeather(this.coordinates);
        StringBuilder aircraftInformation = new StringBuilder();
        aircraftInformation.append(String.format("JetPlane#%s(%s): ", this.name, this.id));

        StringBuilder message = new StringBuilder();

        switch (weatherType){
            case "SUN":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 10,
                        coordinates.getHeight() + 2);
                aircraftInformation.append("The sun blinds my eyes!");
                break;
            case "RAIN":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 5,
                        coordinates.getHeight());
                aircraftInformation.append("RAIN? Free carwash.");
                break;
            case "FOG":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude() + 1,
                        coordinates.getHeight());
                aircraftInformation.append("Oh no it's FOG! I can't see anything");
                break;
            case "SNOW":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 7);
                aircraftInformation.append("Keep calm, there will be a snow day soon.");
                break;
        }
        weatherTower.putDataToFile(aircraftInformation.toString());
        aircraftInformation.delete(15, aircraftInformation.length());

        if (coordinates.getHeight() == 0)
        {
            message.append(String.format("%s landing.", aircraftInformation));
            weatherTower.putDataToFile(message.toString());
            message.replace(0, message.length(),
                    String.format("Tower says: %s unregistered from weather tower.", aircraftInformation));
            weatherTower.putDataToFile(message.toString());
            weatherTower.unregister(this);
        }
    }
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower =  weatherTower;
        weatherTower.register(this);
        StringBuilder message = new StringBuilder();
        message.append(String.format("Tower says: JetPlane#%s(%s) registered to weather tower.", this.name, this.id));
        weatherTower.putDataToFile(message.toString());
    }
}
