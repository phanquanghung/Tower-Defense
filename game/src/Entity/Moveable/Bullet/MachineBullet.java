package Entity.Moveable.Bullet;

import core.Config;
import core.GameField;

public class MachineBullet extends Bullet {

    public MachineBullet(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        setImg(GameField.getImageSheet().imageSheet.get(11*23 + 20));
        setInfo(Config.MACHINE_GUN_BULLET_SPEED, Config.MACHINE_GUN_BULLET_STRENGTH);
    }
}
