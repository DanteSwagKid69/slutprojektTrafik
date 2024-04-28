import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Road extends Tile {

    static int amountOfRoads = 0;
    Object onRoad;
    private String direction;
    private boolean isOccupied = false;
    TraficMap traficMap;

    public Road(int yPos, int xPos, String direction, TraficMap traficMap) {
        super(yPos, xPos);
        this.traficMap = traficMap;

        this.direction = direction;
        this.setLayout(null);
    }


    public Road getNextRoad(TraficMap traficMap) {
        int yChange = 0;
        int xChange = 0;
        switch (this.direction) {
            case "up" -> {
                yChange = -1;
                xChange = 0;
            }
            case "right" -> {
                yChange = 0;
                xChange = 1;
            }
            case "down" -> {
                yChange = 1;
                xChange = 0;
            }
            case "left" -> {
                yChange = 0;
                xChange = -1;
            }
        }
        System.out.println("dir: " + this.direction);
        System.out.println("Coords: " + this.getyPos() + "," + this.getyPos());
        System.out.println("pos: " + Integer.sum(this.getyPos(), yChange) + "," + Integer.sum(this.getxPos(), xChange));

        Tile targetTile = traficMap.getTileFromPosition(Integer.sum(this.getyPos(), yChange), Integer.sum(this.getxPos(), xChange));

        // Handles error
        if (!(targetTile instanceof Road))
            throw new Error("There is no road to the " + direction + " of this car | coords:  " + this.getyPos() + " , " + this.getxPos());

        return (Road) targetTile;
    }

    public void paintComponent(Graphics g) {

        g.setColor(new Color(48, 54, 46));

        g.fillRect(0, 0, 20, 20);
        g.setColor(Color.yellow);
        switch (this.direction) {
            case "right" -> {
                g.fillRect(15, 8, 5, 4);
                g.drawLine(0, 10, 20, 10);
            }
            case "down" -> {
                g.fillRect(8, 15, 4, 5);
                g.drawLine(10, 0, 10, 20);
            }
            case "left" -> {
                g.fillRect(0, 8, 5, 4);
                g.drawLine(0, 10, 20, 10);
            }
            case "up" -> {
                g.fillRect(8, 0, 4, 5);
                g.drawLine(10, 0, 10, 20);
            }

        }
        g.setColor(Color.yellow);


    }


    public void addCar(Car car) {
        this.onRoad = car;
        this.add(car);
        this.isOccupied = true;

    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Object getOnRoad() {
        return onRoad;
    }

    public void removeCar() {
        this.onRoad = null;
        this.isOccupied = false;
    }

    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }
}
