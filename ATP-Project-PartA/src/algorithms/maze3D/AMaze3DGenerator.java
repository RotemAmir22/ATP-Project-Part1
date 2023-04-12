package algorithms.maze3D;

public abstract class AMaze3DGenerator implements IMazeGenerator3D {

    public long measureAlgorithmTimeMillis(int depth, int row, int column){

        long start =  System.currentTimeMillis();
        this.generate(depth, row, column);
        long end = System.currentTimeMillis();
        return end - start;
    }
}
