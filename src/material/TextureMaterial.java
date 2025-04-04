package src.material;
import src.math.Vector;
import src.core.Texture;
public class TextureMaterial extends Material{
    public TextureMaterial(Texture texture)
    {
        super(
        "src/shaders/TextureMaterial.vert",
        "src/shaders/TextureMaterial.frag"  );
        addUniform("vec3", "baseColor", new Vector(1,1,1) );
        addUniform("sampler2D", "tex", 
        new Vector(texture.textureRef, 1));
        addUniform("vec2", "repeatUV", new Vector(1,1) );
        addUniform("vec2", "offsetUV", new Vector(0,0) );
        locateUniforms();
        addRenderSetting( "doubleSide", true );
        addRenderSetting( "wireframe", false );
        addRenderSetting( "lineWidth", 1 );
    }
}