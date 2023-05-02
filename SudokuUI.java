import java.util.EventListener;
import java.awt.*;
import javax.swing.*;

import javax.swing.event.MouseInputListener;

public class SudokuUI implements EventListener
{
    
    private int[][] gameBoard;
    private String title;
    GraphicsConfiguration gc;


    //Constructor
    public SudokuUI(int[][] gameBoard)
    {
        this.gameBoard = gameBoard;
    }


    public JFrame(String title, GraphicsConfiguration gc)
    {
        this.title = title;
        this.gc = gc;
    }
    


    public static void main(String[] args) {
        
        Frame frame = new JFrame("Sudoku", null);

        frame.setVisible(true);

        frame.setSize(170, 90);


        //add(component)
        //add(component)

    }
}
