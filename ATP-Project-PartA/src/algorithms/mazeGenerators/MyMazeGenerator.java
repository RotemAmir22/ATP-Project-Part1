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
    public ArrayList<Position> getNeighbors(Position point, int rows, int colums)
    {
        ArrayList<Position> neighbors = new ArrayList<>();
        Position newP;
        if(point.getRow() + 1 < rows) // SOUTH
        {
            newP = new Position(point.getRow() + 1,colums);
            neighbors.add(newP);
        }
        if(point.getRow() - 1 > rows) // NORTH
        {
            newP = new Position(point.getRow() - 1,colums);
            neighbors.add(newP);
        }
        if(point.getColum() + 1 < rows) // EAST
        {
            newP = new Position(point.getColum() + 1,colums);
            neighbors.add(newP);
        }
        if(point.getColum() - 1 > rows) // WEST
        {
            newP = new Position(point.getColum() - 1,colums);
            neighbors.add(newP);
        }
        return neighbors;

    }
    @Override
    public Maze generate(int rows, int colums) {


        // build maze and initialize with only walls
        Maze maze = new Maze(rows, colums);
        Maze.setAllMazeToWalls(maze);
        Position point = new Position(0,0);
        ArrayList<Position> neighbors = getNeighbors(point, rows, colums);
        Position opposite = point;
        while (!neighbors.isEmpty()) {

            // pick current node at random
            Position currentP = neighbors.remove((int)(Math.random() * neighbors.size()));
            try {
                // if both current point and its opposite are walls
                if (maze.getCellValue(currentP.getRow(),currentP.getColum()) == 1) {
                    if (maze.getCellValue(opposite.getRow(),opposite.getColum()) == 1) {

                        // open path between the points
                        maze.setCellInMaze(currentP.getRow(),currentP.getColum(), 0);
                        maze.setCellInMaze(opposite.getRow(),opposite.getColum(), 0);

                        // iterate through direct neighbors of node, same as earlier
                        neighbors = getNeighbors()
                        for (int x = -1; x <= 1; x++)
                            for (int y = -1; y <= 1; y++) {
                                if (x == 0 && y == 0 || x != 0 && y != 0)
                                    continue;
                                try {
                                    if (maz[op.r + x][op.c + y] == '.') continue;
                                } catch (Exception e) {
                                    continue;
                                }
                                frontier.add(new Point(op.r + x, op.c + y, op));
                            }
                    }
                }
            } catch (Exception e) { // ignore NullPointer and ArrayIndexOutOfBounds
            }

            opposite = currentP;
        }

        // print final maze
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++)
                System.out.print(maz[i][j]);
            System.out.println();
        }


}

