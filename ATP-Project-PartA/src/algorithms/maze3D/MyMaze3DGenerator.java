package algorithms.maze3D;

import algorithms.mazeGenerators.Position;
import algorithms.search.AState;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
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
        //set start point

        //set end point
        maze3D.setCellInMaze(0,maze3D.getRows() -1, maze3D.getColumns()-1,0);
        ArrayList<Position3D> neighbors = new ArrayList<>();
        neighbors.add(maze3D.getStartPosition());
        maze3D.setCellInMaze(0, row - 1, column - 1, 0);
        rand = new Random();
        generateRecursive(0,0,0);
        return maze3D;
    }

    public void generateRecursive(int depth, int row, int column) {
        maze3D.setCellInMaze(depth, row, column, 0);
        ArrayList<Position3D> neighbors = getNeighbors(depth, row, column);
        while (!neighbors.isEmpty()) {
            Position3D neighbor = neighbors.remove(rand.nextInt(neighbors.size()));
            if (maze3D.getCellValue(neighbor.getDepthIndex(), neighbor.getRowIndex(), neighbor.getColumnIndex()) == 1) {
                maze3D.setCellInMaze((depth + neighbor.getDepthIndex()) / 2, (row + neighbor.getRowIndex()) / 2, (column + neighbor.getColumnIndex()) / 2, 0);
                generateRecursive(neighbor.getDepthIndex(), neighbor.getRowIndex(), neighbor.getColumnIndex());
            }
        }
    }


    private ArrayList<Position3D> getNeighbors(int z, int x, int y) {
        ArrayList<Position3D> neighbors = new ArrayList<>();
        if (z > 1 && maze3D.getCellValue(z -2 , x, y) == 1)
            neighbors.add(new Position3D(z-2, x, y));
        if (x > 1 && maze3D.getCellValue(z,x-2,y) == 1)
            neighbors.add(new Position3D(z,x-2,y));
        if (y > 1 && maze3D.getCellValue(z,x,y-2)== 1)
            neighbors.add(new Position3D(z, x,y - 2));
        if (z < maze3D.getDepth() - 2 && maze3D.getCellValue(z+2,x,y) == 1)
            neighbors.add(new Position3D(z + 2, x, y));
        if (x < maze3D.getRows() - 2 && maze3D.getCellValue(z,x+2,y) == 1)
            neighbors.add(new Position3D(z, x + 2, y));
        if (y < maze3D.getColumns() - 2 && maze3D.getCellValue(z,x,y+2) == 1)
            neighbors.add(new Position3D(z, x, y + 2));
        return neighbors;
    }
//        while (!neighbors.isEmpty()) {
//            // pick current node at random
//            Random random = new Random();
//            Position3D currentP = neighbors.remove(random.nextInt(neighbors.size()));
//            setNeighbors(maze3D, neighbors, currentP);
//        }
//        createPath(maze3D);
//        return maze3D;
//    }
//
//    public void createPath(Maze3D maze3D) {
//        Random random = new Random();
//        int[] start = {0, 0, 0};
//        int[] end = {0, maze3D.getRows() - 1, maze3D.getColumns() - 1};
//
//        // Create a single path from start to end
//        while (!Arrays.equals(start, end)) {
//            int[] direction = {end[0] - start[0], end[1] - start[1], end[2] - start[2]};
//            int randomIndex = random.nextInt(3);
//            if (direction[randomIndex] > 0) {
//                start[randomIndex]++;
//            } else if (direction[randomIndex] < 0) {
//                start[randomIndex]--;
//            } else {
//                start[randomIndex] += random.nextBoolean() ? 1 : -1;
//            }
//            maze3D.setCellInMaze(start[0], start[1], start[2], 0);
//        }
//       }
//
//
//    private void setNeighbors(Maze3D maze3D, ArrayList<Position3D> neighbors, Position3D currentP) {
//
//        int countVisited = 0;
//        // Loop over all non-diagonal neighbors of (k, i, j)
//        for(int z = currentP.getDepthIndex() - 1; z <= currentP.getDepthIndex() + 1; z++){
//            for (int x = currentP.getRowIndex() - 1; x <= currentP.getRowIndex() + 1; x++){
//                for (int y = currentP.getColumnIndex() - 1; y <= currentP.getColumnIndex() + 1; y++){
//                    if (z >= 0 &&  z < maze3D.getDepth() && x >= 0 &&  x < maze3D.getRows() &&  y >= 0 && y < maze3D.getColumns()) //in bounds
//                    {
//                        if (z == currentP.getDepthIndex()) // same plain
//                        {
//                            if(!(x != currentP.getRowIndex() && y != currentP.getColumnIndex())) // not diagonal
//                                if (maze3D.getCellValue(z,x,y) == 0)
//                                    countVisited++;
//                        }
//                        else // different plain
//                        {
//                            if(x == currentP.getRowIndex() && y == currentP.getColumnIndex()) // IN or OUT
//                                if (maze3D.getCellValue(z,x,y) == 0)
//                                    countVisited++;
//                        }
//                    }
//                }
//            }
//        }
//        if (countVisited <2)
//            visitedLessThan(maze3D, neighbors, currentP);
//    }
//
//    private boolean containPosition3D(ArrayList<Position3D> neighbors, int z, int x, int y)
//    {
//        for (Position3D neighbor : neighbors) {
//            if (neighbor.getDepthIndex() == z && neighbor.getRowIndex() == x && neighbor.getColumnIndex() == y)
//                return true;
//        }
//        return false;
//    }
//
//    private void visitedLessThan(Maze3D maze3D, ArrayList<Position3D> neighbors, Position3D currentP)
//    {
//        maze3D.setCellInMaze(currentP.getDepthIndex(), currentP.getRowIndex(), currentP.getColumnIndex(), 0);
//        // Loop over all non-diagonal neighbors of (k, i, j)
//        for(int z = currentP.getDepthIndex() - 1; z <= currentP.getDepthIndex() + 1; z++)
//        {
//            for (int x = currentP.getRowIndex() - 1; x <= currentP.getRowIndex() + 1; x++)
//            {
//                for (int y = currentP.getColumnIndex() - 1; y <= currentP.getColumnIndex() + 1; y++)
//                {
//                    if(z != currentP.getDepthIndex() || x != currentP.getRowIndex() || y != currentP.getColumnIndex())
//                    {
//                        if (z >= 0 &&  z < maze3D.getDepth() && x >= 0 &&  x < maze3D.getRows() &&  y >= 0 && y < maze3D.getColumns() && !containPosition3D(neighbors, z, x, y) && (maze3D.getCellValue(z,x,y) == 1)) //in bounds
//                        {
//                            if (z == currentP.getDepthIndex()) // same plain
//                            {
//                                if(!(x != currentP.getRowIndex() && y != currentP.getColumnIndex())) // not diagonal
//                                        neighbors.add(new Position3D( z, x, y));
//                            }
//                            else // different plain
//                            {
//                                if(x == currentP.getRowIndex() && y == currentP.getColumnIndex()) // IN or OUT
//                                        neighbors.add(new Position3D( z, x, y));
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
}
