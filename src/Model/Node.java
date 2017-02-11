package Model;

/**
 * Pegs are the pieces in the game that will be constantly moving
 * @author Yancarlos Diaz
 */
public class Node {
    Node [] adjNodes;
    int isFilled;

    public Node(){
        isFilled = 0;

    }
    public Node(int isFilled){

    }
    public Node(int isFilled, Node [] adjacentNodes){
    }
    public Node(Node [] adjacentNode){
    }

    public int hasNode(){
        return this.isFilled;
    }

    public void setAdjacentNode(int index, Node newNode){
        this.adjNodes[index] = newNode;
    }

    public void getAdjNode(int index){
        return this.adjNodes[index];
    }
