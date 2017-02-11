package PTUI;

import Model.Model;

import java.util.Scanner;

/**
 *
 */
public class PegSolitairePTUI {

    private Model model;

    public PegSolitairePTUI(){
        this.model = new Model();
    }
    public static void main(String[] args){
        PegSolitairePTUI controller = new PegSolitairePTUI();
        Scanner s = new Scanner(System.in);
        System.out.println("Select a Peg to remove:");
        while(true) {
            System.out.print(controller.model);

            System.out.println("Select a Peg to move:");
            controller.model.select(s.nextInt());

            System.out.println("\nSelect a Hole to move to");
            controller.model.move(s.nextInt());
        }

    }
}
