package dijkstra;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author ramse
 */
public class KeyHandler implements KeyListener {
    private final DemoPanel dp;
    
    public KeyHandler(DemoPanel dp) {
        this.dp = dp;
    }
    
    
    @Override
    public void keyTyped(KeyEvent ke) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int key = ke.getKeyCode();
        
        if(key == KeyEvent.VK_ENTER)
            dp.autoSearch();
        else if(key == KeyEvent.VK_BACK_SPACE)
            dp.search();
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
