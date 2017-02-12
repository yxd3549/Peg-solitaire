package Backtracking;

import Model.*;
import Model.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The Backtracker is used to solve the game.
 * @author Yancarlos Diaz
 */
public class Backtracker {
    private ArrayList<Model> models;
    private ArrayList<Move> moves;
    public Backtracker(){
        models = new ArrayList<Model>();
        moves  = new ArrayList<Move>();
    }




    /**
     * solve finds a solution via backtracking!
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


    public List<Model> getSuccessors(Model model) {
        Move[] moves = model.getValidMoves();
        ArrayList<Model> successors = new ArrayList<Model>(moves.length);
        for (Move move : moves) {
            Model child = new Model(model);
            child.setSelected(null);
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
}
