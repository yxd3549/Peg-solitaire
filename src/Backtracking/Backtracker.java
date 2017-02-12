package Backtracking;

import Model.*;
import Model.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The Backtracker is used to solve the game.
 * @author Lucas Shadler
 */
public class Backtracker {
    private ArrayList<Model> models;
    private ArrayList<Move> moves;
    public Backtracker(){
        models = new ArrayList<Model>();
        moves  = new ArrayList<Move>();
    }




    /**
     * Solve finds a solution via backtracking!
     * @param current
     * @return
     */
    public Model solve(Model current) {


        if (current.hasWon()) {
            System.out.println("WINNER");
            return current;
        } else {
            //System.out.println("Adding child Models");
            /*
            current.takeBack();
            for (Model child : current.getSuccessors()) {
                 Model solution = solve(child);
                 if(solution != null){
                     return solution;
                 }
            }
            */
            ///*
            Move [] childMoves = current.getValidMoves();
            for(Move move: childMoves){
                Model child = new Model(current);
                child.setSelected(null);
                boolean sc = child.select(move.getFrom());
                boolean sc2 = child.move(move.getTo());
                Model solution = solve(child);
                if(solution != null) return solution;
                else current.takeBack();
                //else moves.remove(move);
            }
            //*/
        }
        return null;
    }



}
