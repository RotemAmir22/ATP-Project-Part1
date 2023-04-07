package algorithms.mazeGenerators.search;

import java.util.List;

public interface ISearchable {

     AState getStart();
     AState getGoal();

     void setPossibleStates(List<AState> states);

     List<AState> getAllPossibleStates();

     void resetPossibleStates();
}
