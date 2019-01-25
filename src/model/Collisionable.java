package model;

/**
 * Visitor pattern approach (Collisionables can be visitors and visitants)
 */
interface Collisionable {
    void accept (Collisionable visitor);
    
    void checkCollisionWith ( Asteroid asteroid );
}
