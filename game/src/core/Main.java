package core;

import Graphic.Render;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        //load game information (load in a Object name gameField)
        GameField gameField = new GameField();


        //Create new Window
        Render renderGame = new Render();
        Group root = new Group();
        Scene theScene = new Scene(root);
        Canvas canvas = new Canvas(Config.SCREEN_WIDTH, Config.SCREEN_HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Render.generateWindow(primaryStage, root, theScene);
        root.getChildren().addAll(canvas);

        /*
        | Menu game (play (then choice level), credit or tutorial, choice level, load map, load game config, ...)
         */
        GameController.menu(theScene, gameField, root, gc, primaryStage);
        /*
        | Game loop
         */
//        Enemy enemy = new NormalEnemy();
//        System.out.println("new Enemy");
//        enemy.setDirection(Enemy.Direction.UP);
//        GameField.getEnemies().add(enemy);

    }

    public static void main (String[] args){
        launch(args);
    }
}
