import Element.Servidor;
import Element.Usuari;

public class Greedy {

    private Usuari[] candidates;
    private Servidor[] servidors;

    public Greedy(Usuari[] candidates, Servidor[] servidors) {
        this.candidates = candidates;
        this.servidors = servidors;
    }

    public Solution greedy(){
        Solution solution = new Solution(candidates, servidors);
        int i = 0;

        while(still_candidates_to_check(candidates, i)){

            ActivitatsServer possible_candidat = extreu_millor_candidat(i, solution);
            solution = add(solution, possible_candidat);

            i++;
        }   //while

        return solution;
    }

    public boolean still_candidates_to_check(Usuari[] usuaris, int i){
        return i > usuaris.length - 1;
    }

    public ActivitatsServer extreu_millor_candidat(int i, Solution solution){
        ActivitatsServer millor_candidat = new ActivitatsServer();

        for(int j = 0; j < servidors.length - 1; j++){

            if(millorOpcio(j, i,solution)){

            }//if
        }   //for

        return millor_candidat;
    }

    public boolean millorOpcio(int j, int i, Solution solution){
        boolean resultat;

        if(solution.getActivitats()[j].getActivity() == null && /*TODO: tema distancia entre usuari i servidor*/){
            resultat = true;
        }   //if
        else{
            boolean trobat = false;
            int k = 0;

            while(!trobat || k < solution.getActivitats().length - 1){

                if(Math.abs(candidates[i].getActivity()))
                    trobat = true;
                k++;
            }
        }   //else

        return resultat;
    }

    public Solution add(Solution s, ActivitatsServer afegir_candidat){

    }
}
