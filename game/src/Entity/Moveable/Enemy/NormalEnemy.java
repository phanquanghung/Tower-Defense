package Entity.Moveable.Enemy;

import core.Config;
import core.GameField;

public class NormalEnemy extends Enemy{
    //TODO: The Normal enemy image at imgSheet[245]
    public NormalEnemy() {
        this.speed = Config.NORMAL_ENEMY_SPEED;
        this.scale = Config.NORMAL_ENEMY_SIZE;
        this.armor = Config.NORMAL_ENEMY_ARMOR;
        this.health = Config.NORMAL_ENEMY_HEALTH;
        this.reward = Config.NORMAL_ENEMY_REWARD;
        setImg(GameField.getImageSheet().imageSheet.get(245));
        setFirstPos();
    }


}