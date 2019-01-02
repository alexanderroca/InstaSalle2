package Graf;

import Element.Node.Connectat;
import Element.Node.NodeXarxa;
import Element.Servidor;

public class Graf {

    private Connectat[] connectat;
    private NodeXarxa[] node_xarxa;
    private Servidor[] servidors;


    public Graf(Connectat[] connectat, NodeXarxa[] node_xarxa, Servidor[] servidors) {
        this.connectat = connectat;
        this.node_xarxa = node_xarxa;
        this.servidors = servidors;


    }
}
