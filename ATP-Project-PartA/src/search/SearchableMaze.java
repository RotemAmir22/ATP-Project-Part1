package search;

import algorithms.mazeGenerators.Maze;

import java.util.ArrayList;
import java.util.List;

public class SearchableMaze implements ISearchable{

     private Maze maze;
     MazeState[][] s_maze;
     MazeState start;
     MazeState goal;

     private List<AState> possibleStates;

    /**
     * Create a searchable maze with an existing maze, start and goal points
     * @param m the original maze
     * @param s start point to find a path
     * @param g goal point
     */
    public SearchableMaze(Maze m, MazeState s, MazeState g)
    {
        this.maze = m;
        this.start = s;
        this.goal = g;
        this.possibleStates = new ArrayList<>();
        this.s_maze = new MazeState[m.getRows()][m.getColoums()];
        for (int i = 0; i < m.getRows(); i ++){
            for(int j = 0; j < m.getColoums(); j++){
                s_maze[i][j].value = maze.getCellValue(i,j);
            }
        }
    }

    /**
     * Add a possible state to list
     * @param state to optional solution
     */
    public void addState(MazeState state){possibleStates.add(state);}

    /**
     * @return a list of states which create a legal solution
     */
    @Override
    public List<AState> getAllPossibleStates() {
        return possibleStates;
    }
}
