package avaj.simulator.vehicles;
import avaj.simulator.CustomExceptions.IncorrectAircraftType;
import avaj.simulator.Interface.Flyable;

public class AircraftFactory {
    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) throws IncorrectAircraftType {

        Coordinates coordinates = new Coordinates(longitude, latitude, height);

        switch (type) {
            case "Baloon":
                return new Baloon(name, coordinates);
            case "JetPlane":
                return new JetPlane(name, coordinates);
            case "Helicopter":
                return new Helicopter(name, coordinates);
            default:
                throw new IncorrectAircraftType(String.format((char)27 +"[31mIncorrect aircraft type: " + (char)27 + "[0m" + type));
        }
    }

}
