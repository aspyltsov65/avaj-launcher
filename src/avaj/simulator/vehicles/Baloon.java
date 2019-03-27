package avaj.simulator.vehicles;

import avaj.simulator.Interface.Flyable;
import avaj.simulator.WeatherTower;

public class Baloon extends Aircraft implements Flyable {
    private WeatherTower weatherTower;

    Baloon(String name, Coordinates coordinates) {
        super(name, coordinates);
    }

    public void updateConditions() {
        String weatherType = weatherTower.getWeather(this.coordinates);

        StringBuilder aircraftInformation = new StringBuilder();
        aircraftInformation.append(String.format("Baloon#%s(%s): ", this.name, this.id));

        StringBuilder message = new StringBuilder();

        switch (weatherType){
            case "SUN":
                coordinates = new Coordinates(
                        coordinates.getLongitude() + 2,
                        coordinates.getLatitude(),
                        coordinates.getHeight() + 4);
                aircraftInformation.append("It's to bloody hot today.");
                break;
            case "RAIN":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 5);
                aircraftInformation.append("Thank God I brought my umbrella");
                break;
            case "FOG":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 3);
                aircraftInformation.append("FOG! FOG everywhere");
                break;
            case "SNOW":
                coordinates = new Coordinates(
                        coordinates.getLongitude(),
                        coordinates.getLatitude(),
                        coordinates.getHeight() - 15);
                aircraftInformation.append("It's NEVER too cold for ice cream. Am-nam-nam...");
                break;
        }
        weatherTower.putDataToFile(aircraftInformation.toString());
        aircraftInformation.delete(14, aircraftInformation.length());

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
        message.append(String.format("Tower says: Baloon#%s(%s) registered to weather tower.", this.name, this.id));
        weatherTower.putDataToFile(message.toString());
    }
}
