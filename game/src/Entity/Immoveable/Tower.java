package Entity.Immoveable;

import Entity.Moveable.Enemy.Enemy;
import Graphic.Render;
import core.GameField;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public abstract class Tower implements TowerInterface{
    private double speed;
    private double range;
    private long cost;
    private double strength;
    private Image image, stand;
    private double posX;
    private double posY;
    protected Enemy shootingEnemy = null;
    protected Queue<Enemy> enemyQueue;

    public Tower(double speed, double range, double strength) {
        setRange(range);
        setSpeed(speed);
        setStrength(strength);
        enemyQueue = new LinkedList<>();
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public long getCost() {
        return cost;
    }

    //@Override
    public void setImg(Image image, Image stand) {
        this.image = image;
        this.stand = stand;
    }

    public void addToQueue(Enemy enemy){
        enemyQueue.add(enemy);
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

    public double getSpeed() {
        return speed;
    }

    public double getStrength() {
        return strength;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setStrength(double strength) {
        this.strength = strength;
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
    public void update(){
        for (Enemy enemy : GameField.getEnemies()){
            if (enemy.evaluateDistance(this) < getRange() && !(enemyQueue.contains(enemy))){
                addToQueue(enemy);
            }
        }

        Enemy enemy = getShootingEnemy();
        if (shootingEnemy == null) {
            if (!enemyQueue.isEmpty()) {
                enemy = enemyQueue.poll();
                if (enemy != null && enemy.evaluateDistance(this) < getRange()) {
                    setShootingEnemy(enemy);
                } else {
                    setShootingEnemy(null);
                }
            }
        } else if (shootingEnemy.evaluateDistance(this) > getRange() || (shootingEnemy.getHealth()<0)) {
            setShootingEnemy(null);
        } else {
            //System.out.println("Distance = " + enemy.evaluateDistance(this) + " Added!");
            shoot(enemy);
        }
    }

    public void shoot (Enemy enemy){

    }

    @Override
    public void setPosXY(double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
    }

    @Override
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
        //System.out.println("ANGLE = " + angle);
        Render.rotate(gc, angle, getPosX() + 32, getPosY() + 32);
        gc.drawImage(getImg(), getPosX(), getPosY()-8);
        gc.restore();
    }
}
