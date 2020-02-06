package ex;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    // dimension de la zone de dessin et de la zone de saisie
    final static double WIDTH = 250;
    final static double HEIGHT = 280;

    public static void main(String[] args) {
        launch(args);
    }

   
    @Override
    public void start(Stage stage) {

        GridPane pane = new GridPane();
        Scene scene = new Scene(pane, Color.WHITESMOKE);

        Canvas canvas = new Canvas(WIDTH,HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLACK);

        TextArea textArea = new TextArea();
        textArea.setPrefHeight(HEIGHT);
        textArea.setPrefWidth(WIDTH);

        Interpreter interpreter = new Interpreter();

        Button btnRun = new Button("run");
        btnRun.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    gc.clearRect(0, 0, WIDTH, HEIGHT);
                    interpreter.interpreter(textArea.getText(), gc);
                }
            }
        );

        pane.add(canvas, 0, 0);
        pane.add(textArea, 1, 0);
        pane.add(btnRun, 1, 1);

        stage.setScene(scene);
        stage.setTitle("Analyse syntaxique");
        stage.show();

    }

}
