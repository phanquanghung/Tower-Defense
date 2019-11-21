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
        Scene theScene = new Scene(root);
        Canvas canvas = new Canvas(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Render.generateWindow(primaryStage, root, theScene);
        root.getChildren().addAll(canvas);

        /*
        | Menu game + Game loop
         */
        GameController.menu(theScene, gameField, root, gc, primaryStage);
    }

    public static void main (String[] args){
        launch(args);
    }
}
