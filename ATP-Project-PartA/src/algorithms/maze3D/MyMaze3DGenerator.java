package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MyMaze3DGenerator extends AMaze3DGenerator{
    @Override
    public Maze3D generate(int depth, int row, int column) {
        if(depth <=0 || row <= 0 || column <= 0)
            return null;
        Maze3D maze3D = new Maze3D(depth, row, column);
        Maze3D.setAllMazeToWalls(maze3D);
        //set start point
        maze3D.setCellInMaze(0,0,0,0);
        //set end point
        maze3D.setCellInMaze(0,maze3D.getRows() -1, maze3D.getColumns()-1,0);

        ArrayList<Position3D> neighbors = new ArrayList<>();
        neighbors.add(maze3D.getStartPosition());

        while (!neighbors.isEmpty()) {
            // pick current node at random
            Random random = new Random();
            Position3D currentP = neighbors.remove(random.nextInt(neighbors.size()));
            setNeighbors(maze3D, neighbors, currentP);
        }
        createPath(maze3D);
        return maze3D;
    }

    public void createPath(Maze3D maze3D) {
        Random random = new Random();
        int[] start = {0, 0, 0};
        int[] end = {0, maze3D.getRows() - 1, maze3D.getColumns() - 1};

        // Create a single path from start to end
        while (!Arrays.equals(start, end)) {
            int[] direction = {end[0] - start[0], end[1] - start[1], end[2] - start[2]};
            int randomIndex = random.nextInt(3);
            if (direction[randomIndex] > 0) {
                start[randomIndex]++;
            } else if (direction[randomIndex] < 0) {
                start[randomIndex]--;
            } else {
                start[randomIndex] += random.nextBoolean() ? 1 : -1;
            }
            maze3D.setCellInMaze(start[0], start[1], start[2], 0);
        }
    }


    private void setNeighbors(Maze3D maze3D, ArrayList<Position3D> neighbors, Position3D currentP) {

        int countVisited = 0;
        // Loop over all non-diagonal neighbors of (k, i, j)
        for(int z = currentP.getDepthIndex() - 1; z <= currentP.getDepthIndex() + 1; z++){
            for (int x = currentP.getRowIndex() - 1; x <= currentP.getRowIndex() + 1; x++){
                for (int y = currentP.getColumnIndex() - 1; y <= currentP.getColumnIndex() + 1; y++){
                    if (z >= 0 &&  z < maze3D.getDepth() && x >= 0 &&  x < maze3D.getRows() &&  y >= 0 && y < maze3D.getColumns()) //in bounds
                    {
                        if (z == currentP.getDepthIndex()) // same plain
                        {
                            if(!(x != currentP.getRowIndex() && y != currentP.getColumnIndex())) // not diagonal
                                if (maze3D.getCellValue(z,x,y) == 0)
                                    countVisited++;
                        }
                        else // different plain
                        {
                            if(x == currentP.getRowIndex() && y == currentP.getColumnIndex()) // IN or OUT
                                if (maze3D.getCellValue(z,x,y) == 0)
                                    countVisited++;
                        }
                    }
                }
            }
        }
        if (countVisited <2)
            visitedLessThan(maze3D, neighbors, currentP);
    }

    private boolean containPosition3D(ArrayList<Position3D> neighbors, int z, int x, int y)
    {
        for (Position3D neighbor : neighbors) {
            if (neighbor.getDepthIndex() == z && neighbor.getRowIndex() == x && neighbor.getColumnIndex() == y)
                return true;
        }
        return false;
    }

    private void visitedLessThan(Maze3D maze3D, ArrayList<Position3D> neighbors, Position3D currentP)
    {
        maze3D.setCellInMaze(currentP.getDepthIndex(), currentP.getRowIndex(), currentP.getColumnIndex(), 0);
        // Loop over all non-diagonal neighbors of (k, i, j)
        for(int z = currentP.getDepthIndex() - 1; z <= currentP.getDepthIndex() + 1; z++)
        {
            for (int x = currentP.getRowIndex() - 1; x <= currentP.getRowIndex() + 1; x++)
            {
                for (int y = currentP.getColumnIndex() - 1; y <= currentP.getColumnIndex() + 1; y++)
                {
                    if(z != currentP.getDepthIndex() || x != currentP.getRowIndex() || y != currentP.getColumnIndex())
                    {
                        if (z >= 0 &&  z < maze3D.getDepth() && x >= 0 &&  x < maze3D.getRows() &&  y >= 0 && y < maze3D.getColumns()) //in bounds
                        {
                            if (z == currentP.getDepthIndex()) // same plain
                            {
                                if(!(x != currentP.getRowIndex() && y != currentP.getColumnIndex())) // not diagonal
                                    if (!containPosition3D(neighbors, z, x, y) && (maze3D.getCellValue(z,x,y) == 1))
                                        neighbors.add(new Position3D( z, x, y));
                            }
                            else // different plain
                            {
                                if(x == currentP.getRowIndex() && y == currentP.getColumnIndex()) // IN or OUT
                                    if (!containPosition3D(neighbors, z, x, y) && (maze3D.getCellValue(z,x,y) == 1))
                                        neighbors.add(new Position3D( z, x, y));
                            }
                        }
                    }
                }
            }
        }
    }
}
