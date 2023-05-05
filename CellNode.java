import javax.swing.JTextField;
import java.util.EventListener;

public class CellNode extends JTextField
{

    private int row;
    private int col;
    private int value;


    public CellNode(int row, int col, int value)
    {
        
        super(value);
        this.row = row;
        this.col = col;
        this.value = value;

        if(value == 0)
        {
            super.setEditable(true);
        }
        else
        {
            super.setEditable(false);
        }


    }    
}
