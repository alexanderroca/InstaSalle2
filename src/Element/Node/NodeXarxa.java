package Element.Node;

import java.util.ArrayList;

public class NodeXarxa {
    private int id;
    private double reliability;
    private ArrayList<Connectat> connectsTo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getReliability() {
        return reliability;
    }

    public void setReliability(double reliability) {
        this.reliability = reliability;
    }

    public ArrayList<Connectat> getConnectsTo() {
        return connectsTo;
    }

    public void setConnectsTo(ArrayList<Connectat> connectsTo) {
        this.connectsTo = connectsTo;
    }
}
