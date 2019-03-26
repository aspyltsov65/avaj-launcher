package avaj.simulator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import avaj.simulator.Interface.Flyable;

public class Tower {
    private ArrayList<Flyable> observers = new ArrayList<>();
    private ArrayList<Flyable> unregister = new ArrayList<>();

    private File file;
    private BufferedWriter writer;

    public BufferedWriter getWriter() {return writer;}

    public void register(Flyable flyable) {
        if(observers.contains(flyable))
            return;
        if (!unregister.contains(flyable))
            observers.add(flyable);
    }

    public void unregister(Flyable flyable) {
        unregister.add(flyable);
    }

    protected void conditionsChanged(){
        for(Flyable flyable : observers)
            flyable.updateConditions();
        observers.removeAll(unregister);
    }

    public void putDataToFile(String content) {
        this.file = new File("simulation.txt");
        try {
            this.file.createNewFile();
        } catch (FileAlreadyExistsException e) {
            System.out.println("[31m Error: [0malready exists: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            this.writer = new BufferedWriter(new FileWriter(file));
            writer.write(content);
        } catch (IOException io) {
            io.printStackTrace();
        }
    }
}
