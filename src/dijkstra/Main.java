package dijkstra;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author ramse
 */
public class Main {

    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.add(new DemoPanel());
        window.setTitle("DIJKSTRA");
        
        JOptionPane.showConfirmDialog(window, "Como desear activar el juego?\n1. Presiona enter para ejecutar automaticamente el algoritmo.\n2. Manten presionado backspace para ejecutar el paso a paso.", "Bienvenido!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE);
        
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
    
}
