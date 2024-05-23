
import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuScreen extends Screen {
    public MenuScreen(Window window) {
        super(window);
    }

    @Override
    void keyWasPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            window.startSimulator();
        }
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.setColor(Color.white);
        g.drawString("Välkommen till trafiksimulatorn!", 100, 200);
        g.drawString("Här kan du kolla på coola bilar som åker runt.", 100, 300);
        g.drawString("www.makeyourownmapwithdante.netlify.app", 100, 340);
        g.drawString("Tryck på mellanslag för att starta!", 100, 380);
    }
}
