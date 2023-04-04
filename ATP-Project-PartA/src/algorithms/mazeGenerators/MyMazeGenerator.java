package algorithms.mazeGenerators;
import java.util.*;
public class MyMazeGenerator extends AMazeGenerator {

    /**
     * Find the nearest neighbors of a specific point
     * @param point current position
     * @param rows weight
     * @param colums high
     * @return the neighbors
     */
    public void setNeighbors( ArrayList<Position> neighbors, Position point, int rows, int colums)
    {
        Position newP;
        if(point.getRowIndex() + 1 < rows) // SOUTH
        {
            newP = new Position(point.getRowIndex() + 1,point.getColumnIndex());
            neighbors.add(newP);
        }
        if(point.getRowIndex() - 1 >= 0) // NORTH
        {
            newP = new Position(point.getRowIndex() - 1,point.getColumnIndex());
            neighbors.add(newP);
        }
        if(point.getColumnIndex() + 1 < colums) // EAST
        {
            newP = new Position(point.getRowIndex(),point.getColumnIndex() + 1);
            neighbors.add(newP);
        }
        if(point.getColumnIndex() - 1 >= 0) // WEST
        {
            newP = new Position(point.getRowIndex(),point.getColumnIndex() - 1);
            neighbors.add(newP);
        }

    }
    @Override
    public Maze generate(int rows, int colums) {


        // build maze and initialize with only walls
        Maze maze = new Maze(rows, colums);
        Maze.setAllMazeToWalls(maze);
        Position point = new Position(0, 0);
        ArrayList<Position> neighbors = new ArrayList<>();
        setNeighbors(neighbors, point, rows, colums);
        Position predecessor = point; // start point
        while (!neighbors.isEmpty()) {

            // pick current node at random
            Position currentP = neighbors.remove((int) (Math.random() * neighbors.size()));
            if (maze.getCellValue(currentP.getRowIndex(), currentP.getColumnIndex()) == 1) {
                if (maze.getCellValue(predecessor.getRowIndex(), predecessor.getColumnIndex()) == 1) {

                    // open path between the points
                    maze.setCellInMaze(currentP.getRowIndex(), currentP.getColumnIndex(), 0);
                    maze.setCellInMaze(predecessor.getRowIndex(), predecessor.getColumnIndex(), 0);
                    // iterate through direct neighbors of point, same as earlier
                    setNeighbors(neighbors, predecessor, rows, colums);
                }
            }
            if(!neighbors.isEmpty())
                predecessor = neighbors.get((int) (Math.random() * neighbors.size()));
            else
                setNeighbors(neighbors, currentP, rows, colums);;
        }
        return maze;
    }


}

