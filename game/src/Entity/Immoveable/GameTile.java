package Entity.Immoveable;import Entity.AbstractEntity;import Entity.GameEntity;/*|============================================================================================||   This class is interface of (and manages) all stable object (Immovable Object)            ||============================================================================================| */public abstract class GameTile extends AbstractEntity implements GameEntity {    protected GameTile(double posX, double posY, double width, double height) {        super(posX, posY, width, height);    }}