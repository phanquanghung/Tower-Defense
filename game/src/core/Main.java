package core;

//import Entity.Moveable.Enemy.Enemy;
//import Entity.Moveable.Enemy.NormalEnemy;
import Graphic.Render;
import javafx.animation.AnimationTimer;
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

        /*
        |=======================================|
        |   1. Player controller's input        |
        |=======================================|

        /*
        |=======================================|
        |   2. Render Everything                |
        |=======================================|

        /*
        |=======================================|
        |   3. Update world                     |
        |=======================================|

        /*
        |=======================================|
        |   4. Update Physics                   |
        |=======================================|
         */

        /*
        | Game loop
         */
//        Enemy enemy = new NormalEnemy();
//        //System.out.println("new Enemy");
//        enemy.setDirection(Enemy.Direction.UP);
//        GameField.getEnemies().add(enemy);
        AnimationTimer timer = new AnimationTimer() {
            long time = System.nanoTime();
            @Override
            public void handle(long now) {
                GameController.mouseClicked(theScene, gameField, root);
                gameField.update(now - time);
                gameField.draw(gc, gameField);

                //if(player don't play more, click exit button to exit the game) GameStage.closeWindow(primaryStage);
            }
        };
        timer.start();
        primaryStage.show();
    }

    public static void main (String[] args){
        launch(args);
    }
}
