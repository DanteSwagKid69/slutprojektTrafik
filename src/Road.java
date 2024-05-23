import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Road extends Tile {

    // Stores the car that is on this road
    Object onRoad;

    // Stores the direction of the road
    private String direction;

    // Stores if it is a car on the road or not
    private boolean isOccupied = false;
    TraficMap traficMap;

    public Road(int yPos, int xPos, String direction, TraficMap traficMap) {
        super(yPos, xPos);
        this.traficMap = traficMap;
        this.direction = direction;
        this.setLayout(null);
    }

    // Gets the next road depending on this direction

    public Road getNextRoad(TraficMap traficMap) {
        int yChange = 0;
        int xChange = 0;

        // Variables that are used in the next part depending on the current direction
        switch (this.direction) {
            case "up" -> yChange = -1;
            case "right" -> xChange = 1;
            case "down" -> yChange = 1;
            case "left" -> xChange = -1;
        }

        // Debug
        System.out.println("dir: " + this.direction);
        System.out.println("Coords: " + this.getyPos() + "," + this.getxPos());
        System.out.println("pos: " + Integer.sum(this.getyPos(), yChange) + "," + Integer.sum(this.getxPos(), xChange));

        return (Road) traficMap.getTileFromPosition(Integer.sum(this.getyPos(), yChange), Integer.sum(this.getxPos(), xChange));
    }


    // Paints the road
    public void paintComponent(Graphics g) {

        g.setColor(new Color(48, 54, 46));

        g.fillRect(0, 0, 1000, 1000);
        g.setColor(Color.yellow);

        // Paints the road depending on direction
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
    }


    // Adds car
    public void addCar(Car car) {
        this.onRoad = car;
        this.add(car);
        this.isOccupied = true;
    }

    // Returns direction
    public String getDirection() {
        return this.direction;
    }

    // Removes car
    public void removeCar() {
        this.onRoad = null;
        this.isOccupied = false;
    }

    // Returns if its occupied
    public boolean getIsOccupied() {
        return isOccupied;
    }
}
