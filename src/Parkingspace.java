import java.awt.*;
import java.util.ArrayList;

public class Parkingspace extends Tile{

    private int amountOfParkedCars = 0;
    ArrayList<Car> carList = new ArrayList<>();

    public Parkingspace(int yPos, int xPos) {
        super(yPos, xPos);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.blue);
        g.fillRect(0, 0, 20, 20);

        g.setColor(Color.white);
        g.drawString(String.valueOf(this.amountOfParkedCars), this.getxPos(), this.getyPos());

    }

    public void addCar(Car car) {
        this.carList.add(car);
        this.amountOfParkedCars++;
    }

    public void removeCar(Car car) {
        this.amountOfParkedCars--;
        if (!this.carList.contains(car)) throw new Error("This car is not inside this parkinglot");

        this.carList.remove(car);
    }
}
