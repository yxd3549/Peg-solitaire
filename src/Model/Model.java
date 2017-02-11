package Model;

/**
 * Model.Model.java is used to represent the game and perform every internal operation.
 * @author Yancarlos diaz
 */
public class Model {

    /** An rray to represent the board of the game. */
    private Node[] board;
    /** The currently selected peg */
    private Node selected;
    /** The index of the currently selected peg */
    private int selectedIndex;

    /**
     * Public constructor for the Model
     * The constructor initializes the board and fills it with Pegs (Nodes with a value of 1)
     * It then links the Nodes that are adjacent to each other
     */
    public Model(){
        this.board = new Node[15];
        for (int i = 0; i < 15; i++){
            this.board[i] = new Node(true, i);
        }
        // Peg 0
        board[0].setAdjacentNode(2, board[2]);
        board[0].setAdjacentNode(3, board[1]);
        // Peg 1
        board[1].setAdjacentNode(0, board[0]);
        board[1].setAdjacentNode(1, board[2]);
        board[1].setAdjacentNode(3, board[3]);
        board[1].setAdjacentNode(2, board[4]);
        // Peg 2
        board[2].setAdjacentNode(5, board[0]);
        board[2].setAdjacentNode(4, board[1]);
        board[2].setAdjacentNode(3, board[4]);
        board[2].setAdjacentNode(2, board[5]);
        // Peg 3
        board[3].setAdjacentNode(0, board[1]);
        board[3].setAdjacentNode(1, board[4]);
        board[3].setAdjacentNode(2, board[7]);
        board[3].setAdjacentNode(3, board[6]);
        // Peg 4
        board[4].setAdjacentNode(0, board[2]);
        board[4].setAdjacentNode(1, board[5]);
        board[4].setAdjacentNode(2, board[8]);
        board[4].setAdjacentNode(3, board[7]);
        board[4].setAdjacentNode(4, board[3]);
        board[4].setAdjacentNode(5, board[1]);
        // Peg 5
        board[5].setAdjacentNode(2, board[9]);
        board[5].setAdjacentNode(3, board[8]);
        board[5].setAdjacentNode(4, board[4]);
        board[5].setAdjacentNode(5, board[2]);
        // Peg 6
        board[6].setAdjacentNode(0, board[3]);
        board[6].setAdjacentNode(1, board[7]);
        board[6].setAdjacentNode(2, board[11]);
        board[6].setAdjacentNode(3, board[10]);
        // Peg 7
        board[7].setAdjacentNode(0, board[4]);
        board[7].setAdjacentNode(1, board[8]);
        board[7].setAdjacentNode(2, board[12]);
        board[7].setAdjacentNode(3, board[11]);
        board[7].setAdjacentNode(4, board[6]);
        board[7].setAdjacentNode(5, board[3]);
        // Peg 8
        board[8].setAdjacentNode(0, board[3]);
        board[8].setAdjacentNode(1, board[9]);
        board[8].setAdjacentNode(2, board[13]);
        board[8].setAdjacentNode(3, board[12]);
        board[8].setAdjacentNode(4, board[7]);
        board[8].setAdjacentNode(5, board[4]);
        // Peg 9
        board[9].setAdjacentNode(2, board[14]);
        board[9].setAdjacentNode(3, board[13]);
        board[9].setAdjacentNode(4, board[8]);
        board[9].setAdjacentNode(5, board[5]);
        // Peg 10
        board[10].setAdjacentNode(0, board[6]);
        board[10].setAdjacentNode(1, board[12]);
        // Peg 11
        board[11].setAdjacentNode(0, board[7]);
        board[11].setAdjacentNode(1, board[12]);
        board[11].setAdjacentNode(4, board[10]);
        board[11].setAdjacentNode(5, board[6]);
        // Peg 12
        board[12].setAdjacentNode(0, board[8]);
        board[12].setAdjacentNode(1, board[13]);
        board[12].setAdjacentNode(4, board[11]);
        board[12].setAdjacentNode(5, board[7]);
        // Peg 13
        board[13].setAdjacentNode(0, board[9]);
        board[13].setAdjacentNode(1, board[14]);
        board[13].setAdjacentNode(4, board[12]);
        board[13].setAdjacentNode(5, board[8]);
        // Peg 14
        board[14].setAdjacentNode(4, board[13]);
        board[14].setAdjacentNode(5, board[9]);
    }

    @Override
    public String toString(){
        String s = "     " + board[0].toString()
                + "\n   " + board[1].toString() + " " + board[2].toString()
                + "\n  " + board[3].toString() + " " + board[4].toString() + " " + board[5].toString()
                + "\n " + board[6].toString() + " " + board[7].toString() + " " + board[8].toString() + " " + board[9].toString()
                + "\n" + board[10].toString() + " " + board[11].toString() + " " + board[12].toString() + " " + board[13].toString() + " " + board[14].toString();
        return s;
    }

    /**
     *
     */
    public boolean select(int id){
        Node node = board[id];
        if (id > 15){
            return false;
        }
        else if(!node.isPeg()){
            return false;
        }
        else if(node.isPeg()){
            this.selected = node;
            this.selectedIndex = id;
            return true;
        }
        return false; // Should not be reached
    }

    /**
     *
     */
    public boolean move(int id){
        int middleMan = selected.canMove(id);
        if(middleMan == -1){
            return false;
        }
        else{

        }
    }
}
