package algorithms.mazeGenerators.search;

import java.util.List;

public interface ISearchable {

     AState getStart();
     AState getGoal();

     List<AState> getAllPossibleStates();
}
