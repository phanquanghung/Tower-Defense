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
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Pair;

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
    private static ArrayList<Queue<Enemy>> enemyWaiting = new ArrayList<>(); //Enemies that are waiting to be spawned

    private static Queue<Bullet> removingBullet = new LinkedList<>(); //Bullet has been shoot and hit the target
    private static ArrayList<Enemy> enemies = new ArrayList<>(); //Enemies array
    private static Queue<Enemy> deathEnemy = new LinkedList<>(); //Enemies has been killed or go through the map
    private static Queue<Tower> deletedTower = new LinkedList<>(); //Tower that has been deleted

    private static int wave = 1; //wave order
    private static Road roadInfo = new Road();
    private static GameStage gameStage = new GameStage();

    public GameField() {
//        loadMap();
        loadGameplay();
    }

    public static ArrayList<Queue<Enemy>> getEnemyWaiting() {
        return enemyWaiting;
    }

    public static Queue<Enemy> getDeathEnemy() {
        return deathEnemy;
    }

    public static Queue<Tower> getDeletedTower() {
        return deletedTower;
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

    public static int getWave() {
        return wave;
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

    public static void setRoadInfo(Road roadInfo) {
        GameField.roadInfo = roadInfo;
    }

    public void setEnemyWaiting(ArrayList<Queue<Enemy>> enemyWaiting) {
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

    public void gameOver() {
        //TODO: return to menu
        gameStage.gameOver();
        if (gameStage.getStatus() == GameStage.Status.WIN) {
            //GameController.menu(theScene, gameField, root, gc, stage);
            System.out.println("YOU WIN! BRO!!!");
        }
        else if (gameStage.getStatus() == GameStage.Status.LOSS){
            System.out.println("YOU LOSS! BRO!!!");
        }
    }

    public void pauseGame(){
        gameStage.setStatus(GameStage.Status.PAUSE);
    }

    public void unpauseGame(){
        gameStage.setStatus(GameStage.Status.RUNNING);
    }

    public void spawnEnemy(long time) {
        if (!enemyWaiting.get(wave).isEmpty()) {
            Enemy enemy = enemyWaiting.get(wave).peek();
            if (enemy == null) {
                enemyWaiting.get(wave).poll();
                return;
            }
            else {
                enemies.add(enemy);
                enemyWaiting.get(wave).poll();
            }
        }
        else if (enemyWaiting.get(wave).isEmpty() && enemies.size()-1 <0){
            wave++;
        }
    }

    private long tickLastSpawn;

    public void update(long time) {
//        System.out.println("****************Player Money = " + gameStage.getPlayerFinance());
//        System.out.println("Player Heart = " + gameStage.getPlayerHP());
//        System.out.println("*****Wave: " + getWave());
        if(gameStage.getStatus() != GameStage.Status.RUNNING) return;
        if ((time - tickLastSpawn >= 10E8)) {
            spawnEnemy(time);
            tickLastSpawn = time;
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
        while (!deletedTower.isEmpty()) towers.remove(deletedTower.poll());
    }

    public void draw(GraphicsContext gc, GameField gameField, Label heartLabel, Label coinLabel, int playerHP, int playerFinance) {
        heartLabel.setText(Integer.toString(playerHP));
        coinLabel.setText(Integer.toString(playerFinance));
        //render 4 layers: background -> road -> treeRock -> spawn
        Render.renderMap(gc, gameField);

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

        /*
         * Draw Tower
         */
        for (Tower tower : towers) {
            tower.draw(gc, gameField);
        }
    }
}
