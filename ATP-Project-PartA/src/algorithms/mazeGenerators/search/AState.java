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

    public AState(Position node, int value)
    {
        this.node = node;
        this.value = value;
    }
    protected LinkedList<AState> neighbors;

    public Position getState(){return this.node;}
    public int getValue(){return this.value;}
    public AState getParent(){return this.parent;}
    public Color getColor(){return this.color;}
    public double getDist(){return this.dist;}
    public LinkedList<AState> getNeighbors(){return this.neighbors;}

    public void addToNeighbors(MazeState state)
    {
        neighbors.add(state);
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


}
