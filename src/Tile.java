import javax.swing.*;

abstract public class Tile extends JPanel {

    private int yPos;
    private int xPos;


    public Tile(int yPos, int xPos) {
        this.yPos = yPos;
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public int getxPos() {
        return xPos;
    }

}
