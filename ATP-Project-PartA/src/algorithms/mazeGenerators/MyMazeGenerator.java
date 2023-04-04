package algorithms.mazeGenerators;
import java.util.*;

//public class MyMazeGenerator extends AMazeGenerator{
//    /**
//     *
//     * @param r - row
//     * @param c - column
//     * @return maze with walls and cells
//     * generate the maze using prim algorithm.
//     */
//    public Maze generate(int r,int c){
//        if(r<=0 || c<=0){
//            return new EmptyMazeGenerator().generate(r,c);
//        }
//
//        //initiate new maze and sel all it's cells with 1 as walls.
//        Maze maze = new Maze(r, c);
//        Maze.setAllMazeToWalls(maze);
//
//        //select random start point.
//        Position start = maze.getStartPosition();
//
//        Position curr;
//        int count; //num of visited neighbors for current cell.
//
//        //frontiers list , candidates to be selected as the next cell in the maze.
//        List<Position> Flist= new ArrayList<>();
//        Flist.add(start);
//
//        while (!Flist.isEmpty()){
//            Random R = new Random( );
//            curr=Flist.remove(R.nextInt(Flist.size()));
//            count=countVisitedN(maze,curr);
//
//            if(count<=1){
//                maze.setCellInMaze(curr.getRowIndex(),curr.getColumnIndex(),0);
//
//                //down
//                addValidFrontier(curr.getRowIndex()+1, curr.getColumnIndex(), maze,Flist);
//                //up
//                addValidFrontier(curr.getRowIndex()-1, curr.getColumnIndex(), maze,Flist);
//                //left
//                addValidFrontier(curr.getRowIndex(), curr.getColumnIndex()-1, maze,Flist);
//                //right
//                addValidFrontier(curr.getRowIndex(), curr.getColumnIndex()+1, maze,Flist);
//            }
//
//        }
//        maze.getGoalPosition();
//        return maze;
//    }
//
//    private int[][] all1maze(int r,int c) {
//        int[][] Nmaze = new int[r][c];
//        for (int i = 0; i < r; i++) {
//            for (int j = 0; j < c; j++) {
//                Nmaze[i][j] = 1;
//            }
//        }
//        return Nmaze;}
//
//
//
//
//    //return the count of visited neighbors for a cell.
//    private int countVisitedN(Maze m, Position curr){
//        int Ncount=0;
//        //down
//        if(curr.getRowIndex()+1<m.getRows() && m.getCellValue(curr.getRowIndex()+1,curr.getColumnIndex())==0)
//            Ncount++;
//        //up
//        if(curr.getRowIndex()-1>=0 && m.getCellValue(curr.getRowIndex()-1,curr.getColumnIndex())==0)
//            Ncount++;
//        //right
//        if(curr.getColumnIndex()+1<m.getColoums() && m.getCellValue(curr.getRowIndex(),curr.getColumnIndex()+1)==0)
//            Ncount++;
//        //left
//        if(curr.getColumnIndex()-1>=0 && m.getCellValue(curr.getRowIndex(),curr.getColumnIndex()-1)==0)
//            Ncount++;
//        return Ncount;}
//
//    private void addValidFrontier(int r, int c, Maze m,List<Position> frontiers){
//        if(c>=0 && c<m.getColoums() && r>=0 && r<m.getColoums() && m.getCellValue(r,c)==1){
//            frontiers.add(new Position(r,c));
//        }
//
//    }
//
//
//
//}
public class MyMazeGenerator extends AMazeGenerator {

    /**
     * Find the nearest neighbors of a specific point
     * @param point current position
     */
    public void setNeighbors(Maze maze, ArrayList<Position> neighbors, Position point)
    {
        int countVisited = 0;
        for(int i=-1; i<=1; i++)
        {
            for(int j=-1; j<=1; j++)
            {
                try {
                    if(maze.getCellValue(point.getRowIndex() + i,point.getColumnIndex()+j) == 0)
                        countVisited++;
                }
                catch (Exception e)
                {
                    //ignore out of range
                }
            }
        }
        if(countVisited < 2)
        {
            maze.setCellInMaze(point.getRowIndex(), point.getColumnIndex(),0);
            for(int i=-1; i<=1; i++)
            {
                for(int j=-1; j<=1; j++)
                {
                    try {
                        neighbors.add(new Position(point.getRowIndex() + i, point.getColumnIndex() + j));
                    }
                    catch (Exception e)
                    {
                        //ignore out of range
                    }
                }
            }
        }

    }
    @Override
    public Maze generate(int rows, int colums) {


        // build maze and initialize with only walls
        Maze maze = new Maze(rows, colums);
        Maze.setAllMazeToWalls(maze);
        Position point = new Position(0, 0);
        ArrayList<Position> neighbors = new ArrayList<>();
        setNeighbors(maze, neighbors, point);
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
                    setNeighbors(maze, neighbors, currentP);
                }
            }
            predecessor = currentP;



        }
        return maze;
    }


}

