package search;

import algorithms.mazeGenerators.Position;

/**
 * A specific state at the maze
 * include the current position (node), parent (pai), color(white, grey or black) and dist.
 */
public class MazeState extends AState{


    public MazeState(Position node, int value)
    {
        super(node, value);
    }






}
