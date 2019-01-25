package model;

import common.Vector2;

public interface Mobile {
    void accelerate (float velocityToAdd);
    void addForce (Vector2 velocityVector);
    void rotate (float radians);
    void updatePosition(float deltaTime);
    void translate(Vector2 amount);
    
    
    void setVelocity (Vector2 velocity);
    Vector2 getPosition();
    float getDirection();
    Vector2 getVelocity();
}
