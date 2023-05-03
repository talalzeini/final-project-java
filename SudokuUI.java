import java.util.EventListener;
import java.awt.*;
import java.awt.event.*;
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


    public static void main(String[] args) {
        
        JFrame frame = new JFrame("Sudoku");
        frame.setSize(1100, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
       
        JPanel p = new JPanel(new BorderLayout()); //PREFERRED!


        //add(component)
        //add(component)

    }
}
