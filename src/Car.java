import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Car extends JPanel {

    public static ArrayList<Car> carsList = new ArrayList<>();
    private Window window;
    Road currentRoad;

    public Car(Window window, Road currentRoad) {
        this.window = window;
        this.setLayout(null);
        this.setSize(10, 10);
        this.setBackground(new Color((int) Math.floor(Math.random() * 244), (int) Math.floor(Math.random() * 244), (int) Math.floor(Math.random() * 244)));
        this.currentRoad = currentRoad;
        carsList.add(this);
    }

    public void drive() {

        Road nextRoad = this.currentRoad.getNextRoad(window.trafficMap);

        // Calls method depending on if nextRoad is intersection or oneway road
        if (this.currentRoad.getNextRoad(window.trafficMap) instanceof Intersection) driveOnIntersection(nextRoad);
        else driveOnRoad(nextRoad);
    }

    public void driveOnRoad(Road nextRoad) {
        this.currentRoad.removeCar();
        nextRoad.add(this);
        this.currentRoad = nextRoad;
    }

    public void driveOnIntersection(Road nextIntersection) {

    }
    public void move(Window window) {
        System.out.println("move");
        RoadSystemMaker roadSystem = window.getRoadSystem();
        int yPos = this.currentRoad.getyPos();
        int xPos = this.currentRoad.getxPos();

        switch (currentRoad.getDirection()) {
            case "right" -> {
                if (!(roadSystem.getTileFromPosition(yPos, xPos + 1) instanceof Terrain)) {
                    this.currentRoad = (Road) roadSystem.getTileFromPosition(yPos, xPos + 1);
                    //((Road) roadSystem.getTileFromPosition(yPos, xPos + 1)).addCar(this);
                }
            }
            case "down" -> {
                if (!(roadSystem.getTileFromPosition(yPos + 1, xPos) instanceof Terrain)) {
                    this.currentRoad = (Road) roadSystem.getTileFromPosition(yPos + 1, xPos);
                    //((Road) roadSystem.getTileFromPosition(yPos + 1, xPos)).addCar(this);
                }
            }
            case "left" -> {
                if (!(roadSystem.getTileFromPosition(yPos, xPos - 1) instanceof Terrain)) {
                    this.currentRoad = (Road) roadSystem.getTileFromPosition(yPos, xPos - 1);
                    //((Road) roadSystem.getTileFromPosition(yPos, xPos - 1)).addCar(this);
                }
            }
            case "up" -> {
                if (!(roadSystem.getTileFromPosition(yPos - 1, xPos) instanceof Terrain)) {
                    this.currentRoad = (Road) roadSystem.getTileFromPosition(yPos - 1, xPos);
                    //((Road) roadSystem.getTileFromPosition(yPos - 1, xPos)).addCar(this);
                }
            }
        }
        if (currentRoad.getIsOccupied()) return;
        this.currentRoad.addCar(this);
        this.currentRoad.removeCar();


    }
}
