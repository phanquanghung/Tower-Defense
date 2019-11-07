package Entity;/*|==================================================||   This's interface for Enemy, Bullet             ||==================================================| */import javafx.scene.Group;public interface GameEntity {//    /**//     * Every bullet have their own speed//     * @return entity speed//     *///    double getSpeed();////    /**//     * Every bullet have their own range//     * @return entity range//     *///    double getRange();////    /**//     * Every bullet have their own damage//     * @return entity damage//     *///    double getDamage();////    /**//     * Every enemy have their health//     * @return entity range//     *///    long getHealth();////    /**//     * Every enemy when die drop a number of coin, that's reward//     * @return entity range//     *///    long getReward();////    /**//     * Every enemy have their own armor//     * @return entity range//     *///    long getArmor();////    /**//     * @return field pos x//     */    double getPosX();    /**     * @return field pos y     */    double getPosY();    /**     * @return field width     */    double getWidth();    /**     * @return field height     */    double getHeight();    /**     * display the Entity;     */    void draw(Group root);}