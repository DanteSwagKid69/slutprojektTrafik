import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.ArrayList;

public class Window extends JFrame implements ActionListener {


    Timer timer;

    final int pixelSize = 20;

    int frames = 0;


    ArrayList<Tile> tilesList = new ArrayList<>();

    ArrayList<Car> carList = new ArrayList<>();


    public Window() {
        RoadSystemMaker roadSystem = new RoadSystemMaker();
        roadSystem.addTilesToWindow(this);

        this.setTitle("Trafic Sim"); // Sätter en text på rutan
        this.setBounds(100, 150, roadSystem.getColumns() * pixelSize, roadSystem.getRows() * pixelSize); // Sätter position och storlek
        this.setResizable(false); // gör att vi inte får ändra storleken
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GridLayout flow = new GridLayout(roadSystem.getRows(), roadSystem.getColumns(), 0, 0);
        this.setLayout(flow);
        this.setVisible(true);  // gör den synlig

        timer = new Timer(500, this);
        timer.start();
    }

    public void update() {
        this.frames++;
        for (Car car : carList) {
            System.out.println(car.getX());
            car.move();
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();

    }

    public Window getWindow() {
        return this;
    }

    public ArrayList<Tile> getTilesList() {
        return tilesList;
    }
}