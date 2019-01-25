package model;

import common.Observer;
import common.Vector2;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import static model.Constants.*;


public class Field {

    
    Set<Asteroid> asteroids;
    private int maxAsteroids;
    private final int width, height;

    List<Observer> observers = new LinkedList();

    public Field(int width, int height, int maxAsteroids) {
        this.asteroids = new LinkedHashSet();
        this.maxAsteroids = maxAsteroids;
        this.width = width;
        this.height = height;

        for (int i = 0; i < maxAsteroids; i++) {
            generateAsteroid(false);
        }

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void generateAsteroid(boolean out) {
        float size = (float) (Math.random() * 25 + 25);
        
        if (out) { 
            outerGeneration(size);
        } else {
            Vector2 velocity = new Vector2(
                    (float) (Math.random() * MAX_VEL*2f) - MAX_VEL,
                    (float) (Math.random() * MAX_VEL*2f) - MAX_VEL
            );
            Asteroid newAst = new Asteroid(
                    new Vector2((float) Math.random() * width,
                            (float) Math.random() * height),
                    velocity,
                    (float) (Math.random() * Math.PI * 2f),
                    size
            );
            asteroids.add(newAst);
        }

    }
    
    private void outerGeneration( float size ) {
        float x = 0, y = 0;   // Position
        float sX = 0, sY = 0; // Velocity
        
        int random = (int) ( Math.random() * 4f );
        if (random < 2) {
            y = (float) Math.random() * height + size;
            sY = (float) (Math.random() * MAX_VEL*2) - MAX_VEL;
            if (random == 0) {
                x = -size;
                sX = (float) Math.random() * (MAX_VEL-MIN_VEL) + MIN_VEL;
            } else {
                x = width + size;
                sX = (float) -Math.random() * (MAX_VEL-MIN_VEL) - MIN_VEL;
            }
        } else {
            x = (float) Math.random() * width + size;
            sX = (float) (Math.random() * MAX_VEL*2) - MAX_VEL;
            if (random == 2) {
                y = -size;
                sY = (float) Math.random() * (MAX_VEL-MIN_VEL) + MIN_VEL;
            } else {
                y = height + size;
                sY = (float) -Math.random() * (MAX_VEL-MIN_VEL) - MIN_VEL;
            }
        }
        Vector2 position = new Vector2(x, y);
        Vector2 velocity = new Vector2(sX, sY);
        
        Asteroid newAst = new Asteroid(position, velocity, 
                (float) (Math.random() * 2*Math.PI), size);
        
        asteroids.add(newAst);
    }

    public Set<Asteroid> getAsteroids() {
        return asteroids;
    }

    public void subscribe(Observer observer) {
        observers.add(observer);
    }

    public void update() {
        for (Iterator<Asteroid> it = asteroids.iterator(); it.hasNext();) {
            Asteroid asteroid = it.next();
            asteroid.updatePosition(1);
            if (!inScreen(asteroid)) {
                it.remove();
            }
        }

        for (int i = asteroids.size(); i < maxAsteroids; i++) {
            generateAsteroid(true);
        }
        checkCollisions();

        for (Observer observer : observers) {
            observer.update();
        }
    }

    private void checkCollisions() {
        Asteroid[] ast = asteroids.toArray(new Asteroid[asteroids.size()]);
        for (int i = 0; i < ast.length; i++) {
            Asteroid a = ast[i];
            for (int j = i + 1; j < ast.length; j++) {
                a.checkCollisionWith(ast[j]);
            }
        }
    }

    private boolean inScreen(Element element) {
        Vector2 pos = element.getPosition();
        float size = element.getSize();
        return (pos.x() > 0 - size && pos.x() < width + size
                && pos.y() > 0 - size && pos.y() < height + size);
    }
}
