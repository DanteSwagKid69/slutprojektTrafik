import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;

public class Window extends JFrame implements ActionListener {

    // List that stores all cars
    public ArrayList<Car> carList = new ArrayList<>();
    //Timer to make framerate work
    private final Timer timer;

    // Makes new trafficmap
    public TraficMap trafficMap = new TraficMap(this);
    // Declares the pixel size || the tile size
    public final int pixelSize = 20;
    Screen menu = new Screen(this);

    public Window() {
        this.setTitle("Trafic Sim"); // Sätter en text på rutan
        this.setBounds(0, 0, trafficMap.getColumns() * pixelSize, trafficMap.getRows() * pixelSize); // Sätter position och storlek
        this.setResizable(false); // gör att vi inte får ändra storleken
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(menu);
        this.setVisible(true);  // gör den synlig

        // Creates new timer
        timer = new Timer(100, this);




    }

    // Starts the simulator
    public void startSimulator() {
        // Creates a grid pattern
        this.remove(menu);
        GridLayout flow = new GridLayout(trafficMap.getRows(), trafficMap.getColumns(), 0, 0);
        this.setLayout(flow);
        trafficMap.addTilesToWindow(this);
        revalidate();
        timer.start();
    }

    // Update method
    public void update() {

        // Makes every car drive
        for (Car car : carList) {
            System.out.println(car.getX());
            car.drive();
        }

    }

    // Happens everytime the timer updates
    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

}