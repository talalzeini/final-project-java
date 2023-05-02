import java.util.EventListener;

import javax.swing.event.MouseInputListener;

public class SudokuUI implements EventListener
{
    
    private int[][] gameBoard;


    //Constructor
    public SudokuUI(int[][] gameBoard)
    {
        this.gameBoard = gameBoard;
    }

    

}
