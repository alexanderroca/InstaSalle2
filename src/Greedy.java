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

        while(still_candidates_to_check(candidates, i)){

            int possible_candidat = extreu_millor_candidat(i);
            add(possible_candidat, i);

            i++;
        }   //while

        return solution;
    }

    public boolean still_candidates_to_check(Usuari[] usuaris, int i){
        return i > usuaris.length - 1;
    }

    public int extreu_millor_candidat(int i){
        int millor_candidat = -1;
        double[] distancies_User_Sever = new double[servidors.length];


        for(int j = 0; j < servidors.length; j++){
            double longitudO = (double) candidates[i].getPosts().get(candidates[i].getPosts().size() - 1).getLocation().get(1);
            double latitudO = (double) candidates[i].getPosts().get(candidates[i].getPosts().size() - 1).getLocation().get(0);
            double longitudF = (double) servidors[j].getLocation().get(1);
            double latitudF = (double) servidors[j].getLocation().get(0);

            distancies_User_Sever[j] = candidates[i].calculaDistancia(latitudO, longitudO, latitudF, longitudF);
        }   //for

        for(int j = 0; j < servidors.length - 1; j++){

            if(millorOpcio(j, i, distancies_User_Sever)){
                millor_candidat = j;
            }//if
        }   //for

        return millor_candidat;
    }

    public boolean millorOpcio(int j, int i, double[] distancies_User_Sever){
        boolean resultat = false;

        if(solution.getActivitats()[j].getActivity() == null){

            resultat = true;
            solution.setUsuaris(i, extractDistanciaMinima(distancies_User_Sever, i));
        }   //if
        else{

            if(solution.getActivitats()[j].getActivity().contains(candidates[i].getActivity())){

                resultat = true;
                solution.setUsuaris(i, extractDistanciaMinima(distancies_User_Sever, i));
            }   //if
        }   //else

        return resultat;
    }

    public int extractDistanciaMinima(double[] distancies_User_Sever, int k){
        double minim = distancies_User_Sever[0];
        int servidor = 0;

        for(int i = 1; i < distancies_User_Sever.length - 1; i++){

            if(minim > distancies_User_Sever[i]){
                minim = distancies_User_Sever[i];
                servidor = i;
            }   //if
        }   //for

        solution.getActivitats()[k].getActivity().add(minim);

        return servidor;
    }

    public void add(int afegir_candidat, int usuari){
        solution.setUsuaris(afegir_candidat, usuari);
    }
}
