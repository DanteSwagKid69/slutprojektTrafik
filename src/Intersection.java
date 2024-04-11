import java.awt.*;
import java.util.ArrayList;

public class Intersection extends Road {

    ArrayList<String> directions = new ArrayList<>();
    RoadSystemMaker roadSystem;

    String currentDir;

    public Intersection(int yPos, int xPos, String currentDir, RoadSystemMaker roadSystem) {
        super(yPos, xPos, currentDir);
        this.roadSystem = roadSystem;
        this.currentDir = currentDir;
        genAvailableDirections(yPos, xPos, currentDir);

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

    private void genAvailableDirections(int yPos, int xPos, String currentDir) {

        if (xPos + 1 < this.roadSystem.getColumns()) {
            if (this.roadSystem.getTileFromPosition(yPos, xPos + 1) instanceof Road) {
                if (!((Road) this.roadSystem.getTileFromPosition(yPos, xPos + 1)).getDirection().equals("left")) {
                    this.directions.add("right");
                }
            }
        }
        if (yPos + 1 < this.roadSystem.getRows()) {
            if (this.roadSystem.getTileFromPosition(yPos + 1, xPos) instanceof Road) {
                if (!((Road) this.roadSystem.getTileFromPosition(yPos + 1, xPos)).getDirection().equals("up")) {
                    this.directions.add("down");
                }
            }
        }
        if (xPos - 1 > 0) {
            if (this.roadSystem.getTileFromPosition(yPos, xPos - 1) instanceof Road) {
                if (!((Road) this.roadSystem.getTileFromPosition(yPos, xPos - 1)).getDirection().equals("right")) {
                    this.directions.add("left");
                }
            }
        }
        if (yPos - 1 > 0) {
            if (this.roadSystem.getTileFromPosition(yPos - 1, xPos) instanceof Road) {
                if (!((Road) this.roadSystem.getTileFromPosition(yPos - 1, xPos)).getDirection().equals("down")) {
                    this.directions.add("up");
                }
            }
        }

        if (!this.directions.contains(currentDir)) directions.add(currentDir);
    }


}
