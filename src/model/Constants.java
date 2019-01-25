
package model;

public final class Constants {
    
    private Constants() {}
    /**  max random vel in an axis. */
    public static final float MAX_VEL = 1;
    
    /**  min random vel in one axis. */
    public static final float MIN_VEL = 0.5f; 
    
    /** Bounciness. 
     * 1 = max bounce (no energy loss, full elastic)  
     * 0 = non-elastic collision */
    public static final float RESTITUTION_COCIENT = 1; 
}
