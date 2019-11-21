package core;

/*
|======================================================|
|   This class contains all config of this game        |
|======================================================|
 */

public final class Config {
    //the ultimate title :v
    public static final String TITLE = "Tower Defense";

    //size of the map ~.~
    public static final int MAP_WIDTH = 13;

    public static final int MAP_HEIGHT = 7;

    //edge of the sprite in pixel
    public static final int TILE_HORIZONTAL = 64;

    public static final int TILE_VERTICAL = 64;

    //size of the screen
    public static final int SCREEN_WIDTH = MAP_WIDTH*TILE_VERTICAL + 242;

    public static final int SCREEN_HEIGHT = MAP_HEIGHT*TILE_HORIZONTAL;

    //enemy's HP bar
    public static final double HP_BAR_WIDTH = 64;
    public static final double HP_BAR_HEIGHT = 10;
    //properties of entities in the game

    //game default
    public static final int GAME_HEART = 20;
    public static final int GAME_START_MONEY = 100;

    //region Bullet
    public static final long NORMAL_BULLET_STRENGTH = 20;
    public static final double NORMAL_BULLET_SPEED = 15;

    public static final long MACHINE_GUN_BULLET_STRENGTH = 5;
    public static final double MACHINE_GUN_BULLET_SPEED = 15;

    public static final long ROCKET_BULLET_STRENGTH = 30;
    public static final double ROCKET_BULLET_SPEED = 12;

    public static final long SNIPER_BULLET_STRENGTH = 75;
    public static final double SNIPER_BULLET_SPEED = 12;
    //endregion

    //region Tower
    //TODO: change the cost
    public static final long NORMAL_TOWER_SPEED = 35;
    public static final long NORMAL_TOWER_COST = 5;
    public static final double NORMAL_TOWER_RANGE = 127;

    public static final long MACHINE_GUN_TOWER_SPEED = 10;
    public static final long MACHINE_GUN_TOWER_COST = 5;
    public static final double MACHINE_GUN_TOWER_RANGE = 127;

    public static final long ROCKET_TOWER_SPEED = 120;
    public static final long ROCKET_MAGAZINE = 2;
    public static final long ROCKET_TOWER_COST = 5;
    public static final double ROCKET_TOWER_RANGE = 256;

    public static final long SNIPER_TOWER_SPEED = 150;
    public static final long SNIPER_TOWER_COST = 5;
    public static final double SNIPER_TOWER_RANGE = 300;
    //endregion

    //region Enemy
    public static final double NORMAL_ENEMY_SIZE = 0.9;
    public static final long NORMAL_ENEMY_HEALTH = 100;
    public static final long NORMAL_ENEMY_ARMOR = 3;
    public static final double NORMAL_ENEMY_SPEED = 1;
    public static final long NORMAL_ENEMY_REWARD = 1;

    public static final double SMALLER_ENEMY_SIZE = 0.7;
    public static final long SMALLER_ENEMY_HEALTH = 50;
    public static final long SMALLER_ENEMY_ARMOR = 0;
    public static final double SMALLER_ENEMY_SPEED = 1.6;
    public static final long SMALLER_ENEMY_REWARD = 2;

    public static final double TANKER_ENEMY_SIZE = 1.3;
    public static final long TANKER_ENEMY_HEALTH = 300;
    public static final long TANKER_ENEMY_ARMOR = 5;
    public static final double TANKER_ENEMY_SPEED = 0.8;
    public static final long TANKER_ENEMY_REWARD = 3;

    public static final double BOSS_ENEMY_SIZE = 1.5;
    public static final long BOSS_ENEMY_HEALTH = 500;
    public static final long BOSS_ENEMY_ARMOR = 8;
    public static final double BOSS_ENEMY_SPEED = 0.5;
    public static final long BOSS_ENEMY_REWARD = 10;
    //endregion


}
