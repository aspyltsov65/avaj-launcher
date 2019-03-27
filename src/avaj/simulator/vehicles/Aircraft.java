package avaj.simulator.vehicles;

public class Aircraft {
    protected long id;
    protected String name;
    protected Coordinates coordinates;
    private static long idCounter = 0l;

    protected Aircraft() {
    }
    protected Aircraft(String name, Coordinates coordinates) {
        this.id = nextId();
        this.name = name;
        this.coordinates = coordinates;
    }

    private long nextId() {
        this.idCounter++;
        return  idCounter;
    }
}
