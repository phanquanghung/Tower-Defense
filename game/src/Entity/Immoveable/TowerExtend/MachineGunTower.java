package Entity.Immoveable.TowerExtend;

import Entity.Immoveable.Tower;
import Entity.Moveable.Bullet.Bullet;
import Entity.Moveable.Bullet.MachineBullet;
import Entity.Moveable.Enemy.Enemy;
import core.Config;
import core.GameField;
import core.GameStage;

public class MachineGunTower extends Tower {

    public MachineGunTower() {
        super(Config.MACHINE_GUN_TOWER_SPEED, Config.MACHINE_GUN_TOWER_RANGE, Config.MACHINE_GUN_BULLET_UPDATE_COST);
        //TODO: find the imageSheet index for machine gun
        setImg(GameField.getImageSheet().imageSheet.get(10*23 + 20), GameField.getImageSheet().imageSheet.get(7*23 + 20));
        setCost(Config.MACHINE_GUN_TOWER_COST);
        GameStage.buy((int)getCost());
    }

    protected long tickDown = 0;

    @Override
    public void shoot(Enemy enemy) {
        if (tickDown-- > 0) return;
        //System.out.println("SHOOT!");
        if (shootingEnemy != null) {
            tickDown = (int) getSpeed();
            //2 bullet
            //TODO: separate image of 2 bullet
            Bullet bullet1 = new MachineBullet(getPosX(),getPosY(),0,0);
            Bullet bullet2 = new MachineBullet(getPosX(),getPosY(),0,0);
            if (isUpdated()) {
                bullet1.updateBullet();
                bullet2.updateBullet();
            }
            bullet1.setEnemy(enemy);
            bullet2.setEnemy(enemy);
            GameField.addBullet(bullet1);
            GameField.addBullet(bullet2);
        }
    }

    @Override
    public void updateTower() {
        super.updateTower();
        GameStage.buy((int)Config.MACHINE_GUN_BULLET_UPDATE_COST);
        setUpdated(true);
    }
}
