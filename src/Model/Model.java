package Model;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Observable;
/**
 * Model.Model.java is used to represent the game and perform every internal operation.
 * @author Yancarlos diaz
 */
public class Model extends Observable{

    /** An array to represent the board of the game. */
    private Node[] board;
    /** The currently selected peg */
    private Node selected;
    /** Player has won if this equals 14 */
    private int points;
    private ArrayList<Move> moves = new ArrayList<Move>();


    /**
     * Constructor for copying the model from a previous Object
     * @param model Model object to copy
     */
    public Model(Model model){
        this.board = new Node[15];
        this.selected = model.selected;
        this.points = model.points;
        this.moves = model.moves;
        for(int i = 0; i < 15; i++){
            this.board[i] = new Node(model.board[i]);
        }
        for(int i = 0; i < 15; ++i){
            int [] tempAdj = this.getAdjInds(i);
            for(int j = 0; j<6; j++){
                if(tempAdj[j] != -1)
                    board[i].setAdjacentNode(j,board[tempAdj[j]]);
            }
        }
    }
    /**
     * Public constructor for the Model
     * The constructor initializes the board and fills it with Pegs (Nodes with a value of 1)
     * It then links the Nodes that are adjacent to each other
     */
    public Model(){
        points = 0;
        this.board = new Node[15];
        for (int i = 0; i < 15; i++){
            this.board[i] = new Node(true, i);
        }

        for(int i = 0; i < 15; ++i){
            int [] tempAdj = this.getAdjInds(i);
            for(int j = 0; j<6; j++){
                if(tempAdj[j] != -1)
                    board[i].setAdjacentNode(j,board[tempAdj[j]]);
            }
        }

    }

    /**
     * Plain text toString override
     * @return plain text representation of current board state
     */
    @Override
    public String toString(){
        String s = "    " + this.board[0].toString()
                + "\n   " + this.board[1].toString()  + " "   + this.board[2].toString()
                + "\n  "  + this.board[3].toString()  + " "   + this.board[4].toString();
        s       +=" "     + this.board[5].toString()  + "\n " + this.board[6].toString() + " " + this.board[7].toString()
                + " "     + this.board[8].toString()  + " "   + this.board[9].toString()
                + "\n"    + this.board[10].toString() + " "   + this.board[11].toString() + " " + board[12].toString()
                + " "     + this.board[13].toString()  + " " +  this.board[14].toString() + "\n";
        return s;
    }

    /**
     * Tests for ability to select starting index
     * @param id index to test
     * @return true if selection is valid
     */
    public boolean select(int id) {
        if (id >= 15 || id < 0){
            notifyObservers();
            return false;
        }
        Node node = this.board[id];
        if(!node.isPeg()){
            notifyObservers();
            return false;
        }
        else {
            this.selected = node;
            notifyObservers();
            return true;
        }
    }

    /**
     * Tests for moving to destination index and then moves
     * @param id index of destination
     * @return true if move was performed
     */
    public boolean move(int id){
        int middleMan = this.selected.canMove(id);
        if(middleMan == -1){
            setChanged();
            notifyObservers();
            return false;
        } else {
            this.board[this.selected.getIndex()].makeHole();
            this.board[middleMan].makeHole();
            this.board[id].makePeg();
            Move toAdd = new Move(this.selected.getIndex(), id);
            this.moves.add(toAdd);
            this.selected = null;
            points++;
            //setChanged();
            //notifyObservers();
            return true;
        }
    }

    /**
     * Creates a hole at given index and notifies GUI
     * @param id index to create hole
     */
    public void remove(int id) {
        this.board[id].makeHole();
        setChanged();
        notifyObservers();
    }

    /**
     * Tests for win condition
     * @return true if the player has won
     */
    public boolean hasWon() {
        int total = 0;
        for(Node i: this.board){
            total += (i.isPeg()) ? 1 : 0;
        }
        return total == 1;
    }

    /**
     * Get lower and upper bounds of given row
     * @param row row
     * @return lower and upper bounds in int[2]
     */
    public int[] getBounds(int row) {
        int[] result = new int[2];
        result[0] = (row) * (row + 1) / 2;
        result[1] = (row) * (row + 3) / 2;
        return result;
    }

    /**
     * Return row index
     * @param index node index
     * @return row index
     */
    public int getRow(int index) {
        return (int) (0.5 * Math.sqrt(1.0 + 8.0 * index) - 0.5);
    }


    /**
     * gets the adjacent indices of given node index
     * @param index node index
     * @return size 6 array containing neighboring indices
     */
    public int[] getAdjInds(int index) {
        int row = this.getRow(index);
        int[] bounds = this.getBounds(row);
        int[] upBnds = this.getBounds(row - 1);
        int[] dnBnds = this.getBounds(row + 1);
        int[] result = new int[6];
        result[0] = (upBnds[0] + (index - bounds[0]) <= upBnds[1]) ? upBnds[0] + (index - bounds[0]) : -1;
        result[1] = (index + 1 <= bounds[1]) ? index + 1 : -1;
        result[2] = (dnBnds[0] + (index - bounds[0]) + 1 < 15) ? dnBnds[0] + (index - bounds[0]) + 1 : -1;
        result[3] = (dnBnds[0] + (index - bounds[0]) < 15) ? dnBnds[0] + (index - bounds[0]) : -1;
        result[4] = (index - 1 >= bounds[0]) ? index - 1 : -1;
        result[5] = (upBnds[0] + (index - bounds[0]) - 1 >= upBnds[0]) ? upBnds[0] + (index - bounds[0]) - 1 : -1;
        return result;
    }


    /**
     * getter for node index board array
     * @return board
     */
    public Node[] getBoard() {
        return this.board;
    }

    /**
     * gets all currently valid moves
     * @return valid moves
     */
    public Move [] getValidMoves(){
        ArrayList<Move> res = new ArrayList<Move>();
        for(int i = 0; i < 15; ++i){
            if(!this.board[i].isPeg()) {
                int[] adjTemp = this.getAdjInds(i);
                for (int j = 0; j < 6; ++j) {
                    int a = adjTemp[j];
                    if (a != -1 && this.board[a].isPeg()){
                        int b = this.getAdjInds(a)[j];
                        if(b != -1 && this.board[b].isPeg()){
                            res.add(new Move(b,i));
                        }
                    }
                }
            }
        }
        res = new ArrayList<Move>(new LinkedHashSet<Move>(res));
        Move [] result = res.toArray(new Move[res.size()]);
        return result;
    }

    /**
     * Tests for losing condition
     * @return true if the player has lost
     */
    public boolean hasLost(){
        return this.getValidMoves().length == 0;
    }

    /**
     * gets models of next valid moves
     * @return model list
     */
    public List<Model> getSuccessors() {
        Move[] moves = this.getValidMoves();
        ArrayList<Model> successors = new ArrayList<Model>(moves.length);
        for (Move move : moves) {
            Model child = new Model(this);
            child.selected = null;
            boolean sc = child.select(move.getFrom());
            //System.out.println(child.selected.getIndex());
            boolean sc2 = child.move(move.getTo());
            if (!sc || !sc2) {
                System.out.println("getSuccessors(): Something went wrong... (sc " + sc + ")(sc2 " + sc2 + ")");
            }
            successors.add(child);
        }
        return successors;
    }

    /**
     * returns unique moves
     * @return moves
     */
    public ArrayList<Move> getMoves() {
        ArrayList<Move> res = new ArrayList<Move>(new LinkedHashSet<Move>(this.moves));
        return res;
    }

    /**
     * getter for selected node
     * @return selected node
     */
    public Node getSelected() {
        return this.selected;
    }

    /**
     * setter for selected node
     * @param newSel node to set selected to
     */
    public void setSelected(Node newSel) {
        this.selected = newSel;
    }

    /**
     * remove most recent added move to current model
     */
    public void takeBack(){
        if(!this.moves.isEmpty())
            this.moves.remove(this.moves.size()-1);
    }

    /**
     * clears move list
     */
    public void clearMoves(){
        this.moves.clear();
    }
}

