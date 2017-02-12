package Backtracking;

import Model.Model;
import Model.Move;

import java.util.List;
import java.util.Optional;

/**
 * The Backtracker is used to solve the game.
 * @author Yancarlos Diaz
 */
public class Backtracker {

    public Backtracker(){
    }

    public Optional<Model> solve(Model current){
        if(current.hasWon()){
            return Optional.of(current);
        }
        else{
            for(Model child: current.getSuccessors()){
                Optional<Model> sol = solve(child);
                if(sol.isPresent()){
                    return sol;
                }
            }
        }
        return Optional.empty();
    }
}
