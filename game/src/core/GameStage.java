package core;

import Entity.Immoveable.RoadExtend.Spawn;
import Entity.Immoveable.RoadExtend.Target;
import Entity.Moveable.Enemy.*;
import core.Config;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import sun.plugin2.gluegen.runtime.CPU;

import java.io.*;
import java.util.*;
/*
|=======================================================================|
|   This class start a game field (game field's status, config, ...)    |
|=======================================================================|

 */

public class GameStage {
    private static int playerHP = Config.GAME_HEART;
    private static int playerFinance = Config.GAME_START_MONEY;
    protected Status status = Status.RUNNING;

    enum Status {
        PAUSE, WIN, LOSS, RUNNING;
    }

    public GameStage() {
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public int getPlayerHP() {
        return playerHP;
    }

    public void setPlayerHP(int playerHP) {
        this.playerHP = playerHP;
    }

    public int getPlayerFinance() {
        return playerFinance;
    }

    public void decreaseHP() {
        setPlayerHP(getPlayerHP() - 1);
    }

    public static void earnMoney(int money) {
        playerFinance += money;
    }

    public static void buyTower(int money) {
        playerFinance -= money;
    }

    public static void playGame(Canvas canvas, Stage primaryStage, Scene theScene, GameField gameField, Group root, GraphicsContext gc){
        ToggleGroup towerToggle = new ToggleGroup();
        //Button
        ImageView ivNormalTowerButton = new ImageView(GameField.getImageSheet().imageSheet.get(8*23 + 19));
        ToggleButton normalTowerButton = new ToggleButton("",ivNormalTowerButton);
        normalTowerButton.setLayoutY(Config.TILE_VERTICAL*Config.MAP_HEIGHT);

        //Button
        ImageView ivMachineGunTowerButton = new ImageView(GameField.getImageSheet().imageSheet.get(10*23 + 20));
        ToggleButton machineGunTowerButton = new ToggleButton("", ivMachineGunTowerButton);
        machineGunTowerButton.setLayoutX(Config.TILE_HORIZONTAL+22);
        machineGunTowerButton.setLayoutY(Config.TILE_VERTICAL*Config.MAP_HEIGHT);

        //Button
        ImageView ivRocketTowerButton = new ImageView(GameField.getImageSheet().imageSheet.get(8*23 + 20));
        ToggleButton rocketTowerButton = new ToggleButton("", ivRocketTowerButton);
        rocketTowerButton.setLayoutX((Config.TILE_HORIZONTAL+22)*2);
        rocketTowerButton.setLayoutY(Config.TILE_VERTICAL*Config.MAP_HEIGHT);

        //Button
        ImageView ivSniperTowerButton = new ImageView(GameField.getImageSheet().imageSheet.get(10*23 + 19));
        ToggleButton sniperTowerButton = new ToggleButton("", ivSniperTowerButton);
        sniperTowerButton.setLayoutX((Config.TILE_HORIZONTAL+22)*3);
        sniperTowerButton.setLayoutY(Config.TILE_VERTICAL*Config.MAP_HEIGHT);

        normalTowerButton.setToggleGroup(towerToggle);
        machineGunTowerButton.setToggleGroup(towerToggle);
        rocketTowerButton.setToggleGroup(towerToggle);
        sniperTowerButton.setToggleGroup(towerToggle);

        Label[] towerLabel = {
                new Label("$" + Config.NORMAL_TOWER_COST),
                new Label("$" + Config.MACHINE_GUN_TOWER_COST),
                new Label("$" + Config.ROCKET_TOWER_COST),
                new Label("$" + Config.SNIPER_TOWER_COST),
        };
        int id = 0;
        for (Label label : towerLabel) {
            label.setFont(new Font(32));
            label.setLayoutX(24 + id++ * (Config.TILE_HORIZONTAL+22));
            label.setLayoutY(Config.TILE_VERTICAL*Config.MAP_HEIGHT + 75);
            root.getChildren().add(label);
        }

        Label heartLabel = new Label("");
        root.getChildren().addAll(normalTowerButton, machineGunTowerButton, rocketTowerButton, sniperTowerButton);

        AnimationTimer timer = new AnimationTimer() {
            long time = System.nanoTime();

            @Override
            public void handle(long now) {
                GameController.mouseClicked(canvas, theScene, gameField, root, gc, towerToggle, normalTowerButton, machineGunTowerButton, rocketTowerButton, sniperTowerButton);
                gameField.update(now - time);
                gameField.draw(gc, gameField);
                gameField.gameOver();
                //if(player don't play more, click exit button to exit the game) GameStage.closeWindow(primaryStage);
            }
        };
        timer.start();
        primaryStage.show();
    }

    public static boolean hasEnoughMoney(int cost) {
        if (playerFinance >= cost) return true;
        return false;
    }

    public void gameOver() {
        if (getPlayerHP() <= 0) status = Status.LOSS;
        else if(GameField.getEnemies().isEmpty() && GameField.getEnemyWaiting().get(GameField.getEnemyWaiting().size()-1).isEmpty()) status = Status.WIN;
        return;
    }

    public static GameField loadGameField(String fileName) {
        GameField gameField = new GameField();
        Scanner sc = null;
        try {
            sc = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.err.println(e.toString());
            return null;
        }

        //read first line, contain map data
        String line = sc.nextLine();
        String[] mapData = line.split(" ");

        /*
         * Load map, work properly, do not touch
         */
        gameField.loadMap(mapData[0], mapData[1], mapData[2], mapData[3]);

        ArrayList<Queue<Enemy>> waitingEnemy = new ArrayList<>();
        int wave = 0;
        while (sc.hasNext()){
            switch (sc.next()){
                case "wave":
                    wave = sc.nextInt();
                    while (wave > waitingEnemy.size() - 1) waitingEnemy.add(new LinkedList<>());
                    break;
                case "NormalEnemy": {
                    int number = sc.nextInt();
                    while (number-->0){
                        Enemy enemy = new NormalEnemy();
                        enemy.setDirection(Enemy.Direction.UP);
                        waitingEnemy.get(wave).add(enemy);
                    }
                    break;
                }
                case "SmallerEnemy":{
                    int number = sc.nextInt();
                    while (number-->0){
                        Enemy enemy = new SmallerEnemy();
                        enemy.setDirection(Enemy.Direction.UP);
                        waitingEnemy.get(wave).add(enemy);
                    }
                    break;
                }
                case "TankerEnemy":{
                    int number = sc.nextInt();
                    while (number-->0){
                        Enemy enemy = new TankerEnemy();
                        enemy.setDirection(Enemy.Direction.UP);
                        waitingEnemy.get(wave).add(enemy);
                    }
                    break;
                }
                case "BossEnemy":{
                    int number = sc.nextInt();
                    while (number-->0){
                        Enemy enemy = new BossEnemy();
                        enemy.setDirection(Enemy.Direction.UP);
                        waitingEnemy.get(wave).add(enemy);
                    }
                    break;
                }
                case "spawn":
                    int [] dataS = new int [2];
                    dataS[0] = sc.nextInt();
                    dataS[1] = sc.nextInt();
                    System.out.println("spawn: " + dataS[0] + " " + dataS[1]);
                    Spawn spawn = new Spawn();
                    spawn.setPosXY(dataS[1], dataS[0]);
                    GameField.getRoadInfo().setSpawn(spawn);
                    break;
                case "target":
                    int [] dataT = new int [2];
                    dataT[0] = sc.nextInt();
                    dataT[1] = sc.nextInt();
                    System.out.println("target: " + dataT[0] + " " + dataT[1]);
                    Target target = new Target();
                    target.setPosXY(dataT[1], dataT[0]);
                    GameField.getRoadInfo().setTarget(target);
                    break;
            }
        }
        sc.close();
        gameField.setRoadInfo();
        gameField.setEnemyWaiting(waitingEnemy);
        return gameField;
    }
}