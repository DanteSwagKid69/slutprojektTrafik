import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.RandomAccess;
import java.util.random.RandomGenerator;

public class Window extends JFrame implements ActionListener {


    Timer timer;

    int[][] mapArray = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 0, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 1, 0},
            {0, 1, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 0},
            {0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},




    };

    private int rows = mapArray.length;
    private int columns = mapArray[0].length;

    final int pixelSize = 20;


    ArrayList<Tile> tilesList = new ArrayList<>();

    ArrayList<Car> carList = new ArrayList<>();


    public Window() {

        generateMap();
        System.out.println("Y: " + mapArray.length + " | " + " x: " + mapArray[0].length);

        this.setTitle("TE21C Clicker"); // Sätter en text på rutan
        this.setBounds(100, 150, mapArray[0].length * pixelSize, mapArray.length * pixelSize); // Sätter position och storlek
        this.setResizable(false); // gör att vi inte får ändra storleken
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        GridLayout flow = new GridLayout(mapArray.length, mapArray[0].length, 0, 0);
        this.setLayout(flow);
        this.setVisible(true);  // gör den synlig

        timer = new Timer(500, this);
        timer.start();

    }

    public void generateRoadNetwork() {
        for (int i = 0; i < this.mapArray.length; i++) {
            for (int j = 0; j < this.mapArray[0].length; j++) {

            }
        }
    }


    public void generateMap() {

        int placedcars = 0;
        for (int i = 0; i < mapArray.length; i++) {
            for (int j = 0; j < mapArray[0].length; j++) {
                if (mapArray[i][j] == 1) {
                    Road road = new Road(i, j, "up");
                    if (placedcars != 1) {
                        Car car = new Car(this, road);
                        road.addCar(car);
                        this.carList.add(car);
                        placedcars++;
                    }
                    this.add(road);
                    this.tilesList.add(road);
                } else if (mapArray[i][j] == 0) {
                    Terrain terrain = new Terrain(i, j);
                    this.add(terrain);
                    tilesList.add(terrain);
                }
            }
        }
    }

    public Tile getTileFromPosition(int yPosition, int xPosition) {
        return tilesList.get(mapArray[0].length * yPosition + xPosition);
    }

    public Road getNextRoad(Coordinates coords, Road lastRoad) {

        int yPos = coords.getyPos();
        int xPos = coords.getxPos();

        boolean hasMoved = false;

        while (!hasMoved) {
            if (getIsRoad(yPos - 1, xPos) && lastRoad != getTileFromPosition(yPos - 1, xPos) && Math.random() > 0.5) {
                System.out.println("up");
                if (getTileFromPosition(yPos - 1, xPos) instanceof Road) {
                    hasMoved = true;
                    return (Road) getTileFromPosition(yPos - 1, xPos);
                }
            } else if (getIsRoad(yPos, xPos + 1) && lastRoad != getTileFromPosition(yPos, xPos + 1) && Math.random() > 0.5) {
                System.out.println("right");
                if (getTileFromPosition(yPos, xPos + 1) instanceof Road) {
                    hasMoved = true;
                    return (Road) getTileFromPosition(yPos, xPos + 1);
                }
            } else if (getIsRoad(yPos + 1, xPos) && lastRoad != getTileFromPosition(yPos + 1, xPos) && Math.random() > 0.5) {
                System.out.println("down");
                if (getTileFromPosition(yPos + 1, xPos) instanceof Road) {
                    hasMoved = true;
                    return (Road) getTileFromPosition(yPos + 1, xPos);
                }
            } else if (getIsRoad(yPos, xPos - 1) && lastRoad != getTileFromPosition(yPos, xPos - 1) && Math.random() > 0.5) {
                System.out.println("left");
                if (getTileFromPosition(yPos, xPos - 1) instanceof Road) {
                    hasMoved = true;

                    return (Road) getTileFromPosition(yPos, xPos - 1);
                }
            }
        }

        throw new Error("No roads available");

    }

    public boolean getIsRoad(int yPos, int xPos) {
        return getTileFromPosition(yPos, xPos) instanceof Road;
    }

    public void helpCar(Car car) {

    }

    public void update() {
        System.out.println("frame");
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

    public int getColumns() {
        return this.columns;
    }

    public int getRows() {
        return this.rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public ArrayList<Tile> getTilesList() {
        return tilesList;
    }
}