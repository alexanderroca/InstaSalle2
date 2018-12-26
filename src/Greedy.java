import Element.Servidor;
import Element.Usuari;

public class Greedy {

    private Usuari[] candidates;
    private Servidor[] servidors;

    public Solution greedy(){
        Solution solution = new Solution(candidates);
        int i = 0;

        while(still_candidates_to_check(candidates, i)){

            int possible_candidat = extreu_millor_candidat(i, solution);
            solution = add(solution, possible_candidat);

            i++;
        }   //while

        return solution;
    }

    public boolean still_candidates_to_check(Usuari[] usuaris, int i){
        return i > usuaris.length - 1;
    }

    public int extreu_millor_candidat(int i, Solution solution){
        int j;

        for(j = 0; j < servidors.length - 1; j++){

            if(candidates[i].getActivity()){

            }//if
        }   //for

        return j;
    }

    public Solution add(Solution s, int possible_candidat){

    }
}
