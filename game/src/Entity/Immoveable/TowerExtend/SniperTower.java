package Entity.Immoveable.TowerExtend;

import Entity.Immoveable.Tower;
import Entity.Moveable.Bullet.Bullet;
import Entity.Moveable.Bullet.SniperBullet;
import Entity.Moveable.Enemy.Enemy;
import core.Config;
import core.GameField;
import core.GameStage;

public class SniperTower extends Tower {

    public SniperTower() {
        super(Config.SNIPER_TOWER_SPEED, Config.SNIPER_TOWER_RANGE, Config.SNIPER_BULLET_UPDATE_COST);
        setImg(GameField.getImageSheet().imageSheet.get(10*23 + 19), GameField.getImageSheet().imageSheet.get(7*23 + 19));
        setCost(Config.SNIPER_TOWER_COST);
        GameStage.buy((int)getCost());
    }

    protected long tickDown = 0;

    @Override
    public void shoot(Enemy enemy) {
        if (tickDown-- > 0) return;
        if (shootingEnemy != null) {
            tickDown = (int) getSpeed();
            Bullet bullet = new SniperBullet(getPosX(),getPosY(),0,0);
            bullet.setEnemy(enemy);
            GameField.addBullet(bullet);
        }
    }

    @Override
    public void updateTower() {
        super.updateTower();
        GameStage.buy((int)Config.SNIPER_BULLET_UPDATE_COST);
        setUpdated(true);
    }
}
