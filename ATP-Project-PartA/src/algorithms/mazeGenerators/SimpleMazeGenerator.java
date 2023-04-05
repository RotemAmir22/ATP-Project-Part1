package algorithms.mazeGenerators;
import java.util.*;

public class SimpleMazeGenerator extends AMazeGenerator{


    /**
     * crates a simple maze that has at least one route from start to end
     * @param rows: number of rows in maze
     * @param colums: number of columns in maze
     * @return: maze built
     */
    @Override
    public Maze generate(int rows, int colums) {

        Maze maze = new Maze(rows, colums);
        Maze.setAllMazeToWalls(maze);
        createPath(maze, 0, 0); // start
        randomCellsToZero(createListOfWalls(maze), maze);
        return maze;
    }

    /**
     * creates one path from start to end in maze
     * @param maze: maze to edit
     * @param rows: number of rows in maze
     * @param colums: number of columns in maze
     */
    public void createPath(Maze maze, int rows, int colums)
    {
        // check if reached end point
        if(rows == maze.getRows() - 1 && colums == maze.getColoums() - 1)
            return;

       maze.setCellInMaze(rows, colums, 0); // Make this cell open in the path

        Character[] directions = {'L', 'R', 'U', 'D'};
        Collections.shuffle(Arrays.asList(directions));
        int newR = rows, newC = colums;

        //choose random direction for paths next step
        for (int direction : directions) {
            if(direction == 'L'){newC--;}
            else if(direction == 'R'){newC++;}
            else if(direction == 'U'){newR ++;}
            else {newR --;}

            if(!(0 <= newR && newR < maze.getRows() && 0 <= newC && newC < maze.getColoums()) || maze.getCellValue(newR, newC) == 0) {
                continue; // Skip if new cell is out of bounds or already part of the path
            }

            //update maze
            maze.setCellInMaze(newR, newC, 0);

            createPath(maze, newR, newC);
        }
    }

    /**
     * goes over a maze and adds to a list all the cells with walls
     * @param maze: maze to get details from
     * @return: list of positions (cells)
     */
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

    /**
     * all the cells that have walls in them, this function randomly chooses which will be set to 0
     * @param cells: list of positions that are walls
     * @param maze: updated maze
     */
    public void randomCellsToZero(List<Position>cells, Maze maze)
    {
        Collections.shuffle(cells);
        int i = 0;
        for (int row = 0; row < maze.getRows(); row++) {
            for (int col = 0; col < maze.getColoums(); col++) {
                if (maze.getCellValue(row, col) == 1) {
                    Position cell = cells.get(i++);
                    maze.setCellInMaze(cell.getRowIndex(), cell.getColumnIndex(), 0);
                }
            }
        }
    }
}
