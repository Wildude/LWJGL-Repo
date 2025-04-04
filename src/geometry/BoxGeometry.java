package src.geometry;
import java.util.Arrays;
import java.util.List;
import src.math.Vector;
public class BoxGeometry extends Geometry{
    public BoxGeometry()
    {
        this(1,1,1);
    }
    public BoxGeometry(double width, double height, double depth)
    {
        // corners of a cube
        Vector P0 = new Vector(-width/2, -height/2, -depth/2);
        Vector P1 = new Vector( width/2, -height/2, -depth/2);
        Vector P2 = new Vector(-width/2,  height/2, -depth/2);
        Vector P3 = new Vector( width/2,  height/2, -depth/2);
        Vector P4 = new Vector(-width/2, -height/2,  depth/2);
        Vector P5 = new Vector( width/2, -height/2,  depth/2);
        Vector P6 = new Vector(-width/2,  height/2,  depth/2);
        Vector P7 = new Vector( width/2,  height/2,  depth/2);
        // colors for faces in order: x+, x-, y+, y-, z+, z
        Vector C1 = new Vector(1.0f, 0.5f, 0.5f);
        Vector C2 = new Vector(0.5f, 0.0f, 0.0f);
        Vector C3 = new Vector(0.5f, 1.0f, 0.5f);
        Vector C4 = new Vector(0.0f, 0.5f, 0.0f);
        Vector C5 = new Vector(0.5f, 0.5f, 1.0f);
        Vector C6 = new Vector(0.0f, 0.0f, 0.5f);
        List positionList = Arrays.asList(
        P5,P1,P3,P5,P3,P7, P0,P4,P6,P0,P6,P2,
        P6,P7,P3,P6,P3,P2, P0,P1,P5,P0,P5,P4,
        P4,P5,P7,P4,P7,P6, P1,P0,P2,P1,P2,P3  );
        float[] positionData = Vector.flattenList(positionList);
        List colorList = Arrays.asList(
        C1,C1,C1,C1,C1,C1, C2,C2,C2,C2,C2,C2,
        C3,C3,C3,C3,C3,C3, C4,C4,C4,C4,C4,C4,
        C5,C5,C5,C5,C5,C5, C6,C6,C6,C6,C6,C6  );
        float[] colorData = Vector.flattenList(colorList);
        addAttribute("vec3", "VertexPosition", positionData);
        addAttribute("vec3", "VertexColor", colorData);
        vertexCount = 36;
        // texture coordinates
        Vector T0 = new Vector(0,0);
        Vector T1 = new Vector(1,0);
        Vector T2 = new Vector(0,1);
        Vector T3 = new Vector(1,1);
        List uvList = Arrays.asList(
        T0,T1,T3,T0,T3,T2, T0,T1,T3,T0,T3,T2,
        T0,T1,T3,T0,T3,T2, T0,T1,T3,T0,T3,T2,
        T0,T1,T3,T0,T3,T2, T0,T1,T3,T0,T3,T2  );
        float[] uvData = Vector.flattenList(uvList);
        addAttribute("vec2", "vertexUV", uvData);
        // normal vectors for x+, x-, y+, y-, z+, z
        Vector N1 = new Vector( 1,  0,  0);
        Vector N2 = new Vector(-1,  0,  0);
        Vector N3 = new Vector( 0,  1,  0);
        Vector N4 = new Vector( 0, -1,  0);
        Vector N5 = new Vector( 0,  0,  1);
        Vector N6 = new Vector( 0,  0, -1);
        List normalList = Arrays.asList(
        N1,N1,N1,N1,N1,N1, N2,N2,N2,N2,N2,N2,
        N3,N3,N3,N3,N3,N3, N4,N4,N4,N4,N4,N4,
        N5,N5,N5,N5,N5,N5, N6,N6,N6,N6,N6,N6  );
        float[] normalData = Vector.flattenList(normalList);
        addAttribute("vec3", "vertexNormal", normalData);
        addAttribute("vec3", "faceNormal", normalData);
    }
}
