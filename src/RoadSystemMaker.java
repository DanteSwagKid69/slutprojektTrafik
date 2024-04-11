import java.util.ArrayList;

public class RoadSystemMaker {

    int[][] mapArray = new int[30][70];

    final private int rows = mapArray.length;
    final private int columns = mapArray[0].length;

    ArrayList<Tile> tilesList = new ArrayList<>();

    public RoadSystemMaker() {

        createTerrain();
        generateRoads();

    }

    private void createTerrain() {
        for (int y = 0; y < this.mapArray.length; y++) {
            for (int x = 0; x < this.mapArray[0].length; x++) {
                Terrain terrain = new Terrain(y, x);
                this.tilesList.add(terrain);
            }
        }
    }

    private void generateRoads() {

        // Get random position of first road
        // int xPos, int yPos
        int yPos = (int) Math.floor(Math.random() * this.rows);
        int xPos = (int) Math.floor(Math.random() * this.columns);

        // Place the first road
        // Choose a random direction, depending on if there is a wall of road in the way
        // String currentDir
        String currentDir = "right";

        // Place new road
        Road currentRoad = new Road(yPos, xPos, currentDir);
        this.tilesList.set(this.tilesList.indexOf(getTileFromPosition(yPos, xPos)), currentRoad);

        // lastPlacedRoad = the road that was just placed
        String lastDir = currentDir;

        // Check the direction of last road and get available directions of new road
        switch (currentDir) {
            case "right" -> xPos++;
            case "down" -> yPos++;
            case "left" -> xPos--;
            case "up" -> yPos--;
        }

        // Check last roads direction and choose if the current one should turn
        int roads = 0;
        while (roads < 1000) {
            currentDir = getAvailableDirections(yPos, xPos, lastDir);

            currentRoad = new Road(yPos, xPos, currentDir);
            if (tilesList.get(this.tilesList.indexOf(getTileFromPosition(yPos, xPos))) instanceof Road) {
                this.tilesList.set(this.tilesList.indexOf(getTileFromPosition(yPos, xPos)), new Intersection(yPos, xPos, currentDir, this));
                roads++;
            } else {
                this.tilesList.set(this.tilesList.indexOf(getTileFromPosition(yPos, xPos)), currentRoad);
                roads++;
            }
            lastDir = currentDir;

            switch (currentDir) {
                case "right" -> xPos++;
                case "down" -> yPos++;
                case "left" -> xPos--;
                case "up" -> yPos--;
            }


            System.out.println("ROADS: " + roads);
            if (currentDir.equals("none")) {
                while(getTileFromPosition(yPos, xPos) instanceof Terrain) {
                    yPos = (int) Math.floor(Math.random() * this.rows);
                    xPos = (int) Math.floor(Math.random() * this.columns);
                }
            }
        }
        // Place current road with chosen direction
    }

    private String getAvailableDirections(int yPos, int xPos, String lastDir) {

        ArrayList<String> directions = new ArrayList<>();

        if (xPos + 1 < columns) {
            if (getTileFromPosition(yPos, xPos + 1) instanceof Terrain || lastDir.equals("right")) {
                directions.add("right");
            }
        }
        if (yPos + 1 < rows) {
            if (getTileFromPosition(yPos + 1, xPos) instanceof Terrain || lastDir.equals("down")) {
                directions.add("down");
            }
        }
        if (xPos - 1 > 0) {
            if (getTileFromPosition(yPos, xPos - 1) instanceof Terrain || lastDir.equals("left")) {
                directions.add("left");
            }
        }
        if (yPos - 1 > 0) {
            if (getTileFromPosition(yPos - 1, xPos) instanceof Terrain || lastDir.equals("up")) {
                directions.add("up");
            }
        }

        if (directions.isEmpty()) {
            System.out.println("none");
            return "none";
        }

        if (Math.random() > 0.1 && directions.contains(lastDir)) {
            return lastDir;
        }
        return directions.get((int) (Math.random() * directions.size()));
    }

    public Tile getTileFromPosition(int yPosition, int xPosition) {return tilesList.get((this.columns * yPosition) + xPosition);
    }

    public void addTilesToWindow(Window window) {
        for (Tile tile : this.tilesList) {
            window.add(tile);
        }
    }

    public int getRows() {
        return this.rows;
    }

    public int getColumns() {
        return this.columns;
    }

    public ArrayList<Tile> getTilesList() {
        return tilesList;
    }
}
