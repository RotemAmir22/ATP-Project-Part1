package algorithms.mazeGenerators;

public interface IMazeGenerator {

    public Maze generate(int rows, int colums);
    public long measureAlgorithmTimeMillis(int row, int coloums);
}
