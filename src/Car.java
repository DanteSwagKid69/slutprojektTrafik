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
        this.setBackground(Color.red);
        this.currentRoad = currentRoad;
        carsList.add(this);
    }

    public void move() {
        System.out.println("move");


    }
}
