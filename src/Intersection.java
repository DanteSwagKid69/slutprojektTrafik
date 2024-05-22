import java.awt.*;
import java.util.ArrayList;

public class Intersection extends Road {

    // List that holds all available directions
    ArrayList<String> directions = new ArrayList<>();
    TraficMap traficMap;

    public Intersection(int yPos, int xPos, TraficMap traficMap) {
        super(yPos, xPos, "right", traficMap);

        this.traficMap = traficMap;
        // Fills the directions list with all available directions
        getAvailableDirections(yPos, xPos);
        this.setLayout(null);
    }

    // Returns random direction from directions list
    @Override
    public String getDirection() {
        return this.directions.get((int) (Math.random() * directions.size()));
    }

    // Paints the lines on the road
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(new Color(48, 54, 46));
        g.fillRect(0, 0, 20, 20);
        g.setColor(Color.pink);

        // Paints differently depending on directions
        for (String dir : this.directions) {
            switch (dir) {
                case "right" -> {
                    g.fillRect(15, 8, 5, 4);
                    g.drawLine(0, 10, 20, 10);
                }
                case "down" -> {
                    g.fillRect(8, 15, 4, 5);
                    g.drawLine(10, 0, 10, 20);
                }
                case "left" -> {
                    g.fillRect(0, 8, 5, 4);
                    g.drawLine(0, 10, 20, 10);
                }
                case "up" -> {
                    g.fillRect(8, 0, 4, 5);
                    g.drawLine(10, 0, 10, 20);
                }

            }
        }
    }

    public void getAvailableDirections(int yPos, int xPos) {

        // Reset directions
        this.directions.clear();
        this.directions.add("up");
        this.directions.add("right");
        this.directions.add("down");
        this.directions.add("left");

        // Check which ways is out of bounds and remove those directions
        if (xPos + 1 > this.traficMap.getColumns()) this.directions.remove("right");
        if (yPos + 1 > this.traficMap.getRows()) this.directions.remove("down");
        if (xPos - 1 <= 0) this.directions.remove("left");
        if (yPos - 1 <= 0) this.directions.remove("up");

        // Variables that are used in the next part depending on the current direction
        String oppositeDir = "";
        int yChange = 0;
        int xChange = 0;
        ArrayList<String> removedDirections = new ArrayList<>(); // Avoids comodification
        for (String dir : this.directions) {
            switch (dir) {
                case "up" -> {
                    oppositeDir = "down";
                    yChange = -1;
                    xChange = 0;
                }
                case "right" -> {
                    oppositeDir = "left";
                    yChange = 0;
                    xChange = 1;
                }
                case "down" -> {
                    oppositeDir = "up";
                    yChange = 1;
                    xChange = 0;
                }
                case "left" -> {
                    oppositeDir = "right";
                    yChange = 0;
                    xChange = -1;
                }
            }

            // Check if the direction of the roads on each side is pointing at this intersection
            Tile targetTile = this.traficMap.getTileFromPosition(Integer.sum(yPos, yChange), Integer.sum(xPos, xChange));
            if (targetTile instanceof Road) {
                if (((Road) targetTile).getDirection().equals(oppositeDir)) removedDirections.add(dir);
            } else removedDirections.add(dir);
        }

        // Remove all directions from the removedDirections array
        for (String dir : removedDirections) {
            this.directions.remove(dir);
        }


    }

    // Returns next road depending on available directions
    public Road getNextRoadIntersection() {
        int yChange = 0;
        int xChange = 0;
        String dir = this.getDirection();
        // Variables that are used in the next part depending on the current direction
        switch (dir) {
            case "up" -> yChange = -1;
            case "right" -> xChange = 1;
            case "down" -> yChange = 1;
            case "left" -> xChange = -1;
        }

        // Debug
        System.out.println("dir: " + dir);
        System.out.println("Coords: " + this.getyPos() + "," + this.getxPos());
        System.out.println("pos: " + Integer.sum(this.getyPos(), yChange) + "," + Integer.sum(this.getxPos(), xChange));

        // Returns road
        return (Road) traficMap.getTileFromPosition(Integer.sum(this.getyPos(), yChange), Integer.sum(this.getxPos(), xChange));
    }
}
