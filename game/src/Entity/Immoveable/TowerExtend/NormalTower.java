package Entity.Immoveable.TowerExtend;

import Entity.Immoveable.Tower;
import Entity.Moveable.Bullet.Bullet;
import Entity.Moveable.Bullet.NormalBullet;
import Entity.Moveable.Enemy.Enemy;
import core.Config;
import core.GameField;
import core.GameStage;

public class NormalTower extends Tower {

    public NormalTower() {
        super(Config.NORMAL_TOWER_SPEED, Config.NORMAL_TOWER_RANGE, Config.NORMAL_BULLET_UPDATE_COST);
        setImg(GameField.getImageSheet().imageSheet.get(8*23 + 19), GameField.getImageSheet().imageSheet.get(7*23 + 19));
        setCost(Config.NORMAL_TOWER_COST);
        GameStage.buy((int)getCost());
    }

    protected long tickDown = 0;

    @Override
    public void shoot(Enemy enemy) {
        if (tickDown-- > 0) return;
        if (shootingEnemy != null) {
            tickDown = (int) getSpeed();
            Bullet bullet = new NormalBullet(getPosX(),getPosY(),0,0);
            bullet.setEnemy(enemy);
            GameField.addBullet(bullet);
        }
    }

    @Override
    public void updateTower() {
        super.updateTower();
        GameStage.buy((int)Config.NORMAL_BULLET_UPDATE_COST);
        setImg(GameField.getImageSheet().imageSheet.get(9*23 + 19), GameField.getImageSheet().imageSheet.get(7*23 + 19));
        setUpdated(true);
    }
}
