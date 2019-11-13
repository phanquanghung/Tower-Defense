package Entity.Immoveable.TowerExtend;

import Entity.Immoveable.GameTile;
import Entity.Immoveable.Tower;
import Entity.Moveable.Bullet;
import Entity.Moveable.Enemy.Enemy;
import Graphic.Render;
import core.Config;
import core.GameField;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Stack;

public class NormalTower implements GameTile, Tower {
    private double speed;
    private double range;
    private double strength;
    private Image image, stand;
    private double posX;
    private double posY;
    private Enemy shootingEnemy = null;
    private Stack<Enemy> enemyStack;

    public NormalTower() {
        super();
        this.speed = Config.NORMAL_BULLET_SPEED;
        this.range = Config.NORMAL_TOWER_RANGE;
        this.strength = Config.NORMAL_BULLET_STRENGTH;
        setImg(GameField.getImageSheet().imageSheet.get(10*23 + 19), GameField.getImageSheet().imageSheet.get(7*23 + 19));
        enemyStack = new Stack<>();
    }

    @Override
    public void setImg(Image image, Image stand) {
        this.image = image;
        this.stand = stand;
    }

    public void addToStack(Enemy enemy){
        enemyStack.add(enemy);
    }

    public double getRange() {
        return range;
    }

    public void setShootingEnemy(Enemy shootingEnemy) {
        this.shootingEnemy = shootingEnemy;
    }

    public Enemy getShootingEnemy() {
        return shootingEnemy;
    }



    public void setRange(double range) {
        this.range = range;
    }

    @Override
    public Image getImgStand() {
        return stand;
    }

    @Override
    public Image getImg() {
        return image;
    }

    @Override
    public double getPosX() {
        return posX;
    }

    @Override
    public double getPosY() {
        return posY;
    }

    @Override
    public void setPosXY(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    @Override
    public void shoot(Enemy enemy) {
        Bullet bullet = new Bullet(getPosX(),getPosY(),0,0);
        bullet.setEnemy(enemy);
        GameField.addBullet(bullet);
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public void update(long time){
        //TODO: tìm vị trí của enemy, vị trí của tower và bắn
        //Every time, check if there're enemies in tower range => add to enemy stack
        for (Enemy enemy : GameField.getEnemies()){
            if (enemy.evaluateDistance(this) < getRange()){
                addToStack(enemy);
            }
        }

        Enemy enemy = getShootingEnemy();
        if (shootingEnemy == null) {
            if (!enemyStack.isEmpty()) {
                if (enemyStack.peek() != null && enemyStack.peek().evaluateDistance(this) < getRange()) {
                    enemy = enemyStack.pop();
                    setShootingEnemy(enemy);
                } else {
                    setShootingEnemy(null);
                }
            }
        } else if (shootingEnemy.evaluateDistance(this) > getRange()) {
            setShootingEnemy(null);
        } else {
            System.out.println("Distance = " + enemy.evaluateDistance(this) + " Added!");
            shoot(enemy);
        }
    }

    public double findAngle(){
        if (shootingEnemy != null){
            double x = getShootingEnemy().getPosX() - getPosX();
            double y = getShootingEnemy().getPosY() - getPosY();
            double angle = Math.atan2(x, y);
            if (angle < 0) return Math.abs(angle);
            else return 2*Math.PI - angle;
        }
        return 0;
    }

    @Override
    public void draw(GraphicsContext gc, GameField gameField) {
        gc.save(); // saves the current state on stack, including the current transform
        gc.drawImage(getImgStand(), getPosX(), getPosY());
        double angle = Math.toDegrees(Math.PI + findAngle());
        System.out.println("ANGLE = " + angle);
        Render.rotate(gc, angle, getPosX() + 32, getPosY() + 32);
        gc.drawImage(getImg(), getPosX(), getPosY()-8);
        gc.restore();
    }


}
