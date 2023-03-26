package algorithms.mazeGenerators;

import java.util.Random;

public class SimpleMazeGenerator extends AMazeGenerator{
    @Override
    public Maze generate(int rows, int colums) {
        Maze maze = new Maze(rows, colums);

        return maze;
    }
}
