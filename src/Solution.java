import Element.Usuari;

import java.util.ArrayList;

public class Solution {

    private double proximitat_acumulat;
    private String[] activitats;
    private int[] usuaris;
    private int seguent_germa;
    private int seguent_nivell;

    public Solution(String[] activitats, int[] usuaris) {
        this.activitats = new String[activitats.length];
        this.usuaris = new int[usuaris.length];
    }
}
