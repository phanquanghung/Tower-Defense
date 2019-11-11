package Entity.Moveable.Enemy;

import core.Config;
import core.GameField;
import javafx.scene.canvas.GraphicsContext;

public class NormalEnemy extends Enemy{
    //TODO: The Normal enemy image at imgSheet[245]
    public NormalEnemy() {
        super(Config.NORMAL_ENEMY_SPEED, Config.NORMAL_ENEMY_ARMOR, Config.NORMAL_BULLET_HEALTH, Config.NORMAL_ENEMY_REWARD);
        setImg(GameField.getImageSheet().imageSheet.get(245));
        setFirstPos();
    }

    @Override
    public void draw(GraphicsContext gc) {
        double curPosX = getPosX();
        double curPosY = getPosY();
        System.out.println("Current = " + curPosX + " " + curPosY);
        System.out.println("next Road Value = " + getNextRoadValue());
        if (getNextRoadValue()>0){

            switch (getDirection()){
                case UP:
                    setPosY(curPosY - 1);
                    break;
                case DOWN:
                    setPosY(curPosY + 1);
                    break;
                case LEFT:
                    setPosX(curPosX - 1);
                    break;
                case RIGHT:
                    setPosX(curPosX + 1);
                    break;
            }
            if (getNextRoadValue() == 3) {
                //TODO: Decline player's HP then delete the enemy
                //destroy(this);
            }
        }
        else setDirection(findDirection());
        rotateEnemy(gc, getImg(), getPosX(), getPosY());
//        gc.drawImage(getImg(), getPosX(), getPosY());
        //gc.restore();
    }

}