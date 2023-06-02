package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Arc;

public class LabelStar extends Label{

    private double coutEstimeDestination;
    private Node destination;


    public LabelStar(Node n, Arc a, Node destination){
        super(n, a);
        this.destination =destination;
        this.coutEstimeDestination=n.getPoint().distanceTo(this.destination.getPoint());
    }
    

    public double getTotalCost(){ //A réimplémenter par rapport à label
        return this.getCost() + this.coutEstimeDestination;
    }
    
    
}