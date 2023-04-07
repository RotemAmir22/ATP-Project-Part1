package algorithms.mazeGenerators.search;

import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch {

    @Override
    public String getName() {
        return "Best First Search";
    }

    public BestFirstSearch(){
        Q = new PriorityQueue<>();
    }
}