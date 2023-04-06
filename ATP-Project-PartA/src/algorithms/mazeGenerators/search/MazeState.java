package algorithms.mazeGenerators.search;

import algorithms.mazeGenerators.Position;
import java.util.List;
import java.awt.*;
import java.util.Locale;

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
