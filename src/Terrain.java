import java.awt.Color;
import java.awt.Dimension;

public class Terrain extends Tile {

    // Constructur for tile
    public Terrain(int yPos, int xPos) {
        super(yPos, xPos);
        // Makes a difference in color between terrain tiles
        if (Math.random() > 0.3) this.setBackground(new Color(85, 125, 66));
        else this.setBackground(new Color(77, 115, 60));
        this.setSize(new Dimension(40, 40));

    }


}
