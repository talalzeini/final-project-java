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

        JFrame frame = new JFrame("Sudoku Grid");
        JPanel panel = new JPanel(new GridLayout(9, 9));

        for (int i = 0; i < 81; i++) {
            JTextField textField = new JTextField();
            textField.setHorizontalAlignment(JTextField.CENTER);
            panel.add(textField);
        }

        frame.add(panel);
        frame.setSize(500, 500);
        frame.setVisible(true);
    }
}
