import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.util.ArrayList;

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

    int frames = 0;


    ArrayList<Tile> tilesList = new ArrayList<>();

    ArrayList<Car> carList = new ArrayList<>();


    public Window() {
        // generateRoadNetwork();
        // genMap();
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
        // Precentages to spawn different
        double chanceOfTurn = 0.12;

        for (int y = 0; y < this.mapArray.length; y++) {
            for (int x = 0; x < this.mapArray[0].length; x++) {
                Terrain terrain = new Terrain(y, x);
                tilesList.add(terrain);
                // this.add(terrain);
            }
        }

        int startY = (int) Math.floor(Math.random() * mapArray.length);
        int startX = (int) Math.floor(Math.random() * mapArray[0].length);
        Road currentRoad = new Road(startY, startX, "right");
        String dir = "right";

        this.tilesList.set(this.tilesList.indexOf(getTileFromPosition(startY, startX)), currentRoad);
        for (int i = 0; i < 200; i++) {

            dir = availableDirection(dir, startY, startX);
            if (dir.equals("none")) break;

            switch (dir) {
                case "right" -> startX++;
                case "down" -> startY++;
                case "left" -> startX--;
                case "up" -> startY--;
            }

            System.out.println(dir);


            currentRoad = new Road(startY, startX, dir);
            this.tilesList.set(this.tilesList.indexOf(getTileFromPosition(startY, startX)), currentRoad);
        }
    }

    public String availableDirection(String currentDir, int y, int x) {
        ArrayList<String> directions = new ArrayList<>();
        String[] availableDirections = {"right", "down", "left", "up"};


        for (String dir : availableDirections) {
            System.out.println(dir);
            switch (dir) {
                case "right" -> {
                    if (x + 1 < mapArray[0].length) {
                        if (!(getTileFromPosition(y, x + 1) instanceof Road)) {
                            directions.add("right");
                        }
                    }
                }
                case "down" -> {
                    if (y + 1 < mapArray.length) {
                        if (!(getTileFromPosition(y + 1, x) instanceof Road)) {
                            directions.add("down");
                        }
                    }
                }
                case "left" -> {
                    if (x - 1 > 0) {
                        if (!(getTileFromPosition(y, x - 1) instanceof Road)) {
                            directions.add("left");
                        }
                    }
                }
                case "up" -> {
                    if (y - 1 > 0) {
                        if (!(getTileFromPosition(y - 1, x) instanceof Road)) {
                            directions.add("up");
                        }
                    }
                }
            }
        }

        double percentToTurn = 0.1;

        if (directions.contains(currentDir) && Math.random() > percentToTurn) {
            if (currentDir.equals("right") && getTileFromPosition(y, x - 1) instanceof Road)
                ((Road) getTileFromPosition(y, x - 1)).setDirection("right");
            if (currentDir.equals("down") && getTileFromPosition(y - 1, x) instanceof Road)
                ((Road) getTileFromPosition(y - 1, x)).setDirection("down");
            if (currentDir.equals("left") && getTileFromPosition(y, x + 1) instanceof Road)
                ((Road) getTileFromPosition(y, x + 1)).setDirection("left");
            if (currentDir.equals("up") && getTileFromPosition(y + 1, x) instanceof Road)
                ((Road) getTileFromPosition(y + 1, x)).setDirection("up");
            return currentDir;
        } else {
            directions.remove(currentDir);
        }
        if (directions.isEmpty()) return "none";

        String choosedDir = directions.get((int) (Math.random() * directions.size()));
        if (choosedDir.equals("right") && getTileFromPosition(y, x - 1) instanceof Road)
            ((Road) getTileFromPosition(y, x - 1)).setDirection("right");
        if (choosedDir.equals("down") && getTileFromPosition(y - 1, x) instanceof Road)
            ((Road) getTileFromPosition(y - 1, x)).setDirection("down");
        if (choosedDir.equals("left") && getTileFromPosition(y, x + 1) instanceof Road)
            ((Road) getTileFromPosition(y, x + 1)).setDirection("left");
        if (choosedDir.equals("up") && getTileFromPosition(y + 1, x) instanceof Road)
            ((Road) getTileFromPosition(y + 1, x)).setDirection("up");
        return choosedDir;

    }

    public String getRandomDirection(String currentDir) {
        String[] possibleDirections = {"right", "down", "left", "up"};
        ArrayList<String> directions = new ArrayList<>();
        directions.add("right");
        directions.add("down");
        directions.add("left");
        directions.add("up");

        directions.remove(currentDir);
        return directions.get((int) Math.floor(Math.random() * 3));

    }

    public boolean isCollidingWithRoad(int y, int x) {
        if (getTileFromPosition(y, x) instanceof Road) return true;
        else return false;
    }

    public boolean isCollidingWithWall(int y, int x) {
        if (y >= mapArray.length) return true;
        else if (y < 0) return true;
        else if (x >= mapArray[0].length) return true;
        else if (x < 0) return true;


        return false;
    }

    public void genMap() {
        for (Tile tile : this.tilesList) {
            this.add(tile);
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