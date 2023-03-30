package algorithms.mazeGenerators;

public class Maze {

    private int[][] frame;
    private int rows;
    private int coloums;

    private Position startPoint;
    private Position endPoint;

    public Maze(int rows, int coloums)
    {
        frame = new int[rows][coloums]; // default with 0
        this.rows = rows;
        this.coloums = coloums;
        this.startPoint = new Position(0,0);
        this.endPoint = new Position(rows - 1, coloums - 1);
    }

    public static void setAllMazeToWalls(Maze maze)
    {
        for(int i=0; i<maze.rows; i++)
        {
            for(int j=0; j<maze.coloums; i++)
            {
                maze.frame[i][j] = 1;
            }
        }
    }

    public void setCellInMaze(int row, int colum, int val)
    {
        if(0 <= row && row < this.rows && 0 <= colum && colum < this.coloums)
            this.frame[row][colum] = val;
    }

    public int[][] getFrame() {
        return frame;
    }

    public int getRows() {
        return rows;
    }

    public int getColoums() {
        return coloums;
    }

    public int getCellValue(int row, int colum)
    {
        if(0 <= row && row < this.rows && 0 <= colum && colum < this.coloums)
            return this.frame[row][colum];
        else
            return -1;
    }
    public Position getStartPoint() {
        return startPoint;
    }

    public Position getEndPoint() {
        return endPoint;
    }
}

