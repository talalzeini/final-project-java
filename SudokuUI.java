import java.util.EventListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.event.MouseInputListener;

public class SudokuUI implements EventListener
{
    final static int GRID_SIZE = 9; // size of sudoku grid 9x9

    JTextField[][] sudokuJTextFields = new JTextField[GRID_SIZE][GRID_SIZE];
    JButton timerButton;
    JPanel gridJPanel;
    JPanel inputPanel;


    //Constructor Generates the Static
    public SudokuUI()
    {

        JFrame frame = new JFrame("Sudoku Grid");
        JPanel panel = new JPanel(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridLayout(9, 9));

        // Create and add the text fields to the grid panel
        for (int i = 0; i < 81; i++) {
            JTextField textField = new JTextField();
            textField.setHorizontalAlignment(JTextField.CENTER);
            textField.setBorder(BorderFactory.createMatteBorder(
                    i / 9 == 0 ? 3 : 1, // top border
                    i % 9 == 0 ? 3 : 1, // left border
                    (i / 9 + 1) % 3 == 0 ? 3 : 1, // bottom border
                    (i % 9 + 1) % 3 == 0 ? 3 : 1, // right border
                    Color.BLACK));
            gridPanel.add(textField);
        }

       

        // Add the grid panel to the center of the main panel
        panel.add(gridPanel, BorderLayout.CENTER);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);


    }






    

    public static void main(String[] args) {

        SudokuUI instance = new SudokuUI();

       
    }
}
