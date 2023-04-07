package algorithms.mazeGenerators.search;

import java.awt.*;
import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {

   @Override
   public String getName() {
      return "Best First Search";
   }

   public void bestFS(ISearchable searchProblem, AState start) {
      /* initialized the search problem's states */
      for (AState v : searchProblem.getAllPossibleStates()) {
         v.setDist(Double.POSITIVE_INFINITY);
         v.setParent(null);
         v.setColor(Color.white);
      }

      AState v;
      Q = new PriorityQueue<>();
      start.dist = 0;
      start.parent = null;
      start.color = Color.gray;
      Q.add(start);
      while (!Q.isEmpty()) // use in Q to determine the other vertexes
      {
         v = Q.remove();
         for (AState u : v.getNeighbors()) {
            if (u.getColor() == Color.white) {
               u.setColor(Color.gray);
               u.setDist(v.getDist() + 1);
               u.setParent(v);
               Q.add(u);
            }
         }
         v.setColor(Color.black);
      }
   }

   @Override
   public Solution solve(ISearchable domain) {
      Solution solution = new Solution();
      AState start = domain.getStart();
      bestFS(domain, start);
      solution.addToPath(domain.getGoal()); // start from the end to promise a legal path
      AState currentState = domain.getGoal();
      while (currentState != null) { // run until start (parent is null)
         AState minStateDist = findMinDist(currentState.getNeighbors());
         solution.addToPath(minStateDist);
         currentState = currentState.getParent();
      }
      return solution;
   }
}
