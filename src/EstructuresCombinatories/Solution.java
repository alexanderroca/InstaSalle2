package EstructuresCombinatories;

import Element.Servidor;
import Element.Usuari;

import java.util.ArrayList;

public class Solution {

    private double[] activitats;
    private int[] usuaris;
    private int seguent_germa;
    private int seguent_nivell;
    private int[] cami;
    private double cost;
    private int from_node;
    private int to_node;

    public Solution(Usuari[] usuaris, Servidor[] servidors) {
        this.usuaris = new int[usuaris.length];
        seguent_germa = 0;
        seguent_nivell = 0;
        activitats = new double[servidors.length];

        for(int i = 0; i < usuaris.length; i++){
            this.usuaris[i] = -1;
        }   //for
    }

    public Solution(int from_node, int to_node, Servidor[] servidors){
        seguent_germa = 0;
        seguent_nivell = 0;
        cost = 9999;
        this.from_node = from_node;
        this.to_node = to_node;

        cami = new int[servidors.length];

        for(int i = 0; i < servidors.length; i++)
            cami[i] = -1;

    }

    public double getMax(){
        double max = activitats[0];

        for(int i = 1; i < activitats.length; i++){

            if(max < activitats[i])
                max = activitats[i];

        }   //for

        return max;
    }

    public double getMin(){
        double min = activitats[0];

        for(int i = 1; i < activitats.length; i++){

            if(min > activitats[i])
                min = activitats[i];

        }   //for

        return min;
    }

    public double getActivitats(int pos) {
        return activitats[pos];
    }

    public void setActivitats(double activitat, int pos) {
        activitats[pos] = activitats[pos] + activitat;
    }

    public int[] getUsuaris() {
        return usuaris;
    }

    public void setUsuaris(int id, int pos) {
        usuaris[id] = pos;
    }

    public int getSeguent_germa() {
        return seguent_germa;
    }

    public void setSeguent_germa(int seguent_germa) {
        this.seguent_germa = seguent_germa;
    }

    public int getSeguent_nivell() {
        return seguent_nivell;
    }

    public void setSeguent_nivell(int seguent_nivell) {
        this.seguent_nivell = seguent_nivell;
    }

    public int[] getCami() {
        return cami;
    }

    public void setCami(int pos, int value) {
        cami[pos] = value;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getFrom_node() {
        return from_node;
    }

    public void setFrom_node(int from_node) {
        this.from_node = from_node;
    }

    public int getTo_node() {
        return to_node;
    }

    public void setTo_node(int to_node) {
        this.to_node = to_node;
    }
}
