import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Terrain extends Tile {


    public Terrain(int yPos, int xPos) {
        super(yPos, xPos);
            if (Math.random() > 0.3) this.setBackground(new Color(85, 125, 66));
        else this.setBackground(new Color(77, 115, 60));
        this.setSize(new Dimension(40, 40));

    }


}
