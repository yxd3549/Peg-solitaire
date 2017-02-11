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
    public Node(boolean isFilled) {
        adjNodes = new Node[6];
        this.hasPeg = isFilled;
    }

    /**
     * toString() method override
     * @return Character based on what is contained in the node
     */
    @Override
    public String toString() {
        if (this.hasPeg) {
            return "x";
        } else {
            return "o";
        }
    }

    public boolean isPeg() {
        return this.hasPeg;
    }

    public void setAdjacentNode(int index, Node newNode) {
        this.adjNodes[index] = newNode;
    }

    public void setAdjacentNodes(Node[] newNodes){this.adjNodes = newNodes;}

    public Node getAdjNode(int index) {
        return this.adjNodes[index];
    }

    public int canMove(int index) {
        if (!hasPeg){
            return -1;
        }


        return ;
    }
}
