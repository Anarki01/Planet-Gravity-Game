import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Game extends JPanel {

    private Planet planet;
    private Player player;
    private BufferedImage img;

    public Game(JFrame frame) {
        planet = new Planet(300, 200, 100, ResourceLoader.loadImage("circle.png"));
        player = new Player(300, 200, 100, ResourceLoader.loadImage("ball2.png"));
        player.addKeyListener(frame);
    }

    public void paint(Graphics g) {
        super.paint(g);
        planet.paint(g);
        player.paint(g);
    }

    public void move() {
        player.move();
    }
}
