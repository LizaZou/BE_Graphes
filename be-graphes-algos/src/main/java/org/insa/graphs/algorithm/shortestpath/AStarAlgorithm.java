package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;


public class AStarAlgorithm extends DijkstraAlgorithm {

    

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }

    final ShortestPathData data = getInputData();

    //On définit une fonction qui permet d'implémenter la seule chose qui change pour AStar :
    public Label createLabel(Node n, Arc a){
        Node d = data.getDestination();
        return new LabelStar(n, a, d);
     }

     //Rien d'autre ne change : le rangement des sommets (selon coût depuis origine + coût estimpé à la destination) est fait par LabelStar
    

}
