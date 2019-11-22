package Entity.Moveable.Bullet;

import Graphic.Render;
import core.Config;
import core.GameField;
import javafx.scene.canvas.GraphicsContext;

public class RocketBullet extends Bullet{
    public RocketBullet(double posX, double posY, double width, double height) {
        super(posX, posY, width, height);
        setImg(GameField.getImageSheet().imageSheet.get(10*23 + 21));
        setInfo(Config.ROCKET_BULLET_SPEED, Config.ROCKET_BULLET_STRENGTH);
    }

    public void rocketBoom (GraphicsContext gc) {
        gc.drawImage(GameField.getImageSheet().imageSheet.get(22), getEnemy().getPosX(), getEnemy().getPosY());
    }

    public double findAngle(){
        if (getEnemy() != null){
            double x = getEnemy().getPosX() - getPosX();
            double y = getEnemy().getPosY() - getPosY();
            double angle = Math.atan2(x, y);
            if (angle < 0) return (Math.abs(angle));
            else return (2*Math.PI - angle);
        }
        return 0;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.save(); // saves the current state on stack, including the current transform
        double x = getEnemy().getPosX() - getPosX();
        double y = getEnemy().getPosY() - getPosY();
        double inRads = Math.atan2(x, y);
        setPosX(getPosX() + getSpeed() * Math.sin(inRads));
        setPosY(getPosY() + getSpeed() * Math.cos(inRads));

        double angle = Math.toDegrees(Math.PI + findAngle());
        Render.rotate(gc, angle, getPosX() + 32, getPosY() + 32);
        gc.drawImage(getImg(), getPosX(), getPosY());
        gc.restore();
    }
}
