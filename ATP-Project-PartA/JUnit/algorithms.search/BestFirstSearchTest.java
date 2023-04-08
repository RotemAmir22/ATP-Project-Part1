package algorithms.search;
import algorithms.mazeGenerators.*;

import java.util.ArrayList;
import java.util.Objects;

class BestFirstSearchTest {

    //best first search
    private BestFirstSearch bestFirstSearch = new BestFirstSearch();

    //maze generators
    private IMazeGenerator EmptyMaze = new EmptyMazeGenerator();
    private IMazeGenerator SimpleMaze = new SimpleMazeGenerator();
    private IMazeGenerator MyMaze = new MyMazeGenerator();

    //empty mazes
    private Maze emptyMaze1 = EmptyMaze.generate(0, 0);
    private Maze emptyMaze2 = EmptyMaze.generate(1000, 1000);
    private Maze emptyMaze3 = EmptyMaze.generate(-5, -5);

    private SearchableMaze s_mEmpty1 = new SearchableMaze(emptyMaze1);//checked
    private SearchableMaze s_mEmpty2 = new SearchableMaze(emptyMaze2);//checked
    private SearchableMaze s_mEmpty3 = new SearchableMaze(emptyMaze3);//checked

    //simple mazes
    private Maze simpleMaze1 = SimpleMaze.generate(20, 30);
    private Maze simpleMaze2 = SimpleMaze.generate(1000, 1000);
    private Maze simpleMaze3 = SimpleMaze.generate(-5, -5);

    private SearchableMaze s_mSimple1 = new SearchableMaze(simpleMaze1);//checked
    private SearchableMaze s_mSimple2 = new SearchableMaze(simpleMaze2);//checked
    private SearchableMaze s_mSimple3 = new SearchableMaze(simpleMaze3);//checked

    //my mazes
    private Maze MyMaze1 = MyMaze.generate(10, 25);
    private Maze MyMaze2 = MyMaze.generate(1000, 1000);
    private Maze MyMaze3 = MyMaze.generate(-5, -5);

    private SearchableMaze s_mMyMaze1 = new SearchableMaze(MyMaze1);
    private SearchableMaze s_mMyMaze2 = new SearchableMaze(MyMaze2);
    private SearchableMaze s_mMyMaze3 = new SearchableMaze(MyMaze3);//checked

    @org.junit.jupiter.api.Test
    void getName() {
       assert (Objects.equals(bestFirstSearch.getName(), "Best First Search"));
    }

    @org.junit.jupiter.api.Test
    void solve()
    {
        Solution solution;

        //input null or bad maze
        solution=bestFirstSearch.solve(null);
        assert (solution == null);
        solution=bestFirstSearch.solve(s_mEmpty1);
        assert (solution == null);//0X0
        solution=bestFirstSearch.solve(s_mEmpty3);
        assert (solution==null);//-5X-5
        solution=bestFirstSearch.solve(s_mSimple3);
        assert (solution==null);//-5X-5
        solution=bestFirstSearch.solve(s_mMyMaze3);
        assert (solution==null);//-5X-5

        // check empty mazes
        solution = bestFirstSearch.solve(s_mEmpty2);
        assert (checkSolution(solution, emptyMaze2));//1000X1000

        //simple maze
        solution = bestFirstSearch.solve(s_mSimple1);
        assert (checkSolution(solution, simpleMaze1));//20X30
        solution = bestFirstSearch.solve(s_mSimple2);
        assert (checkSolution(solution, simpleMaze2));//1000X1000

        //my maze
        solution = bestFirstSearch.solve(s_mMyMaze1);
        assert (checkSolution(solution, MyMaze1));//10X25
        solution = bestFirstSearch.solve(s_mMyMaze2);
        assert (checkSolution(solution, MyMaze2));//1000X1000

    }

    /* TODO:
      * need to check that all the cells in the path are not 1
      * check that the start and end positions are the same
      * check that the start and end positions are not null
      * cannot check cost because the maze is random
     */
    private boolean checkSolution(Solution solution, Maze maze)
    {
        //basic things to check
        if(solution == null && maze.getStartPosition()!= null)
            return false;
        if(solution == null && maze.getGoalPosition()!= null)
            return false;
        if(solution != null && maze.getStartPosition()== null)
            return false;
        if(solution != null && maze.getGoalPosition()== null)
            return false;
        if(solution == null && maze.getFrame() == null)
            return true;


        assert solution != null;
        ArrayList<AState> solutionPath = solution.getSolutionPath();
        for(int i =0; i< solutionPath.size(); i++)
        {
            AState currentPos = solutionPath.get(i);
            //start point
            if(i==0 && currentPos.node.getRowIndex() != maze.getStartPosition().getRowIndex() && currentPos.node.getColumnIndex() != maze.getStartPosition().getColumnIndex())
                return false;

            //end point
            if(i==solutionPath.size()-1 &&  currentPos.node.getRowIndex() != maze.getGoalPosition().getRowIndex() && currentPos.node.getColumnIndex() != maze.getGoalPosition().getColumnIndex())
                return false;

            //other cells in path
            if(currentPos.value == 1 && currentPos.value != maze.getCellValue(currentPos.node.getRowIndex(),currentPos.node.getColumnIndex()))
                return false;
        }
        return true;
    }
}