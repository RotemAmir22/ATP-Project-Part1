package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * A specific state at the maze
 * include the current position (node), parent (pai), color(white, grey or black) and dist.
 */
public class MazeState extends AState{

    private Position position;

    public MazeState(Position position, int value)
    {
        super(value);
        this.position = position;

    }

    public Position getPosition()
    {
        return this.position;
    }

    public String toString() {
        return "AState{" +
                "node=" + this.position +
                '}';
    }








}
