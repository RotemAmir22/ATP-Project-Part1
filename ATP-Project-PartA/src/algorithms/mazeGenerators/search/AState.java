package algorithms.mazeGenerators.search;

import algorithms.mazeGenerators.Position;

import java.awt.*;
import java.util.LinkedList;

public abstract class AState {

    protected Position node;
    protected AState parent;
    protected Color color;
    protected double dist;
    protected int value;
    protected int cost;
    protected LinkedList<AState> neighbors;

    public AState(Position node, int value)
    {
        this.node = node;
        this.value = value;
        neighbors = new LinkedList<>();
    }


    public Position getState(){return this.node;}
    public int getValue(){return this.value;}
    public AState getParent(){return this.parent;}
    public Color getColor(){return this.color;}
    public double getDist(){return this.dist;}
    public LinkedList<AState> getNeighbors(){return this.neighbors;}

    public void addToNeighbors(AState state)
    {
        neighbors.add(state);
    }

    public void removeFromNeighbors(AState state)
    {
        neighbors.remove(state);
    }
    public void setDist(double d)
    {
        this.dist = d;
    }

    public void setColor(Color c)
    {
        this.color = c;
    }
    public void setParent(AState p)
    {
        this.parent = p;
    }
    public void setCost()
    {
        // check if its diagonal
        if(this.parent != null && Math.abs(this.parent.getState().getRowIndex() - this.getState().getRowIndex()) == 1 && Math.abs(this.parent.getState().getColumnIndex() - this.getState().getColumnIndex()) == 1)
            this.cost = 15;
        else
            this.cost = 10;


    }
    public int getCost(){return this.cost;}
    @Override
    public String toString() {
        return "AState{" +
                "node=" + node +
                '}';
    }
}
