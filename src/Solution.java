import Element.Usuari;

import java.util.ArrayList;

public class Solution {

    private double proximitat_acumulat;
    private ArrayList<String> activitats;
    private int[] usuaris;
    private int seguent_germa;
    private int seguent_nivell;

    public Solution(Usuari[] usuaris) {
        this.usuaris = new int[usuaris.length];
    }
}
