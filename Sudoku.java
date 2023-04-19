public class Sudoku
{

    final static int GRID_SIZE = 9;
    private int[][] sourceBoard = new int[GRID_SIZE][GRID_SIZE];


    public void initializeBoard()
    {
        // Fill the board with zeros
        for (int i = 0; i < 9; i++) 
        {
            for (int j = 0; j < 9; j++) 
            {
                sourceBoard[i][j] = 0;
            }
        }
    }

    







}