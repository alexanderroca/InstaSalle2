package Metode;

import Element.Node.NodeXarxa;
import Element.Servidor;
import java.util.ArrayList;


public class BranchBound {

    private Servidor[] servidors;
    private NodeXarxa[] nodes;
    private double bound_anterior;
    private Servidor first;
    private Servidor last;
    private boolean[] passat;

    public BranchBound(Servidor[] servidors, NodeXarxa[] nodes, Servidor first, Servidor last/*, Connectat conectat*/) {
        this.servidors = servidors;
        this.nodes = nodes;
        this.bound_anterior = 1;
        this.first = first;
        this.last = last;
        this.passat = new boolean[servidors.length];
        for (int i = 0; i < servidors.length; i++) {
            passat[i] = false;
        }
    }

    public Solution Branch() {
        Solution x, best = new Solution();
        ArrayList<Solution> options, live_nodes = new ArrayList<>();

        // Estem en el cas en que busquem el maxim fiable
        x = init_root_node();
        best.setBound(0);
        live_nodes.add(x);

        while (!isEmpty(live_nodes)){
            x = live_nodes.get(0);
            live_nodes.remove(0);
            if(live_nodes.size() > 0) {
                for (int j = 0; j < live_nodes.size() -1; j++) {
                    live_nodes.set(j, live_nodes.get(j + 1));
                }
            }
            options = expand(x);

            for (int i = 0; i < options.size(); i++) {
                if(is_solution(options.get(i), last)){
                    best = max(best, options.get(i));
                }else{
                    if (is_promising(best, options.get(i))){
                        marcaServidor(options.get(i));
                        live_nodes.add(options.get(i));
                    }
                }
            }
        }
        return best;
    }

    public void marcaServidor(Solution solution){
        for (int i = 0; i < servidors.length; i++) {
            if(solution.getBound()/ bound_anterior == nodes[servidors[i].getReachable_from() - 1].getReliability() ){
                first = servidors[i];
                passat[i] = true;
            }
        }
    }

    public Solution init_root_node(){
        Solution x = new Solution();

        x.getPath().add(first);
        x.setBound(nodes[first.getReachable_from() - 1].getReliability());
        marcaServidor(x);

        return x;
    }

    public boolean isEmpty(ArrayList<Solution> live_nodes){
        return live_nodes.size() == 0;
    }

    public ArrayList<Solution> expand(Solution x){
        ArrayList<Solution> options = new ArrayList<>();
        Solution s;
        int node_ant = 0;

        for (int i = 0; i < nodes[first.getReachable_from()-1].getConnectsTo().size(); i++) {
            for (int j = 0; j < servidors.length; j++) {
                if(first.getReachable_from() == last.getReachable_from()){
                    s = reset(x);
                    s.setLevel(1);
                    s.getPath().add(last);
                    bound_anterior = x.getBound();
                    s.setBound(nodes[last.getReachable_from()-1].getReliability());
                    options.add(s);
                }else{
                    if(nodes[first.getReachable_from()-1].getConnectsTo().get(i).getTo() == servidors[j].getReachable_from()){
                        if(!passat[j]){
                            s = reset(x);
                            s.setLevel(s.getLevel()+1);
                            s.getPath().add(servidors[j]);
                            bound_anterior = x.getBound();

                            if(servidors[j].getReachable_from() != node_ant){
                                s.setBound(s.getBound() * nodes[servidors[j].getReachable_from()-1].getReliability());
                            }
                            node_ant = servidors[j].getReachable_from();
                            options.add(s);
                        }
                    }
                }
            }
        }
        return options;
    }


    public Solution reset(Solution solution){
        Solution s = new Solution();

        s.setLevel(solution.getLevel());
        s.setPath( (ArrayList<Servidor>) solution.getPath().clone());
        s.setBound(solution.getBound());

        return s;
    }

    // Plantejar bé el que s'ha de fer
    public Boolean is_solution(Solution option, Servidor last){
        return option.getPath().get(option.getPath().size() -1).getId() == last.getId();
    }

    // Plantejar bé el que s'ha de fer
    public Boolean is_promising(Solution best, Solution option){
       return option.getBound() > best.getBound();
    }

    public Solution max(Solution best, Solution option){
        if(best.getBound() < option.getBound()){
            return option;
        }else{
            return best;
        }
    }
}
