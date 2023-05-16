Sudoku 

Our goal was to design and create a working sudoku game.

Using our own understanding of how a backtracking algorithm for Sudoku board creation is done on the internet, we implemented a simple recursive algorithm which when resulted in breaking the 3 fundamental rules of Sudoku to go back until where the generated Sudoku board is last valid. 

Supporting methods that support the generation of the Sudoku board, contain of the basic 3 rules of Sudoku, each row should have numbers 1-9, no repeats, each column should have numbers 1-9, no repeats, each 3x3 quadrant should have numbers 1-9, no repeats. 

Once we have reached a unique solution for our Sudoku Board, we randomly choose cells within the 81 cells to remove. This is what decides the difficulty of the Sudoku board that will be presented to the user. With 17 remaining cells being considered the bare minimum of cells needed to complete a board from partial state to complete. The other difficulties we decided on are relative to hardest difficulty, with the ease to complete being directly proportional to the number of cells that are already filled in for the user. 

Once we have 2 important two dimensional arrays one being the answer key (master key) and the other being the board going to be presented to the user, we have to present it to the user.

The User interface aspect of this project

The use of Class design is now applied to this part of the project as we need to identify certain components and aspects of the code which let us reference the and accomplish everything we need to make a fully functional sudoku game. 

Before generating the UI we had the boards stored in a primitive int two dimensional array, but now our Sudoku Game needs to bridge the gap between the data structure that holds the board and the users inputs. And graphical user interface outputs. 

This is considered a complex data object, and we need to be able to store all the identifiable characteristics of the board so we can go back and reference the complex two dimensional array data structure. The row index, column index, and the value which is being stored at its respective cell. 


Pitfalls:

We did not get to implement the creature comforts that the user could experience which could aid them in solving the board, or assisting them in when they enter in a wrong input. These two things are highlighting the row and column when selected, or entered in a wrong input last move and highlight the other numbers that are the same in the entire 9x9 grid to help the user discern the pattern of the board to aid them in solving the sudoku.


Input validation: We Use JLabels and JTextFields as our building blocks to assemble our sudoku grid in the UI portion. We did not implement a separate input keypad panel onto the frame, so selecting, typing in a value, pressed enter key to let the JTextField know it has been entered and to do it accordingly. We only accept integers in our input validation and set the JTextField back to its blank state if the input is wrong. 

We did not use MouseListeners to determine when focus is lost and moved so we can automatically select the JTextField that the cursor was hovering over. This can reduce the time and streamline the process to create a more user friendly experience.

We keep track of how many times the user enters a wrong input, but we do not penalize the user in our current state, this can be added by making a component which ends the game after a certain allocated amount of tries are used up. A JCheckbox would also be appropriate in the venture of limiting user tries as the user can select if they want to limit their tries. 


In our implementation of the JTextFields and CellNode class which inherits the JTextField we could not later set the background of our JTextFields after we initialized them in the beginning. This could be resolved by redesigning the CellNode class, and including methods within the CellNode class to be within scope so we can change the status of certain boxes in the Sudoku grid. We could not get our grid to act dynamically for more complex features. 



