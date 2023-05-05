import java.util.EventListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

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

        Sudoku userBoard = new Sudoku();
        Sudoku sourceBoard = new Sudoku();

        int sum = 0;


        sourceBoard.initializeBoard();
        sourceBoard.generateBoard(0);
        System.out.println("\nSolved Board");
        sourceBoard.printBoard();

        userBoard.copyBoard(sourceBoard);

        userBoard.removeNumbers(63); // 81 - 21 = 60 (considered hard)

        System.out.println("\nUnsolved Board");
        int[] unsolvedBoardOneDimensional = userBoard.printBoard();


//         System.out.println(unsolvedBoard[0]);



        JFrame frame = new JFrame("Sudoku Grid");
        JPanel panel = new JPanel(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridLayout(9, 9));
        JPanel buttonPanel = new JPanel(new FlowLayout());

        panel.setBackground(Color.BLUE); // make panel background color blue
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // add panel padding for more empty space

        frame.setResizable(false); // make the frame non-resizable, therefore responsive

        // Create and add the text fields to the grid panel
        for (int i = 0; i < 81; i++) {

            // if cell == 0, create a textfield
            if(unsolvedBoardOneDimensional[i] == 0) {
                JTextField textField = new JTextField();
                textField.setHorizontalAlignment(JTextField.CENTER);
                // Make bold lines to separate the 9 3x3 grids
                textField.setBorder(BorderFactory.createMatteBorder(
                        i / 9 == 0 ? 3 : 1, // top border
                        i % 9 == 0 ? 3 : 1, // left border
                        (i / 9 + 1) % 3 == 0 ? 3 : 1, // bottom border
                        (i % 9 + 1) % 3 == 0 ? 3 : 1, // right border
                        Color.BLACK));

                gridPanel.add(textField);

                // if cell not == 0, create a label for the number value
            }
            else 
            {
                JLabel numberLabel = new JLabel(Integer.toString(unsolvedBoardOneDimensional[i]));
                numberLabel.setHorizontalAlignment(JTextField.CENTER);
                // Make bold lines to separate the 9 3x3 grids
                numberLabel.setBorder(BorderFactory.createMatteBorder(
                        i / 9 == 0 ? 3 : 1, // top border
                        i % 9 == 0 ? 3 : 1, // left border
                        (i / 9 + 1) % 3 == 0 ? 3 : 1, // bottom border
                        (i % 9 + 1) % 3 == 0 ? 3 : 1, // right border
                        Color.BLACK));

                gridPanel.add(numberLabel);
                sum += unsolvedBoardOneDimensional[i];
            }


        }


        // Add the grid panel to the center of the main panel
        panel.add(gridPanel, BorderLayout.CENTER);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setVisible(true);

        // Add three buttons for selecting the difficulty level
        JButton easyButton = new JButton("Easy");
        JButton mediumButton = new JButton("Medium");
        JButton hardButton = new JButton("Hard");

        buttonPanel.add(easyButton);
        buttonPanel.add(mediumButton);
        buttonPanel.add(hardButton);


        easyButton.addActionListener(new ActionListener()
                                     {
                                         public void actionPerformed(ActionEvent e)
                                         {
                                             System.out.println("Easy");
                                         }
                                     }
        );

        mediumButton.addActionListener(new ActionListener()
                                       {
                                           public void actionPerformed(ActionEvent e)
                                           {
                                               System.out.println("Medium");
                                           }
                                       }
        );

        hardButton.addActionListener(new ActionListener()
                                     {
                                         public void actionPerformed(ActionEvent e)
                                         {
                                             System.out.println("Hard");
                                         }
                                     }
        );

        frame.add(buttonPanel, BorderLayout.NORTH); // Display the buttonPanel at the top of the frame

        // Set the background color of the button panel
        buttonPanel.setBackground(Color.BLUE);

        frame.add(panel);
        frame.setSize(500, 500);
        frame.setVisible(true);

        frame.addMouseListener(new MouseAdapter() 
        {
            public void mouseClicked(MouseEvent e) 
            {
                
            }
        });
      
    
        
        
        while(sum != 1215)
        {


            

            JTextField box = new JTextField(" Enter Initial Velocity");
            String velocity_str = box.getText();

            double initialvelocity = Double.parseDouble(velocity_str);

        }

    }
}
