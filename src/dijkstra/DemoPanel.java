package dijkstra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author ramse
 */
public class DemoPanel extends JPanel {
    final int maxCol = 15, maxRow = 10, nodeSize = 70;
    final int screenWidth = nodeSize * maxCol;
    final int screenHeight = nodeSize * maxRow;

    Node[][] node = new Node[maxCol][maxRow];
    Node startNode, goalNode, currentNode;
    
    ArrayList<Node> openList = new ArrayList<>();
    ArrayList<Node> checkedList = new ArrayList<>();
    
    boolean goalReached = false;
    int step = 0;

    public DemoPanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setLayout(new GridLayout(maxRow, maxCol));
        this.addKeyListener(new KeyHandler(this));
        this.setFocusable(true);
        
        int col = 0, row = 0;
        
        while(col < maxCol && row < maxRow) {
            node[col][row] = new Node(col, row);
            this.add(node[col][row]);
            
            col++;
            if(col == maxCol) {
                col = 0;
                row++;
            }
        }
        
        setStartNode(3, 6);
        setGoalNode(11, 3);
        
        setSolidNode(10, 2);
        setSolidNode(10, 3);
        setSolidNode(10, 4);
        setSolidNode(10, 5);
        setSolidNode(10, 6);
        setSolidNode(10, 7);
        setSolidNode(6, 2);
        setSolidNode(7, 2);
        setSolidNode(8, 2);
        setSolidNode(9, 2);
        setSolidNode(11, 7);
        setSolidNode(12, 7);
        setSolidNode(6, 1);
        
        setCostOnNodes();
    }
    
    private void setStartNode(int col, int row) {
        node[col][row].setAsStart();
        startNode = node[col][row];
        currentNode = startNode;
    }
    
    private void setGoalNode(int col, int row) {
        node[col][row].setAsGoal();
        goalNode = node[col][row];
    }
    
    private void setSolidNode(int col, int row) {
        node[col][row].setAsSolid();
    }
    
    private void setCostOnNodes() {
        int col = 0, row = 0;
        
        while(col < maxCol && row < maxRow) {
            getCost(node[col][row]);
            col++;
            if(col == maxCol) {
                col = 0;
                row++;
            }
        }
    }
    
    /**
     * 
     * Costo en G:
     *      La distancia entre el nodo actual y el nodo de inicio.
     * 
     * Costo en H:
     *      La distancia entre el nodo actual y la meta.
     * 
     * Costo en F:
     *      El costo total del nodo (G + H).
     * 
     */
    
    private void getCost(Node node) {
        // Calculo del costo G
        int xDistance = Math.abs(node.col - startNode.col);
        int yDistance = Math.abs(node.row - startNode.row);
        node.gCost = xDistance + yDistance;
        
        // Calculo del costo H
        xDistance = Math.abs(node.col - goalNode.col);
        yDistance = Math.abs(node.row - goalNode.row);
        node.hCost = xDistance + yDistance;
        
        // Calculo del costo total
        node.fCost = node.gCost + node.hCost;
    }
    
    public void search() {
        if(goalReached == false && step < 300) {
            int col = currentNode.col, row = currentNode.row;
            
            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);
            
            // Abrir lista de nodos posibles a visitar (arriba, izquierda, abajo, derecha)
            if(row - 1 >= 0)
                openNode(node[col][row - 1]);
            if(col - 1 >= 0)
                openNode(node[col - 1][row]);
            if(row + 1 < maxRow)
                openNode(node[col][row + 1]);
            if(col + 1 < maxCol)
                openNode(node[col + 1][row]);
            
            int bestNodeIndex = 0;
            // Valor de "infinito"
            int bestNodefCost = 999;
            
            for (int i = 0; i < openList.size(); i++) {
                // Comprueba si el costo del nodo es mejor que infinito
                if(openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                } else if(openList.get(i).fCost == bestNodefCost) {
                    // En caso de ser igual el costo total, comprueba que el costo g sea mejor
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }
            currentNode = openList.get(bestNodeIndex);
            
            if(currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }
        }
        step++;
    }
    
    public void autoSearch() {
        while(goalReached == false && step < 300) {
            int col = currentNode.col;
            int row = currentNode.row;
            
            currentNode.setAsChecked();
            checkedList.add(currentNode);
            openList.remove(currentNode);
            
            if(row - 1 >= 0)
                openNode(node[col][row - 1]);
            if(col - 1 >= 0)
                openNode(node[col - 1][row]);
            if(row + 1 < maxRow)
                openNode(node[col][row + 1]);
            if(col + 1 < maxCol)
                openNode(node[col + 1][row]);
            
            int bestNodeIndex = 0;
            int bestNodefCost = 999;
            
            for (int i = 0; i < openList.size(); i++) {
                if(openList.get(i).fCost < bestNodefCost) {
                    bestNodeIndex = i;
                    bestNodefCost = openList.get(i).fCost;
                } else if(openList.get(i).fCost == bestNodefCost) {
                    if(openList.get(i).gCost < openList.get(bestNodeIndex).gCost) {
                        bestNodeIndex = i;
                    }
                }
            }
            currentNode = openList.get(bestNodeIndex);
            
            if(currentNode == goalNode) {
                goalReached = true;
                trackThePath();
            }
        }
        step++; 
    }
    
    private void openNode(Node node) {
        // En caso de no estar abierto el nodo, se agrega a la lista
        if(node.open == false && node.checked == false && node.solid == false) {
            node.setAsOpen();
            node.parent = currentNode;
            openList.add(node);
        }
    }
    
    private void trackThePath() {
        // Se regresa por el camino recorrido y colorea la mejor ruta
        Node current = goalNode;
        int cost = 0;
        
        while(current != startNode) {
            current = current.parent;
            cost++;
            if(current != startNode) {
                current.setAsPath();
            }
        }
        
        JOptionPane.showConfirmDialog(this, "El costo total del recorrido fue de " + cost + " desplazamientos :)", "MEJOR RUTA ENCONTRADA...", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
    }
    
}
