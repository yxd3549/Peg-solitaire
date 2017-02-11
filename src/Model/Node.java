package Model;

/**
 * Nodes represent the Pegs that will be constantly moving in the game.
 * @author Yancarlos Diaz
 * @author Luke Shadler
 * @author Bryan Camp
 * @author Michelle Zhou
 */
public class Node {

    /** A list of adjacent Nodes */
    private Node [] adjNodes;
    /** Boolean telling whether a Node has a Peg or not*/
    private boolean hasPeg;

    /**
     * Public contro
     */
    public Node(){
        hasPeg = false;

    }
    public Node(int isFilled){

    }
    public Node(int isFilled, Node [] adjacentNodes){
    }
    public Node(Node [] adjacentNode){
    }

    @Override
    public String toString(){
        String s = "";
        if
    }
}
    public boolean isPeg(){
        return this.hasPeg;
    }

    public void setAdjacentNode(int index, Node newNode){
        this.adjNodes[index] = newNode;
    }

    public void getAdjNode(int index){
        return this.adjNodes[index];
    }
