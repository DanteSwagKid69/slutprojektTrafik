import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public abstract class Screen extends JPanel implements KeyListener {
    Window window;

    public Screen(Window window) {
        this.window = window;
        this.setBackground(Color.blue);
        this.setSize(new Dimension(window.getWidth(), window.getHeight()));
        setFocusable(true);
        addKeyListener(this);
    }

    abstract void keyWasPressed(KeyEvent e);


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyWasPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
