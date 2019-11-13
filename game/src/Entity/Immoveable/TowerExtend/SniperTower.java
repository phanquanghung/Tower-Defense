package Entity.Immoveable.TowerExtend;

import Entity.Immoveable.Tower;
import Entity.Moveable.Bullet;
import core.GameField;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class SniperTower implements Tower {

    @Override
    public void setImg(Image image, Image stand) {

    }

    @Override
    public void draw(GraphicsContext gc, GameField gameField) {

    }

    @Override
    public Image getImgStand() {
        return null;
    }

    @Override
    public Image getImg() {
        return null;
    }

    @Override
    public double getPosX() {
        return 0;
    }

    @Override
    public double getPosY() {
        return 0;
    }

    @Override
    public void setPosXY(double posX, double posY) {

    }

    @Override
    public void shoot(GraphicsContext gc, GameField gameField) {

    }
}
