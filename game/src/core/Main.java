package core;

import Graphic.Render;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        //load game information (load in a Object name gameField)
        GameField gameField = GameStage.loadGameField("src/Level/Level0.txt");

        //Create new Window
        Render renderGame = new Render();
        Group root = new Group();
        Scene theScene = new Scene(root, Config.CANVAS_WIDTH, Config.CANVAS_HEIGHT + 120);
        Canvas canvas = new Canvas(Config.CANVAS_WIDTH, Config.CANVAS_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Render.generateWindow(primaryStage, root, theScene);
        root.getChildren().addAll(canvas);

        /*
        | Menu game + Game loop
         */
        GameController.menu(canvas, theScene, gameField, root, gc, primaryStage);
    }

    public static void main (String[] args){
        launch(args);
    }
}
