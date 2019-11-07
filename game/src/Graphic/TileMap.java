package Graphic;import core.Config;import javafx.scene.image.ImageView;import java.io.BufferedReader;import java.io.FileReader;import java.io.IOException;public class TileMap {    private String name;    private int[][] tileMap = new int[Config.MAP_HEIGHT][Config.MAP_WIDTH];    private ImageView[][] imageView = new ImageView[Config.MAP_HEIGHT][Config.MAP_WIDTH];    public ImageView getImageView (int index, int jndex){        return imageView[index][jndex];    }    public void setImageView(int index, int jndex, ImageView img) {        imageView[index][jndex] = img;    }    public void setXY(int index, int jndex, double posX, double posY){        imageView[index][jndex].setLayoutX(posX);        imageView[index][jndex].setLayoutY(posY);    }    public int[][] getTileMap() {        return tileMap;    }    public int getTileMap(int index, int jndex) {        return tileMap[index][jndex];    }    public String getName() {        return name;    }    public void setName(String name) {        this.name = name;    }    public void setTileMap(int[][] tileMap) {        this.tileMap = tileMap;    }    public void readFile(String fileName) {        try {            BufferedReader bReader = new BufferedReader(new FileReader(fileName));            //read first line            String nameTile = bReader.readLine();            setName(nameTile);            //read second line, which is "the map"            String line = bReader.readLine();            bReader.close();            //spit string, the push it into 2D integer array            String[] mapData = line.split(" ");            int index = 0;            for (int i=0; i<Config.MAP_HEIGHT; i++){                for (int j=0; j<Config.MAP_WIDTH; j++){                    tileMap[i][j] = Integer.parseInt(mapData[index++]);                }            }        } catch (IOException e){            System.out.println("IOException error");        }    }    public void printMapData() {        System.out.println(this.getName());        for (int[] i:tileMap){            for (int j: i){                System.out.print(j + " ");            }            System.out.println();        }        System.out.println();    }}