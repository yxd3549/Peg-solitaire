package Model;

/**
 * Nodes represent the places where a peg can be placed in the game.
 * @author Yancarlos Diaz
 * @author Luke Shadleror Bryan Camp
 * @author Michelle Zhou
 */
public class Node {
    /** The index where the Node is in the board array */
    private int index;
    /** A list of adjacent Nodes */
    private Node[] adjNodes;
    /** Whether a Node has a Peg or not */
    private boolean hasPeg;

    /** Public Constructor
     * @param n: Creates this node and sets up its adjacent nodes
     */
    public Node(Node n) {
        this.index = n.index;
        this.adjNodes = new Node[6];
        for(int i = 0; i < 6; i++){
            if(n.adjNodes[i] == null){
                this.adjNodes[i] = null;
            }
            else {
                this.adjNodes[i] = new Node(n.adjNodes[i].isPeg(), n.adjNodes[i].index);
            }
        }
        this.hasPeg = n.hasPeg;
    }

    /** Public Constructor
     * @param isFilled: Whether there is a peg here when initialized
     * @param index: Index of node in the board array
     */
    public Node(boolean isFilled, int index) {
        adjNodes = new Node[6];
        this.hasPeg = isFilled;
        this.index = index;
    }

    /** toString() method override
     * @return Character based on whether node contains a peg. */
    @Override
    public String toString() {
        if (this.hasPeg) {
            return "X";
        } else {
            return "O";
        }
    }

    /** Getter for hasPeg
     * @return Whether the node contains a peg */
    public boolean isPeg() {
        return this.hasPeg;
    }

    /** Getter for index
     * @return The index in the board of this node */
    public int getIndex(){
        return this.index;
    }

    /** Adds the given Node to the adjacent nodes.
     * @param index: Index of node in board array
     * @param newNode: Node that is adjacent to the node of index [index] in the board array */
    public void setAdjacentNode(int index, Node newNode) {
        this.adjNodes[index] = newNode;
    }

    /** Sets the adjacent nodes.
     * @param newNodes: Array of adjacent Nodes */
    public void setAdjacentNodes(Node[] newNodes){this.adjNodes = newNodes;}

    /** Verifies the move the user wants to make.
     * @param: index: Index of the spot in the board array you want to move to
     * @return The index in the board array of the node that is being jumped over. [-1] of the move
     * cannnot be made. */
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

    /** Removes the peg on this node. */
    public void makeHole(){
        this.hasPeg = false;
    }

    /** Puts a peg on this node. */
    public void makePeg(){
        this.hasPeg = true;
    }
}
