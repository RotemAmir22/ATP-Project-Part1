package algorithms.mazeGenerators;

public interface IMazeGenerator {

    Maze generate(int rows, int colums);
    long measureAlgorithmTimeMillis(int row, int coloums);
}
