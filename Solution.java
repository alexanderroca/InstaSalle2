package Metode;

import Element.Servidor;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Solution {
    int level;  // Nombre de servidors afegits
    ArrayList<Servidor> path; // Cami per on ha passat el servidor
    double bound; // Marca segons la opció si és el camí més fiable o el més curt

    public Solution(){
        level = 1;
        path = new ArrayList<>();
        bound = 1;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public ArrayList<Servidor> getPath() {
        return path;
    }

    public void setPath(ArrayList<Servidor> path) {
        this.path = path;
    }

    public double getBound() {
        return bound;
    }

    public void setBound(double bound) {
        this.bound = bound;
    }
}
