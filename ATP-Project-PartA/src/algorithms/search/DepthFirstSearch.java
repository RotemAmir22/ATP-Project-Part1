package algorithms.search;

import java.awt.*;
import java.util.List;
import java.util.Stack;

/* DFS */
public class DepthFirstSearch extends ASearchingAlgorithm {

    private int time;

    public DepthFirstSearch() {

    }

    /**
     * DFS algorithm
     * Two functions used to set the distance and parents of states
     * @param searchProblem to solve
     * @param start pose
     */
    public void DFS(ISearchable searchProblem, AState start) {
        List<AState> possibleStates = searchProblem.resetPossibleStates();
        for (AState u : possibleStates) {
            u.setColor(Color.white);
            u.setParent(null);
        }
        Stack<AState> stack = new Stack<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            time++;
            AState current = stack.pop();
            if (current.getColor() == Color.white) {
                current.setColor(Color.gray);
                current.setDist(time);
                List<AState> adjVertices = searchProblem.getAllPossibleStates(current.getNeighbors());
                for (AState v : adjVertices) {
                    if (v.getColor() == Color.white)
                    {
                        stack.push(v);
                        v.setParent(current);
                    }
                }
            }
            current.setColor(Color.black);
        }
    }

    @Override
    public String getName() {
        return "Depth First Search";
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return numOfNode;
    }

    @Override
    public Solution solve(ISearchable domain) {
        DFS(domain, domain.getStart());
        Solution solution = new Solution();
        solution.addToPath(domain.getGoal()); // start from the end to promise a legal path
        AState currentState = domain.getGoal();
        while (currentState.getParent() != null) { // run until start (parent is null)
            solution.addToPath(currentState.getParent());
            numOfNode++;
            currentState = currentState.getParent();
        }
        return solution;
    }


}

