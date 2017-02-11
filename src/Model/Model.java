package Model;

/**
 * Model.Model.java is used to represent the game and perform every internal operation.
 * @author Yancarlos diaz
 */
public class Model {

    /* A 2D array to represent the board of the game. Only 15 of the indexes will actually contain an instance of a Peg or Hole*/
    private Node[] board;

    /**
     * Public constructor for the Model
     * The constructor initializes the board and fills it with Pegs and one Hole.
     */
    public Model(){

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
