package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Arc;


public class LabelStar extends Label{
    
    public LabelStar(Node n, Arc a, double estimatedCost){
        super(n,a, estimatedCost);
    }
    public double getTotalCost(){
        return this.getCost()+this.getEstimated();
    }

}
