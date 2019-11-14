package core;

/*
|===================================================|
|   This class manages every object in the field    |
|===================================================|
 */

import Entity.Immoveable.Road;
import Entity.Immoveable.Tower;
import Entity.Moveable.Bullet;
import Entity.Moveable.Enemy.*;
import Graphic.ImageSheet;
import Graphic.Render;
import Graphic.TileMap;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

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

    private static Queue<Bullet> removingBullet = new LinkedList<>(); //Bullet has been shoot and hit the target

    private static Road roadInfo = new Road();

    private static ArrayList<Enemy> enemies = new ArrayList<>(); //Enemies array

    private static Queue<Enemy> deathEnemy = new LinkedList<>(); //Enemies has been killed or go through the map

    private static GameStage gameStage = new GameStage();

    public GameField (){
        loadMap();
        loadGameplay();
    }

    public static Queue<Enemy> getDeathEnemy() {
        return deathEnemy;
    }

    public static Queue<Bullet> getRemovingBullet() {
        return removingBullet;
    }

    public static ImageSheet getImageSheet(){
        return imageSheet;
    }

    public TileMap getBackground (){
        return background;
    }

    public TileMap getRoad (){
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

    public void addEnemy(Enemy enemy) {
        GameField.enemies.add(enemy);
    }

    public ArrayList<Tower> getTowers() {
        return towers;
    }

    public TileMap getTower() { return tower; }

    public void addTower(Tower tower) {
        GameField.towers.add(tower);
    }

    public static void addBullet(Bullet bullet) {
        GameField.bullets.add(bullet);
    }

    public static void loadMap(){
        //input layer data
        background.readFile("Map/background3.txt");
        road.readFile("Map/road3.txt");
        rockTree.readFile("Map/rockTree3.txt");
        spawn.readFile("Map/spawn3.txt");
        tower.readFile("Map/spawn3.txt");

        //print to check
        background.printMapData();
        road.printMapData();
        rockTree.printMapData();
        spawn.printMapData();

        //load tiled sheet
        imageSheet.loadImageViewSheet("Graphic/towerDefense_tilesheet.png");
        // render to check
        // Render.renderMap(background.getTileMap(), imageSheet);

        //load road information
        roadInfo.setRoadInfo(road.getTileMap());
        roadInfo.printTestRoadInfo();
    }

    public static void loadGameplay(){
        tower.setName("Tower");
        for (int i = 0; i < Config.MAP_HEIGHT; i++) {
            for (int j = 0; j < Config.MAP_WIDTH; j++) {
                if (tower.getTileMap(i,j)==16) tower.setTileMap(i,j,1);
            }
        }
        System.out.println();
        tower.printMapData();
    }

    public void gameOver(Stage stage){
        if (gameStage.gameOver()) Render.closeWindow(stage);
    }
    private long tickLastSpawn;
    public void update(long time){
//        System.out.println("time = " + time);
//        System.out.println("Player Money = " + gameStage.getPlayerFinance());

        if ((time - tickLastSpawn >= 10E8)) {
            Enemy enemy = new SmallerEnemy();
            enemy.setDirection(Enemy.Direction.UP); //default
            System.out.println("NEW ENEMY");
            tickLastSpawn = time;
            enemies.add(enemy);
        }

        for (int i=0; i<enemies.size(); i++){
            enemies.get(i).update(gameStage);
        }

        for (Tower tower : towers){
            tower.update();
        }

        for (int i=0; i<bullets.size(); i++){
            bullets.get(i).update();
        }

        while (!deathEnemy.isEmpty()) enemies.remove(deathEnemy.poll());
        while (!removingBullet.isEmpty()) bullets.remove(removingBullet.poll());
    }

    public void draw(GraphicsContext gc, GameField gameField){
        //render 4 layers: background -> road -> treeRock -> spawn
        Render.renderMap(gc, gameField);
        //draw tower

        for (Tower tower:towers) {
            tower.draw(gc, gameField);
        }

        /*
         * Draw Enemy
         */
        for(Enemy enemy:enemies){
            enemy.draw(gc);
        }

        /*
         * Draw Enemy HP bar
         */
//        for (Enemy enemy:enemies){
//            enemy.drawHPBar(gc);
//        }

        /*
         * Draw bullets
         */
        for(Bullet bullet:bullets){
            bullet.draw(gc);
        }

    }
}
