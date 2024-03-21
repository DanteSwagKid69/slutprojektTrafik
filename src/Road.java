import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class Road extends Tile {

    Object onRoad;

    private String direction;
    private boolean isOccupied = false;

    public Road(int yPos, int xPos, String direction) {
        super(yPos, xPos);
        this.direction = direction;

        this.setLayout(null);


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

    public Road(int yPos, int xPos, Car car) {
        super(yPos, xPos);
        this.setBackground(Color.gray);
        this.setLayout(null);
        addCar(car);
    }

    public void addCar(Car car) {
        this.onRoad = car;
        this.add(car);
        this.isOccupied = true;

    }

    public Object getOnRoad() {
        return onRoad;
    }

    public void removeCar() {
        this.isOccupied = false;
    }

    public void setOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }
}
