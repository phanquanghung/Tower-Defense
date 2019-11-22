package Entity.Moveable.Bullet;

import core.Config;
import core.GameField;
import javafx.scene.canvas.GraphicsContext;

public class RocketBullet extends Bullet{
    public RocketBullet(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        setImg(GameField.getImageSheet().imageSheet.get(10*23 + 21));
        setInfo(Config.ROCKET_BULLET_SPEED, Config.ROCKET_BULLET_STRENGTH);
    }

    public void rocketBoom (GraphicsContext gc) {
        gc.drawImage(GameField.getImageSheet().imageSheet.get(22), getEnemy().getPosX(), getEnemy().getPosY());
    }
}
