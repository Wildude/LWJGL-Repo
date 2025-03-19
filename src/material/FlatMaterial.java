package src.material;
import src.math.Vector;
import src.core.Texture;
public class FlatMaterial extends Material{
    public FlatMaterial(Texture texture)
    {
        super(
        "src/shaders/FlatMaterial.vert",
        "src/shaders/FlatMaterial.frag"  );
        if (texture == null)addUniform("bool", "useTexture", 0);
        else
        {
            addUniform("bool", "useTexture", 1);
            addUniform("sampler2D", "tex",
            new Vector(texture.textureRef, 1));
        }
        addUniform( "vec3", "baseColor", new Vector(1,1,1) );
        addUniform( "Light", "light0", null );
        addUniform( "Light", "light1", null );
        addUniform( "Light", "light2", null );
        addUniform( "Light", "light3", null );
        locateUniforms();
        addRenderSetting( "doubleSide", true );
        addRenderSetting( "wireframe", false );
        addRenderSetting( "lineWidth", 1 );
        if (texture == null)addUniform("bool", "useTexture", 0);
        else {
            addUniform("bool", "useTexture", 1);
            addUniform("sampler2D", "tex", 
            new Vector(texture.textureRef, 1));
        }
    }
}
