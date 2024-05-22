import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Car extends JPanel {

    private float SpeedPercent;

    // Static list that stores all cars
    public static ArrayList<Car> carsList = new ArrayList<>();
    private final Window window;
    Road currentRoad;

    public Car(Window window, Road currentRoad) {
        this.window = window;
        this.setLayout(null);
        this.setSize(10, 10);
        this.setBackground(new Color((int) Math.floor(Math.random() * 244), (int) Math.floor(Math.random() * 244), (int) Math.floor(Math.random() * 244)));
        this.currentRoad = currentRoad;
        carsList.add(this);
    }

    // Method that controls the driving
    public void drive() {

        Road nextRoad;

        // Use different function depending on if current tile is an intersection or road
        if (this.currentRoad instanceof Intersection) {
            nextRoad = ((Intersection) this.currentRoad).getNextRoadIntersection();
        } else nextRoad = this.currentRoad.getNextRoad(window.trafficMap);

        driveOnRoad(nextRoad);
    }

    // Adds this car on next road and removes it from the current road.
    public void driveOnRoad(Road nextRoad) {
        if (nextRoad.getIsOccupied()) return;
        this.currentRoad.removeCar();
        nextRoad.add(this);
        this.currentRoad = nextRoad;
    }
}
