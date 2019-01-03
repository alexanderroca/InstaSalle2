import Element.Node.NodeXarxa;
import Element.Servidor;
import Element.Usuari;
import EstructuresCombinatories.BackTracking;
import EstructuresCombinatories.Greedy;
import EstructuresCombinatories.Solution;
import TracteJSON.TracteJSON;

/**
 *Main del programa InstaSalle
 *
 * @author Alexander Roca, Marc Cespedes
 * @version 15/11/2018 - 0.1
 */

public class Main {

    public static void main(String[] args){
        final String USERS_PATH = "jsons/users.json";
        final String SERVERS_PATH = "jsons/servers.json";
        final String NODE_PATH = "jsons/nodes.json";

        TracteJSON tracteJSON = new TracteJSON();

        Usuari[] usuaris = tracteJSON.readJSONUsers(USERS_PATH);
        Servidor[] servidors = tracteJSON.readJSONServers(SERVERS_PATH);
        NodeXarxa[] nodes_xarxa = tracteJSON.readJSONNodes(NODE_PATH);

        /*
        Greedy greedy = new Greedy(usuaris, servidors);

        Solution solution = greedy.greedy();

        solution.setSeguent_nivell(0);
        solution.setSeguent_germa(0);

        BackTracking backTracking = new BackTracking(usuaris, servidors);
        solution = backTracking.backtrackingDistribucio(solution);
        */

        BackTracking backTracking2 = new BackTracking(nodes_xarxa, 1, 4);
        Solution solution2 = new Solution(1, 4, nodes_xarxa);
        solution2.setCost(9999);

        solution2 = backTracking2.backtrackingCamiFiable(solution2);

        System.out.println("Gracies per usar el nostre programa!");
    }
}