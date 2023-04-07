package algorithms.mazeGenerators.search;

import java.util.PriorityQueue;

public class BestFirstSearch extends BreadthFirstSearch{

    /**
     * Constructor
     */
    public BestFirstSearch(){
        Q = new PriorityQueue<>();
    }
}
