package Entity.Immoveable.TowerExtend;

import Entity.Immoveable.GameTile;
import Entity.Immoveable.Tower;
import core.Config;
import core.GameField;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class NormalTower implements GameTile, Tower {
    private double speed;
    private double range;
    private double strength;
    private Image image;
    private double posX;
    private double posY;

    public NormalTower() {
        this.speed = Config.NORMAL_BULLET_SPEED;
        this.range = Config.NORMAL_TOWER_RANGE;
        this.strength = Config.NORMAL_BULLET_STRENGTH;
        setImg(GameField.getImageSheet().imageSheet.get(10*23 + 19));
    }

    @Override
    public void setImg(Image image) {
        this.image = image;
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
    public void draw(GraphicsContext gc) {
        gc.drawImage(getImg(), getPosX(), getPosY());
    }
}
