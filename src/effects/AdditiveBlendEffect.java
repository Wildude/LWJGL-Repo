package src.effects;
import src.material.Material;
import src.math.Vector;
import src.core.Texture;
public class AdditiveBlendEffect extends Material{
    public AdditiveBlendEffect(Texture blendTexture, float originalStrength, float blendStrength)
    {
        super(
        "src/shaders/Effect.vert",
        "src/shaders/AdditiveBlendEffect.frag"  );
        // the actual texture reference is not 0;
        //   set from rendertarget by postprocessor class
        addUniform("sampler2D", "tex", new Vector(0, 1));
        addUniform("sampler2D", "blendTexture", 
        new Vector(blendTexture.textureRef, 2));
        addUniform("float", "originalStrength", originalStrength);
        addUniform("float", "blendStrength", blendStrength);
        locateUniforms();
    }
}
