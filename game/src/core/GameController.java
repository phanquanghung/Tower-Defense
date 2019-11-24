package core;
import Entity.Immoveable.Tower;
import Entity.Immoveable.TowerExtend.MachineGunTower;
import Entity.Immoveable.TowerExtend.NormalTower;
import Entity.Immoveable.TowerExtend.RocketTower;
import Entity.Immoveable.TowerExtend.SniperTower;
import Graphic.ImageSheet;
import Graphic.TileMap;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

/**
 * This class manage input event from player
 */
public class GameController {

    static void menu(Canvas canvas, Scene theScene, GameField gameField, Group root, GraphicsContext gc, Stage primaryStage) {
        Image title = new Image("Graphic/title.png");
        gc.drawImage(title, 0, 0);
        Button play = new Button("PLAY");
        root.getChildren().add(play);
        play.setLayoutX(0.5 * Config.TILE_HORIZONTAL * (Config.MAP_WIDTH - 1));
        play.setLayoutY(0.5 * Config.TILE_VERTICAL * (Config.MAP_HEIGHT - 1));
        primaryStage.show();

        Media gamePlay = new Media(new File("src/Graphic/menu.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(gamePlay);
        mediaPlayer.setAutoPlay(true);
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                mediaPlayer.pause();
                GameStage.playGame(canvas, primaryStage, theScene, gameField, root, gc);
                play.setVisible(false);
                play.setDisable(true);
            }
        });

    }

    static void mouseClicked(Canvas canvas, Scene theScene, GameField gameField, Group root, GraphicsContext gc, ToggleGroup towerToggle, ToggleButton normalTowerButton, ToggleButton machineGunTowerButton, ToggleButton rocketTowerButton, ToggleButton sniperTowerButton, ToggleButton upgradeTowerButton, ToggleButton sellTowerButton) {
        String towerSelected = new String();
        if (towerToggle.getSelectedToggle() == normalTowerButton) {
            towerSelected = "Normal Tower";
        } else if (towerToggle.getSelectedToggle() == machineGunTowerButton) {
            towerSelected = "Machine Gun Tower";
        } else if (towerToggle.getSelectedToggle() == rocketTowerButton) {
            towerSelected = "Rocket Tower";
        } else if (towerToggle.getSelectedToggle() == sniperTowerButton) {
            towerSelected = "Sniper Tower";
        } else if (towerToggle.getSelectedToggle() == upgradeTowerButton) {
            towerSelected = "Upgrade Tower";
        } else if (towerToggle.getSelectedToggle() == sellTowerButton) {
            towerSelected = "Sell Tower";
        }
        String finalTowerSelected = towerSelected;
        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent e) {
                MouseButton button = e.getButton();
                int ver = (int) e.getY() / Config.TILE_VERTICAL;
                int hor = (int) e.getX() / Config.TILE_HORIZONTAL;
                int towerCost = -1, towerID = -1;
                boolean towerPlaced = false;
                for (Tower tower : gameField.getTowers()) {
                    if (tower.getPosX() == hor * Config.TILE_HORIZONTAL && tower.getPosY() == ver * Config.TILE_VERTICAL) {
                        towerCost = (int) tower.getUpdateCost();
                        towerPlaced = true;
                    }
                }
                if (towerPlaced && GameStage.hasEnoughMoney(towerCost) && finalTowerSelected.equals("Upgrade Tower")) {
                    towerUpgrade(root, gameField.getSpawn(), ver, hor, gameField.getTower(), gameField, finalTowerSelected);
                    towerToggle.selectToggle(null);
                } else if (towerPlaced && finalTowerSelected.equals("Sell Tower")) {
                    towerSell(root, gameField.getSpawn(), ver, hor, gameField.getTower(), gameField, finalTowerSelected);
                } else if (gameField.getSpawn().getTileMap(ver, hor) == 16) {
                    switch (finalTowerSelected) {
                        case "Normal Tower":
                            towerCost = (int) Config.NORMAL_TOWER_COST;
                            break;
                        case "Rocket Tower":
                            towerCost = (int) Config.ROCKET_TOWER_COST;
                            break;
                        case "Machine Gun Tower":
                            towerCost = (int) Config.MACHINE_GUN_TOWER_COST;
                            break;
                        case "Sniper Tower":
                            towerCost = (int) Config.SNIPER_TOWER_COST;
                            break;
                    }
                    if (GameStage.hasEnoughMoney(towerCost)) {
                        towerMenu(root, gameField.getImageSheet(), gameField.getSpawn(), ver, hor, gameField.getTower(), gameField, finalTowerSelected);
                        towerToggle.selectToggle(null);
                    }
                }
            }
        });
    }

    private static void towerMenu(Group root, ImageSheet imageSheet, TileMap spawn, int ver, int hor, TileMap towerTM, GameField gameField, String towerSelected) {
        Tower newTower;
        if (towerSelected == "Normal Tower") {
            towerTM.setTileMap(ver, hor, 2);
            //towerTM.printMapData();
            newTower = new NormalTower();
        } else if (towerSelected == "Machine Gun Tower") {
            towerTM.setTileMap(ver, hor, 3);
            //towerTM.printMapData();
            newTower = new MachineGunTower();
        } else if (towerSelected == "Rocket Tower") {
            towerTM.setTileMap(ver, hor, 4);
            //towerTM.printMapData();
            newTower = new RocketTower();
        } else if (towerSelected == "Sniper Tower") {
            towerTM.setTileMap(ver, hor, 5);
            //towerTM.printMapData();
            newTower = new SniperTower();
        } else return;
        newTower.setPosXY((hor) * Config.TILE_HORIZONTAL, (ver) * Config.TILE_VERTICAL);
        gameField.addTower(newTower);
    }

    private static void towerUpgrade(Group root, TileMap spawn, int ver, int hor, TileMap towerTM, GameField gameField, String towerSelected) {
        for (Tower tower : gameField.getTowers()) {
            if (tower.getPosX() == hor * Config.TILE_HORIZONTAL && tower.getPosY() == ver * Config.TILE_VERTICAL) tower.updateTower();
        }
    }

    private static void towerSell(Group root, TileMap spawn, int ver, int hor, TileMap towerTM, GameField gameField, String towerSelected) {
        for (Tower tower : gameField.getTowers()) {
            if (tower.getPosX() == hor * Config.TILE_HORIZONTAL && tower.getPosY() == ver * Config.TILE_VERTICAL)
                tower.deleteTower();
        }
    }
}