package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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
import java.util.Random;

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
    private Image imageVball = new Image(getClass().getResourceAsStream("volleyball.png"));
    private Node[] board;
    private ImageView btnImage = new ImageView(imageVball);


    @Override
    public void start(Stage primaryStage) throws Exception{

        this.model = new Model();
        this.model.addObserver(this);
        this.board = model.getBoard();
        this.buttons = new Button[15];
        //Making the Label
        this.label = new Label("Peg-Solitaire");
        label.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        label.setTextFill(javafx.scene.paint.Paint.valueOf("gray"));
        label.setPadding(new javafx.geometry.Insets(10,10,10,10));

        stage = primaryStage;
        BorderPane layout = new BorderPane();
        GridPane grid = makeBoard();
        this.model.remove(Math.abs((new Random()).nextInt() % 15));
        Button restart = new Button("restart");
        restart.setOnMouseClicked(event -> buttonRestart(restart));
        Button quit = new Button("RAGEQUIT");
        quit.setOnMouseClicked(event -> buttonQuit(quit));
        Button moves = new Button("Valid Moves");
        moves.setOnMouseClicked(event -> buttonMoves(moves));

        Button solve = new Button("Solve");

        restart.setMaxWidth(Double.MAX_VALUE);
        quit.setMaxWidth(Double.MAX_VALUE);
        solve.setMaxWidth(Double.MAX_VALUE);
        solve.setMaxWidth(Double.MAX_VALUE);


        VBox clickables = new VBox(restart, quit, solve,moves);
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
        this.repaint();
        if(model.hasLost()){
            label.setText("You are stuck");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("LOSER");
            alert.setHeaderText(null);
            alert.setContentText("No more moves left! Try again...");

            alert.showAndWait();
        }
    }
    public void repaint(){
        for(int i = 0; i < buttons.length; ++i){
            if(board[i].isPeg()) {
                ImageView temp = new ImageView(imageVball);
                temp.setFitHeight(100);
                temp.setFitWidth(100);
                buttons[i].setGraphic(temp);
            } else {
                buttons[i].setGraphic(null);
            }
        }
    }

    private GridPane makeBoard(){
        GridPane pane = new GridPane();
        int index = 0;
        int btnInd = 0;
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
                buttons[btnInd++] = b;
                index++;
            }
        }
        repaint();
        return pane;
    }
    public static void main( String[] args){
        Application.launch(PegSolitaireGUI.class);
    }

    private void buttonEvent( Button b, int index){
        if(selected == null){
            model.select(index);
            selected = b;
            System.out.println("Selected: " + index);
        }
        else{
            boolean madeMove = model.move(index);
            if(madeMove) {
                System.out.println("Moved to: " + index);
            }
            else
                System.out.println("Invalid Move...");
            selected = null;
        }
    }
    private void buttonRestart( Button b){
        this.model = new Model();
        this.model.addObserver(this);
        this.board = model.getBoard();
        this.model.remove(Math.abs((new Random()).nextInt() % 15));
    }

    private void buttonQuit( Button b){
        System.exit(0);
    }

    private void buttonMoves(Button b){
        System.out.println("\n\n-----Valid Moves-----");
        for(Move i: this.model.getValidMoves()) {
            System.out.println(i);
        }
        System.out.println("---------------------");

    }
}


// This comment is here because Yan doesnt understand version control