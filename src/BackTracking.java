import Element.Servidor;
import Element.Usuari;

public class BackTracking {

    private Usuari[] candidates;
    private Servidor[] servidors;
    private Solution solution;

    public BackTracking(Usuari[] candidates, Servidor[] servidors) {
        this.candidates = candidates;
        this.servidors = servidors;
    }

    public Solution backtracking(Solution best){
        solution = new Solution(candidates, servidors);

        if(casTrivial())
            best = tracteSolucio(best);

        else{
            for(solution.setSeguent_nivell(0); solution.getSeguent_nivell() < candidates.length;
                solution.setSeguent_nivell(solution.getSeguent_nivell() + 1)){

                if(esPrometedora()){

                    solution.setUsuaris(solution.getSeguent_nivell(), solution.getSeguent_germa());
                    solution.setActivitats(candidates[solution.getSeguent_nivell()].getActivity() , solution.getSeguent_germa());

                    backtracking(best);

                    solution.setUsuaris(solution.getSeguent_nivell(), -1);
                    solution.setActivitats( -candidates[solution.getSeguent_nivell()].getActivity() , solution.getSeguent_germa());
                }   //if
            }   //for
        }   //else

        return best;
    }

    public boolean casTrivial(){
        return solution.getSeguent_nivell() < solution.getUsuaris().length;
    }

    public Solution tracteSolucio(Solution best){

        double equitivitat_best = best.getMax() - best.getMin();
        double equitivitat_solution = solution.getMax() - solution.getMin();

        if(equitivitat_best > equitivitat_solution)
            best = solution;

        return best;
    }

    public boolean esPrometedora(){
        return solution.getSeguent_germa() < servidors.length;
    }
}
