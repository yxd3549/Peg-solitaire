package Backtracking;

import Model.Model;
import Model.Move;

import java.util.List;

/**
 * The Backtracker is used to solve the game.
 * @author Yancarlos Diaz
 */
public class Backtracker {

    private List<Model> models;

    public List<Model> solvewithPath(Model current){
        if(current.hasWon()){
            models.add(current);
            return models;
        }
        else{
            for (Move child: current.getValidMoves()){

            }
        }
    }
}
