package algorithms.search;

import java.awt.*;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {

    public BestFirstSearch(){
        Q = new PriorityQueue<>(Comparator.comparingInt(AState::getCost).reversed());
    }
    @Override
    public String getName() {
        return "Best First Search";
    }

    /**
     * BFS algorithm
     *
     * @param searchProblem that we want to solve
     * @param start         point
     */
    private void BestFS(ISearchable searchProblem, AState start) {
        /* initialized the search problem's states */
        searchProblem.resetPossibleStates();
        for (int i = 0; i < searchProblem.getAllPossibleStates().size(); i++) {
            AState v = searchProblem.getAllPossibleStates().get(i);
            v.setDist(Double.POSITIVE_INFINITY);
            v.setParent(null);
            v.setColor(Color.white);
        }
        AState v;
        start.setDist(0);
        start.setParent(null);
        start.setColor(Color.gray);
        Q.add(start);
        while (!Q.isEmpty()) // use in Q to determine the other vertexes
        {
            v = Q.remove(); // from priority queue
            searchProblem.setPossibleStates(v.getNeighbors());
            for (AState u : searchProblem.getAllPossibleStates()) {
                if (u.getColor() == Color.white) {
                    u.setColor(Color.gray);
                    u.setDist(v.getDist() + 1);
                    u.setParent(v);
                    u.setCost();
                    Q.add(u);
                }
            }
            if (v.node.getRowIndex() == searchProblem.getGoal().node.getRowIndex() && v.node.getColumnIndex() == searchProblem.getGoal().node.getColumnIndex())
                searchProblem.setGoal(v);
            v.setColor(Color.black);
        }
    }

    /**
     * Use BestFS to find a legal solution
     * @param domain the problem for solving
     * @return a new solution
     */
    public Solution solve(ISearchable domain) {
        //if null
        if(domain == null || domain.getStart() == null)
            return null;
        Solution sol = new Solution();
        AState start = domain.getStart();
        BestFS(domain, start);
        sol.addToPath(domain.getGoal()); // start from the end to promise a legal path
        AState currentState = domain.getGoal();
        while (currentState.getParent() != null) { // run until start (parent is null)
            sol.addToPath(currentState.getParent());
            sol.cost += currentState.getCost();
            numOfNode++;
            currentState = currentState.getParent();
        }
        //System.out.println("Cost: "+sol.cost);
        return sol;
    }


}