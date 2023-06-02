package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.Node;


public class AStarAlgorithm extends DijkstraAlgorithm {

    

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }

    //On définit une fonction qui permet d'implémenter la seule chose qui change pour AStar :
    public Label createLabel(Node n, Node d){
        return new LabelStar(n, null, d);
     }

     //Rien d'autre ne change : le rangement des sommets (selon coût depuis origine + coût estimpé à la destination) est fait par LabelStar
    

}
