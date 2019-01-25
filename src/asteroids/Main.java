package asteroids;

import java.util.Timer;
import java.util.TimerTask;
import model.Field;
import view.GameDisplay;


public class Main {

    public static void main(String[] args) {
        Field f = new Field(900, 500, 10);
        GameDisplay gd = new GameDisplay(f);
        f.subscribe(gd);
        
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() { f.update(); }
        }, 16, 16);
    }
    
}
