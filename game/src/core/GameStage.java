package core;

import Entity.Immoveable.RoadExtend.Spawn;
import Entity.Immoveable.RoadExtend.Target;
import Entity.Moveable.Enemy.*;
import core.Config;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
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
    private static Status status = Status.RUNNING;

    enum Status {
        PAUSE, WIN, LOSS, RUNNING;
    }

    public GameStage() {
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static Status getStatus() {
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

    public static void buy(int money) {
        playerFinance -= money;
    }

    public static void playGame(Canvas canvas, Stage primaryStage, Scene theScene, GameField gameField, Group root, GraphicsContext gc){
        playerFinance = Config.GAME_START_MONEY;
        playerHP = Config.GAME_HEART;

        ToggleGroup towerToggle = new ToggleGroup();
        //Button
        ToggleButton normalTowerButton = new ToggleButton("",new ImageView(GameField.getImageSheet().imageSheet.get(8*23 + 19)));
        normalTowerButton.setLayoutY(Config.TILE_VERTICAL*Config.MAP_HEIGHT);

        //Button
        ToggleButton machineGunTowerButton = new ToggleButton("", new ImageView(GameField.getImageSheet().imageSheet.get(10*23 + 20)));
        machineGunTowerButton.setLayoutX(Config.TILE_HORIZONTAL+22);
        machineGunTowerButton.setLayoutY(Config.TILE_VERTICAL*Config.MAP_HEIGHT);

        //Button
        ToggleButton rocketTowerButton = new ToggleButton("", new ImageView(GameField.getImageSheet().imageSheet.get(8*23 + 20)));
        rocketTowerButton.setLayoutX((Config.TILE_HORIZONTAL+22)*2);
        rocketTowerButton.setLayoutY(Config.TILE_VERTICAL*Config.MAP_HEIGHT);

        //Button
        ToggleButton sniperTowerButton = new ToggleButton("", new ImageView(GameField.getImageSheet().imageSheet.get(10*23 + 19)));
        sniperTowerButton.setLayoutX((Config.TILE_HORIZONTAL+22)*3);
        sniperTowerButton.setLayoutY(Config.TILE_VERTICAL*Config.MAP_HEIGHT);

        //Upgrade Button
        ToggleButton upgradeTowerButton = new ToggleButton("", new ImageView(GameField.getImageSheet().imageSheet.get(23*3 + 16)));
        upgradeTowerButton.setLayoutX((Config.TILE_HORIZONTAL+22)*4);
        upgradeTowerButton.setLayoutY(Config.TILE_VERTICAL*Config.MAP_HEIGHT);

        //Sell Button
        ToggleButton sellTowerButton = new ToggleButton("", new ImageView(GameField.getImageSheet().imageSheet.get(23*3 + 17)));
        sellTowerButton.setLayoutX((Config.TILE_HORIZONTAL+22)*5);
        sellTowerButton.setLayoutY(Config.TILE_VERTICAL*Config.MAP_HEIGHT);

        normalTowerButton.setToggleGroup(towerToggle);
        machineGunTowerButton.setToggleGroup(towerToggle);
        rocketTowerButton.setToggleGroup(towerToggle);
        sniperTowerButton.setToggleGroup(towerToggle);
        upgradeTowerButton.setToggleGroup(towerToggle);
        sellTowerButton.setToggleGroup(towerToggle);

        Label[] towerLabel = {
                new Label("$" + Config.NORMAL_TOWER_COST),
                new Label("$" + Config.MACHINE_GUN_TOWER_COST),
                new Label("$" + Config.ROCKET_TOWER_COST),
                new Label("$" + Config.SNIPER_TOWER_COST),
        };
        int id = 0;
        for (Label label : towerLabel) {
            label.setFont(new Font(32));
            label.setLayoutX(15 + id++ * (Config.TILE_HORIZONTAL+22));
            label.setLayoutY(Config.TILE_VERTICAL*Config.MAP_HEIGHT + 75);
            root.getChildren().add(label);
        }

        Label heartLabel = new Label(Integer.toString(playerHP), new ImageView("Graphic/heart.png"));
        heartLabel.setLayoutX(Config.TILE_HORIZONTAL*(Config.MAP_WIDTH-2));
        heartLabel.setLayoutY(Config.TILE_VERTICAL*Config.MAP_HEIGHT);
        heartLabel.setFont(new Font(32));

        Label coinLabel = new Label(Integer.toString(playerFinance), new ImageView("Graphic/coin.png"));
        coinLabel.setLayoutX(Config.TILE_HORIZONTAL*(Config.MAP_WIDTH-4));
        coinLabel.setLayoutY(Config.TILE_VERTICAL*Config.MAP_HEIGHT);
        coinLabel.setFont(new Font(32));

        Label waveLabel = new Label("Wave " + GameField.getWave(), new ImageView("Graphic/wave.png"));
        waveLabel.setLayoutX(Config.TILE_HORIZONTAL*(Config.MAP_WIDTH-6));
        waveLabel.setLayoutY(Config.TILE_VERTICAL*Config.MAP_HEIGHT);
        waveLabel.setFont(new Font(32));


        Media gamePlay = new Media(new File("src/Graphic/gameplay.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(gamePlay);

        Button pauseButton = new Button("", new ImageView(GameField.getImageSheet().imageSheet.get(23*3 + 18)));
        pauseButton.setLayoutX((Config.TILE_HORIZONTAL+22)*6);
        pauseButton.setLayoutY(Config.TILE_VERTICAL*Config.MAP_HEIGHT);
        pauseButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (getStatus() == Status.RUNNING)
                gameField.pauseGame();
                else gameField.unpauseGame();
                
//                    status = Status.LOSS;
//                    mediaPlayer.pause();
//
//                    GameController.menu(canvas, theScene, gameField, root, gc, primaryStage);
            }
        });

        root.getChildren().addAll(normalTowerButton, machineGunTowerButton, rocketTowerButton, sniperTowerButton, pauseButton, upgradeTowerButton, sellTowerButton, heartLabel, coinLabel, waveLabel);

        AnimationTimer timer = new AnimationTimer() {
            long time = System.nanoTime();
            @Override
            public void handle(long now) {
                GameController.mouseClicked(canvas, theScene, gameField, root, gc, towerToggle, normalTowerButton, machineGunTowerButton, rocketTowerButton, sniperTowerButton, upgradeTowerButton, sellTowerButton);
                gameField.update(now - time);
                gameField.draw(gc, gameField, heartLabel, coinLabel, waveLabel, playerHP, playerFinance);
                gameField.gameOver(canvas, theScene, gameField, root, gc, primaryStage, mediaPlayer);
//                if (getStatus() == Status.WIN || getStatus() == Status.LOSS) {
//                    this.stop();
//                    status = Status.RUNNING;
//                }
                mediaPlayer.setAutoPlay(true);
            }
        };
        timer.start();
        primaryStage.show();
    }

    public static boolean hasEnoughMoney(int cost) {
        if (playerFinance >= cost) return true;
        System.out.println("DON'T HAVE ENOUGH MONEY DUDE!!");
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

        Enemy.Direction defaultDirection = null;
        int wave = 0;
        while (sc.hasNext()){
            switch (sc.next()){
                case "Direction":
                    switch (sc.next()){
                        case "UP": defaultDirection = Enemy.Direction.UP; break;
                        case "DOWN": defaultDirection = Enemy.Direction.DOWN; break;
                        case "RIGHT": defaultDirection = Enemy.Direction.RIGHT; break;
                        case "LEFT" : defaultDirection = Enemy.Direction.LEFT; break;
                    }
                    System.out.println("Hello");
                    break;
                case "Wave":
                    wave = sc.nextInt();
                    while (wave > waitingEnemy.size() - 1) waitingEnemy.add(new LinkedList<>());
                    break;
                case "NormalEnemy": {
                    int number = sc.nextInt();
                    while (number-->0){
                        Enemy enemy = new NormalEnemy();
                        enemy.setDirection(defaultDirection);
                        enemy.setFirstPos();
                        waitingEnemy.get(wave).add(enemy);
                    }
                    break;
                }
                case "SmallerEnemy":{
                    int number = sc.nextInt();
                    while (number-->0){
                        Enemy enemy = new SmallerEnemy();
                        enemy.setDirection(defaultDirection);
                        enemy.setFirstPos();
                        waitingEnemy.get(wave).add(enemy);
                    }
                    break;
                }
                case "TankerEnemy":{
                    int number = sc.nextInt();
                    while (number-->0){
                        Enemy enemy = new TankerEnemy();
                        enemy.setDirection(defaultDirection);
                        enemy.setFirstPos();
                        waitingEnemy.get(wave).add(enemy);
                    }
                    break;
                }
                case "BossEnemy":{
                    int number = sc.nextInt();
                    while (number-->0){
                        Enemy enemy = new BossEnemy();
                        enemy.setDirection(defaultDirection);
                        enemy.setFirstPos();
                        waitingEnemy.get(wave).add(enemy);
                    }
                    break;
                }
                case "Spawn":
                    int [] dataS = new int [2];
                    dataS[0] = sc.nextInt();
                    dataS[1] = sc.nextInt();
                    System.out.println("spawn: " + dataS[0] + " " + dataS[1]);
                    Spawn spawn = new Spawn();
                    spawn.setPosXY(dataS[1], dataS[0]);
                    GameField.getRoadInfo().setSpawn(spawn);
                    break;
                case "Target":
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