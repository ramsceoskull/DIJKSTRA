package dijkstra;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author ramse
 */
public class Node extends JButton {
    public Node parent;
    public int col, row, gCost, hCost, fCost;
    public boolean start, goal, solid, open, checked;

    public Node(int col, int row) {
        this.col = col;
        this.row = row;
        
        setBackground(Color.white);
        setForeground(Color.black);
    }
    
    public void setAsStart() {
        setBackground(Color.darkGray);
        setForeground(Color.white);
        setText("INICIO");
        start = true;
    }
    
    public void setAsGoal() {
        setBackground(Color.green);
        setForeground(Color.black);
        setText("META");
        goal = true;
    }
    
    public void setAsSolid() {
        setBackground(Color.black);
        setForeground(Color.black);
        solid = true;
    }
    
    public void setAsOpen() {
        open = true;
    }
    
    public void setAsChecked() {
        if(start == false && goal == false) {
            setBackground(Color.lightGray);
            setForeground(Color.black);
        }
        checked = true;
    }
    
    public void setAsPath() {
        setBackground(Color.green);
        setForeground(Color.black);
    }
}
