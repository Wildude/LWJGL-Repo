package src.material;
import src.math.Vector;
public class BasicMaterial extends Material{
    public BasicMaterial()
    {
        super(
        "src/shaders/BasicMaterial.vert",
        "src/shaders/BasicMaterial.frag"  );
        addUniform("vec3", "baseColor", new Vector(1,1,1) );
        addUniform("bool", "useVertexColors", 0);
        locateUniforms();
    }
}
