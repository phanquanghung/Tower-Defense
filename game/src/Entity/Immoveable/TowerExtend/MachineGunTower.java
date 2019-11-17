package Entity.Immoveable.TowerExtend;

import Entity.Immoveable.Tower;
import Entity.Moveable.Bullet;
import Entity.Moveable.Enemy.Enemy;
import core.Config;
import core.GameField;

public class MachineGunTower extends Tower {

    public MachineGunTower() {
        super(Config.MACHINE_GUN_TOWER_SPEED, Config.MACHINE_GUN_TOWER_RANGE, Config.MACHINE_GUN_BULLET_STRENGTH);
        //TODO: find the imageSheet index for machine gun
        setImg(GameField.getImageSheet().imageSheet.get(10*23 + 20), GameField.getImageSheet().imageSheet.get(7*23 + 20));
    }

    public void shoot(Enemy enemy) {
        Bullet bullet = new Bullet(getPosX(),getPosY(),0,0);
        bullet.setEnemy(enemy);
        GameField.addBullet(bullet);
    }

    @Override
    public void update(){
//        //TODO: súng ống mới, đạn mới, các bắn mới
//        //Every time, check if there're enemies in tower range => add to enemy stack
//        for (Enemy enemy : GameField.getEnemies()){
//            if (enemy.evaluateDistance(this) < getRange()){
//                addToStack(enemy);
//            }
//        }
//
//        Enemy enemy = getShootingEnemy();
//        if (shootingEnemy == null) {
//            if (!enemyStack.isEmpty()) {
//                if (enemyStack.peek() != null && enemyStack.peek().evaluateDistance(this) < getRange()) {
//                    enemy = enemyStack.pop();
//                    setShootingEnemy(enemy);
//                } else {
//                    setShootingEnemy(null);
//                }
//            }
//        } else if (shootingEnemy.evaluateDistance(this) > getRange()) {
//            setShootingEnemy(null);
//        } else {
//            System.out.println("Distance = " + enemy.evaluateDistance(this) + " Added!");
//            shoot(enemy);
//        }
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
