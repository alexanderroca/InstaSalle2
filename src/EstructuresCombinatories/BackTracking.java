package EstructuresCombinatories;

import Element.Node.NodeXarxa;
import Element.Servidor;
import Element.Usuari;

public class BackTracking {

    private Usuari[] candidates;
    private Servidor[] servidors;
    private NodeXarxa[] nodes_xarxa;
    private Solution solution;

    public BackTracking(Usuari[] candidates, Servidor[] servidors) {
        this.candidates = candidates;
        this.servidors = servidors;
        solution = new Solution(candidates, servidors);
    }

    public BackTracking(NodeXarxa[] nodes_xarxa, int from_server, int to_server, Servidor[] servidors){
        this.nodes_xarxa = nodes_xarxa;
        solution = new Solution(servidors[from_server - 1].getReachable_from(),
                servidors[to_server - 1].getReachable_from(), nodes_xarxa, servidors);
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
                    best = backtrackingDistribucio(best);

                    solution.setSeguent_nivell(solution.getSeguent_nivell() - 1);

                }   //if
            }   //for
        }   //else

        return best;
    }

    public boolean casTrivial(){
        return solution.getSeguent_nivell() == solution.getUsuaris().length - 1;
    }

    public boolean casTrivial2(){

        boolean trobat = false;

        if(solution.getFrom_node() == solution.getTo_node()) {
            trobat = true;
            solution.setCost(9999);
        }   //if

        else {
            if(solution.getCami().size() > 0) {
                if (solution.getTo_node() == (int) solution.getCami().get(solution.getCami().size() - 1))
                    trobat = true;
            }   //if
        }   //else

        return trobat;
    }

    public Solution tracteSolucio(Solution best){

        double equitivitat_best = best.getMax() - best.getMin();
        double equitivitat_solution = solution.getMax() - solution.getMin();

        if(equitivitat_best > equitivitat_solution)
            best = solution;

        return best;
    }

    public Solution tracteSolucio2(Solution best){

        if(best.getCost() == 9999)
            best = solution;

        else if(solution.getCost() > best.getCost())
            best = solution;

        return best;
    }

    public boolean esPrometedora(Solution best){
        return (solution.getMax() - solution.getMin()) < (best.getMax() - best.getMin());
    }

    public boolean esPrometedora2(Solution best){
        return solution.getCost() < best.getCost() && !solution.getVisited(solution.getSeguent_germa());
    }


    //TODO: es algo de cami, no em queda la posicio correctament per formar be l'array de cami
    public Solution backtrackingCamiFiable(Solution best, int i){

        if(casTrivial2())
            best = tracteSolucio2(best);

        else{

            for(solution.setSeguent_germa(0);
                solution.getSeguent_germa() < nodes_xarxa[i].getConnectsTo().size();
                solution.setSeguent_germa(solution.getSeguent_germa() + 1)){

                if (esPrometedora2(best)) {

                    int aux = solution.getSeguent_nivell();

                    solution.setCami(nodes_xarxa[i].getConnectsTo().get(solution.getSeguent_germa()).getTo());
                    solution.setCost(nodes_xarxa[i].getReliability());
                    solution.setVisited(solution.getSeguent_nivell(), true);
                    solution.setSeguent_nivell(nodes_xarxa[i].getConnectsTo().get(solution.getSeguent_germa()).getTo() - 1);

                    best = backtrackingCamiFiable(best, solution.getSeguent_nivell());

                    solution.getCami().remove(aux);

                }   //if
            }   //for
        }   //else

        return best;
    }

}
