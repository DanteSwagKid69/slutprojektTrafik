import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Screen extends JPanel implements KeyListener {
    Window window;

    public Screen(Window window) {
        this.window = window;
        this.setBackground(Color.blue);
        this.setSize(new Dimension(window.getWidth(), window.getHeight()));
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
        g.setColor(Color.white);
        g.drawString("Välkommen till trafiksimulatorn!", 200, 200);
        g.drawString("Här kan du kolla på coola bilar som åker runt.", 200, 300);

        g.drawString("Du kan även gå in på min hemsida och rita en egen karta!", 200, 320);
        g.drawString("Tryck på mellanslag för att starta!", 200, 350);


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) window.startSimulator();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
