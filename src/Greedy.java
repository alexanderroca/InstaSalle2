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
            solution = add(possible_candidat);

            i++;
        }   //while

        return solution;
    }

    public boolean still_candidates_to_check(Usuari[] usuaris, int i){
        return i > usuaris.length - 1;
    }

    public int extreu_millor_candidat(int i){
        int millor_candidat;
        double[] distancies_User_Sever = new double[servidors.length];

        for(int j = 0; j < servidors.length; j++){
            distancies_User_Sever[j] = /*TODO: determinar la distancia entre Usuari i cada Servidor*/
        }   //for

        for(int j = 0; j < servidors.length - 1; j++){

            if(millorOpcio(j, i, distancies_User_Sever)){

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

            if(){

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

    public Solution add(int afegir_candidat){

    }
}
