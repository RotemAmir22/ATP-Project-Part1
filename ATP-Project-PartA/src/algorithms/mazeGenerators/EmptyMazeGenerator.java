package algorithms.mazeGenerators;

public class EmptyMazeGenerator extends AMazeGenerator{


    @Override
    public Maze generate(int rows, int colums) {
        return new Maze(rows, colums);
    }
}
