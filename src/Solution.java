import Element.Servidor;
import Element.Usuari;

import java.util.ArrayList;

public class Solution {

    private double[][] activitats;
    private int[] usuaris;
    private int seguent_germa;
    private int seguent_nivell;

    public Solution(Usuari[] usuaris, Servidor[] servidors) {
        this.usuaris = new int[usuaris.length];
        seguent_germa = 0;
        seguent_nivell = 0;
        activitats = new double[servidors.length][usuaris.length];

        for(int i = 0; i < usuaris.length; i++){
            this.usuaris[i] = -1;
        }   //for

        for(int i = 0; i < servidors.length; i++){
            for(int j = 0; j < usuaris.length; j++){
                activitats[i][j] = -1;
            }   //for
        }   //for
    }

    public double[][] getActivitats() {
        return activitats;
    }

    public void setActivitats(double[][] activitats) {
        this.activitats = activitats;
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
}
