package algorithms.search;

import algorithms.mazeGenerators.Position;

import java.awt.*;
import java.util.LinkedList;

public abstract class AState {

    protected AState parent;
    protected Color color;
    protected int value;
    protected int cost;
    protected LinkedList<AState> neighbors;

    public AState(int value)
    {
        this.value = value;
        this.cost = 0;
        neighbors = new LinkedList<>();
    }

    public int getValue(){return this.value;}
    public AState getParent(){return this.parent;}
    public Color getColor(){return this.color;}

    public LinkedList<AState> getNeighbors(){return this.neighbors;}

    public void addToNeighbors(AState state)
    {
        neighbors.add(state);
    }

    public void removeFromNeighbors(AState state)
    {
        neighbors.remove(state);
    }

    public void setColor(Color c)
    {
        this.color = c;
    }
    public void setParent(AState p)
    {
        this.parent = p;
    }
    public void setCost(int cost)
    {
        this.cost = cost;
    }
    public int getCost(){return this.cost;}

}
