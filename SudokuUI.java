import java.util.EventListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SudokuUI extends Sudoku implements EventListener 
{

    private static CellNode[][] UIBoard = new CellNode[9][9];


    public static void main(String[] args) {

        int sum = 0;

        Sudoku userBoard = new Sudoku();
        Sudoku sourceBoard = new Sudoku();

        sourceBoard.initializeBoard();
        sourceBoard.generateBoard(0);

        System.out.println("\nSolved Board");
        sourceBoard.printBoard();

        userBoard.copyBoard(sourceBoard);

        userBoard.removeNumbers(3); // 81 - 21 = 60 (considered hard)

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


        //My attempt at this
        int[][] userBoard2 = userBoard.getBoard();

        for (int row = 0; row < GRID_SIZE; row++) 
        {
            for (int col = 0; col < GRID_SIZE; col++) 
            {
                int value = userBoard2[row][col];
                sum += value;
                //CellNode node = new CellNode(row, col, value);
                
                // if cell == 0, create a textfield
                if(value == 0) 
                {
                    JTextField textField = new CellNode(row, col);
                    textField.addActionListener(new ActionListener() 
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                            //Cast the obj to CellNode
                            CellNode tempNode = (CellNode)e.getSource();
                            tempNode.setValue(textField.getText());
                            int tempRow = tempNode.getRow();
                            int tempCol = tempNode.getCol();
                            
                            int[][] tempSourceBoard = new int[9][9];
                            //copy2DArray(sourceBoard.getBoard(), tempSourceBoard);
                           System.out.println("row" + tempRow);
                           System.out.println("col" + tempCol);
                            
                            System.out.println("row col: " + sourceBoard.getValue(tempRow, tempCol));
                            
                            if(tempNode.getValue() == sourceBoard.getValue(tempRow, tempCol))
                            {
                                UIBoard[tempRow][tempCol] = tempNode;
                            }
                            else
                            {
                                textField.setText("");
                                //TODO a JFrame popup saying wrong input
                                System.out.println("WRONG INPUT");
                            }

                            

                        System.out.println("Text=" + textField.getText());
                        }
                    });
                        
                    textField.setHorizontalAlignment(JTextField.CENTER);
                    // Make bold lines to separate the 9 3x3 grids
                    /* 
                    textField.setBorder(BorderFactory.createMatteBorder(
                            i / 9 == 0 ? 3 : 1, // top border
                            i % 9 == 0 ? 3 : 1, // left border
                            (i / 9 + 1) % 3 == 0 ? 3 : 1, // bottom border
                            (i % 9 + 1) % 3 == 0 ? 3 : 1, // right border
                            Color.BLACK));
*/
                    gridPanel.add(textField);

                    // if cell not == 0, create a label for the number value
                }
                else 
                {

                    JLabel numberLabel = new JLabel(Integer.toString(value));
                      //Cast the obj to CellNode
                      CellNode tempNode = new CellNode(row, col);
                      tempNode.setValue(Integer.toString(value));
                      int tempRow = tempNode.getRow();
                      int tempCol = tempNode.getCol();

                      UIBoard[tempRow][tempCol] = tempNode;

                    numberLabel.setHorizontalAlignment(JTextField.CENTER);
                    // Make bold lines to separate the 9 3x3 grids
                    /* 
                    numberLabel.setBorder(BorderFactory.createMatteBorder(
                            i / 9 == 0 ? 3 : 1, // top border
                            i % 9 == 0 ? 3 : 1, // left border
                            (i / 9 + 1) % 3 == 0 ? 3 : 1, // bottom border
                            (i % 9 + 1) % 3 == 0 ? 3 : 1, // right border
                            Color.BLACK));
*/
                    gridPanel.add(numberLabel);
                }
                

            }
        }

            System.out.println("Sum of all values: " + sum);
/* 
        // Create and add the text fields to the grid panel
        for (int i = 0; i < 81; i++) 
        {


            // if cell == 0, create a textfield
            if(unsolvedBoardOneDimensional[i] == 0) 
            {
                JTextField textField = new CellNode(row, col);
                textField.addActionListener(new ActionListener() 
                {
                    public void actionPerformed(ActionEvent e)
                    {
                      //getInput(e.getSource());
                      System.out.println("Text=" + textField.getText());
                    }
                });
                    
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
            }


        }
*/

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
        

        

        int newSum = 0;
        while(newSum != 405)
        {

            for(int row = 0; row < 9; row++)
            {
                for(int col = 0; col < 9; col++)
                {
                    CellNode temp = UIBoard[row][col];

                    
                    try {
                    
                        newSum += temp.getValue();
                    } catch (NullPointerException e) {
                        continue;
                    }

                  
                    
                }
            }

            if(newSum != 405)
            {
                newSum = 0;
            }
            




        }

        System.out.println("Done with Game u win");

        

    

    }
}
