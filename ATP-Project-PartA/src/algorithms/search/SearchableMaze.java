package algorithms.search;

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
                            if(!(i ==0 && j==0))
                                if(s_maze[row + i][col + j].value == 0)
                                    s_maze[row][col].addToNeighbors(s_maze[row + i][col + j]);
                    }
                }
            }
        }

        checkNeighbours();
        setStart(s_maze[0][0]);
        setGoal(s_maze[maze.getRows()-1][maze.getColoums()-1]);
    }

    /**
     * check if the diagonals are valid
     * if not then removes from neighbours
     */
    public void checkNeighbours()
    {
        for(int row = 0; row < this.maze.getRows(); row ++)
        {
            for(int col = 0; col < this.maze.getColoums(); col++)
            {
                //check the neighbors list only diagonals
                for (int i = -1; i <= 1; i++)
                {
                    for(int j = -1; j <=1; j++)
                    {
                        if(0 <= (row + i) && (row + i) < this.maze.getRows() && 0 <= (col + j) && (col + j) < this.maze.getColoums())
                            if(row != row+i && col != col +j)
                            {
                                if(!checkCommonNeighbour(this.s_maze[row][col],this.s_maze[row+i][col+j]))
                                    s_maze[row][col].removeFromNeighbors(s_maze[row + i][col + j]);
                            }
                    }
                }
            }
        }
    }

    /**
     * helper function that checks if there is a path between two cells that is not only diagonal
     * @param first : first cell
     * @param second : second cell
     * @return : true if there is a third cell that connects them, in other words, another valid path
     */
    public boolean checkCommonNeighbour(MazeState first, MazeState second)
    {
        for (int i =0; i<first.neighbors.size();i++)
        {
            for (int j=0; j<second.neighbors.size(); j++)
            {
                if(first.neighbors.get(i)==second.neighbors.get(j))
                    return true;
            }
        }
        return false;
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
