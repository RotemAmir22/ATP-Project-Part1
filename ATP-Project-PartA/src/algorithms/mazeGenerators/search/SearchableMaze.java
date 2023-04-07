package algorithms.mazeGenerators.search;

import algorithms.mazeGenerators.Maze;
import algorithms.mazeGenerators.Position;

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
        this.s_maze = new MazeState[m.getRows()][m.getColoums()];
        for (int i = 0; i < m.getRows(); i ++){
            for(int j = 0; j < m.getColoums(); j++){
                s_maze[i][j]=new MazeState(new Position(i,j),maze.getCellValue(i,j));
            }
        }
        for(int row = 0; row < m.getRows(); row ++)
        {
            for(int col = 0; col < m.getColoums(); col++)
            {
                // set the neighbors list
                for (int i = -1; i <= 1; i++)
                {
                    for(int j = -1; j <=1; j++)
                    {
                        if(0 <= (row + i) && (row + i) < m.getRows() && 0 <= (col + j) && (col + j) < m.getColoums())
                            s_maze[row][col].addToNeighbors(s_maze[row + i][col + j]);
                    }
                }
            }
        }

    }

    public void resetPossibleStates()
    {
        this.possibleStates = new ArrayList<>();
        for (int i = 0; i < maze.getRows(); i ++){
            for(int j = 0; j < maze.getColoums(); j++){
                if(s_maze[i][j].value == 0)
                    possibleStates.add(s_maze[i][j]);
            }
        }
    }

    /**
     * Set the possible states' list
     * @param states to optional solutions
     */
    public void setPossibleStates(List<AState> states)
    {
        this.possibleStates = new ArrayList<>();
        for(AState s: states){
            if(s.getValue() == 0)
                possibleStates.add(s);
        }
    }

    @Override
    public void setStart(AState s) {
        this.start = (MazeState) s;
    }

    @Override
    public void setGoal(AState g) {
        this.goal = (MazeState) g;
    }

    @Override
    public AState getStart() {
        return this.start;
    }

    @Override
    public AState getGoal() {
        return this.goal;
    }

    /**
     * @return a list of states which create a legal solution
     */
    @Override
    public List<AState> getAllPossibleStates() {
        return possibleStates;
    }
}
