package algorithms.mazeGenerators;

public abstract class AMazeGenerator implements IMazeGenerator {

    public long measureAlgorithmTimeMillis(int row, int coloums)
    {
        long start =  System.currentTimeMillis();
        this.generate(row, coloums);
        long end = System.currentTimeMillis();
        return end - start;
    }
}
