import java.awt.*;
import java.util.ArrayList;

public class Intersection extends Road {

    ArrayList<String> directions = new ArrayList<>();
    TraficMap traficMap;

    public Intersection(int yPos, int xPos, TraficMap traficMap) {
        super(yPos, xPos, "right");
        this.traficMap = traficMap;
        //    this.currentDir = currentDir;

        getAvailableDirections(yPos, xPos);

        this.setLayout(null);
    }

    @Override
    public String getDirection() {
        return this.directions.get((int) (Math.random() * directions.size()));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(new Color(48, 54, 46));

        g.fillRect(0, 0, 20, 20);

        g.setColor(Color.pink);

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
        if (xPos + 1 > this.traficMap.getColumns()) directions.remove("right");
        if (yPos + 1 > this.traficMap.getRows()) directions.remove("down");
        if (xPos - 1 <= 0) directions.remove("left");
        if (yPos - 1 <= 0) directions.remove("up");

        // Check if the direction of the roads on each side is pointing at this intersection
        if (directions.contains("up")) {
            Tile targetTile = this.traficMap.getTileFromPosition(yPos - 1, xPos);
            if (targetTile instanceof Road) {
                if (((Road) targetTile).getDirection().equals("down")) this.directions.remove("up");
            } else directions.remove("up");
        }
        if (directions.contains("right")) {
            Tile targetTile = this.traficMap.getTileFromPosition(yPos, xPos + 1);
            if (targetTile instanceof Road) {
                if (((Road) targetTile).getDirection().equals("left")) this.directions.remove("right");
            } else directions.remove("right");
        }
        if (directions.contains("down")) {
            Tile targetTile = this.traficMap.getTileFromPosition(yPos + 1, xPos);
            if (targetTile instanceof Road) {
                if (((Road) targetTile).getDirection().equals("up")) this.directions.remove("down");
            } else directions.remove("down");
        }
        if (directions.contains("left")) {
            Tile targetTile = this.traficMap.getTileFromPosition(yPos, xPos - 1);
            if (targetTile instanceof Road) {
                if (((Road) targetTile).getDirection().equals("right")) this.directions.remove("left");
            } else directions.remove("left");
        }


    }

}
