package src.geometry;
import java.util.Arrays;
import java.util.List;
import src.math.Vector;
public class RectangleGeometry extends Geometry {
    public RectangleGeometry(){
        this(1, 1);
    }
    public RectangleGeometry(double width, double height){
        this(width, height, new Vector(0, 0), new Vector(0.5, 0.5));
    }
    public RectangleGeometry(double width, double height, Vector position, Vector alignment){
        double x = position.values[0];
        double y = position.values[1];
        double a = alignment.values[0];
        double b = alignment.values[1];
        //       
        Vector P0 = new Vector(x +  (-a)*width, y +  (-b)*height, 0);
        Vector P1 = new Vector(x + (1-a)*width, y +  (-b)*height, 0);
        Vector P2 = new Vector(x +  (-a)*width, y + (1-b)*height, 0);
        Vector P3 = new Vector(x + (1-a)*width, y + (1-b)*height, 0);
        //
        Vector C0 = new Vector(1f, 1f, 1f);
        Vector C1 = new Vector(1f, 0f, 0f);
        Vector C2 = new Vector(0f, 1f, 0f);
        Vector C3 = new Vector(0f, 0f, 1f);
        List positionList = Arrays.asList(P0, P1, P3,  P0, P3, P2)   ;
        float[] positionData = Vector.flattenList(positionList);
        List colorList = Arrays.asList(C0, C1, C3,  C0, C3, C2)   ;
        float[] colorData = Vector.flattenList(colorList);
        addAttribute("vec3", "VertexPosition", positionData);
        addAttribute("vec3", "VertexColor", colorData);
        vertexCount = 6;
        // texture coordinates
        Vector T0 = new Vector(0,0);
        Vector T1 = new Vector(1,0);
        Vector T2 = new Vector(0,1);
        Vector T3 = new Vector(1,1);
        List uvList = Arrays.asList(T0,T1,T3, T0,T3,T2);
        float[] uvData = Vector.flattenList(uvList);
        addAttribute("vec2", "vertexUV", uvData);
        // normal vector
        Vector N0 = new Vector(0,0,1);
        List normalList = Arrays.asList(N0,N0,N0, N0,N0,N0);
        float[] normalData = Vector.flattenList(normalList);
        addAttribute("vec3", "vertexNormal", normalData);
        addAttribute("vec3", "faceNormal", normalData);
    }
}
