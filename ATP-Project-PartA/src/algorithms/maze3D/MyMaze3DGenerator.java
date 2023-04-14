package algorithms.maze3D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;

public class MyMaze3DGenerator extends AMaze3DGenerator{

    private Maze3D maze3D;
    private Random rand;

    @Override
    public Maze3D generate(int depth, int row, int column) {
        if(depth <=0 || row <= 0 || column <= 0)
            return null;
        maze3D = new Maze3D(depth, row, column);
        Maze3D.setAllMazeToWalls(maze3D);
        //set end point
        maze3D.setCellInMaze(0, row - 1, column - 1, 0);
        rand = new Random();
        generateIterative(0,0,0);
        maze3D.setCellInMaze(0,row-2,column-1,0);
        return maze3D;
    }

    /**
     * this is based on DFS
     */
    public void generateIterative(int depth, int row, int column) {
        Stack<Position3D> stack = new Stack<>();
        stack.push(new Position3D(depth, row, column));
        while (!stack.isEmpty()) {
            Position3D current = stack.pop();
            if (maze3D.frame[current.getDepthIndex()][current.getRowIndex()][current.getColumnIndex()] == 1) {
                maze3D.setCellInMaze(current.getDepthIndex(), current.getRowIndex(), current.getColumnIndex(), 0);
                ArrayList<Position3D> neighbors = getNeighbors(current.getDepthIndex(), current.getRowIndex(), current.getColumnIndex());
                for (Position3D neighbor : neighbors) {
                    if (maze3D.frame[neighbor.getDepthIndex()][neighbor.getRowIndex()][neighbor.getColumnIndex()] == 1) {
                        maze3D.setCellInMaze((current.getDepthIndex() + neighbor.getDepthIndex()) / 2, (current.getRowIndex() + neighbor.getRowIndex()) / 2, (current.getColumnIndex() + neighbor.getColumnIndex()) / 2, 0);
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    /**
     * calculates the neighbours of the cell with one cell between as a "divider"
     */
    private ArrayList<Position3D> getNeighbors(int z, int x, int y) {
        ArrayList<Position3D> neighbors = new ArrayList<>();
        if (z > 1 && maze3D.frame[z -2][x][y] == 1)
            neighbors.add(new Position3D(z-2, x, y));
        if (x > 1 && maze3D.frame[z][x-2][y] == 1)
            neighbors.add(new Position3D(z,x-2,y));
        if (y > 1 && maze3D.frame[z][x][y-2]== 1)
            neighbors.add(new Position3D(z, x,y - 2));
        if (z < maze3D.depth - 2 && maze3D.frame[z+2][x][y] == 1)
            neighbors.add(new Position3D(z + 2, x, y));
        if (x < maze3D.rows - 2 && maze3D.frame[z][x+2][y] == 1)
            neighbors.add(new Position3D(z, x + 2, y));
        if (y < maze3D.columns - 2 && maze3D.frame[z][x][y+2] == 1)
            neighbors.add(new Position3D(z, x, y + 2));
        return neighbors;
    }
}
