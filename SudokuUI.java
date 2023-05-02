import java.util.EventListener;
import java.awt.*;
import javax.swing.*;

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
