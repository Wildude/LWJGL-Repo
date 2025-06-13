package src.material;

public class DepthMaterial extends Material{
    public DepthMaterial(){
        super(
            "src/shaders/DepthMaterial.vert",
            "src/shaders/DepthMaterial.frag"
        );
        locateUniforms();
    }
}
