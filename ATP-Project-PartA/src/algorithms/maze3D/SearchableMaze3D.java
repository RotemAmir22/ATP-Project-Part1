package algorithms.maze3D;

import algorithms.search.AState;
import algorithms.search.ISearchable;

import java.util.List;

public class SearchableMaze3D implements ISearchable {
    @Override
    public void setStart(AState s) {

    }

    @Override
    public void setGoal(AState g) {

    }

    @Override
    public AState getStart() {
        return null;
    }

    @Override
    public AState getGoal() {
        return null;
    }

    public void setPossibleStates(List<AState> states) {

    }

    @Override
    public List<AState> getAllPossibleStates(List<AState> list){return null;}

    @Override
    public List<AState> resetPossibleStates() {

        return null;
    }
}