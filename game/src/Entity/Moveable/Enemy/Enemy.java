package Entity.Moveable.Enemy;

import Entity.AbstractEntity;
import Entity.Immoveable.Tower;
import Graphic.Render;
import core.Config;
import core.GameField;
import core.GameStage;
import javafx.geometry.Rectangle2D;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

/*
|===========================================================================================================|
|   Extend from GameEntity, this is interface of all Enemy, contain properties like HP, speed, armor, price |
|===========================================================================================================|
 */

public abstract class Enemy extends AbstractEntity {
    protected double speed, scale;
    protected long armor, health, reward;
    protected Image img;
    private Direction direction;

    public enum Direction {
        LEFT, RIGHT, UP, DOWN;
    }

//    public Enemy(double posX, double posY, double witdth, double height) {
//        super(posX, posY, witdth, height);
//    }

    public Enemy (){}

    public void setFirstPos() {
        setPosX(GameField.getRoadInfo().getSpawn().getPosX()*Config.TILE_HORIZONTAL + 31);
        setPosY(GameField.getRoadInfo().getSpawn().getPosY()*Config.TILE_VERTICAL);
    }

    public abstract double getMaxHealth ();

    public Direction getDirection() {
        return direction;
    }

    public double getSpeed() {
        return this.speed;
    }

    public long getArmor() {
        return armor;
    }

    public long getHealth() {
        return health;
    }

    public long getReward() {
        return reward;
    }

    public double getScale() {
        return scale;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Image getImg() {
        return img;
    }

    public void setHealth(long health) {
        this.health = health;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setReward(long reward) {
        this.reward = reward;
    }

    public void setArmor(long armor) {
        this.armor = armor;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public void setup(Image img){
        ImageView imageView = new ImageView(img);
        SnapshotParameters sp = new SnapshotParameters();
        sp.setViewport(new Rectangle2D(0, 0, Config.TILE_HORIZONTAL, Config.TILE_VERTICAL));
        sp.setFill(Color.TRANSPARENT);
        imageView.setScaleX(getScale());
        imageView.setScaleY(getScale());
        setImg(imageView.snapshot(sp, null));
    }

    public void rotateEnemy(GraphicsContext gc, Image image, double tlpx, double tlpy) {
        gc.save(); // saves the current state on stack, including the current transform
        double angle;

        switch (getDirection()) {
            case LEFT:
                angle = 30;
                break;
            case UP:
                angle = 270;
                break;
            case RIGHT:
                angle = 0;
                break;
            case DOWN:
                angle = 90;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + getDirection());
        }
        Render.rotate(gc, angle, tlpx + image.getWidth() / 2, tlpy + image.getHeight() / 2);
        //gc.drawImage(getImg(), tlpx, tlpy);
        //gc.restore(); // back to original state (next before rotation)
    }

    public Direction findDirection() {
        /*
         * This function doesn't work properly, it happen when reach i = 3, j = 10, it go LEFT, UP, RIGHT
         */
        int index = (int) Math.round(getPosY() / Config.TILE_HORIZONTAL);
        int jndex = (int) Math.round(getPosX() / Config.TILE_VERTICAL);
        //System.out.println("index = " + index + " jndex = " + jndex);
        switch (getDirection()) {
            case UP:
                double x1U = GameField.getRoadInfo().getRoadInfo(index, jndex + 1); // right
                double x2U = GameField.getRoadInfo().getRoadInfo(index, jndex - 1); // left
                //System.out.println("Left is: " + x2U + " Right is: " + x1U);
                if (x1U > x2U) {
                    //System.out.println("RIGHT");
                    return Direction.RIGHT;
                } else {
                    //System.out.println("LEFT");
                    return Direction.LEFT;
                }
            case DOWN:
                double x1D = GameField.getRoadInfo().getRoadInfo(index, jndex + 1); // left
                double x2D = GameField.getRoadInfo().getRoadInfo(index, jndex - 1); // right
                //System.out.println("Left is: " + x1D + " Right is: " + x2D);
                if (x1D < x2D) {
                    //System.out.println("RIGHT");
                    return Direction.RIGHT;
                } else {
                    //System.out.println("LEFT");
                    return Direction.LEFT;
                }
            case LEFT:
                double x3L = GameField.getRoadInfo().getRoadInfo(index - 1, jndex); //up
                double x4L = GameField.getRoadInfo().getRoadInfo(index + 1, jndex); //down
                //System.out.println("Up is: " + x3L + " Down is: " + x4L);
                if (x3L < x4L) {
                    //System.out.println("UP");
                    return Direction.UP;
                } else {
                    //System.out.println("DOWN");
                    return Direction.DOWN;
                }
            case RIGHT:
                double x3R = GameField.getRoadInfo().getRoadInfo(index - 1, jndex); //
                double x4R = GameField.getRoadInfo().getRoadInfo(index + 1, jndex);
                //System.out.println("Up is: " + x3R + " Down is: " + x4R);
                if (x3R > x4R) {
                    //System.out.println("UP");
                    return Direction.UP;
                } else {
                    //System.out.println("DOWN");
                    return Direction.DOWN;
                }
        }
        return getDirection();
    }

    public double getNextRoadValue() {
        int index = (int) Math.round(getPosY() / Config.TILE_HORIZONTAL);
        int jndex = (int) Math.round(getPosX() / Config.TILE_VERTICAL);
        //System.out.println("index = " + index + " jndex = " + jndex);

        /*
         * This function work properly, do not touch
         */

        switch (getDirection()) {
            case UP:
                return GameField.getRoadInfo().getRoadInfo((int) index - 1, (int) jndex);
            case DOWN:
                return GameField.getRoadInfo().getRoadInfo((int) index + 1, (int) jndex);
            case LEFT:
                return GameField.getRoadInfo().getRoadInfo((int) index, (int) jndex - 1);
            case RIGHT:
                return GameField.getRoadInfo().getRoadInfo((int) index, (int) jndex + 1);
        }
        return 0;
    }

    public void drawHPBar (GraphicsContext gc){
        double HP = getHealth()/getMaxHealth();
        if (HP<1.0 && HP>0){
            gc.setFill(Color.BLACK);
            gc.fillRect(getPosX(), getPosY(), Config.HP_BAR_WIDTH, Config.HP_BAR_HEIGHT);
            if (HP > 0.6){
                gc.setFill(Color.GREEN);
                gc.fillRect(getPosX(), getPosY(), HP*Config.HP_BAR_WIDTH, Config.HP_BAR_HEIGHT);

            }
            else if (HP > 0.3) {
                gc.setFill(Color.ORANGE);
                gc.fillRect(getPosX(), getPosY(), HP*Config.HP_BAR_WIDTH, Config.HP_BAR_HEIGHT);
            }
            else {
                gc.setFill(Color.RED);
                gc.fillRect(getPosX(), getPosY(), HP*Config.HP_BAR_WIDTH, Config.HP_BAR_HEIGHT);
            }
            gc.setStroke(Color.BLACK);
            gc.strokeRect(getPosX(), getPosY(), Config.HP_BAR_WIDTH, Config.HP_BAR_HEIGHT);
        }
    }

    public double evaluateDistance(Tower tower) {
        //to-do: calculate the distance between tower and enemy?
        double x1 = getPosX() + Config.TILE_VERTICAL/2;
        double y1 = getPosY() + Config.TILE_HORIZONTAL/2;
        double x2 = tower.getPosX() + Config.TILE_VERTICAL/2;
        double y2 = tower.getPosY() + + Config.TILE_HORIZONTAL/2;
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    public boolean hitTarget (){
        double x = getPosX();
        double y = getPosY();
//        System.out.println("x = " + x + " y = " + y);
        if (x < - getSpeed()*100 || x > Config.CANVAS_WIDTH + getSpeed()) return true;
        else if (y < - getSpeed()*100 || y > Config.CANVAS_HEIGHT + getSpeed()) return true;
        return false;
    }
    public void update(GameStage gameStage) {
        //update new status of the enemy
        double curPosX = getPosX();
        double curPosY = getPosY();
        double speed = getSpeed();

//        System.out.println("Current = " + curPosX + " " + curPosY);
//        System.out.println("next Road Value = " + getNextRoadValue());
//        System.out.println("speed = " + speed);

        if (getNextRoadValue()>0){
            switch (getDirection()){
                case UP:
                    setPosY(curPosY - speed);
                    break;
                case DOWN:
                    setPosY(curPosY + speed);
                    break;
                case LEFT:
                    setPosX(curPosX - speed);
                    break;
                case RIGHT:
                    setPosX(curPosX + speed);
                    break;
            }
        }
        else setDirection(findDirection());

        if (hitTarget()) {
            //TODO: Decline player's HP then delete the enemy, shouldn't be 3, can be 1000 or something big
            gameStage.decreaseHP();
            System.out.println("CROSS!");
            System.out.println("Player HP = " + gameStage.getPlayerHP());
            destroy(this);
        }

        if (getHealth()<0) {
            //TODO: Earn coin
            earnMoney();
            destroy(this);
        }
    }

    public void earnMoney(){
        GameStage.earnMoney((int) getReward());
    }

    public void doAttack(double damage) {
        //enemy harm the defender
        setHealth((long)(getHealth() - (damage - getArmor())));
    }

    public void destroy(Enemy enemy) {
        //if it must die already, then destroy it
        GameField.getDeathEnemy().add(enemy);
    }

    @Override
    public void draw(GraphicsContext gc) {
        super.draw(gc);
        rotateEnemy(gc, getImg(), getPosX(), getPosY());
        gc.drawImage(getImg(), getPosX(), getPosY());
        gc.restore();
    }
}