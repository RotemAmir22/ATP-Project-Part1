package algorithms.search;

import java.util.List;

public interface ISearchable {

     void setStart(AState s);
     void setGoal(AState g);
     AState getStart();
     AState getGoal();
     List<AState> getAllPossibleStates(List<AState> list);

     List<AState> resetPossibleStates();
}
