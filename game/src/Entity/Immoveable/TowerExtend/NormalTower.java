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

public class NormalTower implements GameTile, Tower {
    private double speed;
    private double range;
    private double strength;
    private Image image, stand;
    private double posX;
    private double posY;

    public NormalTower() {
        this.speed = Config.NORMAL_BULLET_SPEED;
        this.range = Config.NORMAL_TOWER_RANGE;
        this.strength = Config.NORMAL_BULLET_STRENGTH;
        setImg(GameField.getImageSheet().imageSheet.get(10*23 + 19), GameField.getImageSheet().imageSheet.get(7*23 + 19));
    }

    @Override
    public void setImg(Image image, Image stand) {
        this.image = image;
        this.stand = stand;
    }

    public double getRange() {
        return range;
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
    public void shoot(GraphicsContext gc, GameField gameField) {
        for (Enemy enemy:gameField.getEnemies()){
            if (enemy.getDistance() < getRange()){
                //TODO: tìm vị trí của enemy, vị trí của tower và bắn
                Bullet bullet = new Bullet(getPosX(),getPosY(),0,0);
                gameField.addBullet(bullet);
            }
        }
//        Enemy.destroy(GameField.getEnemies().get(0));
    }

    public void update(){
        for (Enemy enemy : GameField.getEnemies()){
            if (enemy.getDistance() < getRange()){
                //TODO: tìm vị trí của enemy, vị trí của tower và bắn
                Bullet bullet = new Bullet(getPosX(),getPosY(),0,0);
                //GameField.ad
            }
        }
    }

    @Override
    public void draw(GraphicsContext gc, GameField gameField) {
        gc.save(); // saves the current state on stack, including the current transform

        gc.drawImage(getImgStand(), getPosX(), getPosY());
//        double angle= Bullet.findAngle(getPosX()+32, getPosY()+24, GameField.getEnemies());
//        Render.rotate(gc, angle, getPosX() + 32, getPosY() + 32);
        gc.drawImage(getImg(), getPosX(), getPosY()-8);
//        gc.restore();
    }


}
