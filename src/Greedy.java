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

        double[] aux = new double[servidors.length];

        for(solution.setSeguent_germa(0); solution.getSeguent_germa() < servidors.length;
            solution.setSeguent_germa(solution.getSeguent_germa() + 1)){

            double latitudO = (double) candidates[solution.getSeguent_nivell()].getPosts().
                    get(candidates[solution.getSeguent_nivell()].getPosts().size()).getLocation().get(0);
            double longitudO = (double) candidates[solution.getSeguent_nivell()].getPosts().
                    get(candidates[solution.getSeguent_nivell()].getPosts().size()).getLocation().get(1);
            double latitudF = (double) servidors[0].getLocation().get(0);
            double longitudF = (double) servidors[0].getLocation().get(1);

            aux[solution.getSeguent_germa()] =
                    Math.abs(candidates[solution.getSeguent_nivell()].calculaDistancia(latitudO, longitudO, latitudF, longitudF));

        }   //for

        return true;
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
