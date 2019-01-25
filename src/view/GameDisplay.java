
package view;

import common.Observer;
import java.awt.Dimension;
import javax.swing.JFrame;
import model.Field;

public class GameDisplay implements Observer {

    private JFrame window;
    private FieldDisplay fieldDisplay;
    
    public GameDisplay(Field field) {
        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fieldDisplay = new FieldDisplay(field);
        
        fieldDisplay.setMinimumSize( 
                new Dimension(field.getWidth(), field.getHeight()) );
        fieldDisplay.setPreferredSize(fieldDisplay.getMinimumSize());
        window.add(fieldDisplay);
        window.pack();
        window.setVisible(true);
    }

    @Override
    public void update() {
        fieldDisplay.repaint();
    }

}
