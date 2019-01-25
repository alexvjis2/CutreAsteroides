
package common;

import java.awt.Polygon;
import java.awt.Shape;
import java.awt.geom.Area;


public class ShapeFactory {

    private ShapeFactory() { }
    
    public static Shape createRegularPolygonShape(int sides, int size) {
        Polygon pol = new Polygon();
        
        double internalAngle = (2*Math.PI) / sides;
        
        for (int i = 0; i < sides; i++) {
            pol.addPoint(
                (int) (size * Math.cos(i*internalAngle)), 
                (int) (size * Math.sin(i*internalAngle))
            );
        }
        
        return pol;
    }
}
