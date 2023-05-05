import javax.swing.JTextField;
import java.util.EventListener;

public class CellNode extends JTextField
{

    private int row;
    private int col;
    private int value;

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getValue() {
        return value;
    }

    public void setValue(String value) 
    {
        this.value = Integer.parseInt(value);
       
    }


    public CellNode(int row, int col)
    {
        
        super();
        this.row = row;
        this.col = col;
     

    }    
}
