import java.util.Collections;
import java.util.Arrays;
import java.util.List;
public class Sudoku
{

    final static int GRID_SIZE = 9; // size of sudoku grid 9x9
    private static int[][] sourceBoard = new int[GRID_SIZE][GRID_SIZE]; // 2D array representing the board

    final static int CELLS_TO_REMOVE = 40; // number of empty cells out of 81 at the start of the game

    public static void main(String[] args)
    {
        initializeBoard();
        printBoard();
    }

    // Initializes the board with all cells set to zero
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

        // Insert first row of Sudoku using shuffle of 1-9
        for(int i = 0; i < GRID_SIZE; i++)
        {
            sourceBoard[0][i] = list.get(i);
            //System.out.println(sourceBoard[0][i]); Testing Purposes
        }

    }

    // Print the board, will be replaced by GUI
    public static void printBoard() {
        System.out.println("+-------+-------+-------+"); // Top border of the board
        // Print each row of the board
        for (int row = 0; row < GRID_SIZE; row++) {
            System.out.print("| ");
            // Print each column of the board
            for (int column = 0; column < GRID_SIZE; column++) {
                int value = sourceBoard[row][column];
                if (value == 0) { // Print the cell value as an empty space if it's zero
                    System.out.print("  ");
                } else { // Print the cell value if it's not zero
                    System.out.print(value + " ");
                }
                if (column % 3 == 2) {
                    System.out.print("| ");
                }
            }
            System.out.println("");
            if (row % 3 == 2) {
                System.out.println("+-------+-------+-------+");  // Bottom border of the board
            }
        }
    }

    // Generate the rest of the board using backtracking method
    public static boolean generateBoard(int numberOfCells)
    {
        int row = numberOfCells / 9; // row index of the current cell that we want to fill with a number
        int column = numberOfCells % 9; // column index of the current cell that we want to fill with a number

        if(numberOfCells == 81){ // Board is completely filled, all 81 cells have a value
            return true;
        }

        // TO-DO:
        //      - Check if cell is already filled with a number
        //      - Move to the next the cell
        //      - Check if its valid
        //      - Backtrack

        return true;
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