package Element;

import Element.ElementUsuari.Connection;
import Element.ElementUsuari.Post;

import java.util.ArrayList;

public class Usuari {
    private String username;
    private int followers;
    private int follows;
    private double activity;
    private ArrayList<Connection> connections;
    private ArrayList<Post> posts;
    private static final double RADI_TERRA = 6371;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getFollowers() {
        return followers;
    }

    public void setFollowers(int followers) {
        this.followers = followers;
    }

    public int getFollows() {
        return follows;
    }

    public void setFollows(int follows) {
        this.follows = follows;
    }

    public double getActivity() {
        return activity;
    }

    public void setActivity(double activity) {
        this.activity = activity;
    }

    public ArrayList<Connection> getConnections() {
        return connections;
    }

    public void setConnections(ArrayList<Connection> connections) {
        this.connections = connections;
    }

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public double calculaDistancia(double latitudO, double longitudO, double latitudF, double longitudF){
        double dLatitud = Math.toRadians(latitudF - latitudO);
        double dLongitud = Math.toRadians(longitudF - longitudO);

        latitudO = Math.toRadians(latitudO);
        latitudF = Math.toRadians(latitudF);

        double var1 = Math.pow(Math.sin(dLatitud / 2), 2) + Math.pow(Math.sin(dLongitud / 2), 2) *
                Math.cos(latitudO) * Math.cos(latitudF);
        double var2 = 2 * Math.asin(Math.sqrt(var1));

        return RADI_TERRA * var2;    //distancia
    }
}
