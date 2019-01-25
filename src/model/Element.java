
package model;

import common.Vector2;
import java.awt.Shape;

public class Element implements Mobile {
    
    private Vector2 position;
    private Vector2 velocity;
    private float direction;
    private Shape shape;
    private float size;  
    
    public Element(Vector2 position, Vector2 velocity, float direction, 
            float size, Shape shape) {
        this.position = position;
        this.velocity = velocity;
        this.direction = direction;
        this.size = size;
        this.shape = shape;
    }

    public Shape getShape() { return shape; }
    public float getSize() { return size; }
    
    
    ////////////////////////////////////////////////////////////////////////////
    //                        Mobile Implementation                           //
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public void accelerate (float velocityToAdd) {
        velocity = new Vector2(
            velocity.x() + (float) Math.cos(direction) * velocityToAdd,
            velocity.y() + (float) Math.sin(direction) * velocityToAdd
        );
    }
    
    @Override
    public void addForce (Vector2 velocityVector) { 
        velocity = velocity.add(velocityVector);
    }
    
    @Override
    public void rotate (float radians) { direction += radians % (2*Math.PI); }
    

    @Override
    public void updatePosition(float deltaTime) {
        position = new Vector2 (
            position.x() + velocity.x() * deltaTime,
            position.y() + velocity.y() * deltaTime
        );
    }

    
    @Override
    public void translate(Vector2 amount) {
        position = position.add(amount);
    }
    
    @Override
    public float getDirection() { return direction;  }
    
    @Override
    public Vector2 getPosition() { return position;  }
    
    @Override
    public Vector2 getVelocity() { return velocity; }

    @Override
    public void setVelocity(Vector2 velocity) {
        this.velocity = velocity;
    }

}
