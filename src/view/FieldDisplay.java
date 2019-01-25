package view;

import common.Vector2;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import model.Asteroid;
import model.Field;

public class FieldDisplay extends JPanel {

    private final Field field;

    public FieldDisplay(Field field) {
        this.field = field;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
        
        drawAsteroids((Graphics2D) g);
    }
    
    public void drawAsteroids(Graphics2D g) {
        g.setColor(Color.WHITE);
        for (Asteroid asteroid : field.getAsteroids()) {
            Vector2 pos = asteroid.getPosition();
            g.translate(pos.x(), pos.y());
            g.draw(asteroid.getShape());
            g.translate(-pos.x(), -pos.y());
        }
        
    }
}
