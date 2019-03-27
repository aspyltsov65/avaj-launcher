package avaj.simulator.vehicles;

import avaj.simulator.Interface.Flyable;
import avaj.simulator.WeatherTower;

public class Helicopter extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Helicopter(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String weatherType = weatherTower.getWeather(this.coordinates);
        StringBuilder aircraftInformation = new StringBuilder();
        aircraftInformation.append(String.format("Helicopter#%s(%s): ", this.name, this.id));

        StringBuilder message = new StringBuilder();

        switch (weatherType){
            case "SUN":
                coordinates = new Coordinates(
                        coordinates.getLongitude() + 10,
                        coordinates.getLatitude(),
                        coordinates.getHeight() + 2);
                aircraftInformation.append("Let's enjoy the sunny weather");
                break;
            case "RAIN":
                coordinates = new Coordinates(
                        coordinates.getLongitude() + 5,
                        coordinates.getLatitude(),
                        coordinates.getHeight());
                aircraftInformation.append("Rain please stop or go to Africa.");
                break;
            case "FOG":
                coordinates = new Coordinates(
                        coordinates.getLongitude() + 1,
                        coordinates.getLatitude(),
                        coordinates.getHeight());
                aircraftInformation.append("Welcome to Silent Hill! Lost in the fog.");
                break;
            case "SNOW":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 12);
                aircraftInformation.append("Today's forecast... Holy crap, I can't feel my face degrees");
                break;
        }
        weatherTower.putDataToFile(aircraftInformation.toString());
        aircraftInformation.delete(17, aircraftInformation.length());

        if (this.coordinates.getHeight() == 0)
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
        message.append(String.format("Tower says: Helicopter#%s(%s) registered to weather tower.", this.name, this.id));
        weatherTower.putDataToFile(message.toString());
    }
}
