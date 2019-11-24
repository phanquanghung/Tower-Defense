package Entity.Immoveable;

import Entity.Moveable.Bullet.Bullet;
import Entity.Moveable.Enemy.Enemy;
import Graphic.Render;
import core.GameField;
import core.GameStage;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public abstract class Tower implements TowerInterface{
    private double speed;
    private double range;
    private long cost;
    private long updateCost;
    private boolean updated = false;
    private Image image, stand;
    private double posX;
    private double posY;
    private double curAngle = 0;
    protected Enemy shootingEnemy = null;
    protected Queue<Enemy> enemyQueue;

    public Tower(double speed, double range, long cost) {
        setRange(range);
        setSpeed(speed);
        setUpdateCost(cost);
        enemyQueue = new LinkedList<>();
    }

    public boolean isUpdated() {
        return updated;
    }

    public void setUpdated(boolean updated) {
        this.updated = updated;
    }

    public void setUpdateCost(long updateCost) {
        this.updateCost = updateCost;
    }

    public long getUpdateCost() {
        return updateCost;
    }

    public void setCurAngle(double curAngle) {
        this.curAngle = curAngle;
    }

    public double getCurAngle() {
        return curAngle;
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

    public void setSpeed(double speed) {
        this.speed = speed;
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
    public void deleteTower() {
        GameField.getDeletedTower().add(this);
        GameStage.earnMoney((int)this.getCost()*80/100);
    }

    @Override
    public void updateTower() {

    }

    @Override
    public void updateBullet(Bullet bullet) {
        bullet.updateBullet();
    }

    @Override
    public void update(){
        //Every time, check if there're enemies in tower range => add to enemy stack
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

    @Override
    public void shoot(Enemy enemy) {

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

            if (angle < 0) setCurAngle(Math.abs(angle));
            else setCurAngle(2*Math.PI - angle);
        }
        return getCurAngle();
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
