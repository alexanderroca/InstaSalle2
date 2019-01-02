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
            tracteSolucio(best);

        else{
            for(Usuari candidat : candidates){

                if(esPrometedora()){

                    backtracking();
                }   //if
            }   //for
        }   //else

        return solution;
    }

    public boolean still_options_to_check(){
        return solution.getSeguent_germa() < servidors.length;
    }

    public boolean casTrivial(){
        return solution.getSeguent_nivell() < solution.getUsuaris().length;
    }

    public void tracteSolucio(Solution best){

        double equitivitat_best = best.getMax() - best.getMin();
        double equitivitat_solution = solution.getMax() - solution.getMin();

        if(equitivitat_best > equitivitat_solution)
            best = solution;

    }

    public boolean esPrometedora(){

        return true;
    }
}
