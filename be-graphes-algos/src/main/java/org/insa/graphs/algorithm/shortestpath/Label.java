package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Arc;


public class Label implements Comparable<Label>{

    public Label(Node n, Arc a){
        this.currentNode=n;
        this.currentValueShortestPath=Double.MAX_VALUE;
        this.previousArc=a;
        this.minCostIsKnown=false;
        this.estimatedCost = Double.MAX_VALUE;
    }

    public Label(Node n, Arc a, double estimatedCost){
        this.currentNode=n;
        this.currentValueShortestPath=Double.MAX_VALUE;
        this.previousArc=a;
        this.minCostIsKnown=false;
        this.estimatedCost = estimatedCost;
    }
    //sommet associé à ce label
    private Node currentNode;

    //marque : vrai si coût min de ce sommet est définitivement connu
    private boolean minCostIsKnown;

    //coût réalisé
    private double currentValueShortestPath;

    //arc venant du prédécesseur qui a le coût le plus faible
    private Arc previousArc;

    private double estimatedCost;

    public double getEstimated(){
        return this.estimatedCost;
    }

    public void setEstimated(int estimatedCost){
        this.estimatedCost = estimatedCost;
    } 

    public Node getNode(){
        return this.currentNode;
    }

    public boolean getMinCostIsKnown(){
        return this.minCostIsKnown;
    }

    //coût réalisé (valeur courante du plus court chemin depuis l'origine vers le sommet)
    public double getCurrentValueShortestPath(){
        return this.currentValueShortestPath;
    }

    public Arc getPreviousArc(){
        return this.previousArc;
    }

    public Arc setPreviousArc(Arc a){
        this.previousArc = a;
        return this.previousArc; //TO DO?
    }

    public double getCost(){
        return this.currentValueShortestPath;
    }

    public double getTotalCost(){
        return this.currentValueShortestPath;
    }

    public void setMinCostIsKnown(){
        this.minCostIsKnown= true;
    }

    public void setCurrentValueShortestPath(double newValue){
        this.currentValueShortestPath = newValue;
    }

    @Override
    public int compareTo(Label o) {
        return Double.compare(this.getTotalCost(),o.getTotalCost());
    }

}


