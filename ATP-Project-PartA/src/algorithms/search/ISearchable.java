package algorithms.search;

import java.util.List;

public interface ISearchable {

     void setStart(AState s);
     void setGoal(AState g);
     AState getStart();
     AState getGoal();

     void setPossibleStates(List<AState> states);

     List<AState> getAllPossibleStates();

     void resetPossibleStates();
}
