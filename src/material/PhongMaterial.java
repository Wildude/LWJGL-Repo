package src.material;
import src.math.Vector;
import src.core.Texture;
public class PhongMaterial extends Material{
    public PhongMaterial(Texture texture)
    {
        super(
        "src/shaders/PhongMaterial.vert",
        "src/shaders/PhongMaterial.frag"  );
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
        addUniform("bool", "useBumpTexture", 0);
        locateUniforms();
        addRenderSetting( "doubleSide", true );
        addRenderSetting( "wireframe", false );
        addRenderSetting( "lineWidth", 1 );
        addUniform("vec3", "viewPosition", new Vector(0,0,0) );
        addUniform("float", "specularStrength", 1.0f);
        addUniform("float", "shininess", 32.0f);
        if (texture == null)addUniform("bool", "useTexture", 0);
        else {
            addUniform("bool", "useTexture", 1);
            addUniform("sampler2D", "tex", 
            new Vector(texture.textureRef, 1));
        }
    }
    public void addBumpData(Texture bumpTexture, float bumpStrength)
    {
        uniforms.get("useBumpTexture").data = 1;
        addUniform("sampler2D", "bumpTexture", new Vector(bumpTexture.textureRef, 2));
        addUniform("float", "bumpStrength", bumpStrength);
        locateUniforms();
    }
}
