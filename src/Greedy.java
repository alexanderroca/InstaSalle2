import Element.Servidor;
import Element.Usuari;

public class Greedy {

    public Solution greedy(Usuari[] candidates, Servidor[] servidors){
        Solution solution = new Solution();
        int i = 0;

        while(still_candidates_to_check(candidates, i)){

            i++;
        }   //while

        return solution;
    }

    public boolean still_candidates_to_check(Usuari[] usuaris, int i){
        return i > usuaris.length - 1;
    }
}
