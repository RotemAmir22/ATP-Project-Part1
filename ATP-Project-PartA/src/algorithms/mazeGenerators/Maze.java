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
        if(rows == 0 && coloums == 0)
            this.endPoint=startPoint;
        else
            this.endPoint = new Position(rows - 1, coloums - 1);
    }

    public static void setAllMazeToWalls(Maze maze)
    {
        for(int i=0; i<maze.rows; i++)
        {
            for(int j=0; j<maze.coloums; j++)
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

    public int[][] getFrame() {return frame;}

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

    public Position getStartPosition() {return startPoint;}

    public Position getGoalPosition() {return endPoint;}

    public void Print(){
        System.out.println("Start Position: Position{ row num:0 col num:0}");
        System.out.println("End Position: Position{ row num:"+this.endPoint.getRowIndex()+" col num:"+ this.endPoint.getColumnIndex()+'}');
        for (int i = 0; i < rows; i++) {
            System.out.print("[");
            for (int j = 0; j < getColoums(); j++) {

                if (i == 0 && j == 0) {
                    System.out.print("\033[1;93mS\033[0m, "); // 'S' represents the entry point
                } else if (i == rows-1 && j == coloums-1) {
                    System.out.print("\033[1;92mE\033[0m"); // 'E' represents the exit point
                } else if (j == getColoums() - 1) {
                    System.out.print(this.frame[i][j]);
                }
                else
                    System.out.print(this.frame[i][j]+", ");
            }
            System.out.println("]");
        }
    }
}

