import Element.Node.NodeXarxa;
import Element.Servidor;
import Element.Usuari;
import TracteJSON.TracteJSON;

import java.util.ArrayList;

/**
 *Test del programa InstaSalle
 *
 * @author Alexander Roca, Marc Cespedes
 * @version 15/11/2018 - 0.1
 */

public class Test {
    public static final String SERVERS = "jsons/servers.json";
    public static final String USERS = "jsons/users.json";
    public static final String NODES = "jsons/nodes.json";

    public static void main(String[] args){
        TracteJSON tracte_json = new TracteJSON();

        Servidor[] servidors = tracte_json.readJSONServers(SERVERS);

        System.out.println("S'ha llegit correctament \"servers.json\"");
    }
}