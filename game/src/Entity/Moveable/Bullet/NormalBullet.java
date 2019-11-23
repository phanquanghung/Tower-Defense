package Entity.Moveable.Bullet;

import core.Config;
import core.GameField;

public class NormalBullet extends Bullet {

    public NormalBullet(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        setImg(GameField.getImageSheet().imageSheet.get(11*23 + 22));
        setInfo(Config.NORMAL_BULLET_SPEED, Config.NORMAL_BULLET_STRENGTH);
    }

    @Override
    public void updateBullet(){
        setDamage(getDamage() + Config.NORMAL_BULLET_UPDATE);
    }
}
