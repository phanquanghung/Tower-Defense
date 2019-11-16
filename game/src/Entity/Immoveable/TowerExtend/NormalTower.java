package Entity.Immoveable.TowerExtend;

import Entity.Immoveable.Tower;
import Entity.Moveable.Bullet;
import Entity.Moveable.Enemy.Enemy;
import core.Config;
import core.GameField;
import core.GameStage;

public class NormalTower extends Tower {

    public NormalTower() {
        super(Config.NORMAL_TOWER_SPEED, Config.NORMAL_TOWER_RANGE, Config.NORMAL_BULLET_STRENGTH);
        setImg(GameField.getImageSheet().imageSheet.get(10*23 + 19), GameField.getImageSheet().imageSheet.get(7*23 + 19));
        setCost(Config.NORMAL_TOWER_COST);
        GameStage.buyTower((int)getCost());
    }

    protected long tickDown = 0;
    public void shoot(Enemy enemy) {
        if (tickDown-- > 0) return;
        if (shootingEnemy != null) {
            tickDown = (int) getSpeed();
            Bullet bullet = new Bullet(getPosX(),getPosY(),0,0);
            bullet.setEnemy(enemy);
            GameField.addBullet(bullet);
        }
    }

    @Override
    public void update(){
        //TODO: tìm vị trí của enemy, vị trí của tower và bắn
        //Every time, check if there're enemies in tower range => add to enemy stack
        for (Enemy enemy : GameField.getEnemies()){
            if (enemy.evaluateDistance(this) < getRange() && !(enemyQueue.contains(enemy))){
                addToQueue(enemy);
            }
        }

        Enemy enemy = getShootingEnemy();
        if (shootingEnemy == null) {
            if (!enemyQueue.isEmpty()) {
                enemy = enemyQueue.poll();
                if (enemy != null && enemy.evaluateDistance(this) < getRange()) {
                    setShootingEnemy(enemy);
                } else {
                    setShootingEnemy(null);
                }
            }
        } else if (shootingEnemy.evaluateDistance(this) > getRange() || (shootingEnemy.getHealth()<0)) {
            setShootingEnemy(null);
        } else {
            //System.out.println("Distance = " + enemy.evaluateDistance(this) + " Added!");
            shoot(enemy);
        }
    }
}
