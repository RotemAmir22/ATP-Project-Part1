package algorithms.mazeGenerators;
import java.util.*;

public class SimpleMazeGenerator extends AMazeGenerator{


    @Override
    public Maze generate(int rows, int colums) {

        Maze maze = new Maze(rows, colums);
        Maze.setAllMazeToWalls(maze);
        createPath(maze, 0, 0); // start
        randomCellsToZero(createListOfWalls(maze), maze);
        return maze;
    }

    public void createPath(Maze maze, int rows, int colums)
    {
        maze.setCellInMaze(rows, colums, 0); // Make this cell open in the path

        Character[] directions = {'L', 'R', 'U', 'D'};
        Collections.shuffle(Arrays.asList(directions));
        int newR = rows, newC = colums;
        for (int direction : directions) {
            if(direction == 'L'){newC--;}
            else if(direction == 'R'){newC++;}
            else if(direction == 'U'){newR ++;}
            else {newR --;}

            if(!(0 <= newR && newR < maze.getRows() && 0 <= newC && newC < maze.getColoums()) || maze.getCellValue(newR, newC) == 0) {
                continue; // Skip if new cell is out of bounds or already part of the path
            }

            maze.setCellInMaze(newR, newC, 0);
            if(newR == maze.getRows() - 1 && newC == maze.getColoums() - 1)
                return;

            createPath(maze, newR, newC);
        }
    }

    public List<Position> createListOfWalls(Maze maze)
    {
        List<Position> cells = new ArrayList<Position>();
        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getColoums(); col++) {
                if (maze.getCellValue(row, col) == 1) {
                    cells.add(new Position(row, col));
                }
            }
        }
        return cells;
    }

    public void randomCellsToZero(List<Position>cells, Maze maze)
    {
        Collections.shuffle(cells);
        int i = 0;
        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getColoums(); col++) {
                if (maze.getCellValue(row, col) == 1) {
                    Position cell = cells.get(i++);
                    maze.setCellInMaze(cell.getRow(), cell.getColum(), 0);
                }
            }
        }
    }
}
