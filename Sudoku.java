import java.util.Collections;
import java.util.Arrays;
import java.util.List;
public class Sudoku
{

    public static void main(String[] args) 
    {
     initializeBoard();   
    }


    final static int GRID_SIZE = 9;
    private static int[][] sourceBoard = new int[GRID_SIZE][GRID_SIZE];


    public static void initializeBoard()
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
        Integer[] numbers = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9}; // ex. 1 2 3 4 5 6 7 8 9
        List<Integer> list = Arrays.asList(numbers);
        Collections.shuffle(list); // ex. 4 2 1 3 8 9 5 6 7 

        //Insert first row of Sudoku using shuffle of 1-9 
        for(int i = 0; i < GRID_SIZE; i++)
        {
            sourceBoard[0][i] = list.get(i);
            //System.out.println(sourceBoard[0][i]); Testing Purposes
        }

        

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