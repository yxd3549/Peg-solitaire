package Model;

import java.lang.reflect.Array;
import java.util.ArrayList;
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


    public Model(Model model){
        this.board = new Node[15];
        this.selected = model.selected;
        this.points = model.points;
        for(int i = 0; i < 15; i++){
            this.board[i] = new Node(model.board[i]);
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
        /*
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
        board[10].setAdjacentNode(1, board[11]);
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
*/
    }

    @Override
    public String toString(){
        String s = "    " + board[0].toString()
                + "\n   " + board[1].toString() + " " + board[2].toString()
                + "\n  " + board[3].toString() + " " + board[4].toString() + " " + board[5].toString()
                + "\n " + board[6].toString() + " " + board[7].toString() + " " + board[8].toString() + " " + board[9].toString()
                + "\n" + board[10].toString() + " " + board[11].toString() + " " + board[12].toString() + " " + board[13].toString() + " " + board[14].toString() + "\n";
        return s;
    }

    /**
     *
     */
    public boolean select(int id) {
        if (id >= 15 || id < 0){
            notifyObservers();
            return false;
        }
        Node node = board[id];
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
     *
     */
    public boolean move(int id){
        int middleMan = selected.canMove(id);
        //System.out.println(middleMan);
        if(middleMan == -1){
            setChanged();
            notifyObservers();
            return false;
        }
        else{
            board[selected.getIndex()].makeHole();
            board[middleMan].makeHole();
            board[id].makePeg();
            selected = null;
            points++;
            setChanged();
            notifyObservers();
            return true;
        }
    }

    public void remove(int id){
        board[id].makeHole();
        setChanged();
        notifyObservers();
    }

    public boolean hasWon() {
        return points >= 14;
    }

    public int[] getBounds(int row){
        int [] result = new int[2];
        result[0] = (row)*(row+1)/2;
        result[1] = (row)*(row+3)/2;
        return result;
    }

    public int getRow(int index){
        return (int)(0.5*Math.sqrt(1.0+8.0*index)-0.5);
    }

    public int[] getAdjInds(int index){
        int row = this.getRow(index);
        int [] bounds = this.getBounds(row);
        int [] upBnds = this.getBounds(row-1);
        int [] dnBnds = this.getBounds(row+1);
        int [] result = new int[6];
        result[0] = (upBnds[0] + (index-bounds[0]) <= upBnds[1]) ? upBnds[0] + (index-bounds[0]) : -1;
        result[1] = (index + 1 <= bounds[1]) ? index + 1 : -1;
        result[2] = (dnBnds[0] + (index - bounds[0]) + 1 < 15) ? dnBnds[0] + (index - bounds[0]) + 1 : -1;
        result[3] = (dnBnds[0] + (index - bounds[0])     < 15) ? dnBnds[0] + (index - bounds[0]) : -1;
        result[4] = (index - 1 >= bounds[0]) ? index - 1 : -1;
        result[5] = (upBnds[0] + (index-bounds[0]) - 1 >= upBnds[0]) ? upBnds[0] + (index-bounds[0]) - 1 : -1;
        return result;
    }

    public Node [] getBoard(){
        return this.board;
    }

    public Move [] getValidMoves(){
        ArrayList<Move> res = new ArrayList<Move>();
        for(int i = 0; i < 15; ++i){
            if(!board[i].isPeg()) {
                int[] adjTemp = this.getAdjInds(i);
                for (int j = 0; j < 6; ++j) {
                    int a = adjTemp[j];
                    if (a != -1 && board[a].isPeg()){
                        int b = this.getAdjInds(a)[j];
                        if(b != -1 && board[b].isPeg()){
                            res.add(new Move(b,i));
                        }
                    }
                }
            }
        }
        Move [] result = res.toArray(new Move[res.size()]);
        return result;
    }

    public boolean hasLost(){
        return this.getValidMoves().length == 0;
    }

    public List<Model> getSuccessors(){
        ArrayList<Model> successors = new ArrayList<Model>();
        Move[] moves = this.getValidMoves();
        for(Move move: moves){
            Model child = new Model(this);
            child.select(move.getFrom());
            child.move(move.getTo());
            successors.add(child);
        }
        return successors;
    }
}


