package algorithms.mazeGenerators.search;

import java.awt.*;

/* DFS */
public class DepthFirstSearch extends ASearchingAlgorithm{

    private int time;

    /**
     * DFS algorithm
     * Two functions used to set the distance and parents of states
     * @param searchProblem to solve
     */
    private void DFS(ISearchable searchProblem)
    {
        searchProblem.resetPossibleStates();
        for(AState u: searchProblem.getAllPossibleStates()){
            u.setColor(Color.white);
            u.setParent(null);
        }
        time = 0;
        for(AState u: searchProblem.getAllPossibleStates()){
            if(u.getColor() == Color.white)
                DFSVisit(searchProblem, u);
        }

    }

    /**
     * Helper function of DFS
     * @param v to set dist, color and parent
     */
    private void DFSVisit(ISearchable searchProblem, AState v)
    {
        time += 1;
        v.setDist(time);
        v.setColor(Color.gray);
        searchProblem.setPossibleStates(v.getNeighbors());
        for(AState u: searchProblem.getAllPossibleStates()){
            if(u.getColor() == Color.white)
            {
                u.setParent(v);
                DFSVisit(searchProblem, u);
            }
        }
        v.setColor(Color.black);
        time += 1;
        //f[v] = time
    }
    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return numOfNode;
    }

    @Override
    public Solution solve(ISearchable domain) {
        DFS(domain);
        Solution solution = new Solution();
        solution.addToPath(domain.getGoal()); // start from the end to promise a legal path
        AState currentState = domain.getGoal();
        while(currentState.getParent() != null){ // run until start (parent is null)
            solution.addToPath(currentState.getParent());
            numOfNode++;
            currentState = currentState.getParent();
        }
        return solution;
    }
}
