import Element.Servidor;
import Element.Usuari;

public class BackTracking {

    private Usuari[] candidates;
    private Servidor[] servidors;
    private Solution solution;

    public BackTracking(Usuari[] candidates, Servidor[] servidors) {
        this.candidates = candidates;
        this.servidors = servidors;
        solution = new Solution(candidates, servidors);
    }

    public Solution backtrackingDistribucio(Solution best){

        if(casTrivial())
            best = tracteSolucio(best);

        else{
            for(solution.setSeguent_germa(0); solution.getSeguent_germa() < servidors.length - 1;
                solution.setSeguent_germa(solution.getSeguent_germa() + 1)){

                if(esPrometedora(best)){

                    solution.setUsuaris(solution.getSeguent_nivell(), solution.getSeguent_germa());
                    solution.setActivitats(candidates[solution.getSeguent_nivell()].getActivity() , solution.getSeguent_germa());

                    solution.setSeguent_nivell(solution.getSeguent_nivell() + 1);
                    backtrackingDistribucio(best);

                    solution.setSeguent_nivell(solution.getSeguent_nivell() - 1);

                }   //if
            }   //for
        }   //else

        return best;
    }

    public boolean casTrivial(){
        return solution.getSeguent_nivell() == solution.getUsuaris().length - 1;
    }

    public Solution tracteSolucio(Solution best){

        double equitivitat_best = best.getMax() - best.getMin();
        double equitivitat_solution = solution.getMax() - solution.getMin();

        if(equitivitat_best > equitivitat_solution)
            best = solution;

        return best;
    }

    public boolean esPrometedora(Solution best){
        return (solution.getMax() - solution.getMin()) < (best.getMax() - best.getMin());
    }
}
