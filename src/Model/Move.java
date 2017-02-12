package Model;

public class Move{
    private int from;
    private int to;
    public Move(int f, int t){
        this.from = f;
        this.to   = t;
    }
    public int getFrom() {return this.from;}
    public int getTo() {return this.to;}

    @Override
    public String toString(){
        String res = "( " +this.from+ " -> " +this.to+ ")";
        return res;
    }

}

