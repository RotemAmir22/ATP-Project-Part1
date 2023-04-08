package algorithms.mazeGenerators.search;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

/* BFS */
public class BreadthFirstSearch extends ASearchingAlgorithm {

    protected Queue<AState> Q;

    /**
     * Constructor
     */
    public BreadthFirstSearch() {
        Q = new LinkedList<>();
    }

    /**
     * BFS algorithm
     *
     * @param searchProblem that we want to solve
     * @param start         point
     */
    private void BFS(ISearchable searchProblem, AState start) {
        /* initialized the search problem's states */
        searchProblem.resetPossibleStates();
        for (int i = 0; i < searchProblem.getAllPossibleStates().size(); i++) {
            AState v = searchProblem.getAllPossibleStates().get(i);
            v.setDist(Double.POSITIVE_INFINITY);
            v.setParent(null);
            v.setColor(Color.white);
        }
        AState v;
        //searchProblem.setStart(searchProblem.getAllPossibleStates().get(0));
        start.setDist(0);
        start.setParent(null);
        start.setColor(Color.gray);
        Q.add(start);
        while (!Q.isEmpty()) // use in Q to determine the other vertexes
        {
            v = Q.remove();
            searchProblem.setPossibleStates(v.getNeighbors());
            for (AState u : searchProblem.getAllPossibleStates()) {
                if (u.getColor() == Color.white) {
                    u.setColor(Color.gray);
                    u.setDist(v.getDist() + 1);
                    u.setParent(v);
                    Q.add(u);
                }
            }
            if (v.node.getRowIndex() == searchProblem.getGoal().node.getRowIndex() && v.node.getColumnIndex() == searchProblem.getGoal().node.getColumnIndex())
                searchProblem.setGoal(v);
            v.setColor(Color.black);
        }
    }

    @Override
    public String getName() {
        return "Breadth First Search";
    }

    @Override
    public int getNumberOfNodesEvaluated() {
        return numOfNode;
    }

    /**
     * Use BFS algorithm and return a solution
     *
     * @param domain the problem for solving
     * @return a new solution for this problem
     */
    @Override
    public Solution solve(ISearchable domain) {
        Solution solution = new Solution();
        AState start = domain.getStart();
        BFS(domain, start);
        solution.addToPath(domain.getGoal()); // start from the end to promise a legal path
        AState currentState = domain.getGoal();
        while (currentState.getParent() != null) { // run until start (parent is null)
            solution.addToPath(currentState.getParent());
            //currentState.setCost();
            numOfNode++;
            currentState = currentState.getParent();
        }
        return solution;
    }
}
