package org.insa.graphs.algorithm.shortestpath;
//import java.nio.file.Path;

import java.util.ArrayList;
import java.util.List;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;


public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    // Retrouver le graph.
    Graph graph = data.getGraph();

    //structure pour obtenir le sommet de plus court chemin
    //ne contient que le sommet d'origine au début
    BinaryHeap <Node> tas = new BinaryHeap<Node>(null);

    

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        Label [] labelArray = new Label[graph.size()];
        Label labelCourant;
        Node nodeCourant;
        //initialisation :
        for(Node n : graph.getNodes()){
            //associe chaque sommet à un label dont le coût min 
            //est inconnu, dont le coût est infni
            Label l = new Label(n, null);
            //on range les labels dans un tableu de labels rangés selon leur id (voir classe Node)
            labelArray[n.getId()]=l;
            
        }
        labelArray[0].setCurrentValueShortestPath(0);
        tas.insert(data.getOrigin());
        labelCourant = labelArray[0];
        boolean existNotMarked = true;
        boolean wasChanged;
        //itérations
        //tas.size()<graph.size()
            while (existNotMarked){ //tant qu'il existe des sommets non marqués (dont le plus petit cout est connu)
                //sélectionner le sommet de coût le plus faible et marquer ce sommet comme visité
                
                nodeCourant = labelCourant.getNode();
                labelCourant.setMinCostIsKnown();
                Node nodeSuccess = new Node(0, null);
                for(Arc a : nodeCourant.getSuccessors()){
                    wasChanged = false;
                    nodeSuccess = a.getDestination();
                    if(!(labelArray[nodeSuccess.getId()].getMinCostIsKnown())){
                        if(labelArray[nodeSuccess.getId()].getCurrentValueShortestPath() > labelArray[nodeCourant.getId()].getCurrentValueShortestPath() + a.getLength()){
                            labelArray[nodeSuccess.getId()].setCurrentValueShortestPath(labelArray[nodeCourant.getId()].getCurrentValueShortestPath() + a.getLength());
                            wasChanged = true;
                        }
                        if(wasChanged){
                            tas.insert(nodeSuccess);
                            labelArray[nodeSuccess.getId()].setPreviousArc(a);
                        }
                    }
                }
                existNotMarked = false;
                //for trouver label de cout minimum
                double temp =Double.POSITIVE_INFINITY;
                for(Label l : labelArray){
                    if(!(l.getMinCostIsKnown())){
                        existNotMarked = true;
                    }
                    if(temp>l.getCost()){
                        temp=l.getCost();
                        labelCourant=l;
                    }
                    
                }
                

            }
            //on ajoute chacun des arcs constituant le plus court chemin dans le chemin solution
            List<Arc> listeArcs = new ArrayList<>();
            Node current = labelArray[0].getNode();
            for(Label l : labelArray){
                if(l.getPreviousArc().getOrigin()==current){
                    listeArcs.add(l.getPreviousArc());
                }
                //actualiser le sommet courant
                current=l.getNode();
            }
            Path shortestPathDijk = new Path(graph,listeArcs);
            
            solution = new ShortestPathSolution(data, Status.OPTIMAL,shortestPathDijk);
            //OPTIMAL

        //arrêt lorsque tous les sommets sont marqués et retourner la solution

        return solution;
    }

}
