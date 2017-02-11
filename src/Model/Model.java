package Model;

/**
 * Model.Model.java is used to represent the game and perform every internal operation.
 * @author Yancarlos diaz
 */
public class Model {

    /** An rray to represent the board of the game. */
    private Node[] board;

    /**
     * Public constructor for the Model
     * The constructor initializes the board and fills it with Pegs (Nodes with a value of 1)
     * It then links the Nodes that are adjacent to each other
     */
    public Model(){
        this.board = new Node[15];
        for (int i = 0; i < 15; i++){
            this.board[i] = new Node();
        }
        // Peg 0
        board[0].function([null, null, board[2], ])
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
    }

    @Override
    public String toString(){
        return "";
    }

    /**
     * Select takes an x and y coordinate in order to find the desired peg
     * The return value of this function can be used to tell the player whether a Peg has been successfully selected or not
     * This function will also change the private variable "selectedPiece" to an instance of a Peg if one is found
     * In the case of a failure, the value of "selectedPiece" will remain untouched
     * @param x The horizontal position of the Tile to access
     * @param y The vertical position of the Tile to access
     * @return If the is a peg at that position, this method will return true
     *         If there is a Hole at that position, this method will return false
     *         If there is a null in that position, this method will return false (Should not happen)
     */
    public boolean select(int x, int y){
        return false;
    }

    /**
     * Move takes an x and y coordinate and attempts to move the selectedPiece to those coordinates
     *
     * If it succeeds, the place where the selectedPiece was will become a Hole as well
     * as the piece that was in between the selectedPiece and the target.
     * Nothing needs to be done to the target.
     *
     * If it fails to do so, the selectedPiece variable will not change
     * The exception to this is the case where the coordinates point to where the selectedPiece is
     * In such case, the piece will be unselected and the variable will become null
     * @param x The horizontal position of the Tile to target
     * @param y The vertical position of the Tile to target
     * @return If the Peg moves, this method will return true
     *         If the Peg does not move, this method will return false
     *         IF the Peg is unselected, this method will return false
     */
    public boolean move(int x, int y){
        return false;
    }
}
