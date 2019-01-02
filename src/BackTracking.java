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

    public Solution backtracking(){
        solution = new Solution(candidates, servidors);

        if(casTrivial())
            tracteSolucio();

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

    public void tracteSolucio(){

    }

    public boolean esPrometedora(){

        return true;
    }
}
