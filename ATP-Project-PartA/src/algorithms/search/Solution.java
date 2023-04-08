package algorithms.search;

import java.util.ArrayList;

public class Solution {

    int cost;
    private ArrayList<AState> solutionPath;

    public Solution()
    {
        this.solutionPath = new ArrayList<>();
    }

    public ArrayList<AState> getSolutionPath()
    {
        return solutionPath;
    }
    public void addToPath(AState state){solutionPath.add(0,state);}
}
