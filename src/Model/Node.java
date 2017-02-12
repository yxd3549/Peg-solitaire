package Model;

/**
 * Nodes represent the Pegs that will be constantly moving in the game.
 * @author Yancarlos Diaz
 * @author Luke Shadler
 * @author Bryan Camp
 * @author Michelle Zhou
 */
public class Node {
    /**
     * An index of where the Node is in the board array.
     */
    private int index;
    /**
     * A list of adjacent Nodes
     */
    private Node[] adjNodes;
    /**
     * Boolean telling whether a Node has a Peg or not
     */
    private boolean hasPeg;

    /**
     * Public Constructor
     */
    public Node() {
        this.hasPeg = false;
    }

    /**
     * Public Constructor
     * @param isFilled: if there is a peg here when initialized
     */
    public Node(boolean isFilled, int index) {
        adjNodes = new Node[6];
        this.hasPeg = isFilled;
        this.index = index;
    }

    /**
     * toString() method override
     * @return Character based on what is contained in the node
     */
    @Override
    public String toString() {
        if (this.hasPeg) {
            return "X";
        } else {
            return "O";
        }
    }

    public boolean isPeg() {
        return this.hasPeg;
    }

    public int getIndex(){
        return this.index;
    }

    public void setAdjacentNode(int index, Node newNode) {
        this.adjNodes[index] = newNode;
    }

    public void setAdjacentNodes(Node[] newNodes){this.adjNodes = newNodes;}


    public int canMove(int index) {
        if (!hasPeg){
            return -1;
        }
        for ( int i = 0; i < 6; i++){
            if(adjNodes[i] != null && adjNodes[i].isPeg()) {
                if (adjNodes[i].adjNodes[i] != null && !adjNodes[i].adjNodes[i].isPeg() && adjNodes[i].adjNodes[i].index == index) {
                    return adjNodes[i].index;
                }
            }
        }
        return -1;
    }

    public void makeHole(){
        this.hasPeg = false;
    }

    public void makePeg(){
        this.hasPeg = true;
    }
}
