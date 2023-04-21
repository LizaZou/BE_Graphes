package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;

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
        //initialisation :
        for(Node n : graph.getNodes()){
            //associe chaque sommet à un label dont le coût min 
            //est inconnu, dont le coût est infni
            Label l = new Label(n, null);
            //on range les labels dans un tableu de labels rangés selon leur id (voir classe Node)
            Label [] labelArray = new Label[graph.size()];
            labelArray[n.getId()]=l;
        }
        tas.insert(data.getOrigin());

        //itérations
            while (){
                //sélectionner le sommet de coût le plus faible et marquer ce sommet comme visité

                //actualiser les coûts des sommets adjacents non marqués
            }
        

        //arrêt lorsque tous les sommets sont marqués et retourner la solution

        return solution;
    }

}
