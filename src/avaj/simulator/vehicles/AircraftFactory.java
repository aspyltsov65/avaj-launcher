package avaj.simulator.vehicles;
import avaj.simulator.Interface.Flyable;

public class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {

        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        switch (type) {
            case "Baloon":
                return new Baloon(name, coordinates);
            case "JetPlane":
                return new JetPlane(name, coordinates);
            case "Helicopter":
                return new Helicopter(name, coordinates);
            default:
                System.out.println("[31m Error: [0m Aircraft type [37m" + type + "[0m not defined");
                System.exit(1);
                return null;
        }
    }
}
