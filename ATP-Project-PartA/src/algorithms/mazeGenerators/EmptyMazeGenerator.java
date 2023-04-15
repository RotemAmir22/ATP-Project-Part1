package algorithms.mazeGenerators;

/**
 * This class extends AMazeGenerator
 * Implement "generate" for an empty maze
 */
public class EmptyMazeGenerator extends AMazeGenerator{


    /**
     * This function build an empty maze
     * @param rows of the maze
     * @param column of the maze
     * @return a new maze
     */
    @Override
    public Maze generate(int rows, int column)
    {
        if(rows <= 0 || column <= 0)
            return null;
        return new Maze(rows, column);
    }
}
