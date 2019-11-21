package Entity.Moveable.Bullet;

import core.Config;
import core.GameField;

public class SniperBullet extends Bullet {

    public SniperBullet(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        setImg(GameField.getImageSheet().imageSheet.get(11*23 + 19));
        setInfo(Config.SNIPER_BULLET_SPEED, Config.SNIPER_BULLET_STRENGTH);
    }
}
