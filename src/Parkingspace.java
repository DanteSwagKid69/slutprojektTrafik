import java.awt.*;
import java.util.ArrayList;

public class Parkingspace extends Tile{

    // Stores the amount of cars that are inside
    private int amountOfParkedCars = 0;

    // Stores the cars that are inside
    ArrayList<Car> carList = new ArrayList<>();

    // Constructor
    public Parkingspace(int yPos, int xPos) {
        super(yPos, xPos);
    }

    // Paints the parkingspace
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.blue);
        g.fillRect(0, 0, 20, 20);

        g.setColor(Color.white);
        g.drawString(String.valueOf(this.amountOfParkedCars), this.getxPos(), this.getyPos());

    }

    // Adds car
    public void addCar(Car car) {
        this.carList.add(car);
        this.amountOfParkedCars++;
    }

    // Removes car
    public void removeCar(Car car) {
        this.amountOfParkedCars--;
        if (!this.carList.contains(car)) throw new Error("This car is not inside this parkinglot");

        this.carList.remove(car);
    }
}
