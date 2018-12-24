package Element;

import java.util.Vector;

public class Servidor {
    private int id;
    private String country;
    private Vector location;
    private int reachable_from;



    public Servidor() {
        location = new Vector(2);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Vector getLocation() {
        return location;
    }

    public void setLocation(Vector location) {
        this.location = location;
    }

    public int getReachable_from() {
        return reachable_from;
    }

    public void setReachable_from(int reachable_from) {
        this.reachable_from = reachable_from;
    }
}
