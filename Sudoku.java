import java.util.Collections;
import java.util.Arrays;
import java.util.List;


public class Sudoku
{

    final static int GRID_SIZE = 9; // size of sudoku grid 9x9
    private int[][] board = new int[GRID_SIZE][GRID_SIZE]; // 2D array representing the finished board



    public Sudoku(int[][] inputBoard)
    {
        this.board = inputBoard;
    }
    public Sudoku()
    {
        this.board = new int[9][9];
    }
    
    /*
     * Setter
     * @param board board to be set to instance variable
     * 
     */
    public void setBoard(int[][] board)
    {
        this.board = board;
    }

    /*
     * Getter/Setter
     * @param board get board and set it to the  instance variable
     * 
     */
    public void copyBoard(Sudoku board)
    {
        this.board = board.getBoard();
    }
    /*
     * Getter
     * @param board board to be get from the instance variable
     * 
     * @return 2D int array of board
     * 
     */
    public int[][] getBoard()
    {
        return board;
    }

    /*
     * Getter
     * @param row the row which to find the value
     * @param col the row which to find the value
     * 
     * @return  int of location in the board either 0 or [1-9]
     * 
     */
    public int getValue(int row, int col)
    {
        return board[row][col];
    }


    public static void main(String[] args)
    {
        Sudoku userBoard = new Sudoku();
        Sudoku sourceBoard = new Sudoku();


        sourceBoard.initializeBoard();
        sourceBoard.generateBoard(0);
        System.out.println("\nSolved Board");
        sourceBoard.printBoard();

        userBoard.copyBoard(sourceBoard);

        userBoard.removeNumbers(63); // 81 - 21 = 60 (considered hard)

        System.out.println("\nUnsolved Board");
        userBoard.printBoard();

    }

  
    /*
     *  Initializes the board with all cells set to zero, and generates the first row randomly from a list to improve efficency
     * 
     */
    public void initializeBoard()
    {
        // Fill the board with zeros
        for (int i = 0; i < 9; i++)
        {
            for (int j = 0; j < 9; j++)
            {
                board[i][j] = 0;
            }
        }

        // Want to generate the first row using a random list of 1-9 to increase efficency of the rest of the soduku generation alogrithm
        Integer[] numbers = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9}; // ex. 1 2 3 4 5 6 7 8 9
        List<Integer> list = Arrays.asList(numbers);
        Collections.shuffle(list); // ex. 4 2 1 3 8 9 5 6 7

        // Insert first row of Sudoku using shuffle of 1-9
        for(int i = 0; i < GRID_SIZE; i++)
        {
            board[0][i] = list.get(i);
        }

    }
     /*
     * Take a complete sudoku board and remove a certain number of cells from it based on diffculty
     * 
     * @param cellsToRemove the number of cells to be removed from 81
     * 
     */
    //userBoard is the board instance here
    public void removeNumbers(int cellsToRemove)
    {
        while (cellsToRemove > 0)
        {
            // Randomly pick a row and column index
            int row = (int)(Math.random() * GRID_SIZE);
            int column = (int)(Math.random() * GRID_SIZE);
           
            if(board[row][column] != 0) //if cell is not empty
            {

                board[row][column] = 0;
                cellsToRemove--; // Keep track of how many cells are being removed

            }

        }
    }

    // Print the board, will be replaced by GUI
    public int[] printBoard() {

        int[] boardArray = new int[GRID_SIZE * GRID_SIZE];

        // Convert the board to a one-dimensional array of integers
        int index = 0;

        System.out.println("+-------+-------+-------+"); // Top border of the board
        // Print each row of the board
        for (int row = 0; row < GRID_SIZE; row++) {
            System.out.print("| ");
            // Print each column of the board
            for (int column = 0; column < GRID_SIZE; column++) {

                // Update 1D Array to use for UI
                boardArray[index] = board[row][column];
                index++;


                int value = board[row][column];
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
        // Print the board as a one-dimensional array of integers

        String arrayString = Arrays.toString(boardArray);
        arrayString = arrayString.replace("[", "").replace("]", ""); // Remove square brackets
        String[] elements = arrayString.split(", ");


        int[] array = new int[elements.length];

        for (int i = 0; i < elements.length; i++) {
            array[i] = Integer.parseInt(elements[i]);
        }

        return array;
    }


    /*
     * Generate the rest of the board using backtracking method recursive
     * Takes an integer as parameter, one that keeps track of the current cell that we want to fill with a number
     * The integer named 'currentCell' should start with a 0 value
     * 
     * @param currentCell keep track of the currentCell to be added into the board
     * 
     * @return if the game board is valid at the current stage (partial or complete)
     */
    
    public boolean generateBoard(int currentCell)
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
        if(board[row][column] != 0){
            return generateBoard(currentCell + 1);
        }

          // Want to generate the first row using a random list of 1-9 to increase efficency of the rest of the soduku generation alogrithm
          Integer[] numbers = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9}; // ex. 1 2 3 4 5 6 7 8 9
          List<Integer> list = Arrays.asList(numbers);
          Collections.shuffle(list); // ex. 4 2 1 3 8 9 5 6 7

        //for(int i = 1; i <= GRID_SIZE; i++) 
        for(int i: list)
        {
            // Check for validity
            if (isValid(i, row, column))
            { // Validate whether the cell can be filled with the value
                board[row][column] = i; // Fill the cell with the value

                if (generateBoard(currentCell + 1))
                { // Recursively call the function to fill the next cells
                    return true; // Retrun true was board is completely filled
                }
                board[row][column] = 0; // Backtrack by clearing the currentCell cell
            }
        }
        return false;
    }


    /*
     * Helper Function: Calls and invokes three methods checkGrid, checkCol, and checkRow.
     * 
     * @param  input input the value of to be checked (entered by user)
     * @param col the col 1-9 at which it the value was entered
     * @param row the row 1-9 at which it the value was entered
     * @return if the user inputted value is valid return yes
     */
    public boolean isValid(int input, int row, int col)
    {
        if(checkGrid(input, row, col) && checkCol(input, row) && checkRow(input, col))
        {
            return true;
        }
        return false;
    }

     /*
     * Check if the gameBoard has the same value present anywhere else in the row 
     * 
     * @param input input the value of to be checked (entered by user)
     * @param col the col 1-9 at which it the value was entered
     * @return if the user inputted value is valid return yes
     */
    public  boolean checkRow(int input, int col)
    {
        for(int row = 0; row < GRID_SIZE; row++)
        {
            if(board[row][col] == input)
            {
                return false;
            }
        }
        return true;
    }

    /*
     * Check if the gameBoard has the same value present anywhere else in the column 
     * 
     * @param input input the value of to be checked (entered by user)
     * @param row the row 1-9 at which it the value was entered
     * @return if the user inputted value is valid return yes
     */
    public boolean checkCol(int input, int row)
    {
        for(int col = 0; col < GRID_SIZE; col++)
        {
            if(board[row][col] == input)
            {
                return false;
            }
        }
        return true;
    }

    /*
     * Find the starting Position of the top left corner of each sub grid
     * 
     * @param input the value of to be checked
     * @param row the row 1-9 at which it the value is entered
     * @param col the col 1-9 at which it the value is entered
     * @return if the user inputted value is valid return yes
     */
    public boolean checkGrid(int input, int row, int col)
    {
        int startingRow = row - row % 3; // ex. r = 1
        int startingColumn = col - col % 3; // ex. c = 1

        for(int i = startingRow; i < startingRow + 3; i++)
        {
            for(int j = startingColumn; j < startingColumn + 3; j++)
            {
                if(board[i][j] == input)
                {
                    return false;
                }
            }

        }
        return true;
    }


     /*
     * Basic Method that copies one 2d array into the other.
     * 
     * @param tempBoard (source board)
     * @param gameBoard (destination board)
     */
    public static void copy2DArray(int[][] tempBoard, int[][] gameBoard)
    {
        // Copy the contents of the first array into the second array
        for (int i = 0; i < tempBoard.length; i++) {
            for (int j = 0; j < tempBoard[0].length; j++) {
                gameBoard[i][j] = tempBoard[i][j];
            }
        }
    }
}

