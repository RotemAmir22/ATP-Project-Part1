package algorithms.mazeGenerators;

/**
 * This class save the coordinates of a specific point in 2D maze
 */
public class Position {
    private int row;
    private int colum;

    // Constructor
    public Position(int row, int colum) {
        this.row = row;
        this.colum = colum;
    }

    // getters
    public int getRowIndex() {
        return row;
    }

    public int getColumnIndex() {
        return colum;
    }

    /**
     * toString for prints
     * @return the print of the position
     */
    @Override
    public String toString() {
       return "Position{ row num:" +this.getRowIndex()+" col num:"+ this.getColumnIndex()+"}";
    }
}
