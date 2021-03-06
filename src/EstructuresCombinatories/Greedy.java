package EstructuresCombinatories;

import Element.Node.NodeXarxa;
import Element.Servidor;
import Element.Usuari;
import EstructuresCombinatories.Solution;

public class Greedy {

    private Usuari[] candidates;
    private Servidor[] servidors;
    private NodeXarxa[] nodes_xarxa;
    private Solution solution;
    private int millor_node;
    private double millor_cost;

    public Greedy(Usuari[] candidates, Servidor[] servidors, NodeXarxa[] nodes_xarxa) {
        this.candidates = candidates;
        this.servidors = servidors;
        this.nodes_xarxa = nodes_xarxa;
    }

    public Solution greedyDistribucio(){
        solution = new Solution(candidates, servidors);

        while(still_candidates_to_check()){

            if(isFeasible())
                add();

            solution.setSeguent_nivell(solution.getSeguent_nivell() + 1);
        }   //while

        return solution;
    }

    public Solution greedyCamiFiable(int from_server, int to_server){
        solution = new Solution(from_server, to_server, nodes_xarxa, servidors);

        solution.getCami().add(solution.getFrom_node());
        solution.setVisited(solution.getFrom_node(), true);

        boolean found = false;

        while(!found){

            isFeasible2();

            if(isSolution())
                found = true;

            solution.setSeguent_germa(0);
        }   //while


        return solution;

    }

    public Solution greedyCamiMesCurt(int from_server, int to_server){
        solution = new Solution(from_server, to_server, nodes_xarxa, servidors);

        solution.getCami().add(solution.getFrom_node());
        solution.setVisited(solution.getFrom_node(), true);

        boolean found = false;

        while(!found){

            isFeasible3();

            if(isSolution())
                found = true;

            solution.setSeguent_germa(0);
        }   //while

        return solution;
    }

    public boolean isSolution(){
        return solution.getTo_node() == solution.getSeguent_nivell();
    }

    public boolean still_candidates_to_check(){
        return solution.getSeguent_nivell() < candidates.length;
    }

    public boolean isFeasible(){

        double[] aux = new double[servidors.length];

        //Determino les distancies
        for(solution.setSeguent_germa(0); solution.getSeguent_germa() < servidors.length;
            solution.setSeguent_germa(solution.getSeguent_germa() + 1)){

            double latitudO = (double) candidates[solution.getSeguent_nivell()].getPosts().
                    get(candidates[solution.getSeguent_nivell()].getPosts().size() - 1).getLocation().get(0);
            double longitudO = (double) candidates[solution.getSeguent_nivell()].getPosts().
                    get(candidates[solution.getSeguent_nivell()].getPosts().size() - 1).getLocation().get(1);
            double latitudF = (double) servidors[solution.getSeguent_germa()].getLocation().get(0);
            double longitudF = (double) servidors[solution.getSeguent_germa()].getLocation().get(1);

            aux[solution.getSeguent_germa()] =
                    Math.abs(candidates[solution.getSeguent_nivell()].calculaDistancia(latitudO, longitudO, latitudF, longitudF));

        }   //for

        int millor_servidor = 0;
        double millor_distancia = aux[0];

        //Miro l'equitivitat dels servidors, cosa que sera mes prioritari que les distancies
        for(solution.setSeguent_germa(1); solution.getSeguent_germa() < servidors.length;
            solution.setSeguent_germa(solution.getSeguent_germa() + 1)){

            if(millor_distancia > aux[solution.getSeguent_germa()] &&
                    solution.getActivitats(millor_servidor) >= solution.getActivitats(solution.getSeguent_germa())){

                millor_servidor = solution.getSeguent_germa();
                millor_distancia = aux[solution.getSeguent_germa()];
            }   //if
            else if(solution.getActivitats(millor_servidor) > solution.getActivitats(solution.getSeguent_germa())){

                millor_servidor = servidors[solution.getSeguent_germa()].getId();
                millor_distancia = aux[solution.getSeguent_germa()];
            }   //else-if

        }   //for

        solution.setSeguent_germa(millor_servidor);

        return true;
    }

    public void isFeasible2(){

        millor_cost = 99;
        millor_node = -1;

        for(solution.setSeguent_germa(0); solution.getSeguent_germa() < nodes_xarxa[solution.getSeguent_nivell()].getConnectsTo().size();
            solution.setSeguent_germa(solution.getSeguent_germa() + 1)){

            double aux_cost = solution.getCost() * nodes_xarxa[solution.getSeguent_germa()].getReliability();
            comparaMillor(aux_cost);
        }   //for

        solution.getCami().add(millor_node);
        solution.setSeguent_nivell(millor_node);
        solution.setVisited(millor_node, true);
        solution.setCost3(millor_cost);
    }

    public void isFeasible3() {
        millor_cost = 99;
        millor_node = -1;

        for(solution.setSeguent_germa(0); solution.getSeguent_germa() < nodes_xarxa[solution.getSeguent_nivell()].getConnectsTo().size();
            solution.setSeguent_germa(solution.getSeguent_germa() + 1)){

            double aux_cost = solution.getCost() + nodes_xarxa[solution.getSeguent_nivell()].getConnectsTo().get(solution.getSeguent_germa()).getCost();
            comparaMillor(aux_cost);
        }   //for

        solution.getCami().add(millor_node);
        solution.setSeguent_nivell(millor_node);
        solution.setVisited(millor_node, true);
        solution.setCost3(millor_cost);
    }

    public void comparaMillor(double aux_cost){
        if(!solution.getVisited()[nodes_xarxa[solution.getSeguent_nivell()].getConnectsTo().get(solution.getSeguent_germa()).getTo() - 1]){
            millor_cost += nodes_xarxa[solution.getSeguent_nivell()].getConnectsTo().get(solution.getSeguent_germa()).getCost();
            millor_node = nodes_xarxa[solution.getSeguent_nivell()].getConnectsTo().get(solution.getSeguent_germa()).getTo() - 1;
        }   //if
        else if((aux_cost < millor_cost && !solution.getVisited()[nodes_xarxa[solution.getSeguent_nivell()].getConnectsTo().get(solution.getSeguent_germa()).getTo() - 1])){
            millor_cost +=  nodes_xarxa[solution.getSeguent_nivell()].getConnectsTo().get(solution.getSeguent_germa()).getCost();
            millor_node = nodes_xarxa[solution.getSeguent_nivell()].getConnectsTo().get(solution.getSeguent_germa()).getTo() - 1;
        }   //else-if
    }

    public void add(){

        solution.setActivitats(candidates[solution.getSeguent_nivell()].getActivity() , solution.getSeguent_germa());
        solution.setUsuaris(solution.getSeguent_nivell(), solution.getSeguent_germa());
    }

}
