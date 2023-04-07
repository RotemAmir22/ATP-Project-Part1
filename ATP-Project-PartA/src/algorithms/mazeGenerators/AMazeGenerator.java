package algorithms.mazeGenerators;

import java.util.Random;

public abstract class AMazeGenerator implements IMazeGenerator {

    public long measureAlgorithmTimeMillis(int row, int coloums)
    {
        long start =  System.currentTimeMillis();
        this.generate(row, coloums);
        long end = System.currentTimeMillis();
        return end - start;
    }

    /**
     * creates one path from start to end in maze
     * @param maze: maze to edit
     */
    public void createPath(Maze maze)
    {
        Random random = new Random();
        int startRow = 0;
        int startCol = 0;
        int endRow = maze.getRows() - 1;
        int endCol = maze.getColoums() - 1;

        // Create a single path from start to end
        while (startRow != endRow || startCol != endCol) {
            // Move towards the end goal
            if (startRow < endRow && (startCol == endCol || random.nextBoolean())) {
                startRow++;
            } else if (startCol < endCol && (startRow == endRow || random.nextBoolean())) {
                startCol++;
            } else if (startRow > endRow && (startCol == endCol || random.nextBoolean())) {
                startRow--;
            } else if (startCol > endCol && (startRow == endRow || random.nextBoolean())) {
                startCol--;
            }

            // Carve out a path in the maze
            maze.setCellInMaze(startRow,startCol,0);
        }
    }
}
