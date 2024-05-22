import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Window extends JFrame implements ActionListener {

    // List that stores all cars
    ArrayList<Car> carList = new ArrayList<>();
    //Timer to make framerate work
     Timer timer;
    // Makes new roadsystemmaker
    RoadSystemMaker roadSystem = new RoadSystemMaker(this);
    // Makes new trafficmap
    TraficMap trafficMap = new TraficMap(this);
    // Declares the pixel size || the tile size
    final int pixelSize = 20;


    public Window() {
        //roadSystem.addTilesToWindow(this);
        trafficMap.addTilesToWindow(this);
        this.setTitle("Trafic Sim"); // Sätter en text på rutan
        this.setBounds(0, 0, trafficMap.getColumns() * pixelSize, trafficMap.getRows() * pixelSize); // Sätter position och storlek
        this.setResizable(false); // gör att vi inte får ändra storleken
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GridLayout flow = new GridLayout(trafficMap.getRows(), trafficMap.getColumns(), 0, 0);
        this.setLayout(flow);
        this.setVisible(true);  // gör den synlig

        // Creates new timer and starts it
        timer = new Timer(100, this);
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

    //Returns roadsystem
    public RoadSystemMaker getRoadSystem() {
        return this.roadSystem;
    }

    // Happens everytime the timer updates
    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

}