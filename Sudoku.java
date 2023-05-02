import java.util.Collections;
import java.util.Arrays;
import java.util.List;
public class Sudoku
{

    final static int GRID_SIZE = 9; // size of sudoku grid 9x9
    private static int[][] sourceBoard = new int[GRID_SIZE][GRID_SIZE]; // 2D array representing the board


    public static void main(String[] args)
    {
        initializeBoard();
        generateBoard(0);

        System.out.println("\nSolved Board");
        printBoard();

        removeNumbers(60); // 81 - 21 = 60 (considered hard)

        System.out.println("\nUnsolved Board");
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
        }

    }

    public static void removeNumbers(int cellsToRemove) {
        while (cellsToRemove > 0) {
            // Randomly pick a row and column index
            int row = (int)(Math.random() * GRID_SIZE);
            int column = (int)(Math.random() * GRID_SIZE);

            int removedCell;
            if (sourceBoard[row][column] != 0) //if cell is not empty
            {
                removedCell = sourceBoard[row][column];
                sourceBoard[row][column] = 0;
                cellsToRemove--; // Keep track of how many cells are being removed
            }

            if(oneSolution() == false)
            {
                sourceBoard[row][column] = removedCell; // Put back wrong RNG choice
                cellsToRemove++;
            }
        }
    }

    public static boolean oneSolution()
    {
        int numOfSolutions = 0;

        generateBoard(0);

        


        return false;
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
    // Takes an integer as parameter, one that keeps track of the current cell that we want to fill with a number
    // The integer named 'currentCell' should start with a 0 value
    public static boolean generateBoard(int currentCell)
    {
        int row = currentCell / 9; // row index of the current cell that we want to fill with a number
        int column = currentCell % 9; // column index of the current cell that we want to fill with a number

        // Board is completely filled, all 81 cells have a value
        // Considering recursion is used, we wait until all cells have been filled
        // Base Case
        if(currentCell == GRID_SIZE * GRID_SIZE){
            return true;
        }

        // Check if cell is already filled
        // If it is, jump to the next cell
        if(sourceBoard[row][column] != 0){
            return generateBoard(currentCell + 1);
        }

        for (int i = 1; i <= GRID_SIZE; i++) {
            // Check for validity
            if (isValid(i, row, column)) { // Validate whether the cell can be filled with the value
                sourceBoard[row][column] = i; // Fill the cell with the value
                if (generateBoard(currentCell + 1)) { // Recursively call the function to fill the next cells
                    return true; // Retrun true was board is completely filled
                }
                sourceBoard[row][column] = 0; // Backtrack by clearing the currentCell cell
            }
        }
        return false;
    }

    public static boolean isValid(int input, int row, int col)
    {
        if(checkGrid(input, row, col) && checkCol(input, row) && checkRow(input, col))
        {
            return true;
        }
        return false;
    }

    public static boolean checkRow(int input, int col)
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

    public static boolean checkCol(int input, int row)
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
    public static boolean checkGrid(int input, int row, int col)
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