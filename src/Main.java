import Element.Servidor;
import Element.Usuari;
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

        TracteJSON tracteJSON = new TracteJSON();

        Usuari[] usuaris = tracteJSON.readJSONUsers(USERS_PATH);
        Servidor[] servidors = tracteJSON.readJSONServers(SERVERS_PATH);

        Greedy greedy = new Greedy();

        greedy.greedy(usuaris);
    }
}