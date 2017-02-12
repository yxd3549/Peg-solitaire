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
    public Backtracker(){
        models = new ArrayList<Model>();
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
            System.out.println("Adding child Models");
            for (Model child : current.getSuccessors()) {
                 Model solution = solve(child);
                 if(solution != null){
                     return solution;
                 }
            }
        }
        return null;
    }

    private Model testNext(){
        if(!this.models.isEmpty()){
            Model nextModel = this.models.remove(0);
            return this.solve(nextModel);
        }
        return null;
    }
}
