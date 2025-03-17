package src.geometry;
import java.util.Arrays;
import java.util.ArrayList;
import src.math.Vector;
public class PolygonGeometry extends Geometry{
    // default constructor creates a triangle
    public PolygonGeometry()
    {
        this(3, 1);
    }
    public PolygonGeometry(int sides, double radius)
    {
        float A = (float)(2 * Math.PI / sides);
        ArrayList<Vector> positionList = 
        new ArrayList<Vector>();
        ArrayList<Vector> colorList    = 
        new ArrayList<Vector>();
        Vector Z  = new Vector(0,0,0);
        Vector C1 = new Vector(1,1,1);
        Vector C2 = new Vector(1,0,0);
        Vector C3 = new Vector(0,0,1);
        ArrayList<Vector> uvList  = new ArrayList<Vector>();
        Vector uvCenter = new Vector(0.5, 0.5);
        ArrayList<Vector> normalList   = new ArrayList<Vector>();
        Vector normalVector = new Vector(0,0,1);
        for (int n = 0; n < sides; n++){
            uvList.add( uvCenter );
            uvList.add( new Vector( Math.cos(n*A) * 0.5 + 0.5, 
            Math.sin(n*A) * 0.5 + 0.5 ) );
            uvList.add( new Vector( Math.cos((n+1)*A) * 0.5 + 0.5, 
            Math.sin((n+1)*A) * 0.5 + 0.5 ) );
            positionList.add( Z );
            positionList.add( new Vector( 
            radius * Math.cos(n*A),              
            radius * Math.sin(n*A), 0) );
            positionList.add( new Vector( 
            radius * Math.cos((n+1)*A), 
            radius * Math.sin((n+1)*A), 0) );
            colorList.add( C1 );
            colorList.add( C2 );
            colorList.add( C3 );
            normalList.add( normalVector );
            normalList.add( normalVector );
            normalList.add( normalVector );
        }
        float[] normalData = Vector.flattenList(normalList);
        addAttribute("vec3", "vertexNormal", normalData);
        addAttribute("vec3", "faceNormal", normalData);
        float[] uvData = Vector.flattenList(uvList);
        addAttribute("vec2", "vertexUV", uvData);
        float[] positionData = Vector.flattenList(positionList);
        float[] colorData = Vector.flattenList(colorList);
        addAttribute("vec3", "vertexPosition", positionData);
        addAttribute("vec3", "vertexColor", colorData);
        vertexCount = sides * 3;
    }
}
