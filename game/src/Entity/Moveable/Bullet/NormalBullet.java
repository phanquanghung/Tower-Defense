package Entity.Moveable.Bullet;

import core.Config;
import core.GameField;

public class NormalBullet extends Bullet {

    public NormalBullet(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        setImg(GameField.getImageSheet().imageSheet.get(11*23 + 19));
        setInfo(Config.MACHINE_GUN_BULLET_SPEED, Config.MACHINE_GUN_BULLET_STRENGTH);
    }
}
