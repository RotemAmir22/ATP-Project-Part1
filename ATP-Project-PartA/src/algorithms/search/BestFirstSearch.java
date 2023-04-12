package algorithms.search;

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


}