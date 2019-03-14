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

                solution.setUsuaris(solution.getSeguent_nivell(), solution.getSeguent_germa());
                solution.setActivitats(candidates[solution.getSeguent_nivell()].getActivity() , solution.getSeguent_germa());

                solution.setSeguent_nivell(solution.getSeguent_nivell() + 1);
                best = backtrackingDistribucio(best);

                solution.setSeguent_nivell(solution.getSeguent_nivell() - 1);


            }   //for
        }   //else

        return best;
    }

    public boolean casTrivial(){
        return solution.getSeguent_nivell() == solution.getUsuaris().length;
    }

    public boolean casTrivial2(){

        boolean trobat = false;

        if(solution.getFrom_node() == solution.getTo_node()) {
            trobat = true;
            solution.setCost(9999);
        }   //if

        else {
            if(solution.getCami().size() > 0) {
                if (solution.getTo_node() == solution.getSeguent_nivell())
                    trobat = true;
            }   //if
        }   //else

        return trobat;
    }

    public Solution tracteSolucio(Solution best){

        double equitivitat_best = best.getMax() - best.getMin();
        double equitivitat_solution = solution.getMax() - solution.getMin();

        if(equitivitat_best > equitivitat_solution) {
            best = cloneSolution2(solution);
            best.setTolerancia(equitivitat_solution);
        }   //if

        return best;
    }

    public Solution tracteSolucio2(Solution best){

        solution.setCost(nodes_xarxa[solution.getTo_node()].getReliability());
        solution.getCami().add(nodes_xarxa[solution.getSeguent_nivell()].getId());
        solution.setVisited(solution.getSeguent_nivell(), true);

        if(solution.getCost() > best.getCost())
            best = cloneSolution(solution);

        return best;
    }

    public Solution tracteSolucio3(Solution best){
        solution.getCami().add(nodes_xarxa[solution.getSeguent_nivell()].getId());
        solution.setVisited(solution.getSeguent_nivell(), true);

        if(solution.getCost() < best.getCost())
            best = cloneSolution(solution);

        return best;
    }

    public boolean esPrometedora2(){

        return !solution.getVisited(nodes_xarxa[solution.getSeguent_nivell()].getConnectsTo().get(solution.getSeguent_germa()).getTo() - 1);
    }

    public Solution cloneSolution2(Solution solution){
        Solution aux = new Solution(candidates, servidors);

        aux.setSeguent_germa(solution.getSeguent_germa());
        aux.setSeguent_nivell(solution.getSeguent_nivell());

        for(int i = 0; i < solution.getActivitats().length; i++){
            aux.getActivitats()[i] = solution.getActivitats()[i];
        }   //for

        for (int i = 0; i < solution.getUsuaris().length; i++){
            aux.getUsuaris()[i] = solution.getUsuaris()[i];
        }   //for

        return solution;
    }

    public Solution cloneSolution(Solution solution){
        Solution aux = new Solution(solution.getVisited().length);

        aux.setFrom_node(solution.getFrom_node());
        aux.setTo_node(solution.getTo_node());
        aux.setSeguent_germa(solution.getSeguent_germa());
        aux.setSeguent_nivell(solution.getSeguent_nivell());
        aux.setCost(solution.getCost());

        for(int i = 0; i < solution.getVisited().length; i++){
            aux.setVisited(i, solution.getVisited(i));
        }   //for

        for(int i = 0; i < solution.getCami().size(); i++){
            aux.getCami().add(solution.getCami().get(i));
        }   //for

        return aux;
    }

    public Solution backtrackingCamiFiable(Solution best){

        if(casTrivial2())
            best = tracteSolucio2(best);

        else{

            for(solution.setSeguent_germa(0);
                solution.getSeguent_germa() < nodes_xarxa[solution.getSeguent_nivell()].getConnectsTo().size();
                solution.setSeguent_germa(solution.getSeguent_germa() + 1)){

                if (esPrometedora2()) {

                    Solution aux = cloneSolution(solution);

                    solution.getCami().add(nodes_xarxa[solution.getSeguent_nivell()].getId());
                    solution.setCost(nodes_xarxa[solution.getSeguent_nivell()].getReliability());
                    solution.setSeguent_nivell(nodes_xarxa[nodes_xarxa[solution.getSeguent_nivell()].getId() - 1]
                            .getConnectsTo().get(solution.getSeguent_germa()).getTo() - 1);
                    solution.setVisited(solution.getSeguent_nivell(), true);

                    best = backtrackingCamiFiable(best);

                    solution = cloneSolution(aux);
                }   //if
            }   //for
        }   //else

        return best;
    }

    public Solution backtrackingCamiMinimCost(Solution best){

        if(casTrivial2())
            best = tracteSolucio3(best);

        else{

            for(solution.setSeguent_germa(0);
                solution.getSeguent_germa() < nodes_xarxa[solution.getSeguent_nivell()].getConnectsTo().size();
                solution.setSeguent_germa(solution.getSeguent_germa() + 1)){

                if (esPrometedora2()) {

                    Solution aux = cloneSolution(solution);

                    solution.getCami().add(nodes_xarxa[solution.getSeguent_nivell()].getId());
                    solution.setCost2(nodes_xarxa[solution.getSeguent_nivell()].getConnectsTo().get(solution.getSeguent_germa()).getCost());
                    solution.setSeguent_nivell(nodes_xarxa[nodes_xarxa[solution.getSeguent_nivell()].getId() - 1]
                            .getConnectsTo().get(solution.getSeguent_germa()).getTo() - 1);
                    solution.setVisited(solution.getSeguent_nivell(), true);

                    best = backtrackingCamiMinimCost(best);

                    solution = cloneSolution(aux);
                }   //if
            }   //for
        }   //else

        return best;
    }

}
