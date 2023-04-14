package algorithms.maze3D;
import algorithms.search.AState;

public class Maze3DState extends AState{

    private Position3D position3D;
    public Maze3DState(Position3D node, int value) {
        super(value);
        this.position3D = node;
    }

    public String toString() {
        return "(" + this.position3D.getDepthIndex()+','+ this.position3D.getRowIndex()+','+this.position3D.getColumnIndex() +')';
    }
}
