package PTUI;

import Model.Model;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 */
public class PegSolitairePTUI{

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
        String os = System.getProperty("os.name");
        while(true) {
            try {
                if (os.equals("windows")) Runtime.getRuntime().exec("cmd /c cls");
                else {
                    System.out.println("\u001b[2J \u001b[H");
                    System.out.flush();
                }
            }
            catch(IOException e){
                e.printStackTrace();
            }
            System.out.print(controller.model);

            System.out.println("Select a Peg to move:");
            int selected = s.nextInt();
            if(controller.model.select(selected)){
                System.out.println("Select a Hole to move to");
                int target = s.nextInt();
                if(selected == target){
                    controller.model.move(target);
                    System.out.println("Unselected");
                }
                else if(!controller.model.move(target)){
                    System.out.println("Invalid move");
                }
            }
            else{
                System.out.println("Invalid selection");
            }

        }

    }
}
