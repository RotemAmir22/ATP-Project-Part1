package algorithms.mazeGenerators.search;

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
     */
    public SearchableMaze(Maze m)
    {
        this.maze = m;
        this.start = new MazeState(m.getStartPosition(), 0);
        this.goal = new MazeState(m.getGoalPosition(), 0);
        this.possibleStates = new ArrayList<>();
        this.s_maze = new MazeState[m.getRows()][m.getColoums()];
        for (int i = 0; i < m.getRows(); i ++){
            for(int j = 0; j < m.getColoums(); j++){
                s_maze[i][j].value = maze.getCellValue(i,j);
            }
        }
    }

    /**
     * Set the possible states' list
     * @param states to optional solutions
     */
    public void setPossibleStates(List<AState> states){

        for(AState s: states){
            if(s.getValue() == 0)
                possibleStates.add(s);
        }
    }

    @Override
    public AState getStart() {
        return null;
    }

    @Override
    public AState getGoal() {
        return null;
    }

    /**
     * @return a list of states which create a legal solution
     */
    @Override
    public List<AState> getAllPossibleStates() {
        return possibleStates;
    }
}
