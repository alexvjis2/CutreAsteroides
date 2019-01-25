package model;

import common.ShapeFactory;
import common.Vector2;

public class Asteroid extends Element implements Collisionable {

    public Asteroid(Vector2 position, Vector2 velocity, 
            float direction, float size) {
        super(position, velocity, direction, size,
            ShapeFactory.createRegularPolygonShape( 
                (int) (Math.random() * 5 + 6), (int) size 
            )
        );
    }

    ////////////////////////////////////////////////////////////////////////////
    //                Collisionable Implementation  (Visitor)                 //
    ////////////////////////////////////////////////////////////////////////////
    @Override
    public void accept(Collisionable visitor) {
        visitor.checkCollisionWith(this);
    }

    @Override
    public void checkCollisionWith(Asteroid ast) {
        float xd = getPosition().x() - ast.getPosition().x();
        float yd = getPosition().y() - ast.getPosition().y();

        float sumRadius = getSize() + ast.getSize();
        float sqrRadius = sumRadius * sumRadius;

        float distSqr = (xd * xd) + (yd * yd);

        if (distSqr <= sqrRadius) {
            resolveCollision(ast);
        }
    }

    /**
     * resolve collisión 
     * @param b 
     */
    private void resolveCollision(Asteroid b) {
        // get the mtd
        Vector2 delta = getPosition().substract(b.getPosition());
        float d = delta.getLength();
        
        // minimum translation distance to push balls apart after intersecting
        Vector2 mtd = delta.multiply( ((getSize() + b.getSize())-d)/d );

        // resolve intersection
        // inverse mass quantities
        float im1 = 1f / getSize();
        float im2 = 1f / b.getSize();

        // push-pull them apart based off their mass
        translate( mtd.multiply( im1 / (im1 + im2) ) );
        b.translate( mtd.multiply(-im2 / (im1 + im2)) );

        // impact speed
        Vector2 v = (getVelocity().substract(b.getVelocity()));
        float vn = v.dot(mtd.normalize());

        // sphere intersecting but moving away from each other already
        if (vn > 0.0f) { return; }

        // collision impulse
        float restitution = 1; // entre 0 y 1
        float i = (-(1.0f + restitution) * vn) / (im1 + im2);
        Vector2 impulse = mtd.normalize().multiply(i);

        // change in momentum
        setVelocity( getVelocity().add(impulse.multiply(im1)) ); 
        b.setVelocity ( b.getVelocity().substract(impulse.multiply(im2)));
        
    }
    
}
