import java.util.EventListener;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class SudokuUI extends Sudoku implements EventListener 
{
    private static CellNode[][] UIBoard = new CellNode[GRID_SIZE][GRID_SIZE];
    private static int[][] sourceBoard2D = new int[GRID_SIZE][GRID_SIZE]; 
    static int steps = 1;
    
    public static void main(String[] args) {

        JFrame frame = new JFrame("Sudoku Grid");
        JPanel panel = new JPanel(new BorderLayout());
        JPanel gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JPanel winningPanel = new JPanel(new FlowLayout());
 
        // Buttons
        JButton easyButton = new JButton("Easy");
        JButton mediumButton = new JButton("Medium");
        JButton hardButton = new JButton("Hard");
        JButton solve = new JButton("Solve");

        
        easyButton.setMargin(new Insets(10, 20, 10, 20));
        mediumButton.setMargin(new Insets(10, 20, 10, 20));
        hardButton.setMargin(new Insets(10, 20, 10, 20));
        solve.setMargin(new Insets(10, 20, 10, 20));
        
        buttonPanel.add(easyButton);
        buttonPanel.add(mediumButton);
        buttonPanel.add(hardButton);
       
    
        
        panel.setVisible(true);
        panel.setBackground(Color.BLUE); // make panel background color blue
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50)); // add panel padding for more empty space

        frame.setResizable(false); // make the frame non-resizable, therefore responsive

       
        JLabel winLabel = new JLabel("Game in progress...");
        winLabel.setOpaque(true); // make the label opaque
        winLabel.setBackground(Color.RED);
        
        winLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        
        JLabel stepsLabel = new JLabel("Steps: 0");
        
        winningPanel.add(winLabel, BorderLayout.SOUTH);
        winningPanel.add(solve, BorderLayout.SOUTH);
        winningPanel.add(stepsLabel, BorderLayout.WEST);
        winLabel.setHorizontalAlignment(JTextField.CENTER);
        winLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        stepsLabel.setForeground(Color.WHITE);
        stepsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

       
        winLabel.setBackground(Color.YELLOW);
        winLabel.setForeground(Color.BLACK);
        
        
        winLabel.setVisible(true);

        // Add the grid panel to the center of the main panel
        panel.add(gridPanel, BorderLayout.CENTER);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);

        

        frame.add(buttonPanel, BorderLayout.NORTH); // Display the buttonPanel at the top of the frame
        frame.add(winningPanel, BorderLayout.SOUTH); // Display the winningPannel at the bottom of the frame
        

        // Set the background color of the button panel
        buttonPanel.setBackground(Color.BLUE);
        winningPanel.setBackground(Color.BLUE);


        frame.add(panel);
        frame.setSize(600, 600);
        frame.setVisible(true);
        
        winningPanel.setVisible(false);

         easyButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {  
                    gridPanel.removeAll();
                    winningPanel.setVisible(true);
                    winLabel.setText("Game in progress...");
                    winLabel.setBackground(Color.YELLOW);
                    winLabel.setForeground(Color.BLACK);
                    generateUI(10, panel, gridPanel, frame, winLabel, stepsLabel);  

                }
            }
        );

        mediumButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {  
                	gridPanel.removeAll();
                    winningPanel.setVisible(true);
                    winLabel.setText("Game in progress...");
                    winLabel.setBackground(Color.YELLOW);
                    winLabel.setForeground(Color.BLACK);
                    generateUI(30, panel, gridPanel, frame, winLabel, stepsLabel);   
                    
                }
            }
        );

        hardButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {  
                    gridPanel.removeAll();
                    winningPanel.setVisible(true);
                    winLabel.setText("Game in progress...");
                    winLabel.setBackground(Color.YELLOW);
                    winLabel.setForeground(Color.BLACK);
                    generateUI(54, panel, gridPanel, frame, winLabel, stepsLabel);       

                }
            }
        );
        solve.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                gridPanel.removeAll();
                winningPanel.setVisible(true);
                solveBoard(panel, gridPanel, frame, winLabel, stepsLabel);    
                

            }
        }
    );
        

        int newSum = 0;
        while(newSum != 405) {
            for(int row = 0; row < GRID_SIZE; row++) {
                for(int col = 0; col < GRID_SIZE; col++) {
                    CellNode temp = UIBoard[row][col];
                    try 
                    {
        
                        newSum += temp.getValue();
                        
                    } 
                    catch (NullPointerException e) 
                    {
                        continue;
                    }  

                }
            }
            if(newSum != 405)
            {
                newSum = 0;
            }
        }
        winLabel.setBackground(Color.GREEN);
        winLabel.setForeground(Color.BLACK);
        winLabel.setText("You win!");
    }


    public static void generateUI(int diffculty, JPanel panel, JPanel gridPanel, JFrame frame, JLabel winLabel, JLabel stepsLabel)
    {
    	
    	steps = 0;
    	stepsLabel.setText("Steps: " + Integer.toString(steps));
        UIBoardClear();
    	Sudoku userBoard = new Sudoku();
        Sudoku sourceBoard = new Sudoku();
   

        sourceBoard.initializeBoard();
        sourceBoard.generateBoard(0);

        copy2DArray(sourceBoard.getBoard(), sourceBoard2D);
      

        userBoard.copyBoard(sourceBoard);

        userBoard.removeNumbers(diffculty);
        
        
        gridPanel.setBackground(Color.BLUE);

        int[][] userBoard2 = userBoard.getBoard();

        for (int row = 0; row < GRID_SIZE; row++) 
        {
            for (int col = 0; col < GRID_SIZE; col++) 
            {
                int value = userBoard2[row][col];
                int top = 0, left = 0, bottom = 0, right = 0;
                            
                // if cell == 0, create a textfield
                if(value == 0) 
                {
                    JTextField textField = new CellNode(row, col);
                    textField.setBackground(Color.LIGHT_GRAY);
                    textField.setForeground(Color.BLACK);
                    
                    if (row % 3 == 0) {
                        top = 3;
                    }
                    if (col % 3 == 0) {
                        left = 3;
                    }
                    if (row == 8) {
                        bottom = 3;
                    }
                    if (col == 8) {
                        right = 3;
                    }
                    textField.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
           
                    gridPanel.add(textField);
                    
                    
                    textField.addActionListener(new ActionListener() 
                    {
                        public void actionPerformed(ActionEvent e)
                        {
                            //Cast the obj to CellNode
                            CellNode tempNode = (CellNode)e.getSource();
                            try {
                                tempNode.setValue(textField.getText());
                            } 
                            catch (NumberFormatException ee) 
                            {
                                tempNode.setValue("0");
                                
                            }
                          
                            
                            int tempRow = tempNode.getRow();
                            int tempCol = tempNode.getCol();

                            if(tempNode.getValue() == sourceBoard2D[tempRow][tempCol])
                            {
                                UIBoard[tempRow][tempCol] = tempNode;
                                winLabel.setText("Game in progress...");
                                winLabel.setBackground(Color.YELLOW);
                                winLabel.setForeground(Color.BLACK);
                            }
                            else
                            {    
                            	steps += 1;
                            	stepsLabel.setText("Steps: " + Integer.toString(steps));
                                textField.setText("");
                                winLabel.setText("Wrong Input!");
                                winLabel.setBackground(Color.RED);
                                winLabel.setForeground(Color.WHITE);
                               

                                
                                //TODO Highlight the row and column
                                //TODO highlight the other numbers that are the same

                            }

              
                        }
                    });
                        
                    textField.setHorizontalAlignment(JTextField.CENTER);

                    gridPanel.add(textField);
                }
                else 
                {

                    JLabel numberLabel = new JLabel(Integer.toString(value));
                    numberLabel.setOpaque(true); // make the label opaque
                    numberLabel.setBackground(Color.WHITE);
                    if (row % 3 == 0) {
                        top = 3;
                    }
                    if (col % 3 == 0) {
                        left = 3;
                    }
                    if (row == 8) {
                        bottom = 3;
                    }
                    if (col == 8) {
                        right = 3;
                    }
                    numberLabel.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
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

           updateBoard();


        }

        
        panel.add(gridPanel, BorderLayout.CENTER);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);

   
    }
    
   

    public static void solveBoard(JPanel panel, JPanel gridPanel, JFrame frame, JLabel winLabel, JLabel stepsLabel)
    {
    
        gridPanel.setBackground(Color.BLUE);
        
        
        int[][] userBoard2 = new int[GRID_SIZE][GRID_SIZE];
        copy2DArray(sourceBoard2D, userBoard2);

        for (int row = 0; row < GRID_SIZE; row++) 
        {
            for (int col = 0; col < GRID_SIZE; col++) 
            {
                int value = userBoard2[row][col];
                
        
                    JLabel numberLabel = new JLabel(Integer.toString(value));
                    
                    try {
                        if(UIBoard[row][col].getValue() == 0)
                        {
                            numberLabel.setOpaque(true); // make the label opaque
                            numberLabel.setBackground(Color.PINK);
                        }
                        else
                        {
                            numberLabel.setOpaque(true); // make the label opaque
                            numberLabel.setBackground(Color.WHITE);
                        }
                        
                    } catch (NullPointerException e) 
                    {
                        // do nothing skip
                    }
                   
                    
                    int top = 0, left = 0, bottom = 0, right = 0;
		                    if (row % 3 == 0) {
		                        top = 3;
		                    }
		                    if (col % 3 == 0) {
		                        left = 3;
		                    }
		                    if (row == 8) {
		                        bottom = 3;
		                    }
		                    if (col == 8) {
		                        right = 3;
		                    }
	                    numberLabel.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
                        CellNode tempNode = new CellNode(row, col);
                        tempNode.setValue(Integer.toString(value));
                        int tempRow = tempNode.getRow();
                        int tempCol = tempNode.getCol();

                        UIBoard[tempRow][tempCol] = tempNode;

                    numberLabel.setHorizontalAlignment(JTextField.CENTER);
                    gridPanel.add(numberLabel);
                
            

            }
        }

        panel.add(gridPanel, BorderLayout.CENTER);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);
        frame.setVisible(true);

    
    }

    
    public static void UIBoardClear()
    {
        for(int row = 0; row < GRID_SIZE; row++)
        {
            for(int col = 0; col < GRID_SIZE; col++)
            {
                UIBoard[row][col] = null;
            }
        }
    }

    public static void updateBoard() 
    {
        for (int row = 0; row < GRID_SIZE; row++) 
        {
          for (int col = 0; col < GRID_SIZE; col++) 
            {
                try {
                    if(UIBoard[row][col].getValue() == 0)
                    {
                        UIBoard[row][col].setValue("");
                    }
                } catch (NullPointerException e) 
                {
                    //do nothing continue to next iteration
                }
               
               
            }
        }
    }
}