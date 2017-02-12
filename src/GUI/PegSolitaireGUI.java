package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

import Model.*;

/**
 * PegSolitaireGUI is the Graphical User Interface of the game.
 * @author Yancarlos Diaz
 */
public class PegSolitaireGUI extends Application implements Observer {

    private Model model;
    private Button selected;
    private Button[] buttons;
    private Label label;
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.model = new Model();
        this.model.addObserver(this);
        this.buttons = new Button[15];
        //Making the Label
        this.label = new Label("Peg-Solitaire");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        label.setTextFill(javafx.scene.paint.Paint.valueOf("gray"));
        label.setPadding(new javafx.geometry.Insets(10,10,10,10));

        stage = primaryStage;
        BorderPane layout = new BorderPane();
        GridPane grid = makeBoard();
        Button restart = new Button("restart");
        restart.setOnMouseClicked(event -> buttonRestart(restart));
        Button quit = new Button("RAGEQUIT");
        quit.setOnMouseClicked(event -> buttonQuit(quit));

        Button solve = new Button("Solve");

        VBox clickables = new VBox(restart, quit, solve);
        clickables.setPadding(new Insets(20,10,20,10));
        clickables.setSpacing(10);
        VBox box = new VBox(label, grid);
        layout.setLeft(box);
        layout.setCenter(clickables);

        Scene s = new Scene(layout);
        primaryStage.setScene(s);
        primaryStage.setResizable( false );
        primaryStage.setTitle("Peg-Solitaire Game! BrickHack 2017");
        primaryStage.show();


    }

    @Override
    public void update(Observable o, Object arg){



    }

    private GridPane makeBoard(){
        GridPane pane = new GridPane();
        int index = 0;
        for(int i = 1; i < 6; i++){
            for(int j = 0; i != j; j++){
                Button b = new Button();
                pane.add(b,j,i);
                b.setPadding(new Insets(1,1,1,1));
                b.setMinSize(50,50);
                b.setStyle( "-fx-background-radius: 25em; " +
                            "-fx-min-width: 100px; " +
                            "-fx-min-height: 100px; " +
                            "-fx-max-width: 100px; " +
                            "-fx-max-height: 100px; "
                );
                int finalIndex = index;
                b.setOnMouseClicked(event -> buttonEvent(b, finalIndex));
                buttons[buttons.length-1] = b;
                index++;
            }
        }
        return pane;
    }
    public static void main( String[] args){
        Application.launch(PegSolitaireGUI.class);
    }

    private void buttonEvent( Button b, int index){
        if(selected == null){
            model.select(index);
        }
        else{
            model.move(index);
        }
    }
    private void buttonRestart( Button b){
        model = new Model();
    }

    private void buttonQuit( Button b){
        System.exit(0);
    }
}
