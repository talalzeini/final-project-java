import java.util.Collections;
import java.util.Arrays;
import java.util.List;
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
        // Want to generate the first row using a random list of 1-9 to increase efficency of the rest of the soduku alogrithm
        Integer[] numbers = new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<Integer> list = Arrays.asList(numbers);
        Collections.shuffle(list);
        

    }

    public boolean checkRow(int input, int col)
    {
        for(int row = 0; row < GRID_SIZE; row++)
        {
            if(sourceBoard[row][col] == input)
            {
                return false;
            }
        }
        return true;
    }

    public boolean checkCol(int input, int row)
    {
        for(int col = 0; col < GRID_SIZE; col++)
        {
            if(sourceBoard[row][col] == input)
            {
                return false;
            }
        }
        return true;
    }

    









}