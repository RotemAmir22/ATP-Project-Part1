package algorithms.mazeGenerators.search;

public interface ISearchingAlgorithm {

    String getName();

    int getNumberOfNodesEvaluated();
    Solution solve(ISearchable domain);
}
