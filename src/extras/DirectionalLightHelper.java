package src.extras;
import src.light.DirectionalLight;
import src.geometry.Geometry;
import src.math.Vector;
public class DirectionalLightHelper extends GridHelper{
    public DirectionalLightHelper(DirectionalLight light){
        super(1, 4, light.color, new Vector(1, 1, 1), 1);
        Geometry line = new Geometry();
        float[] posData = new float[] {0, 0, 0,  0, 0, -10};
        line.addAttribute("vec3", "vertexPosition", posData);
        float[] colData = new float[] {1, 1, 0, 0, 0, 0};
        line.addAttribute("vec3", "vertexColor", colData);
        line.vertexCount = 2;
        this.geometry.merge(line);
    }
}
