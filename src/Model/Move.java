package Model;

public class Move{
    private int from;
    private int to;

    /**
     * Move Constructor
     * @param f : Selected node index to move from
     * @param t : Node index to move to
     */
    public Move(int f, int t){
        this.from = f;
        this.to   = t;
    }

    /**
     * Getter for from index
     * @return index of selection
     */
    public int getFrom() {return this.from;}

    /**
     * Getter for destination
     * @return destination index
     */
    public int getTo() {return this.to;}


    /**
     * toString override method
     * @return string represenation of Move
     */
    @Override
    public String toString(){
        String res = "( " +this.from+ " -> " +this.to+ ")";
        return res;
    }

    /**
     * Test for equality of two moves
     * @param move: Move object to test with
     * @return true if the moves are identical
     */
    public boolean equals(Move move){
        return this.to == move.getTo() && this.from == move.getFrom();
    }

}

