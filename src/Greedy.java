import Element.Servidor;
import Element.Usuari;

public class Greedy {

    private Usuari[] candidates;
    private Servidor[] servidors;

    public Solution greedy(){
        Solution solution = new Solution(candidates);
        int i = 0;

        while(still_candidates_to_check(candidates, i)){

            if(){

            }   //if
            i++;
        }   //while

        return solution;
    }

    public boolean still_candidates_to_check(Usuari[] usuaris, int i){
        return i > usuaris.length - 1;
    }

    public boolean is_feasible(Solution solution, ){

    }
}
