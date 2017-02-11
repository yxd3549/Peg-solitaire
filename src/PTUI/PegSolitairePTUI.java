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

        System.out.print(controller.model);
        System.out.println("Select a Peg to remove:");
        controller.model.remove(s.nextInt());
        while(!controller.model.hasWon()) {
            System.out.print(controller.model);
            System.out.println("Move | Hint | Solve | Restart | RAGEQUIT");
            String choice = s.next().toLowerCase();
                if (choice.equals("move")) {
                    System.out.println("Select a Peg to move:");
                    int selected = s.nextInt();
                    if (controller.model.select(selected)) {
                        System.out.println("Select a Hole to move to");
                        int target = s.nextInt();
                        if (selected == target) {
                            controller.model.move(target);
                            System.out.println("Unselected");
                        } else if (!controller.model.move(target)) {
                            System.out.println("Invalid move");
                        }
                    } else {
                        System.out.println("Invalid selection");
                    }
                } else if (choice.equals("hint")) {
                    continue;
                } else if (choice.equals("solve")) {
                    continue;
                } else if (choice.equals("restart")) {
                    controller.model = new Model();
                } else if (choice.equals("ragequit")) {
                    System.exit(2);
                } else {
                    System.out.println("Error: Please provide correct input...");
                }
            }
        }

    }


