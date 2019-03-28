package avaj.simulator;

import avaj.simulator.CustomExceptions.IncorrectAircraftType;
import avaj.simulator.CustomExceptions.InvalidArgumentsNbrException;
import avaj.simulator.CustomExceptions.InvalidSimulationNbrException;
import avaj.simulator.Interface.Flyable;
import avaj.simulator.vehicles.AircraftFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Simulator {
    private static WeatherTower weatherTower;
    private static List<Flyable> flyables = new ArrayList<>();


    public static void main(String[] arg) throws InterruptedException, IncorrectAircraftType, InvalidArgumentsNbrException, InvalidSimulationNbrException {
        try {
            if (arg.length > 1)
                throw new InvalidArgumentsNbrException((char)27 +"[34m\nUsage: " + (char)27 + "[0m" + "java avaj.simulator.Simulator <filename>");
            BufferedReader reader = new BufferedReader(new FileReader(arg[0]));
            String line = reader.readLine();
            if (line != null) {
                weatherTower = new WeatherTower();
                int simulations = Integer.parseInt(line.split(" ")[0]);
                if (simulations < 0) {
                    throw new InvalidSimulationNbrException((char)27 +"[31m \nError: " + (char)27 + "[0m" + "Invalid simulations count " + simulations);
                }
                while ((line = reader.readLine()) != null) {
                    Flyable flyable = AircraftFactory.newAircraft(line.split(" ")[0], line.split(" ")[1],
                            Integer.parseInt(line.split(" ")[2]), Integer.parseInt(line.split(" ")[3]),
                            Integer.parseInt(line.split(" ")[4]));
                    flyables.add(flyable);
                }

                for (Flyable flyable : flyables) {
                    flyable.registerTower(weatherTower);
                }

                for (int i = 1; i <= simulations; i++) {
                    String putNumberOfSimulation = "\nSimulation: " + i + "\n";
                    weatherTower.putDataToFile(putNumberOfSimulation);
                    weatherTower.changeWeather();
                }
                reader.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println((char)27 +"[31m Error: " + (char)27 + "[0m" + "Couldn't find file " + arg[0]);
        } catch (IOException e) {
            System.out.println((char)27 +"[31m Error: " + (char)27 + "[0m" +  "There was an error while reading the file " + arg[0]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println((char)27 +"[31m Error: " + (char)27 + "[0m" + "Specify simulation file");
        }

    }
}

