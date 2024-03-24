import java.util.ArrayList;

public class RoadSystemMaker {




    int[][] mapArray = new int[20][30];

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
        int yPos = (int) Math.floor(Math.random() * this.columns);
        int xPos = (int) Math.floor(Math.random() * this.rows);

        // Place the first road
        // Choose a random direction, depending on if there is a wall of road in the way
        // String currentDir
        String currentDir = getAvailableDirections(yPos, xPos);

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

        while(true) {
            currentDir = getAvailableDirections(yPos, xPos);

            currentRoad = new Road(yPos, xPos, currentDir);
            this.tilesList.set(this.tilesList.indexOf(getTileFromPosition(yPos, xPos)), currentRoad);
            lastDir = currentDir;

            switch (currentDir) {
                case "right" -> xPos++;
                case "down" -> yPos++;
                case "left" -> xPos--;
                case "up" -> yPos--;
            }

            if (currentDir.equals("none")) break;
        }
        // Place current road with chosen direction
    }

    private String getAvailableDirections(int yPos, int xPos) {

        ArrayList<String> directions = new ArrayList<>();

        if (xPos + 1 < columns) {
            if (getTileFromPosition(yPos, xPos + 1) instanceof Terrain) {
                directions.add("right");
            }
        }
        if (yPos + 1 < rows) {
            if (getTileFromPosition(yPos + 1, xPos) instanceof Terrain) {
                directions.add("down");
            }
        }
        if (xPos - 1 > 0) {
            if (getTileFromPosition(yPos, xPos - 1) instanceof Terrain) {
                directions.add("left");
            }
        }
        if (yPos - 1 > 0) {
            if (getTileFromPosition(yPos - 1, xPos) instanceof Terrain) {
                directions.add("up");
            }
        }

        if (directions.isEmpty()) {
            System.out.println("none");
            return "none";
        }
        return directions.get((int) (Math.random() * directions.size()));
    }

    public Tile getTileFromPosition(int yPosition, int xPosition) {
        return tilesList.get(mapArray[0].length * yPosition + xPosition);
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
}
