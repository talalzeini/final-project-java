import javax.swing.JTextField;

public class CellNode extends JTextField
{
    //Instance Variables
    private int row;
    private int col;
    private int value;

    /*
     * Getter
     * @return row 1-9
     */
    public int getRow() {
        return row;
    }
    /*
     * Getter
     * @return col 1-9
     */
    public int getCol() {
        return col;
    }

    /*
     * Getter
     * @return value an int 1-9
     */
    public int getValue() {
        return value;
    }

    /*
     * Setter
     * @param value to be set to in integer value
     */
    public void setValue(String value) 
    {
        this.value = Integer.parseInt(value);
       
    }


    /*
     * Constructor
     * @param row at which cellNode is desginated to
     * @param col at which cellNode is desginated to
     */
    public CellNode(int row, int col)
    {
        
        super();
        this.row = row;
        this.col = col;
     

    }    
}
