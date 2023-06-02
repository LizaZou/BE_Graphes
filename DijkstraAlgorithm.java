package org.insa.graphs.algorithm.shortestpath;
//import java.nio.file.Path;

import java.util.ArrayList;
import java.util.Collections;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;


public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    // Retrouver le graph.
    private Graph graph = data.getGraph();

    //structure pour obtenir le sommet de plus court chemin
    //ne contient que le sommet d'origine au début
    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    //On définit une fonction qui permet d'implémenter la seule chose qui change pour AStar :
    public Label createLabel(Node n, Arc a){
       return new Label(n, a);
    }
    

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        
        Label [] labelArray = new Label[graph.size()];
        BinaryHeap <Label> tas = new BinaryHeap<Label>();
        
        Label labelCourant;
        Node nodeCourant;
        //initialisation :
        for(Node n : graph.getNodes()){
            //associe chaque sommet à un label dont le coût min 

            /*est inconnu, dont le coût est infni                                       //Modifié pour l'algo AStar
            Label l = new Label(n, null);*/
            //à la place :
            Label l=createLabel(n, n.previousArc);

            //on range les labels dans un tableu de labels rangés selon leur id (voir classe Node)
            labelArray[n.getId()]=l;
        }
        tas.insert(labelArray[data.getOrigin().getId()]);
        labelArray[data.getOrigin().getId()].setCurrentValueShortestPath(0);
        //tas.insert(data.getOrigin());
        labelCourant = labelArray[data.getOrigin().getId()];
        // Notify observers about the first event (origin processed).
        notifyOriginProcessed(data.getOrigin());


        //itérations
        //tas.size()<graph.size()
            do{ //tant que le tas n'est pas vide
                //sélectionner le sommet de coût le plus faible et on l'extrait du tas
                labelCourant = tas.findMin();
                nodeCourant = labelCourant.getNode();
                labelCourant.setMinCostIsKnown();
                notifyNodeMarked(nodeCourant);
                tas.deleteMin();
                
                for(Arc a : nodeCourant.getSuccessors()){

                    if(!(labelArray[a.getDestination().getId()].getMinCostIsKnown())){

                        if(data.isAllowed(a)){
                            Label compare = createLabel(nodeCourant, labelCourant.getPreviousArc());
                            compare.setCurrentValueShortestPath(labelCourant.getCurrentValueShortestPath() + data.getCost(a));
                            if(labelArray[a.getDestination().getId()].compareTo(compare) >= 0 ){
                                labelArray[a.getDestination().getId()].setCurrentValueShortestPath(labelArray[nodeCourant.getId()].getCurrentValueShortestPath() + data.getCost(a));
                                notifyNodeReached(a.getDestination());
                                try {
                                    tas.remove(labelArray[a.getDestination().getId()]);
                                } catch (Exception exception){
                                    
                                }
                                tas.insert(labelArray[a.getDestination().getId()]);
                                labelArray[a.getDestination().getId()].setPreviousArc(a);
                            }
                        }
                    }
                }
            }while (!tas.isEmpty() && !labelArray[data.getDestination().getId()].getMinCostIsKnown());

            //on ajoute chacun des arcs constituant le plus court chemin dans le chemin solution
            /*List<Arc> listeArcs = new ArrayList<>();
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
            //OPTIMAL*/

            ShortestPathSolution solution = null;

        // Destination has no predecessor, the solution is infeasible...
        if (labelArray[data.getDestination().getId()].getPreviousArc() == null) {
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }
        else {

            // The destination has been found, notify the observers.
            //notifyDestinationReached(data.getDestination());

            // Create the path from the array of predecessors...
            ArrayList<Arc> arcs = new ArrayList<>();
            Arc arc = labelArray[data.getDestination().getId()].getPreviousArc();
            while (arc != null) {
                arcs.add(arc);
                arc = labelArray[arc.getOrigin().getId()].getPreviousArc();
            }

            // Reverse the path...
            Collections.reverse(arcs);

            // Create the final solution.
            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
        }

        //arrêt lorsque tous les sommets sont marqués et retourner la solution

        return solution;
    }

}
