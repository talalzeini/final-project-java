import java.util.Collections;
import java.util.Arrays;
import java.util.List;
public class Sudoku
{

    final static int GRID_SIZE = 9;
    private static int[][] sourceBoard = new int[GRID_SIZE][GRID_SIZE];


    public static void main(String[] args) 
    {
     initializeBoard();   
    }

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

        // Want to generate the first row using a random list of 1-9 to increase efficency of the rest of the soduku generation alogrithm
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

    //backtracking method to generate rest of sudoku board
    public static boolean generateBoard()
    {
        
        return true; //temp
    }

    public boolean isValid(int input, int row, int col)
    {
        if(checkGrid(input, row, col) && checkCol(input, row) && checkRow(input, col))
        {
            return true;
        }
        return false;
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

    // Find the starting Position of the top left corner of each sub grid
    // Figure out how to do that
    public boolean checkGrid(int input, int row, int col)
    {
        int startingRow = row - row % 3; // ex. r = 1
        int startingColumn = col - col % 3; // ex. c = 1

        for(int i = startingRow; i < startingRow + 3; i++)
        {
            for(int j = startingColumn; j < startingColumn + 3; j++)
            {
                if(sourceBoard[i][j] == input)
                {
                    return false;
                }
            }
       
         }
         return true;
    }



}