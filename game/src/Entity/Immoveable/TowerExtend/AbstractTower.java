//package Entity.Immoveable.TowerExtend;
//
//import Entity.Immoveable.Tower;
//import Entity.Moveable.Bullet;
//import Entity.Moveable.Enemy.Enemy;
//import core.GameField;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.image.Image;
//
//import java.util.Stack;
//
//public abstract class AbstractTower implements Tower {
//    private double speed;
//    private double range;
//    private double strength;
//    private Image image, stand;
//    private double posX;
//    private double posY;
//    private Enemy shootingEnemy = null;
//    private Stack<Enemy> enemyStack;
//
//    public AbstractTower() {
//        enemyStack = new Stack<>();
//    }
//
//    @Override
//    public void setImg(Image image, Image stand) {
//        this.image = image;
//        this.stand = stand;
//    }
//
//    public void addToStack(Enemy enemy){
//        enemyStack.add(enemy);
//    }
//
//    public double getRange() {
//        return range;
//    }
//
//    public void setShootingEnemy(Enemy shootingEnemy) {
//        this.shootingEnemy = shootingEnemy;
//    }
//
//    public Enemy getShootingEnemy() {
//        return shootingEnemy;
//    }
//
//    public void setRange(double range) {
//        this.range = range;
//    }
//
//    @Override
//    public Image getImgStand() {
//        return stand;
//    }
//
//    @Override
//    public Image getImg() {
//        return image;
//    }
//
//    @Override
//    public double getPosX() {
//        return posX;
//    }
//
//    @Override
//    public double getPosY() {
//        return posY;
//    }
//
//    @Override
//    public void setPosXY(double posX, double posY) {
//        this.posX = posX;
//        this.posY = posY;
//    }
//
//    @Override
//    public void shoot(Enemy enemy) {
//        Bullet bullet = new Bullet(getPosX(),getPosY(),0,0);
//        bullet.setEnemy(enemy);
//        GameField.addBullet(bullet);
//    }
//
//
//    @Override
//    public void update(){
//        //TODO: tìm vị trí của enemy, vị trí của tower và bắn
//        //Every time, check if there're enemies in tower range => add to enemy stack
//        for (Enemy enemy : GameField.getEnemies()){
//            if (enemy.evaluateDistance(this) < getRange()){
//                addToStack(enemy);
//            }
//        }
//
//        Enemy enemy = getShootingEnemy();
//
//        if (shootingEnemy == null){
//            if (!enemyStack.isEmpty()){
//                if (enemyStack.peek() != null && enemyStack.peek().evaluateDistance(this) < getRange()){
//                    enemy = enemyStack.pop();
//                    setShootingEnemy(enemy);
//                }
//                else {
//                    setShootingEnemy(null);
//                }
//            }
//        }
//
//        else if (shootingEnemy.evaluateDistance(this) > getRange()) {
//            setShootingEnemy(null);
//        }
//
//        else {
//            System.out.println("Distance = " + enemy.evaluateDistance(this) + " Added!");
//            shoot(enemy);
//        }
//    }
//
//    @Override
//    public void draw(GraphicsContext gc, GameField gameField) {
//        gc.save(); // saves the current state on stack, including the current transform
//
//        gc.drawImage(getImgStand(), getPosX(), getPosY());
////        double angle= Bullet.findAngle(getPosX()+32, getPosY()+24, GameField.getEnemies());
////        Render.rotate(gc, angle, getPosX() + 32, getPosY() + 32);
//        gc.drawImage(getImg(), getPosX(), getPosY()-8);
//        gc.restore();
//    }
//}
