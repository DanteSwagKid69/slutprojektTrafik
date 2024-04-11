import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.ArrayList;

public class Window extends JFrame implements ActionListener {


    ArrayList<Car> carList = new ArrayList<>();
    Timer timer;

    RoadSystemMaker roadSystem = new RoadSystemMaker();
    final int pixelSize = 20;
    int frames = 0;




    public Window() {
        //roadSystem.addTilesToWindow(this);

        this.setTitle("Trafic Sim"); // Sätter en text på rutan
        this.setBounds(0, 0, roadSystem.getColumns() * pixelSize, roadSystem.getRows() * pixelSize); // Sätter position och storlek
        this.setResizable(false); // gör att vi inte får ändra storleken
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GridLayout flow = new GridLayout(roadSystem.getRows(), roadSystem.getColumns(), 0, 0);
        this.setLayout(flow);
        this.setVisible(true);  // gör den synlig

        timer = new Timer(100, this);
        timer.start();

        for (Tile tile: this.roadSystem.getTilesList()) {
            if (tile instanceof Road && Math.random() < 0.05) {
                Car newCar = new Car(this, (Road) tile);
                ((Road) tile).addCar(newCar);
                this.carList.add(newCar);
            }
        }

    }

    public void update() {
        this.frames++;
        for (Car car : carList) {
            System.out.println(car.getX());
            car.move(this);
        }

    }


    public RoadSystemMaker getRoadSystem() {
        return this.roadSystem;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    public Window getWindow() {
        return this;
    }

}