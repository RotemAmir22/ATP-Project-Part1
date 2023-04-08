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
        searchProblem.resetPossibleStates();
        for (int i = 0; i < searchProblem.getAllPossibleStates().size(); i++) {
            AState u = searchProblem.getAllPossibleStates().get(i);
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
                searchProblem.setPossibleStates(current.getNeighbors());
                List<AState> adjVertices = searchProblem.getAllPossibleStates();
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



// OLD DFS
//    private void DFS(ISearchable searchProblem)
//    {
//        searchProblem.resetPossibleStates();
//        for (int i = 0; i < searchProblem.getAllPossibleStates().size(); i++) {
//            AState u = searchProblem.getAllPossibleStates().get(i);
//            u.setColor(Color.white);
//            u.setParent(null);
//        }
//        time = 0;
//        for (int i = 0; i < searchProblem.getAllPossibleStates().size(); i++) {
//            AState u = searchProblem.getAllPossibleStates().get(i);
//            if(u.getColor() == Color.white)
//                DFSVisit(searchProblem, u);
//            if (u.node.getRowIndex() == searchProblem.getGoal().node.getRowIndex() && u.node.getColumnIndex() == searchProblem.getGoal().node.getColumnIndex())
//                searchProblem.setGoal(u);
//        }
//
//
//    }
//
//    /**
//     * Helper function of DFS
//     *
//     * @param v to set dist, color and parent
//     */
//    private void DFSVisit(ISearchable searchProblem, AState v) {
//        time += 1;
//        v.setDist(time);
//        v.setColor(Color.gray);
//        searchProblem.setPossibleStates(v.getNeighbors());
//        for (int i = 0; i < searchProblem.getAllPossibleStates().size(); i++) {
//            AState u = searchProblem.getAllPossibleStates().get(i);
//            if (u.getColor() == Color.white) {
//                u.setParent(v);
//                DFSVisit(searchProblem, u);
//            }
//        }
//        v.setColor(Color.black);
//        time += 1;
//        //f[v] = time
//    }
