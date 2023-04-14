package algorithms.maze3D;

public class Position3D {

    private int depth;
    private int row;
    private int colum;
    public Position3D(int depth, int row, int column)
    {
        this.depth = depth;
        this.row = row;
        this.colum = column;
    }

    public int getDepthIndex(){

        return this.depth;
    }

    public int getRowIndex()
    {

        return this.row;
    }
    public int getColumnIndex()
    {

        return this.colum;
    }

    @Override
    public String toString() {
        return"{"+getDepthIndex()+","+getRowIndex()+","+getColumnIndex()+"}";
    }
}
