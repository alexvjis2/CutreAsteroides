package common;

public final class Vector2 {
    private final float x, y;

    public Vector2(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float x() { return x; }
    public float y() { return y; }
    
    public Vector2 add(Vector2 toAdd) {
        return add(toAdd.x, toAdd.y);
    }
    public Vector2 substract(Vector2 toSub) {
        return add(-toSub.x, -toSub.y);
    }
    public Vector2 add(float x, float y) {
        return new Vector2( this.x + x, this.y + y );
    }
    
    public Vector2 multiply(float scalar) {
        return new Vector2 ( this.x * scalar, this.y * scalar );
    }
    
    public float dot(Vector2 other) { return x*other.x + y*other.y; }
    public Vector2 normalize() {
        float length = getLength();
        return new Vector2(x / length, y / length);
    }
    public float distanceTo (Vector2 point) {
        float xD = x - point.x();
        float yD = y - point.y();
        
        return (float) Math.sqrt( xD*xD + yD*yD );
    }
    public float getLength() {
        return (float) Math.sqrt(x*x + y*y);
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
    
    
}
