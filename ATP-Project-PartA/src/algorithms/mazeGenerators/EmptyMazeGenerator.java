package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{


    @Override
    public Maze generate(int rows, int colums)
    {
        if(rows <= 0 || colums <= 0)
            return null;
        return new Maze(rows, colums);
    }
}
