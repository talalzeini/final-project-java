
import java.util.EventListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SudokuUI extends Sudoku implements EventListener 
{

    private static CellNode[][] UIBoard = new CellNode[9][9];
    private static int[][] sourceBoard2D = new int[9][9]; 

    public static void generateUI(int diffculty, JPanel panel, JFrame frame)
    {
        Sudoku userBoard = new Sudoku();
        Sudoku sourceBoard = new Sudoku();
        JPanel gridPanel = new JPanel(new GridLayout(9, 9));

        sourceBoard.initializeBoard();
        sourceBoard.generateBoard(0);

        System.out.println("\nSolved Board");
        sourceBoard.printBoard();

        copy2DArray(sourceBoard.getBoard(), sourceBoard2D);
      

        userBoard.copyBoard(sourceBoard);

        userBoard.removeNumbers(diffculty);


         int[][] userBoard2 = userBoard.getBoard();

        for (int row = 0; row < GRID_SIZE; row++) 
        {
            for (int col = 0; col < GRID_SIZE; col++) 
            {
                int value = userBoard2[row][col];
                            
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

                            if(tempNode.getValue() == sourceBoard2D[tempRow][tempCol])
                            {
                                UIBoard[tempRow][tempCol] = tempNode;

                            }
                            else
                            {    
                                textField.setText("");
                                //TODO a JFrame popup saying wrong input
                                //TODO Highlight the row and column
                                //TODO highlight the other numbers that are the same
                                System.out.println("WRONG INPUT");

                            }

              
                        }
                    });
                        
                    textField.setHorizontalAlignment(JTextField.CENTER);

                    gridPanel.add(textField);
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
                    gridPanel.add(numberLabel);
                }
                

            }
        }

        panel.add(gridPanel, BorderLayout.CENTER);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 900);
        frame.setVisible(true);


    }

    public static void main(String[] args) {

        JFrame frame = new JFrame("Sudoku Grid");
        JPanel panel = new JPanel(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridLayout(9, 9));
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JPanel winningPanel = new JPanel(new FlowLayout());
        
        // Buttons
        JButton easyButton = new JButton("Easy");
        JButton mediumButton = new JButton("Medium");
        JButton hardButton = new JButton("Hard");

        buttonPanel.add(easyButton);
        buttonPanel.add(mediumButton);
        buttonPanel.add(hardButton);
    
        
        panel.setVisible(true);
        panel.setBackground(Color.BLUE); // make panel background color blue
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // add panel padding for more empty space

        frame.setResizable(false); // make the frame non-resizable, therefore responsive

       
        JLabel winLabel = new JLabel("Game in progress...");
        
        winningPanel.add(winLabel, BorderLayout.SOUTH);
        winLabel.setHorizontalAlignment(JTextField.CENTER);
        winLabel.setForeground(Color.WHITE);
        winLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        winLabel.setVisible(true);
        winLabel.setBackground(Color.BLUE);

        // Add the grid panel to the center of the main panel
        panel.add(gridPanel, BorderLayout.CENTER);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 900);
        frame.setVisible(true);

        

        frame.add(buttonPanel, BorderLayout.NORTH); // Display the buttonPanel at the top of the frame
        frame.add(winningPanel, BorderLayout.SOUTH); // Display the buttonPanel at the top of the frame

        // Set the background color of the button panel
        buttonPanel.setBackground(Color.BLUE);
        winningPanel.setBackground(Color.BLUE);


        frame.add(panel);
        frame.setSize(600, 600);
        frame.setVisible(true);




         easyButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    System.out.println("Easy");
                    gridPanel.setVisible(false);
                    generateUI(10, panel, frame);  

                }
            }
        );

        mediumButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    System.out.println("Medium");
                    gridPanel.setVisible(false);
                    generateUI(30, panel, frame);   
                    
                }
            }
        );

        hardButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    System.out.println("Hard");
                    gridPanel.setVisible(false);
                    generateUI(54, panel, frame);       

                }
            }
        );
        

        int newSum = 0;
        while(newSum != 405) {
            for(int row = 0; row < 9; row++) {
                for(int col = 0; col < 9; col++) {
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
        System.out.println("You win!");
        winLabel.setText("You win!");
    }
}