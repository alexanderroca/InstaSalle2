import Element.Servidor;
import Element.Usuari;

public class Greedy {

    private Usuari[] candidates;
    private Servidor[] servidors;
    private Solution solution;

    public Greedy(Usuari[] candidates, Servidor[] servidors) {
        this.candidates = candidates;
        this.servidors = servidors;
    }

    public Solution greedy(){
        solution = new Solution(candidates, servidors);
        int i = 0;

        while(still_candidates_to_check()){

            if(isFeasible())
                add();

            solution.setSeguent_nivell(solution.getSeguent_germa() + 1);
        }   //while

        return solution;
    }

    public boolean still_candidates_to_check(){
        return solution.getSeguent_nivell() < candidates.length;
    }

    public boolean isFeasible(){
        boolean ok = false;



        for(int i = 0; ; i++){

        }   //for

        return ok;
    }

    public void add(){

        if(!solution.getActivitats()[solution.getSeguent_germa()].
                contains(candidates[solution.getSeguent_nivell()].getActivity())){
            solution.getActivitats()[solution.getSeguent_germa()].
                    add(candidates[solution.getSeguent_nivell()].getActivity());
        }   //if

        solution.setUsuaris(solution.getSeguent_germa(), solution.getSeguent_germa());
    }

}
