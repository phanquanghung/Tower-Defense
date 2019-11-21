package core;

/*
|===================================================|
|   This class manages every object in the field    |
|===================================================|
 */

import Entity.Immoveable.Road;
import Entity.Immoveable.Tower;
import Entity.Moveable.Bullet.Bullet;
import Entity.Moveable.Enemy.*;
import Graphic.ImageSheet;
import Graphic.Render;
import Graphic.TileMap;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class GameField {
    private static ImageSheet imageSheet = new ImageSheet(); //tiled sheet

    private static TileMap background = new TileMap(); //first layer
    private static TileMap road = new TileMap();       //second layer
    private static TileMap rockTree = new TileMap();   //third layer
    private static TileMap spawn = new TileMap();      //final layer
    private static TileMap tower = new TileMap();      //tower layer

    private static ArrayList<Tower> towers = new ArrayList<>(); //Tower array
    private static ArrayList<Bullet> bullets = new ArrayList<>(); //Bullet array
    private ArrayList<Queue<Pair<Enemy, Integer>>> enemyWaiting = new ArrayList<>(); //Enemies that are waiting to be spawned

    private static Queue<Bullet> removingBullet = new LinkedList<>(); //Bullet has been shoot and hit the target
    private static ArrayList<Enemy> enemies = new ArrayList<>(); //Enemies array
    private static Queue<Enemy> deathEnemy = new LinkedList<>(); //Enemies has been killed or go through the map

    private static int wave = 0; //wave order
    private static Road roadInfo = new Road();
    private static GameStage gameStage = new GameStage();

    public GameField() {
//        loadMap();
        loadGameplay();
    }

    public ArrayList<Queue<Pair<Enemy, Integer>>> getEnemyWaiting() {
        return enemyWaiting;
    }

    public static Queue<Enemy> getDeathEnemy() {
        return deathEnemy;
    }

    public static Queue<Bullet> getRemovingBullet() {
        return removingBullet;
    }

    public static ImageSheet getImageSheet() {
        return imageSheet;
    }

    public TileMap getBackground() {
        return background;
    }

    public TileMap getRoad() {
        return road;
    }

    public TileMap getRockTree() {
        return rockTree;
    }

    public TileMap getSpawn() {
        return spawn;
    }

    public static Road getRoadInfo() {
        return roadInfo;
    }

    public static ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public static ArrayList<Bullet> getBullets() {
        return bullets;
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public TileMap getTower() {
        return tower;
    }

    public void addTower(Tower tower) {
        GameField.towers.add(tower);
    }

    public void addEnemy(Enemy enemy) {
        GameField.enemies.add(enemy);
    }

    public static void addBullet(Bullet bullet) {
        GameField.bullets.add(bullet);
    }

    public void setEnemyWaiting(ArrayList<Queue<Pair<Enemy, Integer>>> enemyWaiting) {
        this.enemyWaiting = enemyWaiting;
    }

    public void loadMap(String backgroundData, String roadData, String rockTreeData, String spawnData) {
        //input layer data
        background.readFile(backgroundData);
        road.readFile(roadData);
        rockTree.readFile(rockTreeData);
        spawn.readFile(spawnData);
        tower.readFile(spawnData);

        //print to check
        background.printMapData();
        road.printMapData();
        rockTree.printMapData();
        spawn.printMapData();
        tower.printMapData();

        //load tiled sheet
        imageSheet.loadImageViewSheet("Graphic/towerDefense_tilesheet.png");
        // render to check
        // Render.renderMap(background.getTileMap(), imageSheet);

        //load road information
        //roadInfo.setRoadInfo(road.getTileMap());
        //roadInfo.printTestRoadInfo();
    }

    public void setRoadInfo (){
        //load road information
        roadInfo.setRoadInfo(road.getTileMap());
        roadInfo.printTestRoadInfo();
    }

    public static void loadGameplay() {
        tower.setName("Tower");
        for (int i = 0; i < Config.MAP_HEIGHT; i++) {
            for (int j = 0; j < Config.MAP_WIDTH; j++) {
                if (tower.getTileMap(i, j) == 16) tower.setTileMap(i, j, 1);
            }
        }
        System.out.println();
        tower.printMapData();
    }

    public void gameOver(Scene theScene, GameField gameField, Group root, GraphicsContext gc, Stage stage) {
        //TODO: return to menu
        if (gameStage.gameOver()) {
            //GameController.menu(theScene, gameField, root, gc, stage);
            Render.closeWindow(stage);
        }
    }

    private long tickLastSpawn;

    public void update(long time) {
//        System.out.println("****************Player Money = " + gameStage.getPlayerFinance());
//        System.out.println("Player Heart = " + gameStage.getPlayerHP());
        if ((time - tickLastSpawn >= 10E8)) {
            Enemy enemy = new NormalEnemy();
            enemy.setDirection(Enemy.Direction.UP); //default
//            System.out.println("NEW ENEMY");
            tickLastSpawn = time;
            enemies.add(enemy);
        }

        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update(gameStage);
        }

        for (Tower tower : towers) {
            tower.update();
        }

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
        }

        while (!deathEnemy.isEmpty()) enemies.remove(deathEnemy.poll());
        while (!removingBullet.isEmpty()) bullets.remove(removingBullet.poll());
    }

    public void draw(GraphicsContext gc, GameField gameField) {
        //render 4 layers: background -> road -> treeRock -> spawn
        Render.renderMap(gc, gameField);

        /*
         * Draw Tower
         */
        for (Tower tower : towers) {
            tower.draw(gc, gameField);
        }

        /*
         * Draw Enemy
         */
        for (Enemy enemy : enemies) {
            enemy.draw(gc);
        }

        /*
         * Draw Enemy's HP_BAR
         */
        for (Enemy enemy:enemies){
            enemy.drawHPBar(gc);
        }

        /*
         * Draw bullets
         */
        for (Bullet bullet : bullets) {
            bullet.draw(gc);
        }

    }
}
